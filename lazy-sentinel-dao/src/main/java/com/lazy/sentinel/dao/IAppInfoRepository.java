package com.lazy.sentinel.dao;

import com.lazy.cheetah.core.repository.IBaseRepository;
import com.lazy.sentinel.entity.TAppInfoEntity;

/**
 * @author laizhiyuan
 * @date 2018/1/10.
 * <p>对接应用信息数据层接口</p>
 */
public interface IAppInfoRepository extends IBaseRepository<TAppInfoEntity, Long> {

    /**
     * 通过给定的ip参数进行查找
     * @param appIp 应用ip
     * @return 应用信息实体对象
     */
    TAppInfoEntity findByAppIp(String appIp);
}
