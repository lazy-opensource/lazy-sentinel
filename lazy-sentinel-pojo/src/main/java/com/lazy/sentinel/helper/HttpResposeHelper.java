package com.lazy.sentinel.helper;


import com.lazy.sentinel.bo.ClientInfoBo;
import com.lazy.sentinel.common.enums.CodeEnum;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;

/**
 * @author laizhiyuan
 * @date 2018/1/6.
 * <p>http响应工具类</p>
 */
@SuppressWarnings("unchecked")
public class HttpResposeHelper {

    /**
     * 构建 OAuth 错误响应实体
     * @param httpStatusCode http状态码
     * @param error 错误枚举
     * @return 响应实体
     * @throws Exception 异常
     */
    public static ResponseEntity buildOAuthErrorResonse(int httpStatusCode, CodeEnum error) throws Exception{
        OAuthResponse response = OAuthASResponse
                .errorResponse(httpStatusCode)
                .setError(error.getValue())
                .setErrorDescription(error.getDesc())
                .buildJSONMessage();
        return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
    }

    /**
     * 构建 OAuth 成功响应实体
     * @param clientInfoBo 完成后的业务对象
     * @return 响应实体
     * @throws Exception 异常
     */
    public static ResponseEntity buildOAuthSuccessResonse(ClientInfoBo clientInfoBo) throws Exception{
        OAuthResponse response = OAuthASResponse
                .tokenResponse(HttpServletResponse.SC_OK)
                .setAccessToken(clientInfoBo.getToken())
                .setExpiresIn(String.valueOf(clientInfoBo.getTokenCycleRuleBo().getTokenExpires()))
                .setRefreshToken(clientInfoBo.getRefreshToken())
                .setParam("status", clientInfoBo.getStatus())
                .buildJSONMessage();
        return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
    }
}
