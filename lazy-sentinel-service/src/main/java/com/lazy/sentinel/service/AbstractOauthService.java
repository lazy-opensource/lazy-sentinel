package com.lazy.sentinel.service;


import com.lazy.cheetah.common.tools.DateUtils;
import com.lazy.cheetah.common.tools.SetsUtils;
import com.lazy.cheetah.common.tools.SnowflakeIdWorkerUtils;
import com.lazy.cheetah.common.tools.StringUtils;
import com.lazy.cheetah.core.dto.SimpleResCodeDto;
import com.lazy.cheetah.core.enums.ValidStatusEnum;
import com.lazy.cheetah.core.service.BaseRedisTemplateService;
import com.lazy.sentinel.api.IClientInfoService;
import com.lazy.sentinel.api.IClientIpInfoService;
import com.lazy.sentinel.api.IOauthService;
import com.lazy.sentinel.api.ITokenCycleRuleService;
import com.lazy.sentinel.bo.ClientInfoBo;
import com.lazy.sentinel.bo.TokenCycleRuleBo;
import com.lazy.sentinel.common.enums.CodeEnum;
import com.lazy.sentinel.common.enums.CommonEnum;
import com.lazy.sentinel.common.exception.AccessTokenException;
import com.lazy.sentinel.common.exception.RefreshTokenException;
import com.lazy.sentinel.common.exception.TokenCycleRuleException;
import com.lazy.sentinel.dto.CacheClientInfoDto;
import com.lazy.sentinel.dto.CacheRefreshTokenDto;
import com.lazy.sentinel.entity.TClientInfoEntity;
import com.lazy.sentinel.entity.TClientIpInfoEntity;
import com.lazy.sentinel.entity.TTokenCycleRuleEntity;
import com.lazy.sentinel.helper.TokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *
 *  <p>
 *     OAuth 2.0定义了四种授权方式。
 *     授权码模式（authorization code）
 *     简化模式（implicit）
 *     密码模式（resource owner password credentials）
 *     客户端模式（client credentials）
 *
 *     这是四种模式胡基础抽象类
 * </p>
 * @author laizhiyuan
 * @date 2018/1/4.
 */
public abstract class AbstractOauthService extends BaseRedisTemplateService implements IOauthService {

    @Autowired
    @Qualifier("clientInfoServiceImpl")
    protected IClientInfoService iClientInfoService;

    @Autowired
    protected ITokenCycleRuleService iTokenCycleRuleService;
    @Autowired
    protected IClientIpInfoService iClientIpInfoService;

