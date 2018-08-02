package com.lazy.sentinel.service;

import org.springframework.transaction.annotation.Transactional;
import com.lazy.cheetah.common.tools.AssertUtils;
import com.lazy.cheetah.core.service.impl.BaseServiceImpl;
import com.lazy.sentinel.api.IClientResourceRelService;
import com.lazy.sentinel.dao.IClientResourceRelRepository;
import com.lazy.sentinel.entity.TClientResourceRelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

/**
 * @author laizhiyuan
 * @date 2018/1/3.
 * <p>客户端信息关联资源服务层实现类</p>
 */
@Service
@SuppressWarnings("unchecked")
@Transactional(rollbackFor = {Exception.class, RuntimeException.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class ClientResourceRelServiceImpl extends BaseServiceImpl<TClientResourceRelEntity, Long> implements IClientResourceRelService {

    /**
     * 数据层接口
     */
    private IClientResourceRelRepository repository;

    /**
     * 构造器
     * 注入方式可参考com.tcl.b2b.base.framework.service.impl.ExampleServiceImpl
     *
     * @param repository 子类注入对应的数据层Bean；必须
     */
    @Autowired
    public ClientResourceRelServiceImpl(IClientResourceRelRepository repository) {
        this.setRepository(repository);
        this.repository = repository;
    }

    /**
     * 通过客户端信息主键 + 资源主键查客户端资源关联表 不支持缓存
     * @param cliId t_client_info表主键 必须
     * @param resId t_resource表主键 必须
     * @return 关联关系实体对象
     */
    @Override
    public TClientResourceRelEntity findByCliIdAndResId(Long cliId, Long resId) {
        AssertUtils.isNull(cliId, "param cliId is null");
        AssertUtils.isNull(resId, "param resId is null");
        return this.repository.findByCliIdAndResId(cliId, resId);
    }
}
