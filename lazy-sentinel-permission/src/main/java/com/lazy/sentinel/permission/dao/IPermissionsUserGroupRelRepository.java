package com.lazy.sentinel.permission.dao;


import com.lazy.sentinel.permission.api.entity.TPermissionsUserGroupRelEntity;
import com.lazy.cheetah.core.repository.IBaseRepository;

import java.util.List;

/**
 * <p>系统权限-用户和用户组数据仓库接口</p>
 *
 * @author laizhiyuan
 * @date 2018/03/23
 */
public interface IPermissionsUserGroupRelRepository extends IBaseRepository<TPermissionsUserGroupRelEntity, Long> {


    /**
     * 获取某个用户关联的用户组列表
     * @param uId 用户id
     * @return 关联关系列表
     */
    List<TPermissionsUserGroupRelEntity> findAllByUid(Long uId);

    /**
     * 获取某个用户组关联的用户列表
     * @param gId 用户组id
     * @return 关联关系列表
     */
    List<TPermissionsUserGroupRelEntity> findAllByGid(Long gId);

    /**
     * 获取某些用户关联的用户组列表
     * @param uIds 用户id列表
     * @return 关联关系列表
     */
    List<TPermissionsUserGroupRelEntity> findAllByUidIn(Iterable<Long> uIds);

    /**
     * 获取某些用户组关联的用户列表
     * @param gIds 用户组id列表
     * @return 关联关系列表
     */
    List<TPermissionsUserGroupRelEntity> findAllByGidIn(Iterable<Long> gIds);
}
