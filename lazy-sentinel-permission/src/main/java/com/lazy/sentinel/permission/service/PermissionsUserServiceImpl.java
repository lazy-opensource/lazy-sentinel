package com.lazy.sentinel.permission.service;


import com.lazy.sentinel.permission.api.entity.TPermissionsUserEntity;
import com.lazy.sentinel.permission.dao.IPermissionsUserRepository;
import com.lazy.sentinel.permission.api.IPermissionsUserService;
import com.lazy.cheetah.common.tools.AssertUtils;
import com.lazy.cheetah.core.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PermissionsUserServiceImpl extends BaseServiceImpl<TPermissionsUserEntity, Long> implements IPermissionsUserService {

    /**
     * 数据仓库接口
     */
    private IPermissionsUserRepository repository;

    /**
     * 构造器注入数据仓库接口
     * @param repository 数据仓库接口
     */
    @Autowired
    public PermissionsUserServiceImpl(IPermissionsUserRepository repository) {
        super();
        this.setRepository(repository);
        this.repository = repository;
    }

    /**
     * 根据登录名称获取一条数据
     * @param loginName 登录名
     * @return 超级用户实体
     */
    @Override
    public TPermissionsUserEntity findOneByLoginName(String loginName) {
        AssertUtils.isBlank(loginName, "param loginName is blank");
        return this.repository.findOneByLoginName(loginName);
    }
}
