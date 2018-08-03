package com.lazy.sentinel.admin.controller.permissions;


import com.lazy.sentinel.permission.api.IPermissionsGroupService;
import com.lazy.sentinel.permission.api.PermissionServiceRegistry;
import com.lazy.sentinel.permission.api.dto.SysPermissionTreeDto;
import com.lazy.sentinel.permission.api.entity.*;
import com.lazy.cheetah.common.tools.SetsUtils;
import com.lazy.cheetah.common.tools.StringUtils;
import com.lazy.cheetah.core.dto.WebPageResultDto;
import com.lazy.cheetah.core.enums.QueryRuleEnum;
import com.lazy.cheetah.core.enums.SimpleResCodeEnum;
import com.lazy.cheetah.core.helper.EntityHelper;
import com.lazy.cheetah.core.helper.PagingSortHelper;
import com.lazy.cheetah.core.web.BasicAdminController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>系统权限-用户组控制器</p>
 *
 * @author laizhiyuan
 * @date 2018/3/28.
 */
@Controller
@RequestMapping("/permissions/group")
public class SysPermissionsGroupController extends BasicAdminController<TPermissionsGroupEntity> {

    @Autowired
    public SysPermissionsGroupController(IPermissionsGroupService iPermissionsGroupService) {
        this.initData(iPermissionsGroupService, "permissions/group");
    }

    /**
     * 检查组名称是否存在
     *
     * @param groupName
     * @return
     */
    @GetMapping("/checkGroupName/{groupName}")
    public @ResponseBody
    WebPageResultDto checkGroupName(@PathVariable("groupName") String groupName) {
        return responseSuccessfully(PermissionServiceRegistry.getInstance().iPermissionsGroupService.findByGroupName(groupName));
    }

    /**
     * 检查组编码是否存在
     *
     * @param groupCode
     * @return
     */
    @GetMapping("/checkGroupCode/{groupCode}")
    public @ResponseBody
    WebPageResultDto checkGroupCode(@PathVariable("groupCode") String groupCode) {
        return responseSuccessfully(PermissionServiceRegistry.getInstance().iPermissionsGroupService.findByGroupCode(groupCode));
    }

    /**
     * 根据PID查询子用户组
     *
     * @param request Servlet 请求
     * @return 自用户组
     */
    @GetMapping("/queryByParentGroupId")
    public @ResponseBody
    WebPageResultDto queryByParentGroupId(HttpServletRequest request) {
        String pid = request.getParameter("pid");
        if (StringUtils.isBlank(pid)) {
            pid = "-1";
        }
        Long pidLong = Long.valueOf(pid);
        PagingSortHelper<TPermissionsGroupEntity> pagingSortHelper = new PagingSortHelper<>();
        pagingSortHelper.getConditionsMap().put("pid", QueryRuleEnum.EQ);
        pagingSortHelper.onlySimpleSelect();

        TPermissionsGroupEntity query = new TPermissionsGroupEntity();
        query.setPid(pidLong);
        pagingSortHelper = PermissionServiceRegistry.getInstance().iPermissionsGroupService.findByMultiCiretConditions(query, pagingSortHelper);
        return responseSuccessfully(pagingSortHelper.getRows());
    }

    /**
     * 子类重写查询列表逻辑
     *
     * @return
     */
    @Override
    protected List queryList(HttpServletRequest request) {
        List<SysPermissionTreeDto> treeDtoList;
        treeDtoList = PermissionServiceRegistry.getInstance().iPermissionsGroupService.tree();
        return treeDtoList;
    }

    /**
     * 逻辑删除用户组之前删除与用户组相关的信息
     *
     * @param idLongList id列表
     */
    @Override
    protected void delBefore(List<Long> idLongList) {
        //删除用户组-角色的关联关系
        List<TPermissionsRoleGroupRelEntity> roleGroupRelEntityList = PermissionServiceRegistry.getInstance().iPermissionsRoleGroupRelService.findAllByGidIn(idLongList);
        List<Long> roleGroupRelIdList = EntityHelper.toIds(roleGroupRelEntityList);
        PermissionServiceRegistry.getInstance().iPermissionsRoleGroupRelService.deleteByIds(TPermissionsRoleGroupRelEntity.class, roleGroupRelIdList);
        //删除用户组-资源的关联关系
        List<TPermissionsResGroupRelEntity> resGroupRelEntityList = PermissionServiceRegistry.getInstance().iPermissionsResGroupRelService.findAllByGidIn(idLongList);
        List<Long> resGroupRelIdList = EntityHelper.toIds(resGroupRelEntityList);
        PermissionServiceRegistry.getInstance().iPermissionsResGroupRelService.deleteByIds(TPermissionsResGroupRelEntity.class, resGroupRelIdList);
        //逻辑删除用户组-用户的关联关系
        List<TPermissionsUserGroupRelEntity> userGroupRelEntityList = PermissionServiceRegistry.getInstance().iPermissionsUserGroupRelService.findAllByGidIn(idLongList);
        List<Long> userGroupRelIdList = EntityHelper.toIds(userGroupRelEntityList);
        PermissionServiceRegistry.getInstance().iPermissionsUserGroupRelService.logicDeleteByIds(TPermissionsUserGroupRelEntity.class, userGroupRelIdList);
    }

