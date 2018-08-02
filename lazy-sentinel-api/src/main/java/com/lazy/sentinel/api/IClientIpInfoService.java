package com.lazy.sentinel.api;


import com.lazy.cheetah.core.service.IBaseService;
import com.lazy.sentinel.entity.TClientIpInfoEntity;

import java.util.List;

/**
 * <p>客户端ip信息服务接口层</p>
 *
 * @author laizhiyuan
 * @date 2018/3/21.
 */
public interface IClientIpInfoService extends IBaseService<TClientIpInfoEntity, Long> {

    /**
     * 通过客户端id查对应的ip列表
     * @param cliId 客户端id
     * @return ip列表
     */
    List<TClientIpInfoEntity> findAllByCliId(String cliId);
}
