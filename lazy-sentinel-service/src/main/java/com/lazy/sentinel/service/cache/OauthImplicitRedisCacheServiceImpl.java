package com.lazy.sentinel.service.cache;


import com.lazy.cheetah.core.dto.ServiceResultDto;
import com.lazy.sentinel.bo.AppInfoBo;
import com.lazy.sentinel.bo.ClientInfoBo;
import com.lazy.sentinel.service.AbstractOauthService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author laizhiyuan
 * @date 2018/1/4.
 *  <p>
 *     OAuth 2.0定义了四种授权方式。
 *     授权码模式（authorization code）
 *     简化模式（implicit）
 *     密码模式（resource owner password credentials）
 *     客户端模式（client credentials）
 *
 *     这里定义简化模式实现
 * </p>
 */
@Service("oAuthImplicitRedisCacheServiceImpl")
@SuppressWarnings("unchecked")
@Transactional(rollbackFor = {Exception.class, RuntimeException.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class OauthImplicitRedisCacheServiceImpl extends AbstractOauthService {

    @Override
    public ServiceResultDto executeOAuthModelFlow(ClientInfoBo clientInfoBo) throws Exception {
        return null;
    }

    @Override
    public ServiceResultDto validateTokenExpires(AppInfoBo appInfoBo) throws Exception {
        return null;
    }
}
