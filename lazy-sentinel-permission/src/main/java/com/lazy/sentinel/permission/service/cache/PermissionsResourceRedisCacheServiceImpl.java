package com.lazy.sentinel.permission.service.cache;

import com.lazy.cheetah.core.enums.CacheKeyRuleEnum;
import com.lazy.cheetah.core.manager.CacheKeyRuleManager;
import com.lazy.cheetah.core.service.impl.cache.BaseRedisCacheServiceImpl;
import com.lazy.sentinel.permission.api.IPermissionsResourceService;
import com.lazy.sentinel.permission.api.dto.SysPermissionTreeDto;
import com.lazy.sentinel.permission.api.entity.TPermissionsResourceEntity;
import com.lazy.sentinel.permission.dao.IPermissionsResourceRepository;
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
 * <p>系统权限-资源原子服务实现类</p>
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
@SuppressWarnings("unchecked")
@CacheConfig(keyGenerator = "keyGenerator", cacheManager = "sysCacheManager", cacheNames = {"sentinel_permissions_redis_cache"})
public class PermissionsResourceRedisCacheServiceImpl extends BaseRedisCacheServiceImpl<TPermissionsResourceEntity, Long> implements IPermissionsResourceService {

    /**
     * 数据仓库接口
     */
    private IPermissionsResourceRepository repository;
    /**
     * 注入不支持缓存服务实现类
     */
    private IPermissionsResourceService noSupportCacheImpl;
    /**
     * 资源树缓存列表
     */
    private final String TREE_LIST_FIXED_CACHE_KEY = "TREE_LIST";

    /**
     * 构造器注入数据仓库接口
     * @param repository 数据仓库接口
     */
    @Autowired
    public PermissionsResourceRedisCacheServiceImpl(IPermissionsResourceRepository repository) {
        super();
        this.setCacheKeyPrefix("T_PERMISSIONS_RESOURCE_");
        this.addCacheKeyRuleManager(CacheKeyRuleManager.newInstance().addKey(TREE_LIST_FIXED_CACHE_KEY).setRule(CacheKeyRuleEnum.FIXED));
        this.setRepository(repository);
        this.repository = repository;
        this.noSupportCacheImpl = (IPermissionsResourceService) this.getNoSupportCacheImpl();
    }

    /**
     * 获取系统资源树列表 支持缓存
     * @return 树列表
     */
    @Override
    public List<SysPermissionTreeDto> tree() {
        Object treeListCacheValue = commonRedisTemplateOps.get(this.joinCacheKey(TREE_LIST_FIXED_CACHE_KEY));
        if (treeListCacheValue != null){
            commonRedisTemplate.expire(this.joinCacheKey(TREE_LIST_FIXED_CACHE_KEY), cacheExpiration, TimeUnit.SECONDS);
            return (List<SysPermissionTreeDto>) treeListCacheValue;
        }
        List<SysPermissionTreeDto> treeListDataBase = noSupportCacheImpl.tree();
        if (treeListDataBase != null && !treeListDataBase.isEmpty()){
            commonRedisTemplateOps.set(this.joinCacheKey(TREE_LIST_FIXED_CACHE_KEY), treeListDataBase, cacheExpiration, TimeUnit.SECONDS);
        }
        return treeListDataBase;
    }

    /**
     * 获取某个用户的某个资源类型列表
     * @param uId 用户id
     * @param resType 资源类型
     * @return 资源列表
     */
    @Override
    public List<SysPermissionTreeDto> treeByUidAndResType(Long uId, String resType) {
        return noSupportCacheImpl.treeByUidAndResType(uId, resType);
    }

    /**
     * 获取某个用户某个父级资源下的某个资源类型列表
     * @param uId 用户id
     * @param pId 父级id
     * @param resType 资源类型
     * @return 资源列表
     */
    @Override
    public List<SysPermissionTreeDto> treeByPidAndUidAndResType(Long uId, Long pId, String resType) {
        return noSupportCacheImpl.treeByPidAndUidAndResType(uId, pId, resType);
    }

    /**
     * 获取某个用户某个资源类型某个父级菜单下的子菜单列表
     * @param uId 用户id
     * @param pId 父级id
     * @param resType 资源类型
     * @return 导航栏菜单列表
     */
    @Override
    public List<TPermissionsResourceEntity> findAllByPidAndUidAndResType(Long uId, Long pId, String resType) {
        return noSupportCacheImpl.findAllByPidAndUidAndResType(uId, pId, resType);
    }

    /**
     * 通过资源编码查数据
     * @param resCode 资源编码
     * @return 实体对象
     */
    @Override
    public TPermissionsResourceEntity findByResCode(String resCode) {
        return noSupportCacheImpl.findByResCode(resCode);
    }

    /**
     * 根据资源id生成面包屑
     * @param id 资源id
     * @return 面包屑列表
     */
    @Override
    public List<SysPermissionTreeDto> genCrumbs(Long id) {
        return noSupportCacheImpl.genCrumbs(id);
    }
}
