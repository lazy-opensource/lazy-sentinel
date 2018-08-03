package com.lazy.sentinel.admin.controller.permissions;

import com.lazy.sentinel.permission.api.IPermissionsUserService;
import com.lazy.sentinel.permission.api.PermissionServiceRegistry;
import com.lazy.sentinel.permission.api.constants.PermissionConstants;
import com.lazy.sentinel.permission.api.entity.TPermissionsRoleEntity;
import com.lazy.sentinel.permission.api.entity.TPermissionsUserEntity;
import com.lazy.sentinel.permission.api.entity.TPermissionsUserRoleRelEntity;
import com.lazy.cheetah.common.tools.StringUtils;
import com.lazy.cheetah.core.dto.WebPageResultDto;
import com.lazy.cheetah.core.exception.BlankParamException;
import com.lazy.cheetah.core.helper.EntityHelper;
import com.lazy.cheetah.core.shiro.SysDefaultShiroEncryptPassword;
import com.lazy.cheetah.core.shiro.SysDefaultShiroEncryptPasswordBo;
import com.lazy.cheetah.core.web.BasicAdminController;
import com.lazy.sentinel.admin.controller.permissions.vo.SysPermissionsUserVo;
import com.lazy.sentinel.admin.enums.MessageEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>系统权限-用户控制器</p>
 *
 * @author laizhiyuan
 * @date 2018/3/29.
 */
@Controller
@RequestMapping("/permissions/user")
public class SysPermissionsUserController extends BasicAdminController<TPermissionsUserEntity> {

    @Autowired
    private SysDefaultShiroEncryptPassword sysDefaultShiroEncryptPassword;

    @Autowired
    public SysPermissionsUserController(IPermissionsUserService iPermissionsUserService) {
        this.initData(iPermissionsUserService, "permissions/user");
    }

    /**
     * 根据用户id查关联的角色列表
     * @param request Servlet请求对象
     * @return
     */
    @RequestMapping("/role")
    public @ResponseBody
    WebPageResultDto role(HttpServletRequest request){
        String uid = request.getParameter("uid");
        if(StringUtils.isBlank(uid)){
            return this.responseMessage(MessageEnum.PARAM_ERROR.getDesc());
        }
        return responseSuccessfully(PermissionServiceRegistry.getInstance().iPermissionsUserRoleRelService.findAllByUid(Long.valueOf(uid)));
    }

    /**
     * 详情后继续查询角色名称
     * @param request Servlet请求对象
     * @param entity 实体对象
     * @return
     */
    @Override
    public Object detailAfter(HttpServletRequest request, TPermissionsUserEntity entity) {
        SysPermissionsUserVo vo = new SysPermissionsUserVo();
        vo.setEntity(entity);
        List<TPermissionsUserRoleRelEntity> userRoleRelEntityList = PermissionServiceRegistry.getInstance().iPermissionsUserRoleRelService.findAllByUid(entity.getId());
        if (userRoleRelEntityList == null || userRoleRelEntityList.isEmpty()){
            vo.setRoleName("");
            return vo;
        }
        TPermissionsRoleEntity roleEntity = PermissionServiceRegistry.getInstance().iPermissionsRoleService.findOne(userRoleRelEntityList.get(0).getRid());
        vo.setRoleName(roleEntity.getRoleName());
        return vo;
    }

    /**
     * 新增用户前设置盐和加密密码
     * @param request Servlet请求对象
     * @param entity 实体对象
     */
    @Override
    public void editBefore(HttpServletRequest request, TPermissionsUserEntity entity) {
        //新增之前设置盐和加密密码
        if (entity.getId() == null){
            SysDefaultShiroEncryptPasswordBo bo = new SysDefaultShiroEncryptPasswordBo();
            bo.setPassword(entity.getPassword());
            bo.setUsername(entity.getLoginName());
            sysDefaultShiroEncryptPassword.encryptPassword(bo);
            entity.setPassword(bo.getPassword());
            entity.setSalt(bo.getSalt());
        }else {
            //编辑之前删除旧的用户和角色关联关系，添加新的用户和角色的关联关系
            List<TPermissionsUserRoleRelEntity> userRoleRelEntityList = PermissionServiceRegistry.getInstance().iPermissionsUserRoleRelService.findAllByUid(entity.getId());
            List<Long> ids = EntityHelper.toIds(userRoleRelEntityList);
            PermissionServiceRegistry.getInstance().iPermissionsUserRoleRelService.deleteByIds(TPermissionsUserRoleRelEntity.class, ids);

            //查出旧的数据
            TPermissionsUserEntity oldEntity = PermissionServiceRegistry.getInstance().iPermissionsUserService.findOne(entity.getId());
            entity.setPassword(oldEntity.getPassword());
            entity.setSalt(oldEntity.getSalt());
            entity.setCreateTime(oldEntity.getCreateTime());
            entity.setValidStatus(oldEntity.getValidStatus());
        }
    }

    /**
     * 编辑用户后保存角色关联关系
     * @param request Servlet请求对象
     * @param entity 实体对象
     */
    @Override
    public void editAfter(HttpServletRequest request, TPermissionsUserEntity entity) {
        //新增后或编辑后均需要添加角色关联关系
        String roleId = request.getParameter("roleId");
        if (StringUtils.isNotBlank(roleId)){
            TPermissionsUserRoleRelEntity userRoleRelEntity = new TPermissionsUserRoleRelEntity();
            userRoleRelEntity.setRid(Long.valueOf(roleId));
            userRoleRelEntity.setUid(entity.getId());
            PermissionServiceRegistry.getInstance().iPermissionsUserRoleRelService.save(userRoleRelEntity);
        }
    }

    /**
     * 删除用户前删除用户角色关联关系 过滤超级管理员
     * @param idLongList id列表
     */
    @Override
    protected void delBefore(List<Long> idLongList) {
        if (idLongList == null || idLongList.isEmpty()){
            throw new BlankParamException("ids is empty");
        }
        //移除超级管理员id
        idLongList.remove(PermissionConstants.ROOT_PID);
        if (idLongList.size() > 0){
            //删除用户-角色关联关系
            List<TPermissionsUserRoleRelEntity> userRoleRelEntityList = PermissionServiceRegistry.getInstance().iPermissionsUserRoleRelService.findAllByUidIn(idLongList);
            if (userRoleRelEntityList != null && userRoleRelEntityList.size() > 0){
                List<Long> ids = EntityHelper.toIds(userRoleRelEntityList);
                PermissionServiceRegistry.getInstance().iPermissionsUserRoleRelService.deleteByIds(TPermissionsUserRoleRelEntity.class, ids);
            }
        }
    }
}