    /**
     * 重新分配资源
     *
     * @return com.hcl.basic.basicframework.dto.WebPageResultDto
     */
    @PostMapping("/resource/reset")
    public @ResponseBody
    WebPageResultDto resourceReset(HttpServletRequest request) {
        String groupId = request.getParameter("groupId");
        if (StringUtils.isBlank(groupId)) {
            return this.responseMessage(SimpleResCodeEnum.BLANK_PARAM + ",groupId is null");
        }
        TPermissionsResGroupRelEntity entity = new TPermissionsResGroupRelEntity();
        entity.setGid(Long.valueOf(groupId));
        Map<String, QueryRuleEnum> conditions = SetsUtils.map(1);
        conditions.put("gid", QueryRuleEnum.EQ);
        PermissionServiceRegistry.getInstance().iPermissionsResGroupRelService.deleteByConditions(TPermissionsResGroupRelEntity.class, entity, conditions);
        String resIds = request.getParameter("resIds");
        if (StringUtils.isBlank(resIds)) {
            return this.responseSuccessfully(null);
        }
        List<String> resIdList = Arrays.asList(resIds.split(","));
        List<TPermissionsResGroupRelEntity> resGroupRelEntityList = SetsUtils.list();
        TPermissionsResGroupRelEntity resGroupRelEntity;
        Long groupIdLong = Long.valueOf(groupId);
        for (String resId : resIdList) {
            resGroupRelEntity = new TPermissionsResGroupRelEntity();
            resGroupRelEntity.setGid(groupIdLong);
            resGroupRelEntity.setRid(Long.valueOf(resId));
            resGroupRelEntityList.add(resGroupRelEntity);
        }
        PermissionServiceRegistry.getInstance().iPermissionsResGroupRelService.save(resGroupRelEntityList);
        return this.responseSuccessfully(null);
    }

    /**
     * 获取该用户组用户列表
     *
     * @return com.hcl.basic.basicframework.dto.WebPageResultDto
     */
    @GetMapping("/users")
    public @ResponseBody
    WebPageResultDto users(HttpServletRequest request) {
        String groupId = request.getParameter("groupId");
        if (StringUtils.isBlank(groupId)) {
            return this.responseMessage(SimpleResCodeEnum.BLANK_PARAM + ",groupId is null");
        }
        List<TPermissionsUserGroupRelEntity> userGroupRelEntityList = PermissionServiceRegistry.getInstance().iPermissionsUserGroupRelService.findAllByGid(Long.valueOf(groupId));
        List<Long> userIdList = SetsUtils.list(userGroupRelEntityList.size());
        for (TPermissionsUserGroupRelEntity entity : userGroupRelEntityList) {
            userIdList.add(entity.getUid());
        }
        List<TPermissionsUserEntity> userEntityList = (List<TPermissionsUserEntity>) PermissionServiceRegistry.getInstance().iPermissionsUserService.findAll(userIdList);
        return this.responseSuccessfully(userEntityList);
    }

    /**
     * 获取该用户组角色列表
     *
     * @return com.hcl.basic.basicframework.dto.WebPageResultDto
     */
    @GetMapping("/roles")
    public @ResponseBody
    WebPageResultDto roles(HttpServletRequest request) {
        String groupId = request.getParameter("groupId");
        if (StringUtils.isBlank(groupId)) {
            return this.responseMessage(SimpleResCodeEnum.BLANK_PARAM + ",groupId is null");
        }
        List<TPermissionsRoleGroupRelEntity> roleGroupRelEntityList = PermissionServiceRegistry.getInstance().iPermissionsRoleGroupRelService.findAllByGid(Long.valueOf(groupId));
        List<Long> roleIdList = SetsUtils.list(roleGroupRelEntityList.size());
        for (TPermissionsRoleGroupRelEntity entity : roleGroupRelEntityList) {
            roleIdList.add(entity.getRid());
        }
        List<TPermissionsRoleEntity> roleEntityList = (List<TPermissionsRoleEntity>) PermissionServiceRegistry.getInstance().iPermissionsRoleService.findAll(roleIdList);
        return this.responseSuccessfully(roleEntityList);
    }

}
