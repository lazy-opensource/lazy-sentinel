package com.hcl.common.permissioncenter.api;


import com.hcl.common.permissioncenter.api.entity.TPermissionsRoleGroupRelEntity;
import com.lazy.cheetah.core.service.IBaseService;

import java.util.List;

/**
 * <p>系统权限-角色和用户组多对多关联关系原子服务接口</p>
 *
 * @author laizhiyuan
 * @date 2018/03/23
 */
public interface IPermissionsRoleGroupRelService extends IBaseService<TPermissionsRoleGroupRelEntity, Long> {

    /**
     * 获取某个角色关联的用户组列表
     * @param rId 角色id
     * @return 关联关系列表
     */
    List<TPermissionsRoleGroupRelEntity> findAllByRid(Long rId);

    /**
     * 获取某个用户组关联的角色列表
     * @param gId 用户组id
     * @return 关联关系列表
     */
    List<TPermissionsRoleGroupRelEntity> findAllByGid(Long gId);

    /**
     * 获取某些角色关联的用户组列表
     * @param rIds 角色id列表
     * @return 关联关系列表
     */
    List<TPermissionsRoleGroupRelEntity> findAllByRidIn(Iterable<Long> rIds);

    /**
     * 获取某些用户组关联的角色列表
     * @param gIds 用户组id列表
     * @return 关联关系列表
     */
    List<TPermissionsRoleGroupRelEntity> findAllByGidIn(Iterable<Long> gIds);
}