    /**
     * 检查客户端是否合法
     * 如果合法则将相关元数据设置到业务对象ClientInfoBo
     * 元数据主要有:
     * 该客户端是否第一次访问token;
     * token上周期初始化时间;
     * 上周期初始化后到目前为止初始化token的次数
     * @param clientInfoBo 封装客户端信息业务对象
     * @throws Exception 抛出所有未捕获异常
     */
    protected void getMetadataByCheckClient(ClientInfoBo clientInfoBo) throws Exception {
        logger.info(String.format("client_id: %s", clientInfoBo.getKey()));
        TClientInfoEntity entity = iClientInfoService.findByCliId(clientInfoBo.getKey());
        //检查是否注册
        if (entity == null){
            throw new AccessTokenException(new SimpleResCodeDto(CodeEnum.CLIENT_ID_NOT_REGISTER.getValue(), CodeEnum.CLIENT_ID_NOT_REGISTER.getDesc()));
        }
        //检查client_secret安全密钥
        if (!clientInfoBo.getSecret().equals(entity.getCliSecret())){
            throw new AccessTokenException(new SimpleResCodeDto(CodeEnum.CLIENT_SECRET_ERROR.getValue(), CodeEnum.CLIENT_SECRET_ERROR.getDesc()));
        }
        //检查数据是否有效
        if (CommonEnum.STOP_STATUS.getValue().equals(entity.getValidStatus())){
            throw new AccessTokenException(new SimpleResCodeDto(CodeEnum.CLIENT_ID_STOP.getValue(), CodeEnum.CLIENT_ID_STOP.getDesc()));
        }
        //检查ip是否合法
        List<TClientIpInfoEntity> ipInfoEntityList = iClientIpInfoService.findAllByCliId(entity.getCliId());
        if (ipInfoEntityList == null || ipInfoEntityList.isEmpty()){
            throw new AccessTokenException(new SimpleResCodeDto(CodeEnum.CLIENT_IP_NOT_REGISTER.getValue(), CodeEnum.CLIENT_IP_NOT_REGISTER.getDesc()));
        }
        boolean notFoundIp = true;
        for (TClientIpInfoEntity ipInfoEntity : ipInfoEntityList){
            clientInfoBo.getCliIpList().add(ipInfoEntity.getIp());
            if (clientInfoBo.getIp().equals(ipInfoEntity.getIp())){
                notFoundIp = false;
            }
        }
        if (notFoundIp){
            logger.error(String.format("检查到有不合法ip[%s]访问OAuth系统的情况：", clientInfoBo.getIp()));
            throw new AccessTokenException(new SimpleResCodeDto(CodeEnum.ILLEG_IP.getValue(), CodeEnum.ILLEG_IP.getDesc()));
        }
        //设置元数据
        clientInfoBo.setUseStatus(entity.getUseStatus());
        clientInfoBo.setInitRefreshTokenCount(entity.getInitRefreshTokenCount());
        clientInfoBo.setInitRefreshTokenBeginTime(entity.getInitRefreshTokenBeginTime());
        clientInfoBo.setEntityId(entity.getId());
    }

    /**
     *  如果客户端是第一次访问token则直接初始化token生命周期参数
     *  生命周期参数主要两个：
     *  当前周期开始时间
     *  当前周期访问token次数
     *
     *  如果不是第一次访问则进行如下检查：
     *  是否需要开始新的生命周期（当前时间 - 上个周期开始时间 > cycle）
     *  如果不需要开始新的生命周期则判断是否超过一个周期内可以初始化token的次数
     *
     * 规则用表来配置，生命周期规则指：
     * 多少天内同一个客户端获取token次数最大为N次；
     * 多少天内同一个客户端刷新token次数最大为N次;
     *
     * @param clientInfoBo 客户端信息业务对象
     * @throws Exception 抛出所有未捕获异常
     */
    protected void initRefreshTokenCycleByCheck(ClientInfoBo clientInfoBo) throws Exception {
        logger.info(clientInfoBo.toString());
        //首次对接
        if (this.isFirstInit(clientInfoBo)){
            clientInfoBo.setInitRefreshTokenBeginTime(DateUtils.getCurrentTimestamp(DateUtils.YYYY_MM_DD_HH_MM_SS));
            clientInfoBo.setInitRefreshTokenCount(1);
        }else{
            //（当前时间 - token生命周期开始时间）判断是否超过一个周期
            long currentTime = System.currentTimeMillis() / 1000;
            long intervalTime = currentTime - (clientInfoBo.getInitRefreshTokenBeginTime().getTime() / 1000);
            long cycleTime = clientInfoBo.getTokenCycleRuleBo().getCycle() * 24 * 60 * 60;
            //超出一个周期
            if (intervalTime > cycleTime){
                //重置，开始新的生命周期
                clientInfoBo.setInitRefreshTokenBeginTime(DateUtils.getCurrentTimestamp(DateUtils.YYYY_MM_DD_HH_MM_SS));
                clientInfoBo.setInitRefreshTokenCount(1);
            }else{
                //否则判断是否超过一个周期内可以初始化的次数，如果没超过,则累计次数
                if (clientInfoBo.getInitRefreshTokenCount() <= clientInfoBo.getTokenCycleRuleBo().getInitRefreshTokenCount()){
                    clientInfoBo.setInitRefreshTokenCount(clientInfoBo.getInitRefreshTokenCount() + 1);
                }else {
                    //如果超出规定初始化次数，构建一个错误响应
                    throw new AccessTokenException(new SimpleResCodeDto(CodeEnum.INIT_TOKEN_COUNT_OUT_OF_BOUNDS.getValue(), CodeEnum.INIT_TOKEN_COUNT_OUT_OF_BOUNDS.getDesc()));
                }
            }
        }
    }

