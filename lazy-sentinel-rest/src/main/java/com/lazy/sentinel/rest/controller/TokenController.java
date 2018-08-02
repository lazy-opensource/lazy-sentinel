package com.lazy.sentinel.rest.controller;


import com.lazy.cheetah.core.dto.WebApiResultDto;
import com.lazy.cheetah.core.web.BaseController;
import com.lazy.sentinel.dto.TokenParamDto;
import com.lazy.sentinel.rest.handler.TokenControllerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Version: v1
 * Produces: application/json;charset=UTF-8
 *
 * @author laizhiyuan
 * @date 2018/1/4.
 * <p>OAuth2.0 token核心控制器</p>
 */
@RestController
@RequestMapping("/oauth/token")
public class TokenController extends BaseController {

    @Autowired
    private TokenControllerHandler handler;

    /**
     *  获取、刷新token路由
     * 请求示例
     * 获取token：
     * http://localhost:8688/v1/b2bapi/oauth/token/access
     {
     "client_id": "a7410047a02b4ac1a215a549cd2454a5",
     "client_secret": "d519d7f0f7044258aa912c204511a203",
     "grant_type": "client_credentials",
     "status": "ok
     }
     * 刷新token：
     * http://localhost:8688/v1/b2bapi/oauth/token/access
     {
     "grant_type": "refresh_token",
     "refresh_token": "c8416c4f1bb973b765adafe3a650c515",
     "status": "ok"
     }
     *
     * @param request servlet请求对象
     * @param tokenParamDto 参数对象
     * @return com.tcl.b2b.base.framework.dto.WebResultDto
     */
    @PostMapping(value = "/access")
    public WebApiResultDto accessToken(HttpServletRequest request, @RequestBody TokenParamDto tokenParamDto) throws Exception {
        return handler.handlerAccessTokenRouter(request, tokenParamDto);
    }

    /**
     *  验证token
     *  请求示例：
     *  http://localhost/v1/b2bapi/oauth/token/verify
     {
     "grant_type": "client_credentials",
     "access_token": "a3c284536ab6013e3117b7e3512c6080",
     "resource_code": "QUERY.STOCK",
     "resource_owner": "SG-API",
     "client_ip": "127.0.0.1"
     }
     * @param request servlet请求对象
     * @param tokenParamDto 参数对象
     * @return com.tcl.b2b.base.framework.dto.WebResultDto
     */
    @PostMapping(value = "/verify")
    public WebApiResultDto verifyToken(HttpServletRequest request, @RequestBody TokenParamDto tokenParamDto) throws Exception {
        return handler.handlerTokenVerifyRouter(request, tokenParamDto);
    }

}
