package com.lazy.sentinel.permission.service.cache;

import com.lazy.cheetah.core.enums.CacheKeyRuleEnum;
import com.lazy.cheetah.core.manager.CacheKeyRuleManager;
import com.lazy.cheetah.core.service.impl.cache.BaseRedisCacheServiceImpl;
import com.lazy.sentinel.permission.api.IPermissionsGroupService;
import com.lazy.sentinel.permission.api.dto.SysPermissionTreeDto;
import com.lazy.sentinel.permission.api.entity.TPermissionsGroupEntity;
import com.lazy.sentinel.permission.dao.IPermissionsGroupRepository;
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
 * <p>系统权限-用户组原子服务实现类</p>
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
public class PermissionsGroupRedisCacheServiceImpl extends BaseRedisCacheServiceImpl<TPermissionsGroupEntity, Long> implements IPermissionsGroupService {

    /**
     * 数据仓库接口
     */
    private IPermissionsGroupRepository repository;
    /**
     * 注入不支持缓存服务实现类
     */
    private IPermissionsGroupService noSupportCacheImpl;
    /**
     * 用户组树缓存key
     */
    private static final String T_PERMISSIONS_GROUP_TREE_LIST = "TREE_LIST_20180506";

    /**
     * 构造器注入数据仓库接口
     * @param repository 数据仓库接口
     */
    @Autowired
    public PermissionsGroupRedisCacheServiceImpl(IPermissionsGroupRepository repository) {
        super();
        this.setCacheKeyPrefix("T_PERMISSIONS_GROUP_");
        this.addCacheKeyRuleManager(CacheKeyRuleManager.newInstance().addKey(T_PERMISSIONS_GROUP_TREE_LIST).setRule(CacheKeyRuleEnum.FIXED));
        this.setRepository(repository);
        this.repository = repository;
        this.noSupportCacheImpl = (IPermissionsGroupService) this.getNoSupportCacheImpl();
    }

    /**
     * 获取系统用户组树列表 支持缓存
     * @return 树列表
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<SysPermissionTreeDto> tree() {
        Object treeListCache = commonRedisTemplateOps.get(this.joinCacheKey(T_PERMISSIONS_GROUP_TREE_LIST));
        if (treeListCache != null){
            commonRedisTemplate.expire(this.joinCacheKey(T_PERMISSIONS_GROUP_TREE_LIST), cacheExpiration, TimeUnit.SECONDS);
            return (List<SysPermissionTreeDto>) treeListCache;
        }
        List<SysPermissionTreeDto> treeDtoList = noSupportCacheImpl.tree();
        if (treeDtoList != null && !treeDtoList.isEmpty()){
            commonRedisTemplateOps.set(this.joinCacheKey(T_PERMISSIONS_GROUP_TREE_LIST), treeDtoList, cacheExpiration, TimeUnit.SECONDS);
            return treeDtoList;
        }
        return treeDtoList;
    }

    /**
     * 通过组名称查数据
     * @param groupName 组名称
     * @return 实体对象
     */
    @Override
    public TPermissionsGroupEntity findByGroupName(String groupName) {
        return noSupportCacheImpl.findByGroupName(groupName);
    }

    /**
     * 通过组编码查数据
     * @param groupCode 组编码
     * @return 实体对象
     */
    @Override
    public TPermissionsGroupEntity findByGroupCode(String groupCode) {
        return noSupportCacheImpl.findByGroupCode(groupCode);
    }

    /**
     * 根据父级用户组id集合查出所有子用户组
     * @param pidList 父级用户组id集合
     * @return 实体对象
     */
    @Override
    public List<TPermissionsGroupEntity> findByPidIn(Iterable<Long> pidList) {
        return noSupportCacheImpl.findByPidIn(pidList);
    }

    /**
     * 根据父级递归出所有的下级
     * @param pids 父级id
     * @return 所有下级id
     */
    @Override
    public List<Long> recursionSubIdByPids(List<Long> pids) {
        return noSupportCacheImpl.recursionSubIdByPids(pids);
    }

}
