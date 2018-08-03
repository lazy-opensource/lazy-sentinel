package com.lazy.sentinel.admin.controller.permissions;


import com.lazy.sentinel.permission.api.IPermissionsRoleService;
import com.lazy.sentinel.permission.api.PermissionServiceRegistry;
import com.lazy.sentinel.permission.api.constants.PermissionConstants;
import com.lazy.sentinel.permission.api.entity.TPermissionsResRoleRelEntity;
import com.lazy.sentinel.permission.api.entity.TPermissionsResourceEntity;
import com.lazy.sentinel.permission.api.entity.TPermissionsRoleEntity;
import com.lazy.sentinel.permission.api.entity.TPermissionsUserRoleRelEntity;
import com.lazy.cheetah.common.tools.SetsUtils;
import com.lazy.cheetah.common.tools.StringUtils;
import com.lazy.cheetah.core.dto.WebPageResultDto;
import com.lazy.cheetah.core.enums.QueryRuleEnum;
import com.lazy.cheetah.core.exception.BlankParamException;
import com.lazy.cheetah.core.helper.EntityHelper;
import com.lazy.cheetah.core.web.BasicAdminController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>系统权限-角色控制器</p>
 *
 * @author laizhiyuan
 * @date 2018/3/29.
 */
@Controller
@RequestMapping("/permissions/role")
public class SysPermissionsRoleController extends BasicAdminController<TPermissionsRoleEntity> {

    @Autowired
    public SysPermissionsRoleController(IPermissionsRoleService iPermissionsRoleService) {
        this.initData(iPermissionsRoleService, "permissions/role");
    }

    /**
     * 查角色关联的资源数据
     * @param roleId 角色id
     * @return
     */
    @GetMapping("/resourceByRoleId/{roleId}")
    public @ResponseBody
    WebPageResultDto getResourceByRoleId(@PathVariable("roleId") String roleId){
        List<TPermissionsResRoleRelEntity> resRoleRelEntityList = PermissionServiceRegistry.getInstance().
                iPermissionsResRoleRelService.findAllByRoleId(Long.valueOf(roleId));
        if (resRoleRelEntityList == null || resRoleRelEntityList.isEmpty()){
            return responseSuccessfully(null);
        }
        List<Long> resIds = SetsUtils.list(resRoleRelEntityList.size());
        for (TPermissionsResRoleRelEntity entity : resRoleRelEntityList){
            resIds.add(entity.getResId());
        }
        List<TPermissionsResourceEntity> resourceEntityList = (List<TPermissionsResourceEntity>) PermissionServiceRegistry.getInstance().iPermissionsResourceService.findAll(resIds);
        return responseSuccessfully(resourceEntityList);
    }

    /**
     * 检查角色编码是否重复
     *
     * @param request
     * @return
     */
    @RequestMapping("/checkRoleCode")
    public @ResponseBody
    WebPageResultDto checkRoleCode(HttpServletRequest request) {
        String roleCode = request.getParameter("roleCode");
        if (StringUtils.isBlank(roleCode)) {
            return responseMessage("param roleCode is blank");
        }
        TPermissionsRoleEntity roleEntity = PermissionServiceRegistry.getInstance().iPermissionsRoleService.findByRoleCode(roleCode);
        return responseSuccessfully(roleEntity);
    }

    /**
     * 修改角色之前删除角色和资源的关联关系
     * @param request Servlet请求对象
     * @param entity 实体对象
     */
    @Override
    public void editBefore(HttpServletRequest request, TPermissionsRoleEntity entity) {
        if (entity.getId() != null){
            Map<String, QueryRuleEnum> paramMap = SetsUtils.map();
            paramMap.put("roleId", QueryRuleEnum.EQ);
            TPermissionsResRoleRelEntity resRoleRelEntity = new TPermissionsResRoleRelEntity();
            resRoleRelEntity.setRoleId(entity.getId());
            PermissionServiceRegistry.getInstance().iPermissionsResRoleRelService.deleteByConditions(TPermissionsResRoleRelEntity.class, resRoleRelEntity, paramMap);
        }
    }

    /**
     * 添加角色之后添加角色和资源的关联关系 清空授权缓存
     * @param request Servlet请求对象
     * @param entity 实体对象
     */
    @Override
    public void editAfter(HttpServletRequest request, TPermissionsRoleEntity entity) {
        String resIds = request.getParameter("resIds");
        if (StringUtils.isBlank(resIds)){
            return;
        }
        List<String> resIdsListStr = Arrays.asList(resIds.split(","));
        if (!resIdsListStr.isEmpty()){
            List<TPermissionsResRoleRelEntity> resRoleRelEntityList = SetsUtils.list(resIdsListStr.size());
            TPermissionsResRoleRelEntity resRoleRelEntity;
            for (String resId : resIdsListStr){
                resRoleRelEntity = new TPermissionsResRoleRelEntity();
                resRoleRelEntity.setResId(Long.valueOf(resId));
                resRoleRelEntity.setRoleId(entity.getId());
                resRoleRelEntity.setAllocated(PermissionConstants.ALLOCATED_CAN);
                resRoleRelEntityList.add(resRoleRelEntity);
            }
            PermissionServiceRegistry.getInstance().iPermissionsResRoleRelService.save(resRoleRelEntityList);
        }
    }

    /**
     * 删除角色时连带删除角色和用户、角色和资源的关联关系 过滤超级管理员
     *
     * @param idLongList id列表
     */
    @Override
    protected void delBefore(List<Long> idLongList) {
        if (idLongList == null || idLongList.isEmpty()){
            throw new BlankParamException("ids is empty");
        }
        //移除超级管理员角色id
        idLongList.remove(PermissionConstants.ROOT_PID);
        if (idLongList.size() > 0){
            //角色-资源
            List<TPermissionsResRoleRelEntity> resRoleRelEntityList = PermissionServiceRegistry.getInstance().iPermissionsResRoleRelService.findAllByRoleIdIn(idLongList);
            if (resRoleRelEntityList != null && !resRoleRelEntityList.isEmpty()){
                List<Long> resRoleRelIds = EntityHelper.toIds(resRoleRelEntityList);
                PermissionServiceRegistry.getInstance().iPermissionsResRoleRelService.deleteByIds(TPermissionsResRoleRelEntity.class, resRoleRelIds);
            }
            //角色-用户
            List<TPermissionsUserRoleRelEntity> userRoleRelEntityList = PermissionServiceRegistry.getInstance().iPermissionsUserRoleRelService.findAllByRidIn(idLongList);
            if (userRoleRelEntityList != null && !userRoleRelEntityList.isEmpty()){
                List<Long> userRoleRelIds = EntityHelper.toIds(userRoleRelEntityList);
                PermissionServiceRegistry.getInstance().iPermissionsUserRoleRelService.deleteByIds(TPermissionsUserRoleRelEntity.class, userRoleRelIds);
            }
        }
    }

}
