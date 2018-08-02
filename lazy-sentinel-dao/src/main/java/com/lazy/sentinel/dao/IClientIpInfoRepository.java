package com.lazy.sentinel.dao;

import com.lazy.cheetah.core.repository.IBaseRepository;
import com.lazy.sentinel.entity.TClientIpInfoEntity;

import java.util.List;

/**
 * <p></p>
 *
 * @author laizhiyuan
 * @date 2018/3/21.
 */
public interface IClientIpInfoRepository extends IBaseRepository<TClientIpInfoEntity, Long> {

    /**
     * 通过客户端id查对应的ip列表
     * @param cliId 客户端id
     * @return ip列表
     */
    List<TClientIpInfoEntity> findAllByCliId(String cliId);
}
