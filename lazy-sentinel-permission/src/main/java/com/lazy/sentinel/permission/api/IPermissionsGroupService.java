package com.lazy.sentinel.permission.api;

import com.lazy.sentinel.permission.api.dto.SysPermissionTreeDto;
import com.lazy.sentinel.permission.api.entity.TPermissionsGroupEntity;
import com.lazy.cheetah.core.service.IBaseService;

import java.util.List;

/**
 * <p>系统权限-用户组原子服务接口</p>
 *
 * @author laizhiyuan
 * @date 2018/03/23
 */
public interface IPermissionsGroupService extends IBaseService<TPermissionsGroupEntity, Long> {

    /**
     * 获取系统用户组树列表
     * @return 树列表
     */
    List<SysPermissionTreeDto> tree();

    /**
     * 通过组名称查数据
     * @param groupName 组名称
     * @return 实体对象
     */
    TPermissionsGroupEntity findByGroupName(String groupName);

    /**
     * 通过组编码查数据
     * @param groupCode 组编码
     * @return 实体对象
     */
    TPermissionsGroupEntity findByGroupCode(String groupCode);

    /**
     * 根据父级用户组id集合查出所有子用户组
     * @param pidList 父级用户组id集合
     * @return 实体对象
     */
    List<TPermissionsGroupEntity> findByPidIn(Iterable<Long> pidList);

    /**
     * 根据父级递归出所有的下级
     * @param pids 父级id
     * @return 所有下级id
     */
    List<Long> recursionSubIdByPids(List<Long> pids);

}
