package com.lazy.sentinel.service;

import com.lazy.cheetah.core.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.lazy.sentinel.api.IResourceTypeService;
import com.lazy.sentinel.dao.IResourceTypeRepository;
import com.lazy.sentinel.entity.TResourceTypeEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

/**
 * @author laizhiyuan
 * @date 2018/1/3.
 * <p>资源类型服务层实现类</p>
 */
@Service
@SuppressWarnings("unchecked")
@Transactional(rollbackFor = {Exception.class, RuntimeException.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class ResourceTypeServiceImpl extends BaseServiceImpl<TResourceTypeEntity, Long> implements IResourceTypeService {

    /**
     * 数据层接口
     */
    private IResourceTypeRepository repository;

    /**
     * 构造器
     * 注入方式可参考com.tcl.b2b.base.framework.service.impl.ExampleServiceImpl
     *
     * @param iResourceTypeRepository 子类注入对应的数据层Bean；必须
     */
    @Autowired
    public ResourceTypeServiceImpl(IResourceTypeRepository iResourceTypeRepository) {
        this.setRepository(iResourceTypeRepository);
        this.repository = iResourceTypeRepository;
    }
}
