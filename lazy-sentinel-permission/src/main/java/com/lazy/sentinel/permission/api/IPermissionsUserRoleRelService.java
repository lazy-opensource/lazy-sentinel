package com.lazy.sentinel.permission.api;


import com.lazy.sentinel.permission.api.entity.TPermissionsUserRoleRelEntity;
import com.lazy.cheetah.core.service.IBaseService;

import java.util.List;

/**
 * <p>系统权限-用户和角色多对多关联关系原子服务接口</p>
 *
 * @author laizhiyuan
 * @date 2018/03/23
 */
public interface IPermissionsUserRoleRelService extends IBaseService<TPermissionsUserRoleRelEntity, Long> {

    /**
     * 获取某个角色关联的用户列表
     * @param rId 角色id
     * @return 关联关系列表
     */
    List<TPermissionsUserRoleRelEntity> findAllByRid(Long rId);

    /**
     * 获取某个用户关联的角色列表
     * @param uId 用户id
     * @return 关联关系列表
     */
    List<TPermissionsUserRoleRelEntity> findAllByUid(Long uId);

    /**
     * 获取某些角色关联的用户列表
     * @param rIds 角色id列表
     * @return 关联关系列表
     */
    List<TPermissionsUserRoleRelEntity> findAllByRidIn(Iterable<Long> rIds);

    /**
     * 获取某些用户关联的角色列表
     * @param uIds 用户id列表
     * @return 关联关系列表
     */
    List<TPermissionsUserRoleRelEntity> findAllByUidIn(Iterable<Long> uIds);
}
