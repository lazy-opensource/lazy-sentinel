package com.lazy.sentinel.permission.service;

import com.lazy.sentinel.permission.api.entity.TPermissionsUserRoleRelEntity;
import com.lazy.sentinel.permission.dao.IPermissionsUserRoleRelRepository;
import com.lazy.sentinel.permission.api.IPermissionsUserRoleRelService;
import com.lazy.cheetah.core.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>系统权限-用户和角色多对多关联关系原子服务实现类</p>
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
public class PermissionsUserRoleRelServiceImpl extends BaseServiceImpl<TPermissionsUserRoleRelEntity, Long> implements IPermissionsUserRoleRelService {

    /**
     * 数据仓库接口
     */
    private IPermissionsUserRoleRelRepository repository;

    /**
     * 构造器注入数据仓库接口
     * @param repository 数据仓库接口
     */
    @Autowired
    public PermissionsUserRoleRelServiceImpl(IPermissionsUserRoleRelRepository repository) {
        super();
        this.setRepository(repository);
        this.repository = repository;
    }

    /**
     * 获取某个角色关联的用户列表
     * @param rId 角色id
     * @return 关联关系列表
     */
    @Override
    public List<TPermissionsUserRoleRelEntity> findAllByRid(Long rId) {
        return repository.findAllByRid(rId);
    }

    /**
     * 获取某个用户关联的角色列表
     * @param uId 用户id
     * @return 关联关系列表
     */
    @Override
    public List<TPermissionsUserRoleRelEntity> findAllByUid(Long uId){
        return repository.findAllByUid(uId);
    }

    /**
     * 获取某些角色关联的用户列表
     * @param rIds 角色id列表
     * @return 关联关系列表
     */
    @Override
    public List<TPermissionsUserRoleRelEntity> findAllByRidIn(Iterable<Long> rIds) {
        return repository.findAllByRidIn(rIds);
    }

    /**
     * 获取某些用户关联的角色列表
     * @param uIds 用户id列表
     * @return 关联关系列表
     */
    @Override
    public List<TPermissionsUserRoleRelEntity> findAllByUidIn(Iterable<Long> uIds) {
        return repository.findAllByUidIn(uIds);
    }
}
