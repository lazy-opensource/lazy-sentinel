package com.lazy.sentinel.permission.service.cache;

import com.lazy.cheetah.core.service.impl.cache.BaseRedisCacheServiceImpl;
import com.lazy.sentinel.permission.api.IPermissionsResGroupRelService;
import com.lazy.sentinel.permission.api.entity.TPermissionsResGroupRelEntity;
import com.lazy.sentinel.permission.dao.IPermissionsResGroupRelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>系统权限-资源和用户组多对多关联关系原子服务实现类</p>
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
public class PermissionsResGroupRelRedisCacheServiceImpl extends BaseRedisCacheServiceImpl<TPermissionsResGroupRelEntity, Long> implements IPermissionsResGroupRelService {

    /**
     * 数据仓库接口
     */
    private IPermissionsResGroupRelRepository repository;

    /**
     * 构造器注入数据仓库接口
     * @param repository 数据仓库接口
     */
    @Autowired
    public PermissionsResGroupRelRedisCacheServiceImpl(IPermissionsResGroupRelRepository repository) {
        super();
        this.setCacheKeyPrefix("T_PERMISSIONS_RES_GROUP_REL_");
        this.setRepository(repository);
        this.repository = repository;
    }

    /**
     * 获取某个资源关联的用户组列表
     * @param rId 资源id
     * @return 关联关系列表
     */
    @Override
    public List<TPermissionsResGroupRelEntity> findAllByRid(Long rId) {
        return repository.findAllByRid(rId);
    }

    /**
     * 获取某个用户组关联的资源列表
     * @param gId 用户组id
     * @return 关联关系列表
     */
    @Override
    public List<TPermissionsResGroupRelEntity> findAllByGid(Long gId) {
        return repository.findAllByGid(gId);
    }

    /**
     * 获取某些资源关联的用户组列表
     * @param rIds 资源id列表
     * @return 关联关系列表
     */
    @Override
    public List<TPermissionsResGroupRelEntity> findAllByRidIn(Iterable<Long> rIds) {
        return repository.findAllByRidIn(rIds);
    }

    /**
     * 获取某些用户组关联的资源列表
     * @param gIds 用户组id列表
     * @return 关联关系列表
     */
    @Override
    public List<TPermissionsResGroupRelEntity> findAllByGidIn(Iterable<Long> gIds) {
        return repository.findAllByGidIn(gIds);
    }
}
