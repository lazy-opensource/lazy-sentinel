package com.lazy.sentinel.service;

import com.lazy.cheetah.common.tools.StringUtils;
import com.lazy.cheetah.core.service.impl.BaseServiceImpl;
import com.lazy.sentinel.api.ITokenCycleRuleService;
import com.lazy.sentinel.dao.ITokenCycleRuleRepository;
import com.lazy.sentinel.entity.TTokenCycleRuleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author laizhiyuan
 * @date 2018/1/5.
 * <p>token生命周期规则服务层实现类</p>
 */
@Service
@SuppressWarnings("unchecked")
@Transactional(rollbackFor = {Exception.class, RuntimeException.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class TokenCycleRuleServiceImpl extends BaseServiceImpl<TTokenCycleRuleEntity, Long> implements ITokenCycleRuleService {

    /**
     * 数据层接口
     */
    private ITokenCycleRuleRepository repository;

    /**
     * 构造器
     * 注入方式可参考com.tcl.b2b.base.framework.service.impl.ExampleServiceImpl
     *
     * @param iTokenCycleRuleRepository 子类注入对应的数据层Bean；必须
     */
    @Autowired
    public TokenCycleRuleServiceImpl(ITokenCycleRuleRepository iTokenCycleRuleRepository) {
        this.repository = iTokenCycleRuleRepository;
        this.repository = iTokenCycleRuleRepository;
    }

    /**
     * 通过有效状态查数据集合
     * @param validStatus 有效状态
     * @return token生命周期配置信息实体对象
     */
    @Override
    public TTokenCycleRuleEntity findByValidStatus(String validStatus){
        if (StringUtils.isBlank(validStatus)){
            return null;
        }
        List<TTokenCycleRuleEntity> tokenCycleRuleEntityList = repository.findByValidStatus(validStatus);
        if (tokenCycleRuleEntityList == null || tokenCycleRuleEntityList.isEmpty()){
            return null;
        }
        return tokenCycleRuleEntityList.get(0);
    }
}
