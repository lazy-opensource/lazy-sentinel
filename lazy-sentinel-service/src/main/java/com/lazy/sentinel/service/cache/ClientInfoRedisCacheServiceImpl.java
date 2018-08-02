package com.lazy.sentinel.service.cache;

import com.lazy.cheetah.common.tools.StringUtils;
import com.lazy.cheetah.core.enums.CacheKeyRuleEnum;
import com.lazy.cheetah.core.manager.CacheKeyRuleManager;
import com.lazy.cheetah.core.service.impl.cache.BaseRedisCacheServiceImpl;
import com.lazy.sentinel.api.IClientInfoService;
import com.lazy.sentinel.dao.IClientInfoRepository;
import com.lazy.sentinel.entity.TClientInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author laizhiyuan
 * @date 2018/1/3.
 * <p>客户端信息服务层支持Redis缓存实现类</p>
 */
@Service
@SuppressWarnings("unchecked")
@Primary
@CacheConfig(keyGenerator = "keyGenerator", cacheManager = "sysCacheManager", cacheNames = {"oauth_client_info"})
@Transactional(rollbackFor = {Exception.class, RuntimeException.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class ClientInfoRedisCacheServiceImpl extends BaseRedisCacheServiceImpl<TClientInfoEntity, Long> implements IClientInfoService {

    /**
     * 数据层接口
     */
    private IClientInfoRepository repository;

    /**
     * 构造器
     *
     * @param repository 注入对应的数据层Bean；必须
     */
    @Autowired
    public ClientInfoRedisCacheServiceImpl(IClientInfoRepository repository) {
        this.addCacheKeyRuleManager(CacheKeyRuleManager.newInstance().setRule(CacheKeyRuleEnum.APPEND).addKey("cliId"));
        this.setCacheKeyPrefix("T_CLIENT_INFO");
        this.setRepository(repository);
        this.repository = repository;
    }

    /**
     * 通过cli_id查一条数据
     *
     * @param cliId 颁发给对接客户端id 必须
     * @return 客户端信息实体对象
     */
    @Override
    @Cacheable
    public TClientInfoEntity findByCliId(String cliId) {
        if (StringUtils.isBlank(cliId)){
            return null;
        }
        TClientInfoEntity entity = repository.findByCliId(cliId);
        return entity;
    }
}
