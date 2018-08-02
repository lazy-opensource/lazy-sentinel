package com.lazy.sentinel.service.cache;

import com.lazy.cheetah.core.service.impl.cache.BaseRedisCacheServiceImpl;
import com.lazy.sentinel.api.IClientIpInfoService;
import com.lazy.sentinel.dao.IClientIpInfoRepository;
import com.lazy.sentinel.entity.TClientIpInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>客户端ip信息服务层缓存实现</p>
 *
 * @author laizhiyuan
 * @date 2018/3/21.
 */
@Service
@Primary
@SuppressWarnings("unchecked")
@CacheConfig(keyGenerator = "keyGenerator", cacheManager = "sysCacheManager", cacheNames = {"oauth_cli_res_rel"})
@Transactional(rollbackFor = {Exception.class, RuntimeException.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class ClientIpInfoRedisCacheServiceImpl extends BaseRedisCacheServiceImpl<TClientIpInfoEntity, Long> implements IClientIpInfoService {

    /**
     * 数据层接口
     */
    private IClientIpInfoRepository repository;

    /**
     * 注入方式可参考com.tcl.b2b.base.framework.service.impl.ExampleServiceImpl
     *
     * @param repository 子类注入对应的数据层Bean；必须
     */
    @Autowired
    public ClientIpInfoRedisCacheServiceImpl(IClientIpInfoRepository repository) {
        this.setCacheKeyPrefix("T_CLIENT_IP_INFO");
        this.setRepository(repository);
        this.repository = repository;
    }

    /**
     * 通过客户端id查对应的ip列表 不支持缓存
     * @param cliId 客户端id
     * @return ip列表
     */
    @Override
    public List<TClientIpInfoEntity> findAllByCliId(String cliId) {
        return repository.findAllByCliId(cliId);
    }
}
