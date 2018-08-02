package com.lazy.sentinel.helper;


import com.lazy.cheetah.core.dto.ServiceResultDto;
import com.lazy.cheetah.core.dto.WebApiResultDto;
import com.lazy.sentinel.bo.ClientInfoBo;
import com.lazy.sentinel.common.enums.CodeEnum;
import com.lazy.sentinel.dto.TokenDataDto;

/**
 * @author laizhiyuan
 * @date 2018/1/6.
 * <p>http响应工具类</p>
 */
@SuppressWarnings("unchecked")
public abstract class OauthResponseHelper {

    /**
     * 构建 OAuth 系统服务错误响应实体
     * @return ServiceResultDto实体
     * @throws Exception 异常
     */
    public static ServiceResultDto buildServiceOAuthSystemErrorResonse() {
        return new ServiceResultDto(CodeEnum.OAUTH_SYSTEM_ERROR.getValue(), CodeEnum.OAUTH_SYSTEM_ERROR.getDesc());
    }

    /**
     * 构建 OAuth 系统服务错误响应实体
     * @return WebResultDto实体
     * @throws Exception 异常
     */
    public static WebApiResultDto buildWebOAuthSystemErrorResonse() {
        return new WebApiResultDto(CodeEnum.OAUTH_SYSTEM_ERROR.getValue(), CodeEnum.OAUTH_SYSTEM_ERROR.getDesc());
    }

    /**
     * 构建 OAuth 不支持的模式类型错误响应实体
     * @return ServiceResultDto实体
     * @throws Exception 异常
     */
    public static ServiceResultDto buildNotSupportGrantTypeResonse() {
        return new ServiceResultDto(CodeEnum.GRANT_TYPE_NOT_SUPPER.getValue(), CodeEnum.GRANT_TYPE_NOT_SUPPER.getDesc());
    }

    /**
     * 构建 OAuth 成功响应实体
     * @param clientInfoBo 完成后的业务对象
     * @return ServiceResultDto实体
     * @throws Exception 异常
     */
    public static ServiceResultDto buildOAuthSuccessRespose(ClientInfoBo clientInfoBo) throws Exception{
        TokenDataDto dataDto = new TokenDataDto(
                clientInfoBo.getToken(),clientInfoBo.getTokenCycleRuleBo().getTokenExpires(),
                clientInfoBo.getRefreshToken(), clientInfoBo.getStatus()
        );
        return new ServiceResultDto(CodeEnum.SUCCESS.getValue(), CodeEnum.SUCCESS.getDesc(), dataDto);
    }

}
