package com.lazy.sentinel.bo;


import com.lazy.cheetah.common.tools.SetsUtils;
import com.lazy.sentinel.dto.CacheClientInfoDto;
import com.lazy.sentinel.dto.CacheRefreshTokenDto;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author laizhiyuan
 * @date 2018/1/4.
 * <p>客户端信息业务(business object)类</p>
 */
public class ClientInfoBo {

    /**
     * 数据库实体主键
     */
    private Long entityId;
    /**
     * 客户端名称
     */
    private String name;
    /**
     * 客户端Id
     */
    private String key;
    /**
     * 客户端动态安全
     */
    private String secret;
    /**
     * 客户端请求Ip（Nginx 获取）
     */
    private String ip;
    /**
     * 客户端ip列表
     */
    private List<String> cliIpList = SetsUtils.list();
    /**
     * 客户端状态
     */
    private String status;
    /**
     * 使用状态
     */
    private String useStatus;
    /**
     * 模式类型
     */
    private String grantType;
    /**
     * 令牌
     */
    private String token;
    /**
     * 刷新令牌
     */
    private String refreshToken;
    /**
     *  token当前生命周期范围内已经刷新过的次数
     */
    private Integer refreshTokenCount;
    /**
     * 生命周期范围内已经初始化refresh_token过的次数
     */
    private Integer initRefreshTokenCount;
    /**
     *  新的生命周期初始化refresh_token开始时间
     */
    private Timestamp initRefreshTokenBeginTime;
    /**
     *  刷新token生命周期开始时间
     */
    private Timestamp refreshTokenCycleBeginTime;
    /**
     * 组合token生命周期业务对象
     */
    private TokenCycleRuleBo tokenCycleRuleBo;
    /**
     * 组合refresh_token数据传输对象
     */
    private CacheRefreshTokenDto refreshTokenDto;
    /**
     * 客户端信息数据传输对象
     */
    private CacheClientInfoDto clientInfoDto;

    @Override
    public String toString() {
        return "ClientInfoBo{" +
                "entityId=" + entityId +
                ", name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", secret='" + secret + '\'' +
                ", ip='" + ip + '\'' +
                ", cliIpList=" + cliIpList +
                ", status='" + status + '\'' +
                ", useStatus='" + useStatus + '\'' +
                ", grantType='" + grantType + '\'' +
                ", token='" + token + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", refreshTokenCount=" + refreshTokenCount +
                ", initRefreshTokenCount=" + initRefreshTokenCount +
                ", initRefreshTokenBeginTime=" + initRefreshTokenBeginTime +
                ", refreshTokenCycleBeginTime=" + refreshTokenCycleBeginTime +
                ", tokenCycleRuleBo=" + tokenCycleRuleBo +
                ", refreshTokenDto=" + refreshTokenDto +
                ", clientInfoDto=" + clientInfoDto +
                '}';
    }

    public List<String> getCliIpList() {
        return cliIpList;
    }

    public void setCliIpList(List<String> cliIpList) {
        this.cliIpList = cliIpList;
    }

    public CacheClientInfoDto getClientInfoDto() {
        return clientInfoDto;
    }

    public void setClientInfoDto(CacheClientInfoDto clientInfoDto) {
        this.clientInfoDto = clientInfoDto;
    }

    public Timestamp getRefreshTokenCycleBeginTime() {
        return refreshTokenCycleBeginTime;
    }

    public void setRefreshTokenCycleBeginTime(Timestamp refreshTokenCycleBeginTime) {
        this.refreshTokenCycleBeginTime = refreshTokenCycleBeginTime;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Integer getRefreshTokenCount() {
        return refreshTokenCount;
    }

    public void setRefreshTokenCount(Integer refreshTokenCount) {
        this.refreshTokenCount = refreshTokenCount;
    }

    public Integer getInitRefreshTokenCount() {
        return initRefreshTokenCount;
    }

    public void setInitRefreshTokenCount(Integer initRefreshTokenCount) {
        this.initRefreshTokenCount = initRefreshTokenCount;
    }

    public Timestamp getInitRefreshTokenBeginTime() {
        return initRefreshTokenBeginTime;
    }

    public void setInitRefreshTokenBeginTime(Timestamp initRefreshTokenBeginTime) {
        this.initRefreshTokenBeginTime = initRefreshTokenBeginTime;
    }

    public TokenCycleRuleBo getTokenCycleRuleBo() {
        return tokenCycleRuleBo;
    }

    public void setTokenCycleRuleBo(TokenCycleRuleBo tokenCycleRuleBo) {
        this.tokenCycleRuleBo = tokenCycleRuleBo;
    }

    public CacheRefreshTokenDto getRefreshTokenDto() {
        return refreshTokenDto;
    }

    public void setRefreshTokenDto(CacheRefreshTokenDto refreshTokenDto) {
        this.refreshTokenDto = refreshTokenDto;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }
}
