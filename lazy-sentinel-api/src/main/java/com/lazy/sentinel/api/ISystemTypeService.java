package com.lazy.sentinel.api;


import com.lazy.cheetah.core.service.IBaseService;
import com.lazy.sentinel.entity.TSystemTypeEntity;

/**
 * @author laizhiyuan
 * @date 2018/1/3.
 * <p>系统类型服务层接口</p>
 */
public interface ISystemTypeService extends IBaseService<TSystemTypeEntity, Long> {

    /**
     * 通过系统类型编码查一条数据
     * @param sysTypeCode 系统编码
     * @return 系统类型信息实体对象
     */
    TSystemTypeEntity findBySysTypeCode(String sysTypeCode);
}
