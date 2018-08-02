package com.lazy.sentinel.service.cache;

import com.lazy.sentinel.helper.OauthResponseHelper;
import org.springframework.transaction.annotation.Transactional;
import com.lazy.cheetah.common.tools.AssertUtils;
import com.lazy.cheetah.core.dto.ServiceResultDto;
import com.lazy.cheetah.core.dto.SimpleResCodeDto;
import com.lazy.sentinel.api.IAppInfoService;
import com.lazy.sentinel.api.IClientResourceRelService;
import com.lazy.sentinel.api.IResourceService;
import com.lazy.sentinel.api.ISystemTypeService;
import com.lazy.sentinel.bo.AppInfoBo;
import com.lazy.sentinel.bo.ClientInfoBo;
import com.lazy.sentinel.common.enums.CodeEnum;
import com.lazy.sentinel.common.exception.VerifyTokenException;
import com.lazy.sentinel.dto.CacheClientInfoDto;
import com.lazy.sentinel.entity.TAppInfoEntity;
import com.lazy.sentinel.entity.TClientResourceRelEntity;
import com.lazy.sentinel.entity.TResourceEntity;
import com.lazy.sentinel.entity.TSystemTypeEntity;
import com.lazy.sentinel.helper.TokenHelper;
import com.lazy.sentinel.service.AbstractOauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

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
 *     这里实现客户端模式缓存实现
 * </p>
 */
