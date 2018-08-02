package com.lazy.sentinel.service.cache;


import com.lazy.cheetah.common.tools.StringUtils;
import com.lazy.cheetah.core.enums.CacheKeyRuleEnum;
import com.lazy.cheetah.core.manager.CacheKeyRuleManager;
import com.lazy.cheetah.core.service.impl.cache.BaseRedisCacheServiceImpl;
import com.lazy.sentinel.api.IAppInfoService;
import com.lazy.sentinel.dao.IAppInfoRepository;
import com.lazy.sentinel.entity.TAppInfoEntity;
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
 * @date 2018/1/10.
 * <p>对接应用信息服务层Redis缓存实现</p>
 */
@Service
@SuppressWarnings("unchecked")
@Primary
@CacheConfig(keyGenerator = "keyGenerator", cacheManager = "sysCacheManager", cacheNames = {"oauth_app_info"})
@Transactional(rollbackFor = {Exception.class, RuntimeException.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class AppInfoRedisCacheServiceImpl extends BaseRedisCacheServiceImpl<TAppInfoEntity, Long> implements IAppInfoService {

    /**
     * 数据层接口
     */
    private IAppInfoRepository repository;

    /**
     * 构造器
     *
     * @param repository 注入对应的数据层Bean；必须
     */
    @Autowired
    public AppInfoRedisCacheServiceImpl(IAppInfoRepository repository) {
        this.addCacheKeyRuleManager(CacheKeyRuleManager.newInstance().setRule(CacheKeyRuleEnum.APPEND).addKey("appIp"));
        this.setCacheKeyPrefix("T_APP_INFO");
        this.setRepository(repository);
        this.repository = repository;
    }

    /**
     * 通过给定的ip参数进行查找
     * @param appIp 应用ip
     * @return 应用信息实体对象
     */
    @Override
    @Cacheable
    public TAppInfoEntity findByAppIp(String appIp) {
        if (StringUtils.isBlank(appIp)){
            return null;
        }
        return this.repository.findByAppIp(appIp);
    }
}
