package com.hcl.common.permissioncenter.dao;


import com.hcl.common.permissioncenter.api.entity.TPermissionsGroupEntity;
import com.lazy.cheetah.core.repository.IBaseRepository;

import java.util.List;

/**
 * <p>系统权限-用户组数据仓库接口</p>
 *
 * @author laizhiyuan
 * @date 2018/03/23
 */
public interface IPermissionsGroupRepository extends IBaseRepository<TPermissionsGroupEntity, Long> {

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
     * @return
     */
    List<TPermissionsGroupEntity> findByPidIn(Iterable<Long> pidList);

}
