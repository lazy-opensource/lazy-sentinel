package com.lazy.sentinel.service.cache;


import com.lazy.cheetah.common.tools.StringUtils;
import com.lazy.cheetah.core.enums.CacheKeyRuleEnum;
import com.lazy.cheetah.core.manager.CacheKeyRuleManager;
import com.lazy.cheetah.core.service.impl.cache.BaseRedisCacheServiceImpl;
import com.lazy.sentinel.api.ISystemTypeService;
import com.lazy.sentinel.dao.ISystemTypeRepository;
import com.lazy.sentinel.entity.TSystemTypeEntity;
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
 * <p>系统类型缓存服务层实现类</p>
 */
@Service
@SuppressWarnings("unchecked")
@Primary
@CacheConfig(keyGenerator = "keyGenerator", cacheManager = "sysCacheManager", cacheNames = {"oauth_sys_type"})
@Transactional(rollbackFor = {Exception.class, RuntimeException.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class SystemTypeRedisCacheServiceImpl extends BaseRedisCacheServiceImpl<TSystemTypeEntity, Long> implements ISystemTypeService {

    /**
     * 数据层接口
     */
    private ISystemTypeRepository repository;

    /**
     * 构造器
     * 注入方式可参考com.tcl.b2b.base.framework.service.impl.ExampleServiceImpl
     *
     * @param iSystemTypeRepository 子类注入对应的数据层Bean；必须
     */
    @Autowired
    public SystemTypeRedisCacheServiceImpl(ISystemTypeRepository iSystemTypeRepository) {
        this.addCacheKeyRuleManager(CacheKeyRuleManager.newInstance().setRule(CacheKeyRuleEnum.APPEND).addKey("sysTypeCode"));
        this.setCacheKeyPrefix("T_SYSTEM_TYPE");
        this.setRepository(iSystemTypeRepository);
        this.repository = iSystemTypeRepository;
    }

    /**
     * 通过系统类型编码查一条数据 支持缓存
     * @param sysTypeCode 系统编码
     * @return 系统类型信息实体对象
     */
    @Cacheable
    @Override
    public TSystemTypeEntity findBySysTypeCode(String sysTypeCode){
        if (StringUtils.isBlank(sysTypeCode)){
            return null;
        }
        return this.repository.findBySysTypeCode(sysTypeCode);
    }
}
