package com.lazy.sentinel.dao;


import com.lazy.cheetah.core.repository.IBaseRepository;
import com.lazy.sentinel.entity.TResourceEntity;

/**
 * @author laizhiyuan
 * @date 2018/1/3.
 * <p>注册资源数据层接口</p>
 */
public interface IResourceRepository extends IBaseRepository<TResourceEntity, Long> {

    /**
     * 通过资源编码和所属系统类型查找
     * @param resCode 资源编码
     * @param ownSysId 资源归属系统表主键
     * @return 资源实体对象
     */
    TResourceEntity findByResCodeAndOwnSysId(String resCode, Long ownSysId);
}
