package com.lazy.sentinel.service.cache;

import com.lazy.cheetah.core.dto.ServiceResultDto;
import com.lazy.sentinel.bo.AppInfoBo;
import com.lazy.sentinel.bo.ClientInfoBo;
import com.lazy.sentinel.helper.OauthResponseHelper;
import com.lazy.sentinel.service.AbstractOauthService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author laizhiyuan
 * @date 2018/1/5.
 * @date 2018/1/4.
 *  <p>
 *     OAuth 2.0定义了四种授权方式。
 *     授权码模式（authorization code）
 *     简化模式（implicit）
 *     密码模式（resource owner password credentials）
 *     客户端模式（client credentials）
 *
 *     这里定义刷新Token模式接口实现类
 * </p>
 */
@Service("oAuthRefreshTokenRedisCacheServiceImpl")
@SuppressWarnings("unchecked")
@Transactional(rollbackFor = {Exception.class, RuntimeException.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class OauthRefreshTokenRedisCacheServiceImpl extends AbstractOauthService {


    /**
     * 执行当前模式 OAuth流程
     * @param clientInfoBo 客户端信息业务对象
     * @return 返回一个封装好的 ServiceResultDto对象
     * @throws Exception 抛出所有异常
     */
    @Override
    public ServiceResultDto executeOAuthModelFlow(ClientInfoBo clientInfoBo) throws Exception {
        //检查refresh_token是否过期以及客户端是否合法
        this.getMetadataByCheckRefreshToken(clientInfoBo);
        //从配置表获取token生命周期规则，设置到业务对象中
        this.setTokenCycleRule(clientInfoBo);
        //如果在生命周期内没有超过刷新次数或超过一个生命周期，则重置或初始化生命周期参数
        this.initTokenCycleByCheck(clientInfoBo);
        //刷新token
        this.refreshToken(clientInfoBo);
        return OauthResponseHelper.buildOAuthSuccessRespose(clientInfoBo);
    }

    /**
     * 验证token是否过期
     * @param appInfoBo 封装应用信息业务对象
     * @return 如果检查不通过则返回一个封装好的 ServiceResultDto对象，如果检查通过返回Null
     * @throws Exception 抛出所有未捕获异常
     */
    @Override
    public ServiceResultDto validateTokenExpires(AppInfoBo appInfoBo) throws Exception {
        return null;
    }
}
