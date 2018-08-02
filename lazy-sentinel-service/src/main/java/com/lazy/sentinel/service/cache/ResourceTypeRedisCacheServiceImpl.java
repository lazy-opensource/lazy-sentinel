package com.lazy.sentinel.service.cache;


import com.lazy.cheetah.core.service.impl.cache.BaseRedisCacheServiceImpl;
import com.lazy.sentinel.api.IResourceTypeService;
import com.lazy.sentinel.dao.IResourceTypeRepository;
import com.lazy.sentinel.entity.TResourceTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author laizhiyuan
 * @date 2018/1/3.
 * <p>资源类型缓存服务层实现类</p>
 */
@Service
@SuppressWarnings("unchecked")
@Primary
@CacheConfig(keyGenerator = "keyGenerator", cacheManager = "sysCacheManager", cacheNames = {"oauth_res_type"})
@Transactional(rollbackFor = {Exception.class, RuntimeException.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class ResourceTypeRedisCacheServiceImpl extends BaseRedisCacheServiceImpl<TResourceTypeEntity, Long> implements IResourceTypeService {

    /**
     * 数据层接口
     */
    private IResourceTypeRepository repository;

    /**
     * 构造器
     * 注入方式可参考com.tcl.b2b.base.framework.service.impl.ExampleServiceImpl
     *
     * @param iResourceTypeRepository 子类注入对应的数据层Bean；必须
     */
    @Autowired
    public ResourceTypeRedisCacheServiceImpl(IResourceTypeRepository iResourceTypeRepository) {
        this.setCacheKeyPrefix("T_RESOURCE_TYPE");
        this.setRepository(iResourceTypeRepository);
        this.repository = iResourceTypeRepository;
    }
}
