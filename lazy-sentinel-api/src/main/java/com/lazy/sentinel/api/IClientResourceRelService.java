package com.lazy.sentinel.api;


import com.lazy.cheetah.core.service.IBaseService;
import com.lazy.sentinel.entity.TClientResourceRelEntity;

/**
 * @author laizhiyuan
 * @date 2018/1/3.
 * <p>客户端信息关联资源服务层接口</p>
 */
public interface IClientResourceRelService extends IBaseService<TClientResourceRelEntity, Long> {

    /**
     * 通过客户端信息主键 + 资源主键查客户端资源关联表
     * @param cliId t_client_info表主键 必须
     * @param resId t_resource表主键 必须
     * @return 关联关系实体对象
     */
    TClientResourceRelEntity findByCliIdAndResId(Long cliId, Long resId);
}
