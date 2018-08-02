package com.lazy.sentinel.service;

import org.springframework.transaction.annotation.Transactional;
import com.lazy.cheetah.core.service.impl.BaseServiceImpl;
import com.lazy.sentinel.api.IClientIpInfoService;
import com.lazy.sentinel.dao.IClientIpInfoRepository;
import com.lazy.sentinel.entity.TClientIpInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>客户端ip信息服务层实现</p>
 *
 * @author laizhiyuan
 * @date 2018/3/21.
 */
public class ClientIpInfoServiceImpl extends BaseServiceImpl<TClientIpInfoEntity, Long> implements IClientIpInfoService {

    /**
     * 数据层接口
     */
    private IClientIpInfoRepository repository;

    /**
     * 构造器
     *
     * @param repository 注入对应的数据层Bean；必须
     */
    @Autowired
    public ClientIpInfoServiceImpl(IClientIpInfoRepository repository) {
        this.setRepository(repository);
        this.repository = repository;
    }

    /**
     * 通过客户端id查对应的ip列表
     * @param cliId 客户端id
     * @return ip列表
     */
    @Override
    public List<TClientIpInfoEntity> findAllByCliId(String cliId) {
        return repository.findAllByCliId(cliId);
    }
}
