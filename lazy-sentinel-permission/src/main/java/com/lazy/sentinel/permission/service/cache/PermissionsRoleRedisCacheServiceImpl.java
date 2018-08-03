package com.lazy.sentinel.permission.service.cache;

import com.lazy.cheetah.common.tools.AssertUtils;
import com.lazy.cheetah.core.enums.CacheKeyRuleEnum;
import com.lazy.cheetah.core.manager.CacheKeyRuleManager;
import com.lazy.cheetah.core.service.impl.cache.BaseRedisCacheServiceImpl;
import com.lazy.sentinel.permission.api.IPermissionsRoleService;
import com.lazy.sentinel.permission.api.dto.SysPermissionTreeDto;
import com.lazy.sentinel.permission.api.entity.TPermissionsRoleEntity;
import com.lazy.sentinel.permission.dao.IPermissionsRoleRepository;
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
 * <p>系统权限-角色原子服务实现类</p>
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
public class PermissionsRoleRedisCacheServiceImpl extends BaseRedisCacheServiceImpl<TPermissionsRoleEntity, Long> implements IPermissionsRoleService {

    /**
     * 数据仓库接口
     */
    private IPermissionsRoleRepository repository;
    /**
     * 注入不支持缓存服务实现类
     */
    private IPermissionsRoleService noSupportCacheImpl;
    /**
     * 用户组树缓存key
     */
    private static final String T_PERMISSIONS_ROLE_TREE_LIST = "T_PERMISSIONS_ROLE_TREE_LIST";

    /**
     * 构造器注入数据仓库接口
     * @param repository 数据仓库接口
     */
    @Autowired
    public PermissionsRoleRedisCacheServiceImpl(IPermissionsRoleRepository repository) {
        super();
        this.setCacheKeyPrefix("T_PERMISSIONS_ROLE_");
        this.addCacheKeyRuleManager(CacheKeyRuleManager.newInstance().addKey(T_PERMISSIONS_ROLE_TREE_LIST).setRule(CacheKeyRuleEnum.FIXED));
        this.setRepository(repository);
        this.repository = repository;
        this.noSupportCacheImpl = (IPermissionsRoleService) this.getNoSupportCacheImpl();
    }

    /**
     * 通过角色编码查数据
     * @param roleCode 角色编码
     * @return 角色实体对象
     */
    @Override
    public TPermissionsRoleEntity findByRoleCode(String roleCode) {
        AssertUtils.isBlank(roleCode, "param roleCode is blank");
        return repository.findByRoleCode(roleCode);
    }

    /**
     * 获取系统角色树列表 支持缓存
     *
     * @return 树列表
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<SysPermissionTreeDto> tree() {
        List<SysPermissionTreeDto> treeDtoList = (List<SysPermissionTreeDto>) commonRedisTemplateOps.get(T_PERMISSIONS_ROLE_TREE_LIST);
        if (treeDtoList != null){
            commonRedisTemplate.expire(T_PERMISSIONS_ROLE_TREE_LIST, cacheExpiration, TimeUnit.SECONDS);
            return treeDtoList;
        }
        treeDtoList = noSupportCacheImpl.tree();
        if (treeDtoList == null || treeDtoList.isEmpty()){
            return treeDtoList;
        }else {
            commonRedisTemplateOps.set(T_PERMISSIONS_ROLE_TREE_LIST, treeDtoList, cacheExpiration, TimeUnit.SECONDS);
            return treeDtoList;
        }
    }
}
