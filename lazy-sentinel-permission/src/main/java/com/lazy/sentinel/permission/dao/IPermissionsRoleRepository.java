package com.lazy.sentinel.permission.dao;


import com.lazy.sentinel.permission.api.entity.TPermissionsRoleEntity;
import com.lazy.cheetah.core.repository.IBaseRepository;

/**
 * <p>系统权限-角色数据仓库接口</p>
 *
 * @author laizhiyuan
 * @date 2018/03/23
 */
public interface IPermissionsRoleRepository extends IBaseRepository<TPermissionsRoleEntity, Long> {

    /**
     * 通过角色编码查数据
     * @param roleCode 角色编码
     * @return 角色实体对象
     */
    TPermissionsRoleEntity findByRoleCode(String roleCode);
}
