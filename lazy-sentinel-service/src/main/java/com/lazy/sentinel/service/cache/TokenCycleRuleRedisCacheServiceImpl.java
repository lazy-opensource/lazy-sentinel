package com.lazy.sentinel.service.cache;


import com.lazy.cheetah.common.tools.StringUtils;
import com.lazy.cheetah.core.enums.CacheKeyRuleEnum;
import com.lazy.cheetah.core.manager.CacheKeyRuleManager;
import com.lazy.cheetah.core.service.impl.cache.BaseRedisCacheServiceImpl;
import com.lazy.sentinel.api.ITokenCycleRuleService;
import com.lazy.sentinel.common.enums.CommonEnum;
import com.lazy.sentinel.dao.ITokenCycleRuleRepository;
import com.lazy.sentinel.entity.TTokenCycleRuleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author laizhiyuan
 * @date 2018/1/5.
 * <p>token生命周期规则服务层实现类</p>
 */
@Service
@SuppressWarnings("unchecked")
@Primary
@CacheConfig(keyGenerator = "keyGenerator", cacheManager = "commonCacheManager", cacheNames = {"oauth_token_cycle"})
@Transactional(rollbackFor = {Exception.class, RuntimeException.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class TokenCycleRuleRedisCacheServiceImpl extends BaseRedisCacheServiceImpl<TTokenCycleRuleEntity, Long> implements ITokenCycleRuleService {

    /**
     * 数据层接口
     */
    private ITokenCycleRuleRepository repository;
    /**
     * 缓存key,
     */
    private static final String CACHE_KEY = "token_cycle_rule_20180108";

    /**
     * 构造器
     * 注入方式可参考com.tcl.b2b.base.framework.service.impl.ExampleServiceImpl
     *
     * @param iTokenCycleRuleRepository 子类注入对应的数据层Bean；必须
     */
    @Autowired
    public TokenCycleRuleRedisCacheServiceImpl(ITokenCycleRuleRepository iTokenCycleRuleRepository) {
        this.repository = iTokenCycleRuleRepository;
        this.addCacheKeyRuleManager(CacheKeyRuleManager.newInstance().setRule(CacheKeyRuleEnum.FIXED).addKey(CACHE_KEY));
        this.setCacheKeyPrefix("T_TOKEN_CYCLE");
        this.setRepository(iTokenCycleRuleRepository);
    }

    /**
     * 通过有效状态查数据集合
     * @param validStatus 有效状态
     * @return token生命周期配置信息实体对象集合
     */
    @Override
    public TTokenCycleRuleEntity findByValidStatus(String validStatus){
        if (StringUtils.isBlank(validStatus)){
            return null;
        }
        TTokenCycleRuleEntity tokenCycleRuleEntityCache = (TTokenCycleRuleEntity) commonRedisTemplateOps.get(this.getCacheKeyPrefix() + CACHE_KEY);
        if (tokenCycleRuleEntityCache != null){
            return tokenCycleRuleEntityCache;
        }
        List<TTokenCycleRuleEntity> tokenCycleRuleEntityList = repository.findByValidStatus(CommonEnum.ENABLE_STATUS.getValue());
        if (tokenCycleRuleEntityList == null || tokenCycleRuleEntityList.size() < 1){
            throw new RuntimeException("not config token cycle rule in table t_toke_cycle_rule");
        }
        if (tokenCycleRuleEntityList.size() > 1){
            logger.warn("token cycle rule config status enable greater than 1, but system get list index is 0");
        }
        TTokenCycleRuleEntity tokenCycleRuleEntity = tokenCycleRuleEntityList.get(0);
        commonRedisTemplateOps.set(this.getCacheKeyPrefix() + CACHE_KEY, tokenCycleRuleEntity);
        commonRedisTemplate.expire(this.getCacheKeyPrefix() + CACHE_KEY, this.cacheExpiration, TimeUnit.SECONDS);
        return tokenCycleRuleEntity;
    }
}
