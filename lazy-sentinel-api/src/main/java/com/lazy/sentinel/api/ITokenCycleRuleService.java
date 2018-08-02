package com.lazy.sentinel.api;


import com.lazy.cheetah.core.service.IBaseService;
import com.lazy.sentinel.entity.TTokenCycleRuleEntity;

/**
 * @author laizhiyuan
 * @date 2018/1/5.
 * <p>token生命周期规则服务层接口</p>
 */
public interface ITokenCycleRuleService extends IBaseService<TTokenCycleRuleEntity, Long> {

    /**
     * 通过有效状态查数据集合
     * @param validStatus 有效状态
     * @return token生命周期配置信息实体对象
     */
    TTokenCycleRuleEntity findByValidStatus(String validStatus);
}
