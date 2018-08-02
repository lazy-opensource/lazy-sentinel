package com.hcl.common.permissioncenter.service;

import com.hcl.common.permissioncenter.api.IPermissionsResRoleRelService;
import com.hcl.common.permissioncenter.api.entity.TPermissionsResRoleRelEntity;
import com.hcl.common.permissioncenter.dao.IPermissionsResRoleRelRepository;
import com.lazy.cheetah.core.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>系统权限-资源和角色多对多关联关系原子服务实现类</p>
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
public class PermissionsResRoleRelServiceImpl extends BaseServiceImpl<TPermissionsResRoleRelEntity, Long> implements IPermissionsResRoleRelService {

    /**
     * 数据仓库接口
     */
    private IPermissionsResRoleRelRepository repository;

    /**
     * 构造器注入数据仓库接口
     * @param repository 数据仓库接口
     */
    @Autowired
    public PermissionsResRoleRelServiceImpl(IPermissionsResRoleRelRepository repository) {
        super();
        this.setRepository(repository);
        this.repository = repository;
    }

    /**
     * 获取某个角色关联的资源列表
     * @param roleId 角色id
     * @return 关联关系列表
     */
    @Override
    public List<TPermissionsResRoleRelEntity> findAllByRoleId(Long roleId) {
        return repository.findAllByRoleId(roleId);
    }

    /**
     * 获取某个资源关联的角色列表
     * @param resId 资源id
     * @return 关联关系列表
     */
    @Override
    public List<TPermissionsResRoleRelEntity> findAllByResId(Long resId) {
        return repository.findAllByResId(resId);
    }

    /**
     * 获取某些角色关联的资源列表
     * @param roleIds 角色id列表
     * @return 关联关系列表
     */
    @Override
    public List<TPermissionsResRoleRelEntity> findAllByRoleIdIn(Iterable<Long> roleIds){
        return repository.findAllByRoleIdIn(roleIds);
    }

    /**
     * 获取某些资源关联的角色列表
     * @param resIds 资源id列表
     * @return 关联关系列表
     */
    @Override
    public List<TPermissionsResRoleRelEntity> findAllByResIdIn(Iterable<Long> resIds) {
        return repository.findAllByResIdIn(resIds);
    }
}
