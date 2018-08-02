package com.lazy.sentinel.dao;


import com.lazy.cheetah.core.repository.IBaseRepository;
import com.lazy.sentinel.entity.TTokenCycleRuleEntity;

import java.util.List;

/**
 * @author laizhiyuan
 * @date 2018/1/5.
 *
 * <p>token生命周期规则数据层接口</p>
 */
public interface ITokenCycleRuleRepository extends IBaseRepository<TTokenCycleRuleEntity, Long> {

    /**
     * 通过有效状态查数据集合
     * @param validStatus 有效状态
     * @return token生命周期配置信息实体对象集合
     */
    List<TTokenCycleRuleEntity> findByValidStatus(String validStatus);
}
