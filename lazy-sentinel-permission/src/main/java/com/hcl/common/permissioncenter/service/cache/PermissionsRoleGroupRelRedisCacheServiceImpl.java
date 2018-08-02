package com.hcl.common.permissioncenter.service.cache;

import com.lazy.cheetah.core.service.impl.cache.BaseRedisCacheServiceImpl;
import com.hcl.common.permissioncenter.api.IPermissionsRoleGroupRelService;
import com.hcl.common.permissioncenter.api.entity.TPermissionsRoleGroupRelEntity;
import com.hcl.common.permissioncenter.dao.IPermissionsRoleGroupRelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>系统权限-角色和用户组多对多关联关系原子服务实现类</p>
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
public class PermissionsRoleGroupRelRedisCacheServiceImpl extends BaseRedisCacheServiceImpl<TPermissionsRoleGroupRelEntity, Long> implements IPermissionsRoleGroupRelService {

    /**
     * 数据仓库接口
     */
    private IPermissionsRoleGroupRelRepository repository;

    /**
     * 构造器注入数据仓库接口
     * @param repository 数据仓库接口
     */
    @Autowired
    public PermissionsRoleGroupRelRedisCacheServiceImpl(IPermissionsRoleGroupRelRepository repository) {
        super();
        this.setCacheKeyPrefix("T_PERMISSIONS_ROLE_GROUP_REL_");
        this.setRepository(repository);
        this.repository = repository;
    }


    /**
     * 获取某个角色关联的用户组列表
     * @param rId 角色id
     * @return 关联关系列表
     */
    @Override
    public List<TPermissionsRoleGroupRelEntity> findAllByRid(Long rId) {
        return repository.findAllByRid(rId);
    }

    /**
     * 获取某个用户组关联的角色列表
     * @param gId 用户组id
     * @return 关联关系列表
     */
    @Override
    public List<TPermissionsRoleGroupRelEntity> findAllByGid(Long gId) {
        return repository.findAllByGid(gId);
    }

    /**
     * 获取某些角色关联的用户组列表
     * @param rIds 角色id列表
     * @return 关联关系列表
     */
    @Override
    public List<TPermissionsRoleGroupRelEntity> findAllByRidIn(Iterable<Long> rIds) {
        return repository.findAllByRidIn(rIds);
    }

    /**
     * 获取某些用户组关联的角色列表
     * @param gIds 用户组id列表
     * @return 关联关系列表
     */
    @Override
    public List<TPermissionsRoleGroupRelEntity> findAllByGidIn(Iterable<Long> gIds) {
        return repository.findAllByGidIn(gIds);
    }

}
