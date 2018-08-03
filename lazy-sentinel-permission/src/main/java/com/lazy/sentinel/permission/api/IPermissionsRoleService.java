package com.lazy.sentinel.permission.api;


import com.lazy.sentinel.permission.api.dto.SysPermissionTreeDto;
import com.lazy.sentinel.permission.api.entity.TPermissionsRoleEntity;
import com.lazy.cheetah.core.service.IBaseService;

import java.util.List;

/**
 * <p>系统权限-角色原子服务接口</p>
 *
 * @author laizhiyuan
 * @date 2018/03/23
 */
public interface IPermissionsRoleService extends IBaseService<TPermissionsRoleEntity, Long> {

    /**
     * 获取系统角色树列表
     * @return 树列表
     */
    List<SysPermissionTreeDto> tree();

    /**
     * 通过角色编码查数据
     * @param roleCode 角色编码
     * @return 角色实体对象
     */
    TPermissionsRoleEntity findByRoleCode(String roleCode);
}
