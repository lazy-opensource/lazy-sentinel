package com.lazy.sentinel.permission.api;


import com.lazy.sentinel.permission.api.entity.TPermissionsResGroupRelEntity;
import com.lazy.cheetah.core.service.IBaseService;

import java.util.List;

/**
 * <p>系统权限-资源和用户组多对多关联关系原子服务接口</p>
 *
 * @author laizhiyuan
 * @date 2018/03/23
 */
public interface IPermissionsResGroupRelService extends IBaseService<TPermissionsResGroupRelEntity, Long> {

    /**
     * 获取某个资源关联的用户组列表
     * @param rId 资源id
     * @return 关联关系列表
     */
    List<TPermissionsResGroupRelEntity> findAllByRid(Long rId);

    /**
     * 获取某个用户组关联的资源列表
     * @param gId 用户组id
     * @return 关联关系列表
     */
    List<TPermissionsResGroupRelEntity> findAllByGid(Long gId);

    /**
     * 获取某些资源关联的用户组列表
     * @param rIds 资源id列表
     * @return 关联关系列表
     */
    List<TPermissionsResGroupRelEntity> findAllByRidIn(Iterable<Long> rIds);

    /**
     * 获取某些用户组关联的资源列表
     * @param gIds 用户组id列表
     * @return 关联关系列表
     */
    List<TPermissionsResGroupRelEntity> findAllByGidIn(Iterable<Long> gIds);
}
