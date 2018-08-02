package com.lazy.sentinel.service;

import org.springframework.transaction.annotation.Transactional;
import com.lazy.cheetah.common.tools.AssertUtils;
import com.lazy.cheetah.core.service.impl.BaseServiceImpl;
import com.lazy.sentinel.api.IResourceService;
import com.lazy.sentinel.dao.IResourceRepository;
import com.lazy.sentinel.entity.TResourceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

/**
 * @author laizhiyuan
 * @date 2018/1/3.
 * <p>资源服务层实现类</p>
 */
@Service
@SuppressWarnings("unchecked")
@Transactional(rollbackFor = {Exception.class, RuntimeException.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class ResourceServiceImpl extends BaseServiceImpl<TResourceEntity, Long> implements IResourceService {

    /**
     * 数据层接口
     */
    private IResourceRepository repository;

    /**
     * 构造器
     * 注入方式可参考com.tcl.b2b.base.framework.service.impl.ExampleServiceImpl
     *
     * @param iResourcesRepository 子类注入对应的数据层Bean；必须
     */
    @Autowired
    public ResourceServiceImpl(IResourceRepository iResourcesRepository) {
        this.setRepository(iResourcesRepository);
        this.repository = iResourcesRepository;
    }

    /**
     * 通过资源编码和所属系统类型查找 不支持缓存
     * @param resCode 资源编码
     * @param ownSysId 资源归属系统表主键
     * @return 资源实体对象
     */
    @Override
    public TResourceEntity findByResCodeAndOwnSysId(String resCode, Long ownSysId) {
        AssertUtils.isNull(resCode, "param resCode is null");
        AssertUtils.isNull(ownSysId, "param ownSysId is null");
        return this.repository.findByResCodeAndOwnSysId(resCode, ownSysId);
    }
}
