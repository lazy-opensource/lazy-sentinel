package com.lazy.sentinel.service.cache;


import com.lazy.cheetah.common.tools.AssertUtils;
import com.lazy.cheetah.core.enums.CacheKeyRuleEnum;
import com.lazy.cheetah.core.manager.CacheKeyRuleManager;
import com.lazy.cheetah.core.service.impl.cache.BaseRedisCacheServiceImpl;
import com.lazy.sentinel.api.IResourceService;
import com.lazy.sentinel.dao.IResourceRepository;
import com.lazy.sentinel.entity.TResourceEntity;
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
 * <p>资源服务层Redis缓存实现类</p>
 */
@Service
@SuppressWarnings("unchecked")
@Primary
@CacheConfig(keyGenerator = "keyGenerator", cacheManager = "sysCacheManager", cacheNames = {"oauth_resource"})
@Transactional(rollbackFor = {Exception.class, RuntimeException.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class ResourceRedisCacheServiceImpl extends BaseRedisCacheServiceImpl<TResourceEntity, Long> implements IResourceService {

    /**
     * 数据层接口
     */
    private IResourceRepository repository;

    /**
     * 构造器
     * 注入方式可参考com.tcl.b2b.base.framework.service.impl.ExampleServiceImpl
     *
     * @param repository 子类注入对应的数据层Bean；必须
     */
    @Autowired
    public ResourceRedisCacheServiceImpl(IResourceRepository repository) {
        this.addCacheKeyRuleManager(CacheKeyRuleManager.newInstance().setRule(CacheKeyRuleEnum.APPEND).addKey("resCode").addKey("ownSysId"));
        this.setCacheKeyPrefix("T_RESOURCE");
        this.setRepository(repository);
        this.repository = repository;
    }

    /**
     * 通过资源编码和所属系统类型查找 支持缓存
     * @param resCode 资源编码
     * @param ownSysId 资源归属系统表主键
     * @return 资源实体对象
     */
    @Cacheable
    @Override
    public TResourceEntity findByResCodeAndOwnSysId(String resCode, Long ownSysId) {
        AssertUtils.isNull(resCode, "param resCode is null");
        AssertUtils.isNull(ownSysId, "param ownSysId is null");
        return this.repository.findByResCodeAndOwnSysId(resCode, ownSysId);
    }
}
