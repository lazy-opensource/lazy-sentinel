package com.lazy.sentinel.dao;


import com.lazy.cheetah.core.repository.IBaseRepository;
import com.lazy.sentinel.entity.TSystemTypeEntity;

/**
 * @author laizhiyuan
 * @date 2018/1/3.
 * <p>对接系统类型数据层接口</p>
 */
public interface ISystemTypeRepository extends IBaseRepository<TSystemTypeEntity, Long> {

    /**
     * 通过系统类型编码查一条数据
     * @param sysTypeCode 系统编码
     * @return 系统类型信息实体对象
     */
    TSystemTypeEntity findBySysTypeCode(String sysTypeCode);
}