    /**
     * 客户端是否首次对接
     * @param clientInfoBo 客户端信息实体对象
     * @return 布尔值
     */
    private boolean isFirstInit(ClientInfoBo clientInfoBo){
        if(CommonEnum.IS_NOT_USE.getValue().equals(clientInfoBo.getUseStatus())
                || clientInfoBo.getInitRefreshTokenBeginTime() == null
                || StringUtils.isBlank(clientInfoBo.getUseStatus())
                || clientInfoBo.getInitRefreshTokenCount() == null){
            return true;
        }
        return false;
    }

    /**
     * 修改客户端生命周期
     * @param clientInfoBo 客户端信息实体对象
     * @throws Exception 抛出未捕获异常
     */
    protected void updateClientCycle(ClientInfoBo clientInfoBo) throws Exception{
        Map<String, Object> fieldValueMap = SetsUtils.map();
        fieldValueMap.put("initRefreshTokenCount", clientInfoBo.getInitRefreshTokenCount());
        fieldValueMap.put("initRefreshTokenBeginTime", clientInfoBo.getInitRefreshTokenBeginTime());
        fieldValueMap.put("useStatus", CommonEnum.IS_USE.getValue());

        List<Long> ids = SetsUtils.list();
        ids.add(clientInfoBo.getEntityId());
        iClientInfoService.updateMultiFieldByIds(fieldValueMap, TClientInfoEntity.class, ids, null);
    }

    /**
     * 生成token
     *
     * @return token
     * @throws Exception 抛出所有未捕获异常
     */
    protected String genToken() throws Exception {
        return CommonEnum.ACCESS_TOKEN_PREFIX.getValue() + String.valueOf(SnowflakeIdWorkerUtils.getINSTANCE().nextId());
    }

    /**
     * 生成 refresh_token
     * @return refresh_token
     * @throws Exception 抛出所有未捕获异常
     */
    protected String genRefreshToken() throws Exception {
        return CommonEnum.REFRESH_ACCESS_TOKEN_PREFIX.getValue() + String.valueOf(SnowflakeIdWorkerUtils.getINSTANCE().nextId());
    }

    /**
     * 添加token
     *
     * @param clientInfoBo 客户端信息业务对象
     * @throws Exception 抛出所有未捕获异常
     */
    protected void addToken(ClientInfoBo clientInfoBo) throws Exception {
        //根据client_id判断上次初始化的refresh_token是否过期，如果是则清除未过期的脏数据
        TokenHelper.cleanAllTokenByClientId(clientInfoBo.getKey());
        //生成新的token、refresh_token
        clientInfoBo.setToken(this.genToken());
        clientInfoBo.setRefreshToken(this.genRefreshToken());
        //实例化refresh_token缓存值数据传输对象
        CacheRefreshTokenDto refreshTokenDto = this.instantiateRefreshTokenForInitRefreshToken(clientInfoBo);
        //实例化token缓存值数据传输对象
        CacheClientInfoDto clientInfoDto = this.instantiateClientInfoDtoForInitToken(clientInfoBo);
        //设置新的refresh_token、token数据
        clientInfoBo.setRefreshTokenDto(refreshTokenDto);
        clientInfoBo.setClientInfoDto(clientInfoDto);
        this.addTokenToRedis(clientInfoBo);
    }

