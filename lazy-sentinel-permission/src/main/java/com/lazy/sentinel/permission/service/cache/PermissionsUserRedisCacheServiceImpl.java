package com.lazy.sentinel.permission.service.cache;

import com.lazy.cheetah.common.tools.AssertUtils;
import com.lazy.cheetah.core.enums.CacheKeyRuleEnum;
import com.lazy.cheetah.core.manager.CacheKeyRuleManager;
import com.lazy.cheetah.core.service.impl.cache.BaseRedisCacheServiceImpl;
import com.lazy.sentinel.permission.api.entity.TPermissionsUserEntity;
import com.lazy.sentinel.permission.dao.IPermissionsUserRepository;
import com.lazy.sentinel.permission.api.IPermissionsUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>系统权限-普通用户原子服务实现类</p>
 *
 * @author laizhiyuan
 * @date 2018/03/23
 */
@Service
@Transactional(
        readOnly = true,
        rollbackFor = {RuntimeException.class},
        isolation = Isolation.DEFAULT,
        propagation = Propagation.REQUIRED
)
@Primary
@CacheConfig(keyGenerator = "keyGenerator", cacheManager = "sysCacheManager", cacheNames = {"permissions_redis_cache"})
public class PermissionsUserRedisCacheServiceImpl extends BaseRedisCacheServiceImpl<TPermissionsUserEntity, Long> implements IPermissionsUserService {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionsUserRedisCacheServiceImpl.class);

    /**
     * 数据仓库接口
     */
    private IPermissionsUserRepository repository;

    /**
     * 构造器注入数据仓库接口
     * @param repository 数据仓库接口
     */
    @Autowired
    public PermissionsUserRedisCacheServiceImpl(IPermissionsUserRepository repository) {
        super();
        this.setCacheKeyPrefix("T_PERMISSIONS_USER_");
        this.addCacheKeyRuleManager(CacheKeyRuleManager.newInstance().addKey("loginName").setRule(CacheKeyRuleEnum.APPEND));
        this.setRepository(repository);
        this.repository = repository;
    }

    /**
     * 根据登录名称获取一条数据
     * @param loginName 登录名
     * @return 超级用户实体
     */
    @Cacheable
    @Override
    public TPermissionsUserEntity findOneByLoginName(String loginName) {
        AssertUtils.isBlank(loginName, "param loginName is blank");
        return this.repository.findOneByLoginName(loginName);
    }
}
