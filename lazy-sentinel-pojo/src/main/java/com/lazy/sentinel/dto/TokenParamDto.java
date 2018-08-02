package com.lazy.sentinel.dto;

import java.io.Serializable;

/**
 * @author laizhiyuan
 * @date 2018/1/11.
 * <p>token控制器参数数据传输(Data Transfer Object) 对象</p>
 */
public class TokenParamDto implements Serializable {

    /**
     * 序列化版本Id
     */
    static final long serialVersionUID = -996872L;

    /**
     * 客户端Id
     */
    private String client_id;
    /**
     * 客户端安全密钥
     */
    private String client_secret;
    /**
     * 模式类型
     */
    private String grant_type;
    /**
     * 状态
     */
    private String status;
    /**
     * 令牌
     */
    private String access_token;
    /**
     * 刷新token令牌
     */
    private String refresh_token;
    /**
     * 客户端真实ip
     */
    private String cusRelIp;
    /**
     * 客户端ip
     */
    private String client_ip;
    /**
     * 资源归属
     */
    private String resource_owner;
    /**
     * 资源编码
     */
    private String resource_code;

    @Override
    public String toString() {
        return "TokenParamDto{" +
                "client_id='" + client_id + '\'' +
                ", client_secret='" + client_secret + '\'' +
                ", grant_type='" + grant_type + '\'' +
                ", status='" + status + '\'' +
                ", access_token='" + access_token + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", cusRelIp='" + cusRelIp + '\'' +
                ", client_ip='" + client_ip + '\'' +
                ", resource_owner='" + resource_owner + '\'' +
                ", resource_code='" + resource_code + '\'' +
                '}';
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getClient_ip() {
        return client_ip;
    }

    public void setClient_ip(String client_ip) {
        this.client_ip = client_ip;
    }

    public String getCusRelIp() {
        return cusRelIp;
    }

    public void setCusRelIp(String cusRelIp) {
        this.cusRelIp = cusRelIp;
    }

    public String getResource_owner() {
        return resource_owner;
    }

    public void setResource_owner(String resource_owner) {
        this.resource_owner = resource_owner;
    }

    public String getResource_code() {
        return resource_code;
    }

    public void setResource_code(String resource_code) {
        this.resource_code = resource_code;
    }
}