@Service("oAuthClientCredentialsRedisCacheServiceImpl")
@SuppressWarnings("unchecked")
@Transactional(rollbackFor = {Exception.class, RuntimeException.class}, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class OauthClientCredentialsRedisCacheServiceImpl extends AbstractOauthService {

    @Autowired
    private IAppInfoService iAppInfoService;
    @Autowired
    private IResourceService iResourceService;
    @Autowired
    private IClientResourceRelService iClientResourceRelService;
    @Autowired
    private ISystemTypeService iSystemTypeService;

    /**
     * 执行当前模式 OAuth流程
     *
     * @param clientInfoBo 客户端信息业务对象
     * @return 返回一个封装好的 HttpEntity实现类对象
     * @throws Exception 抛出所有未捕获异常
     */
    @Override
    public ServiceResultDto executeOAuthModelFlow(ClientInfoBo clientInfoBo) throws Exception{
        AssertUtils.isNull(clientInfoBo, "appInfoBo is null");
       //检查客户端是否合法，包括client_id、client_secret、ip、status,通过后设置元数据到业务对象
        this.getMetadataByCheckClient(clientInfoBo);
        //从配置表获取token生命周期规则，设置到业务对象中
        this.setTokenCycleRule(clientInfoBo);
        //检查token生命周期，通过后初始化token生命周期
        this.initRefreshTokenCycleByCheck(clientInfoBo);
        //添加token到缓存
        this.addToken(clientInfoBo);
        //最后更新初始化token生命周期
        this.updateClientCycle(clientInfoBo);
        //返回结果数据对象
        return OauthResponseHelper.buildOAuthSuccessRespose(clientInfoBo);
    }

    /**
     * 验证token是否过期
     * @param appInfoBo 封装应用端信息业务对象
     * @return 如果检查不通过则返回一个封装好ServiceResultDto对象，如果检查通过返回Null
     * @throws Exception 抛出所有未捕获异常
     */
    @Override
    public ServiceResultDto validateTokenExpires(AppInfoBo appInfoBo) throws Exception {
        AssertUtils.isNull(appInfoBo, "appInfoBo is null");
        //检查应用ip
        this.isLegalApp(appInfoBo);
        //检查token是否有效,客户端ip是否合法
        this.checkTokenExpiresAndClientIp(appInfoBo);
        //检查资源权限
        this.checkResourcePermi(appInfoBo);
        //token和对应资源权限有效
        return new ServiceResultDto(CodeEnum.TOKEN_ROURCE_PERMI_VALID.getValue(), CodeEnum.TOKEN_ROURCE_PERMI_VALID.getDesc());
    }

    /**
     * 检查资源权限
     * @param appInfoBo 应用信息业务对象
     * @throws Exception 抛出未捕获异常
     */
    protected void checkResourcePermi(AppInfoBo appInfoBo) throws Exception{
        Object clientInfoDto = commonRedisTemplateOps.get(TokenHelper.getAccessTokenCacheKey(appInfoBo.getToken()));
        //token过期
        if (clientInfoDto == null){
            throw new VerifyTokenException(new SimpleResCodeDto(CodeEnum.INVALID_TOKEN.getValue(), CodeEnum.INVALID_TOKEN.getDesc()));
        }
        //根据资源类型编码查资源类型数据
        TSystemTypeEntity systemTypeEntity = iSystemTypeService.findBySysTypeCode(appInfoBo.getResourceOwner());
        if (systemTypeEntity == null){
            throw new VerifyTokenException(new SimpleResCodeDto(CodeEnum.RESOURCE_OWNER_NOT_REGISTER.getValue(), CodeEnum.RESOURCE_OWNER_NOT_REGISTER.getDesc()));
        }
        CacheClientInfoDto clientInfoDtoTrans = (CacheClientInfoDto) clientInfoDto;
        //根据资源编码 + 所属类型id查资源数据
        TResourceEntity resourceEntity = iResourceService.findByResCodeAndOwnSysId(appInfoBo.getResourceCode(), systemTypeEntity.getId());
        if (resourceEntity == null){
            throw new VerifyTokenException(new SimpleResCodeDto(CodeEnum.INVALID_RESOURCE.getValue(), CodeEnum.INVALID_RESOURCE.getDesc()));
        }
        //根据cli_id + res_id查关联关系数据
        TClientResourceRelEntity clientResourceRelEntity = iClientResourceRelService.findByCliIdAndResId(clientInfoDtoTrans.getEntityId(), resourceEntity.getId());
        if (clientResourceRelEntity == null){
            throw new VerifyTokenException(new SimpleResCodeDto(CodeEnum.RESOURCE_PERMISSION_DENIED.getValue(), CodeEnum.RESOURCE_PERMISSION_DENIED.getDesc()));
        }
    }

    /**
     * 检查token有效期和客户端Ip
     * @param appInfoBo 应用信息业务对象
     * @throws Exception 抛出未捕获异常
     */
    private void checkTokenExpiresAndClientIp(AppInfoBo appInfoBo) throws Exception{
        Object clientInfoDto = commonRedisTemplateOps.get(TokenHelper.getAccessTokenCacheKey(appInfoBo.getToken()));
        if (clientInfoDto == null){
            throw new VerifyTokenException(new SimpleResCodeDto(CodeEnum.INVALID_TOKEN.getValue(), CodeEnum.INVALID_TOKEN.getDesc()));
        }
        CacheClientInfoDto clientInfoDtoTrans = (CacheClientInfoDto) clientInfoDto;
        if (clientInfoDtoTrans.getIpList().indexOf(appInfoBo.getClientIp()) == -1){
            throw new VerifyTokenException(new SimpleResCodeDto(CodeEnum.ILLEG_IP.getValue(), CodeEnum.ILLEG_IP.getDesc()));
        }
    }

    /**
     * 检查应用Ip是否合法
     * @param appInfoBo 封装应用端信息业务对象
     * @throws Exception 抛出所有未捕获异常
     */
    private void isLegalApp(AppInfoBo appInfoBo) throws Exception{
        TAppInfoEntity appInfoEntity = iAppInfoService.findByAppIp(appInfoBo.getCusRelIp());
        if (appInfoEntity == null){
            throw new VerifyTokenException(new SimpleResCodeDto(CodeEnum.ILLEG_APP_IP.getValue(), CodeEnum.ILLEG_APP_IP.getDesc()));
        }
    }
}
