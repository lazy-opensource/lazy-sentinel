package com.lazy.sentinel.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author laizhiyuan
 * @date 2018/1/9.
 * <p>Simple refresh_token缓存value数据传输(Data Transfer Object) 类</p>
 */
public class SimpleRefreshTokenDto implements Serializable {
    /**
     * 序列化版本Id
     */
    static final long serialVersionUID = -996872L;
    /**
     * refresh_token
     */
    private String refreshToken;
    /**
     * token有效期 单位:秒
     */
    private long tokenExpiresIn;
    /**
     * 一个周期内已经刷新token次数
     */
    private int refreshTokenCount = 0;
    /**
     *  token新生命周期开始时间
     */
    private Timestamp refreshTokenCycleBeginTime;
    /**
     * 可以刷新最大次数
     */
    private int refreshTokenMaxCount;
    /**
     * 最后刷的token
     */
    private String lastToken;
    /**
     * client_id
     */
    private String key;
    /**
     * 客户端ip
     */
    private String ip;
    /**
     * Default Constructor
     */
    public SimpleRefreshTokenDto() {
        super();
    }

    public String getLastToken() {
        return lastToken;
    }

    public void setLastToken(String lastToken) {
        this.lastToken = lastToken;
    }

    public int getRefreshTokenMaxCount() {
        return refreshTokenMaxCount;
    }

    public void setRefreshTokenMaxCount(int refreshTokenMaxCount) {
        this.refreshTokenMaxCount = refreshTokenMaxCount;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getTokenExpiresIn() {
        return tokenExpiresIn;
    }

    public void setTokenExpiresIn(long tokenExpiresIn) {
        this.tokenExpiresIn = tokenExpiresIn;
    }

    public int getRefreshTokenCount() {
        return refreshTokenCount;
    }

    public void setRefreshTokenCount(int refreshTokenCount) {
        this.refreshTokenCount = refreshTokenCount;
    }

    public Timestamp getRefreshTokenCycleBeginTime() {
        return refreshTokenCycleBeginTime;
    }

    public void setRefreshTokenCycleBeginTime(Timestamp refreshTokenCycleBeginTime) {
        this.refreshTokenCycleBeginTime = refreshTokenCycleBeginTime;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
