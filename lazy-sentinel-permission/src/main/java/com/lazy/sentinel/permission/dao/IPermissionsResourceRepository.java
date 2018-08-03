package com.lazy.sentinel.permission.dao;


import com.lazy.sentinel.permission.api.entity.TPermissionsResourceEntity;
import com.lazy.cheetah.core.repository.IBaseRepository;

/**
 * <p>系统权限-资源数据仓库接口</p>
 *
 * @author laizhiyuan
 * @date 2018/03/23
 */
public interface IPermissionsResourceRepository extends IBaseRepository<TPermissionsResourceEntity, Long> {

    /**
     * 通过资源编码查数据
     * @param resCode 资源编码
     * @return 实体对象
     */
    TPermissionsResourceEntity findByResCode(String resCode);
}
