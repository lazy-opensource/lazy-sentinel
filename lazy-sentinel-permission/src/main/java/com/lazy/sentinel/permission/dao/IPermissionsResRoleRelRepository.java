package com.lazy.sentinel.permission.dao;


import com.lazy.sentinel.permission.api.entity.TPermissionsResRoleRelEntity;
import com.lazy.cheetah.core.repository.IBaseRepository;

import java.util.List;

/**
 * <p>系统权限-资源和角色多对多关联关系数据仓库接口</p>
 *
 * @author laizhiyuan
 * @date 2018/03/23
 */
public interface IPermissionsResRoleRelRepository extends IBaseRepository<TPermissionsResRoleRelEntity, Long> {

    /**
     * 获取某个角色关联的资源列表
     * @param roleId 角色id
     * @return 关联关系列表
     */
    List<TPermissionsResRoleRelEntity> findAllByRoleId(Long roleId);

    /**
     * 获取某个资源关联的角色列表
     * @param resId 资源id
     * @return 关联关系列表
     */
    List<TPermissionsResRoleRelEntity> findAllByResId(Long resId);

    /**
     * 获取某些角色关联的资源列表
     * @param roleIds 角色id列表
     * @return 关联关系列表
     */
    List<TPermissionsResRoleRelEntity> findAllByRoleIdIn(Iterable<Long> roleIds);

    /**
     * 获取某些资源关联的角色列表
     * @param resIds 资源id列表
     * @return 关联关系列表
     */
    List<TPermissionsResRoleRelEntity> findAllByResIdIn(Iterable<Long> resIds);
}
