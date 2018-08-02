package com.lazy.sentinel.rest.handler;

import com.lazy.cheetah.common.annotation.ControllerHandler;
import com.lazy.cheetah.common.tools.SpringUtils;
import com.lazy.cheetah.common.tools.StringUtils;
import com.lazy.cheetah.core.dto.ServiceResultDto;
import com.lazy.cheetah.core.dto.SimpleResCodeDto;
import com.lazy.cheetah.core.dto.WebApiResultDto;
import com.lazy.cheetah.core.enums.SimpleResCodeEnum;
import com.lazy.cheetah.core.exception.RequestParamIllegalException;
import com.lazy.cheetah.core.exception.SystemServerException;
import com.lazy.cheetah.core.helper.RequestParamHelper;
import com.lazy.cheetah.core.web.BaseController;
import com.lazy.sentinel.api.IOauthService;
import com.lazy.sentinel.bo.AppInfoBo;
import com.lazy.sentinel.bo.ClientInfoBo;
import com.lazy.sentinel.common.enums.CodeEnum;
import com.lazy.sentinel.dto.TokenParamDto;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import javax.servlet.http.HttpServletRequest;

/**
 * @author laizhiyuan
 * @date 2018/1/16.
 * <p>TokenController处理器</p>
 */
@ControllerHandler
public class TokenControllerHandler extends BaseController {

    /**
     * 处理获取、刷新token控制器路由
     * @param tokenParamDto 参数对象
     * @param request servlet请求对象
     * @return com.tcl.b2b.base.framework.dto.WebResultDto
     */
    public WebApiResultDto handlerAccessTokenRouter(HttpServletRequest request, TokenParamDto tokenParamDto) throws Exception {
        //设置请求客户端真实ip
        tokenParamDto.setCusRelIp(RequestParamHelper.getCustomerRelIp(request));
        this.initGetTokenRequestParamByCheck(tokenParamDto);
        ClientInfoBo clientInfoBo = this.toClientInfo(tokenParamDto);
        //根据不同的模式获取不同的实现服务类
        IOauthService ioAuthService = this.getBeanByGrantType(clientInfoBo.getGrantType());
        //执行OAuth对应模式流程
        ServiceResultDto serviceResultDto = ioAuthService.executeOAuthModelFlow(clientInfoBo);
        return new WebApiResultDto(serviceResultDto);
    }

    /**
     * 转换为ClientInfoBo对象
     * @param tokenParamDto 参数对象
     * @return ClientInfoBo对象
     */
    private ClientInfoBo toClientInfo(TokenParamDto tokenParamDto){
        ClientInfoBo clientInfoBo = new ClientInfoBo();
        //设置参数到业务类
        clientInfoBo.setKey(tokenParamDto.getClient_id());
        clientInfoBo.setSecret(tokenParamDto.getClient_secret());
        clientInfoBo.setGrantType(tokenParamDto.getGrant_type());
        clientInfoBo.setRefreshToken(tokenParamDto.getRefresh_token());
        clientInfoBo.setStatus(tokenParamDto.getStatus());
        clientInfoBo.setIp(tokenParamDto.getCusRelIp());
        return clientInfoBo;
    }

    /**
     * 转换为AppInfoBo对象
     * @param tokenParamDto 参数对象
     * @return AppInfoBo对象
     */
    private AppInfoBo toAppInfo(TokenParamDto tokenParamDto){
        AppInfoBo appInfoBo = new AppInfoBo();
        //将参数设置到业务对象
        appInfoBo.setGrantType(tokenParamDto.getGrant_type());
        appInfoBo.setToken(tokenParamDto.getAccess_token());
        appInfoBo.setCusRelIp(tokenParamDto.getCusRelIp());
        appInfoBo.setResourceCode(tokenParamDto.getResource_code());
        appInfoBo.setResourceOwner(tokenParamDto.getResource_owner());
        appInfoBo.setStatus(tokenParamDto.getStatus());
        appInfoBo.setClientIp(tokenParamDto.getClient_ip());
        return appInfoBo;
    }

    /**
     * 处理验证token控制器路由
     * @param tokenParamDto 参数对象
     * @param request servlet请求对象
     * @return com.tcl.b2b.base.framework.dto.WebResultDto
     */
    public WebApiResultDto handlerTokenVerifyRouter(HttpServletRequest request, TokenParamDto tokenParamDto) throws Exception {
        //设置请求客户端真实ip
        tokenParamDto.setCusRelIp(RequestParamHelper.getCustomerRelIp(request));
        this.initVerifyTokenRequestParamByCheck(tokenParamDto);
        AppInfoBo appInfoBo = this.toAppInfo(tokenParamDto);
        //根据不同的模式获取不同的实现服务类
        IOauthService ioAuthService = this.getBeanByGrantType(appInfoBo.getGrantType());
        //执行OAuth对应模式的验证token实现
        ServiceResultDto serviceResultDto = ioAuthService.validateTokenExpires(appInfoBo);
        return new WebApiResultDto(serviceResultDto);
    }

