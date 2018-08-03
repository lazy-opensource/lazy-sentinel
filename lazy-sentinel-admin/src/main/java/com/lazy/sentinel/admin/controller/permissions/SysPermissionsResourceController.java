package com.lazy.sentinel.admin.controller.permissions;

import com.lazy.cheetah.common.tools.AssertUtils;
import com.lazy.cheetah.core.dto.WebPageResultDto;
import com.lazy.cheetah.core.exception.BlankParamException;
import com.lazy.cheetah.core.helper.EntityHelper;
import com.lazy.cheetah.core.web.BasicAdminController;
import com.lazy.sentinel.permission.api.IPermissionsResourceService;
import com.lazy.sentinel.permission.api.PermissionServiceRegistry;
import com.lazy.sentinel.permission.api.constants.PermissionConstants;
import com.lazy.sentinel.permission.api.dto.SysPermissionTreeDto;
import com.lazy.sentinel.permission.api.entity.TPermissionsResRoleRelEntity;
import com.lazy.sentinel.permission.api.entity.TPermissionsResourceEntity;
import com.lazy.sentinel.permission.api.entity.TPermissionsUserEntity;
import com.lazy.sentinel.helper.SubjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>系统权限-资源控制器</p>
 *
 * @author laizhiyuan
 * @date 2018/3/28.
 */
@Controller
@RequestMapping("/permissions/resource")
public class SysPermissionsResourceController extends BasicAdminController<TPermissionsResourceEntity> {

    @Autowired
    public SysPermissionsResourceController(IPermissionsResourceService iPermissionsResourceService) {
        this.initData(iPermissionsResourceService, "permissions/resource");
    }

    /**
     * 检查资源编码是否存在
     * @param resCode
     * @return
     */
    @GetMapping("/checkResCode/{resCode}")
    public @ResponseBody
    WebPageResultDto checkResCode(@PathVariable("resCode") String resCode){
        return responseSuccessfully(PermissionServiceRegistry.getInstance().iPermissionsResourceService.findByResCode(resCode));
    }

    /**
     * 子类重写查询列表逻辑
     *
     * @return
     */
    @Override
    protected List queryList(HttpServletRequest request) {
        List<SysPermissionTreeDto> treeDtoList;
        treeDtoList = PermissionServiceRegistry.getInstance().iPermissionsResourceService.tree();
        return treeDtoList;
    }

    /**
     * 删除资源连带删除关联关系 过滤系统默认菜单
     *
     * @param idLongList id列表
     */
    @Override
    protected void delBefore(List<Long> idLongList) {
        if (idLongList == null || idLongList.isEmpty()){
            throw new BlankParamException("ids is empty");
        }
        int i = 1;
        while (idLongList.size() > 0 && i <= PermissionConstants.SYS_DEFAULT_MENU_NUM){
            //移除系统默认菜单
            idLongList.remove(Long.valueOf(i++));
        }
        if (idLongList.size() > 0){
            //删除资源-角色管关联关系
            List<TPermissionsResRoleRelEntity> resRoleRelEntityList = PermissionServiceRegistry.getInstance().iPermissionsResRoleRelService.findAllByResIdIn(idLongList);
            List<Long> resRoleRelIdList = EntityHelper.toIds(resRoleRelEntityList);
            PermissionServiceRegistry.getInstance().iPermissionsResRoleRelService.deleteByIds(TPermissionsResRoleRelEntity.class, resRoleRelIdList);
        }
    }

    /**
     * 导航栏菜单
     *
     * @param request
     * @return
     */
    @GetMapping("/navMenu")
    public @ResponseBody
    WebPageResultDto navMenu(HttpServletRequest request) {
        List<TPermissionsResourceEntity> navMenuList = PermissionServiceRegistry.getInstance().iPermissionsResourceService.findAllByPidAndUidAndResType(this.getCurrentLoginUserId(), PermissionConstants.ROOT_PID, PermissionConstants.RES_TYPE_MENU);
        return responseSuccessfully(navMenuList);
    }

    /**
     * 查询一级菜单
     *
     * @return
     */
    @GetMapping("/queryRootMenuList")
    public @ResponseBody
    WebPageResultDto queryRootMenuList(HttpServletRequest request) {
        List<TPermissionsResourceEntity> rootMenuList = new ArrayList<TPermissionsResourceEntity>();
        List<TPermissionsResourceEntity> allMenuList = (List<TPermissionsResourceEntity>)PermissionServiceRegistry.getInstance().iPermissionsResourceService.findAll();
        for (TPermissionsResourceEntity resourceEntity : allMenuList){
            if (PermissionConstants.ROOT_PID.equals(resourceEntity.getPid())){
                rootMenuList.add(resourceEntity);
            }
        }
        return responseSuccessfully(rootMenuList);
    }

    /**
     * 左侧栏菜单
     *
     * @return
     */
    @GetMapping("/sideMenu")
    public @ResponseBody
    WebPageResultDto sideMenu(HttpServletRequest request) {
        String pid = request.getParameter("pid");
        AssertUtils.isBlank(pid, "param pid not is blank");
        List<SysPermissionTreeDto> sideMenuList = PermissionServiceRegistry.getInstance().iPermissionsResourceService.treeByPidAndUidAndResType(this.getCurrentLoginUserId(), Long.valueOf(pid), PermissionConstants.RES_TYPE_MENU);
        return responseSuccessfully(sideMenuList);
    }

    /**
     * 获取当前登录主体Id
     *
     * @return
     */
    public Long getCurrentLoginUserId() {
        TPermissionsUserEntity entity = PermissionServiceRegistry.
                getInstance().iPermissionsUserService.findOneByLoginName(SubjectHelper.getSubjectName());
        return entity != null ? entity.getId() : null;
    }
}
