package com.lazy.sentinel.api;


import com.lazy.cheetah.core.dto.ServiceResultDto;
import com.lazy.sentinel.bo.AppInfoBo;
import com.lazy.sentinel.bo.ClientInfoBo;

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
 *     这里定义四种模式共有接口
 * </p>
 */
public interface IOauthService {

    /**
     *
     * 执行OAuth 对应模式的流程
     * @param clientInfoBo 客户端信息业务对象
     * @return 返回一个封装好的 ServiceResultDto对象
     * @throws Exception 抛出所有未捕获异常
     */
    ServiceResultDto executeOAuthModelFlow(ClientInfoBo clientInfoBo) throws Exception;

    /**
     * 验证token是否过期
     * @param appInfoBo 封装应用端信息业务对象
     * @return 如果检查不通过则返回一个封装好的 ServiceResultDto对象，如果检查通过返回Null
     * @throws Exception 抛出所有异常
     */
    ServiceResultDto validateTokenExpires(AppInfoBo appInfoBo) throws Exception;


}
