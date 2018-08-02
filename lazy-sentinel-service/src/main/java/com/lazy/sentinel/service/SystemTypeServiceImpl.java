package com.lazy.sentinel.service;

import com.lazy.cheetah.common.tools.StringUtils;
import com.lazy.cheetah.core.service.impl.BaseServiceImpl;
import com.lazy.sentinel.api.ISystemTypeService;
import com.lazy.sentinel.dao.ISystemTypeRepository;
import com.lazy.sentinel.entity.TSystemTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author laizhiyuan
 * @date 2018/1/3.
 * <p>对接方系统类型服务层实现类</p>
 */
@Service
@SuppressWarnings("unchecked")
@Transactional(rollbackFor = {Exception.class, RuntimeException.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class SystemTypeServiceImpl extends BaseServiceImpl<TSystemTypeEntity, Long> implements ISystemTypeService {

    /**
     * 数据层接口
     */
    private ISystemTypeRepository repository;

    /**
     * 构造器
     * 注入方式可参考com.tcl.b2b.base.framework.service.impl.ExampleServiceImpl
     *
     * @param iSystemTypeRepository 子类注入对应的数据层Bean；必须
     */
    @Autowired
    public SystemTypeServiceImpl(ISystemTypeRepository iSystemTypeRepository) {
        this.setRepository(iSystemTypeRepository);
        this.repository = iSystemTypeRepository;
    }

    /**
     * 通过系统类型编码查一条数据
     * @param sysTypeCode 系统编码
     * @return 系统类型信息实体对象
     */
    @Override
    public TSystemTypeEntity findBySysTypeCode(String sysTypeCode){
        if (StringUtils.isBlank(sysTypeCode)){
            return null;
        }
        return this.repository.findBySysTypeCode(sysTypeCode);
    }
}
