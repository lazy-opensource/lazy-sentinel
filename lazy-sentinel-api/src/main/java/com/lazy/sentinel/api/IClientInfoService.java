package com.lazy.sentinel.api;


import com.lazy.cheetah.core.service.IBaseService;
import com.lazy.sentinel.entity.TClientInfoEntity;

/**
 * @author laizhiyuan
 * @date 2018/1/3.
 * <p>客户端信息服务层接口</p>
 */
public interface IClientInfoService extends IBaseService<TClientInfoEntity, Long> {

    /**
     * 通过client_id查一条数据
     *
     * @param cliId 颁发给对接客户端id 必须
     * @return 客户端信息实体对象
     */
    TClientInfoEntity findByCliId(String cliId);
}
