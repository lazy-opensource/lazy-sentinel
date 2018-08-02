package com.lazy.sentinel.api;


import com.lazy.cheetah.core.service.IBaseService;
import com.lazy.sentinel.entity.TAppInfoEntity;

/**
 * @author laizhiyuan
 * @date 2018/1/10.
 * <p>对接应用信息服务层接口</p>
 */
public interface IAppInfoService extends IBaseService<TAppInfoEntity, Long> {

    /**
     * 通过给定的ip参数进行查找
     * @param appIp 应用ip
     * @return 应用信息实体对象
     */
    TAppInfoEntity findByAppIp(String appIp);
}