    /**
     * 添加token refresh_token to redis
     * @param clientInfoBo 客户端信息业务对象
     * @throws Exception 抛出未捕获异常
     */
    private void addTokenToRedis(ClientInfoBo clientInfoBo) throws Exception{
        commonRedisTemplateOps.set(TokenHelper.getAccessTokenCacheKey(clientInfoBo.getToken()), clientInfoBo.getClientInfoDto());
        commonRedisTemplateOps.set(TokenHelper.getRefreshTokenCacheKey(clientInfoBo.getRefreshToken()), clientInfoBo.getRefreshTokenDto());
        commonRedisTemplateOps.set(TokenHelper.getRefreshTokenClientIdCacheKey(clientInfoBo.getKey()), clientInfoBo.getRefreshToken());
        commonRedisTemplate.expire(TokenHelper.getAccessTokenCacheKey(clientInfoBo.getToken()), clientInfoBo.getTokenCycleRuleBo().getTokenExpires(), TimeUnit.SECONDS);
        commonRedisTemplate.expire(TokenHelper.getRefreshTokenCacheKey(clientInfoBo.getRefreshToken()), clientInfoBo.getTokenCycleRuleBo().getRefreshTokenExpires(), TimeUnit.SECONDS);
        commonRedisTemplate.expire(TokenHelper.getRefreshTokenClientIdCacheKey(clientInfoBo.getKey()), clientInfoBo.getTokenCycleRuleBo().getRefreshTokenExpires(), TimeUnit.SECONDS);
    }

    /**
     * 为初始化refresh_token实例化刷新token对象数据传输对象
     * @param clientInfoBo 客户端信息业务对象
     * @return com.tcl.b2bapi.oauth2.dto.CacheClientInfoDto
     * @throws Exception 抛出未捕获异常
     */
    private CacheRefreshTokenDto instantiateRefreshTokenForInitRefreshToken(ClientInfoBo clientInfoBo)throws Exception{
        CacheRefreshTokenDto refreshTokenDto = new CacheRefreshTokenDto();
        refreshTokenDto.setLastToken(clientInfoBo.getToken());
        refreshTokenDto.setRefreshTokenMaxCount(clientInfoBo.getTokenCycleRuleBo().getRefreshTokenCount());
        refreshTokenDto.setRefreshTokenCycleBeginTime(DateUtils.getCurrentTimestamp(DateUtils.YYYY_MM_DD_HH_MM_SS));
        refreshTokenDto.setRefreshTokenCount(0);
        refreshTokenDto.setTokenExpiresIn(clientInfoBo.getTokenCycleRuleBo().getTokenExpires());
        refreshTokenDto.setRefreshToken(clientInfoBo.getRefreshToken());
        refreshTokenDto.setKey(clientInfoBo.getKey());
        refreshTokenDto.setSecret(clientInfoBo.getSecret());
        refreshTokenDto.setIpList(clientInfoBo.getCliIpList());
        refreshTokenDto.setClientInfoId(clientInfoBo.getEntityId());
        return refreshTokenDto;
    }


    /**
     * 为初始化token实例化客户端信息数据传输对象
     * @param clientInfoBo 客户端信息业务对象
     * @return com.tcl.b2bapi.oauth2.dto.CacheClientInfoDto
     * @throws Exception 抛出未捕获异常
     */
    private CacheClientInfoDto instantiateClientInfoDtoForInitToken(ClientInfoBo clientInfoBo)throws Exception{
        CacheClientInfoDto clientInfoDto = new CacheClientInfoDto();
        clientInfoDto.setEntityId(clientInfoBo.getEntityId());
        clientInfoDto.setIpList(clientInfoBo.getCliIpList());
        clientInfoDto.setKey(clientInfoBo.getKey());
        clientInfoDto.setSecret(clientInfoBo.getSecret());
        return clientInfoDto;
    }

