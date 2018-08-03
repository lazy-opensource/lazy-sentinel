package com.lazy.sentinel.permission.service;

import com.lazy.sentinel.permission.api.IPermissionsUserGroupRelService;
import com.lazy.sentinel.permission.api.entity.TPermissionsUserGroupRelEntity;
import com.lazy.sentinel.permission.dao.IPermissionsUserGroupRelRepository;
import com.lazy.cheetah.core.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>系统权限-用户和用户组原子服务实现类</p>
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
public class PermissionsUserGroupRelServiceImpl extends BaseServiceImpl<TPermissionsUserGroupRelEntity, Long> implements IPermissionsUserGroupRelService {

    /**
     * 数据仓库接口
     */
    private IPermissionsUserGroupRelRepository repository;

    /**
     * 构造器注入数据仓库接口
     * @param repository 数据仓库接口
     */
    @Autowired
    public PermissionsUserGroupRelServiceImpl(IPermissionsUserGroupRelRepository repository) {
        super();
        this.setRepository(repository);
        this.repository = repository;
    }

    /**
     * 获取某个用户关联的用户组列表
     * @param uId 用户id
     * @return 关联关系列表
     */
    @Override
    public List<TPermissionsUserGroupRelEntity> findAllByUid(Long uId) {
        return repository.findAllByUid(uId);
    }

    /**
     * 获取某个用户组关联的用户列表
     * @param gId 用户组id
     * @return 关联关系列表
     */
    @Override
    public List<TPermissionsUserGroupRelEntity> findAllByGid(Long gId) {
        return repository.findAllByGid(gId);
    }

    /**
     * 获取某些用户关联的用户组列表
     * @param uIds 用户id列表
     * @return 关联关系列表
     */
    @Override
    public List<TPermissionsUserGroupRelEntity> findAllByUidIn(Iterable<Long> uIds) {
        return repository.findAllByUidIn(uIds);
    }

    /**
     * 获取某些用户组关联的用户列表
     * @param gIds 用户组id列表
     * @return 关联关系列表
     */
    @Override
    public List<TPermissionsUserGroupRelEntity> findAllByGidIn(Iterable<Long> gIds) {
        return repository.findAllByGidIn(gIds);
    }
}
