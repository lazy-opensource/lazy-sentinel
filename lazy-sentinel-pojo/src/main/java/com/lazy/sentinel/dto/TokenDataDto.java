package com.lazy.sentinel.dto;

import java.io.Serializable;

/**
 * @author laizhiyuan
 * @date 2018/1/11.
 * <p>OAuth Token 数据传输对象</p>
 */
public class TokenDataDto implements Serializable {
    /**
     * 序列化版本Id
     */
    static final long serialVersionUID = -99687666L;

    /**
     * token令牌
     */
    private String access_token;
    /**
     * token有效期 单位：s
     */
    private Long expires_in;
    /**
     * 刷新token令牌
     */
    private String refresh_token;
    /**
     * 状态
     */
    private String status;

    /**
     * Full Constructor
     *
     * @param access_token access_token
     * @param expires_in expires_in
     * @param refresh_token refresh_token
     * @param status status
     */
    public TokenDataDto(String access_token, Long expires_in, String refresh_token, String status) {
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.refresh_token = refresh_token;
        this.status = status;
    }

    @Override
    public String toString() {
        return "TokenDataDto{" +
                "access_token='" + access_token + '\'' +
                ", expires_in=" + expires_in +
                ", refresh_token='" + refresh_token + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public TokenDataDto() {
        super();
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