    /**
     * 如果refresh_token没有过期，且客户端合法，则将元数据组合到业务对象
     * @param clientInfoBo 客户端信息业务对象
     * @throws Exception 抛出所有未捕获异常
     */
    protected void getMetadataByCheckRefreshToken(ClientInfoBo clientInfoBo) throws Exception{
        //refresh_token数据传输对象
        Object refreshTokenDto = commonRedisTemplateOps.get(TokenHelper.getRefreshTokenCacheKey(clientInfoBo.getRefreshToken()));
        //refresh_token的剩余过期时间
        long refreshTokenLeftExpiresIn = commonRedisTemplate.getExpire(TokenHelper.getRefreshTokenCacheKey(clientInfoBo.getRefreshToken()));
        //剩下程序处理需要的大约时间 6s
        long leftProcessingTime = 6L;
        if (refreshTokenDto == null || refreshTokenLeftExpiresIn < leftProcessingTime){
            //封装过期错误响应
            throw new RefreshTokenException(new SimpleResCodeDto(CodeEnum.INVALID_REFRESH_TOKEN.getValue(), CodeEnum.INVALID_REFRESH_TOKEN.getDesc()));
        }else {
            //如果没有过期则检查本次客户ip是否和初始化时一致
            CacheRefreshTokenDto refreshTokenDtoTrans = (CacheRefreshTokenDto) refreshTokenDto;
            if (refreshTokenDtoTrans.getIpList().indexOf(clientInfoBo.getIp()) == -1){
                logger.error(String.format("检查到有不合法ip[%s]访问OAuth系统的情况：", clientInfoBo.getIp()));
                //如果不一致，则封装不合法ip错误响应
                throw new RefreshTokenException(new SimpleResCodeDto(CodeEnum.ILLEG_IP.getValue(), CodeEnum.ILLEG_IP.getDesc()));
            }
            //如果一致, 则组合refresh_token数据传输对象
            clientInfoBo.setRefreshTokenDto(refreshTokenDtoTrans);
        }
    }

    /**
     *  判断刷新token生命周期是否超出配置周期时间
     *    超出：重新初始化刷新token生命周期
     *    没有超出：继续判断刷新次数是否超过配置的次数
     *      超过：封装错误响应
     *      没有超过：累计
     * @param clientInfoBo 客户端信息业务对象
     * @throws Exception 抛出所有未捕获异常
     */
    protected void initTokenCycleByCheck(ClientInfoBo clientInfoBo) throws Exception {
        //（当前时间 - token生命周期开始时间）判断是否超过一个周期
        long currentTime = System.currentTimeMillis() / 1000;
        long intervalTime = currentTime - (clientInfoBo.getRefreshTokenDto().getRefreshTokenCycleBeginTime().getTime() / 1000);
        long cycleTime = clientInfoBo.getTokenCycleRuleBo().getCycle() * 24 * 60 * 60;
        //如果超出生命周期
        if (intervalTime > cycleTime){
            //初始化
            clientInfoBo.getRefreshTokenDto().setRefreshTokenCount(1);
            clientInfoBo.getRefreshTokenDto().setRefreshTokenCycleBeginTime(DateUtils.getCurrentTimestamp(DateUtils.YYYY_MM_DD_HH_MM_SS));
        }else {
            //如果没有超过，则继续检查刷新次数是否超过,如果没有超过则累计
            if (clientInfoBo.getRefreshTokenDto().getRefreshTokenCount() <= clientInfoBo.getTokenCycleRuleBo().getRefreshTokenCount()){
                clientInfoBo.getRefreshTokenDto().setRefreshTokenCount(clientInfoBo.getRefreshTokenDto().getRefreshTokenCount() + 1);
            }else {
                //如果超过, 封装错误响应
                throw new RefreshTokenException(new SimpleResCodeDto(CodeEnum.REFRESH_TOKEN_COUNT_OUT_OF_BOUNDS.getValue(), CodeEnum.REFRESH_TOKEN_COUNT_OUT_OF_BOUNDS.getDesc()));
            }
        }
    }