    /**
     * 检查获取token请求参数，检查通过后设置到业务对象中
     * @param tokenParamDto 客户端业务对象
     * @throws Exception 抛出所有未捕获异常
     */
    private void initGetTokenRequestParamByCheck(TokenParamDto tokenParamDto) throws Exception{
        if (tokenParamDto == null){
            throw new RequestParamIllegalException(new SimpleResCodeDto(SimpleResCodeEnum.BLANK_PARAM.getCode(), SimpleResCodeEnum.BLANK_PARAM.getDesc()));
        }
        if (StringUtils.isBlank(tokenParamDto.getCusRelIp())){
            throw new SystemServerException("客户端真实ip为空,请检查Nginx配置");
        }
        if (StringUtils.isBlank(tokenParamDto.getGrant_type())){
            throw new RequestParamIllegalException(new SimpleResCodeDto(CodeEnum.GRANT_TYPE_EMPTY.getValue(), CodeEnum.GRANT_TYPE_EMPTY.getDesc()));
        }
        if (!GrantType.REFRESH_TOKEN.toString().equals(tokenParamDto.getGrant_type())){
            if (StringUtils.isBlank(tokenParamDto.getClient_id())){
                throw new RequestParamIllegalException(new SimpleResCodeDto(CodeEnum.CLIENT_ID_EMPTY.getValue(), CodeEnum.CLIENT_ID_EMPTY.getDesc()));
            }
            if (StringUtils.isBlank(tokenParamDto.getClient_secret())){
                throw new RequestParamIllegalException(new SimpleResCodeDto(CodeEnum.CLIENT_SECRET_EMPTY.getValue(), CodeEnum.CLIENT_SECRET_EMPTY.getDesc()));
            }
        }
        if (!GrantType.CLIENT_CREDENTIALS.toString().equals(tokenParamDto.getGrant_type())
                && !GrantType.REFRESH_TOKEN.toString().equals(tokenParamDto.getGrant_type())){
            LOGGER.error(String.format("request grant_type param is [%s]", tokenParamDto.getGrant_type()));
            throw new RequestParamIllegalException(new SimpleResCodeDto(CodeEnum.GRANT_TYPE_NOT_SUPPER.getValue(), CodeEnum.GRANT_TYPE_NOT_SUPPER.getDesc()));
        }
        if (GrantType.REFRESH_TOKEN.toString().equals(tokenParamDto.getGrant_type())){
            if (StringUtils.isBlank(tokenParamDto.getRefresh_token())){
                throw new RequestParamIllegalException(new SimpleResCodeDto(CodeEnum.REFRESH_TOKEN_EMPTY.getValue(), CodeEnum.REFRESH_TOKEN_EMPTY.getDesc()));}
        }
    }

    /**
     * 检查验证token参数
     * @param tokenParamDto 参数对象
     * @throws Exception
     */
    private void initVerifyTokenRequestParamByCheck(TokenParamDto tokenParamDto) throws Exception{
        if (tokenParamDto == null){
            throw new RequestParamIllegalException(new SimpleResCodeDto(SimpleResCodeEnum.BLANK_PARAM.getCode(), SimpleResCodeEnum.BLANK_PARAM.getDesc()));
        }
        if (StringUtils.isBlank(tokenParamDto.getCusRelIp())){
            throw new SystemServerException("客户端真实ip为空,请检查Nginx配置");
        }
        if (StringUtils.isBlank(tokenParamDto.getGrant_type())){
            throw new RequestParamIllegalException(new SimpleResCodeDto(CodeEnum.GRANT_TYPE_EMPTY.getValue(), CodeEnum.GRANT_TYPE_EMPTY.getDesc()));
        }
        if (!GrantType.CLIENT_CREDENTIALS.toString().equals(tokenParamDto.getGrant_type())){
            LOGGER.error(String.format("request grant_type param is [%s]", tokenParamDto.getGrant_type()));
            throw new RequestParamIllegalException(new SimpleResCodeDto(CodeEnum.GRANT_TYPE_NOT_SUPPER.getValue(), CodeEnum.GRANT_TYPE_NOT_SUPPER.getDesc()));
        }
        if (StringUtils.isBlank(tokenParamDto.getAccess_token())){
            throw new RequestParamIllegalException(new SimpleResCodeDto(CodeEnum.ACCESS_TOKEN_EMPTY.getValue(), CodeEnum.ACCESS_TOKEN_EMPTY.getDesc()));
        }
        if (StringUtils.isBlank(tokenParamDto.getClient_ip())){
            throw new RequestParamIllegalException(new SimpleResCodeDto(CodeEnum.CLIENT_IP_EMPTY.getValue(), CodeEnum.CLIENT_IP_EMPTY.getDesc()));
        }
        if (StringUtils.isBlank(tokenParamDto.getResource_code())){
            throw new RequestParamIllegalException(new SimpleResCodeDto(CodeEnum.RESOURCE_CODE_EMPTY.getValue(), CodeEnum.RESOURCE_CODE_EMPTY.getDesc()));
        }
        if (StringUtils.isBlank(tokenParamDto.getResource_owner())){
            throw new RequestParamIllegalException(new SimpleResCodeDto(CodeEnum.RESOURCE_OWNER_EMPTY.getValue(), CodeEnum.RESOURCE_OWNER_EMPTY.getDesc()));
        }
    }

    /**
     * 根据不同的模式（grant_type）参数, 从Spring容器中找到对应的Bean
     * @param grantType 模式
     * @return com.tcl.b2bapi.oauth2.service.IOauthService实现类
     * @throws Exception 抛出所有未捕获异常
     */
    private IOauthService getBeanByGrantType(String grantType) throws Exception{
        LOGGER.info("======================> grant_type:" + grantType);
        String[] splitGrantType = grantType.split("_");
        String oauthServiceBeanPrefix = "oAuth";
        String oauthServiceBeanSuffix = "RedisCacheServiceImpl";
        StringBuilder oauthServiceBeanFullName = new StringBuilder(oauthServiceBeanPrefix)
                .append(StringUtils.toUpperCaseFirstOne(splitGrantType[0]));
        if (splitGrantType.length > 1){
            oauthServiceBeanFullName.append(StringUtils.toUpperCaseFirstOne(splitGrantType[1]));
        }
        oauthServiceBeanFullName.append(oauthServiceBeanSuffix);
        IOauthService ioAuthService = SpringUtils.getBean(oauthServiceBeanFullName.toString());
        return  ioAuthService;
    }
}