    /**
     * 刷新token
     * @param clientInfoBo 封装客户端信息业务对象
     * @throws Exception 抛出所有未捕获异常
     */
    protected void refreshToken(ClientInfoBo clientInfoBo) throws Exception{
        //如果上次初始化或刷新的token未过期，则清除未过期的脏数据
        Object preToken = commonRedisTemplateOps.get(TokenHelper.getAccessTokenCacheKey(clientInfoBo.getRefreshTokenDto().getLastToken()));
        if (preToken != null){
            commonRedisTemplate.delete(TokenHelper.getAccessTokenCacheKey(clientInfoBo.getRefreshTokenDto().getLastToken()));
        }
        Object preRefreshTokenDto = commonRedisTemplateOps.get(TokenHelper.getRefreshTokenCacheKey(clientInfoBo.getRefreshToken()));
        //上个refresh_token的剩余过期时间
        long refreshTokenLeftExpiresIn = commonRedisTemplate.getExpire(TokenHelper.getRefreshTokenCacheKey(clientInfoBo.getRefreshToken()));
        //剩下程序处理需要的大约时间 3s
        long leftProcessingTime = 3L;
        //如果程序处理时间超过之前给定的10s, 则依然做过期处理
        if (preRefreshTokenDto == null || refreshTokenLeftExpiresIn < leftProcessingTime){
            //封装过期错误响应
            throw new RefreshTokenException(new SimpleResCodeDto(CodeEnum.INVALID_REFRESH_TOKEN.getValue(), CodeEnum.INVALID_REFRESH_TOKEN.getDesc()));
        }else {
            //如果没有过期 则清除旧的数据
            commonRedisTemplate.delete(TokenHelper.getRefreshTokenCacheKey(clientInfoBo.getRefreshToken()));
            commonRedisTemplate.delete(TokenHelper.getRefreshTokenClientIdCacheKey(clientInfoBo.getRefreshTokenDto().getKey()));
        }
        //生成新的token、refresh_token
        clientInfoBo.setToken(this.genToken());
        clientInfoBo.setRefreshToken(this.genRefreshToken());
        clientInfoBo.getRefreshTokenDto().setLastToken(clientInfoBo.getToken());
        clientInfoBo.getRefreshTokenDto().setRefreshToken(clientInfoBo.getRefreshToken());
        clientInfoBo.getTokenCycleRuleBo().setRefreshTokenExpires(refreshTokenLeftExpiresIn);

        //从RefreshTokenDto转换值给clientInfoBo
        clientInfoBo.setKey(clientInfoBo.getRefreshTokenDto().getKey());
        clientInfoBo.setEntityId(clientInfoBo.getRefreshTokenDto().getClientInfoId());
        clientInfoBo.setSecret(clientInfoBo.getRefreshTokenDto().getSecret());

        //实例化token值数据传输对象
        CacheClientInfoDto clientInfoDto = this.instantiateClientInfoDtoForInitToken(clientInfoBo);

        //设置新的refresh_token、token数据
        clientInfoBo.setClientInfoDto(clientInfoDto);
        this.addTokenToRedis(clientInfoBo);
    }

    /**
     * 从配置表获取token生命周期规则，设置到业务对象中
     *
     * @param clientInfoBo 封装客户端信息业务对象
     * @throws Exception 抛出所有未捕获异常
     */
    protected void setTokenCycleRule(ClientInfoBo clientInfoBo) throws Exception{
        TTokenCycleRuleEntity tokenCycleRuleEntity = iTokenCycleRuleService.findByValidStatus(ValidStatusEnum.VALID.getStatus());
        if (tokenCycleRuleEntity != null){
            clientInfoBo.setTokenCycleRuleBo(TokenCycleRuleBo.entity2Bo(tokenCycleRuleEntity));
            return;
        }
        throw new TokenCycleRuleException("tokenCycleRuleEntity is null,please check config token cycle rule in table t_toke_cycle_rule");
    }
}
