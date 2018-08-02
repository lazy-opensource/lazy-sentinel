package com.lazy.sentinel.bo;

/**
 * @author laizhiyuan
 * @date 2018/1/10.
 * <p>对接应用信息业务(business object)类</p>
 */
public class AppInfoBo {

    /**
     * 实体主键
     */
    private Long entityId;
    /**
     * 对接应用名称
     */
    private String name;
    /**
     * 请求客户端真实ip
     */
    private String cusRelIp;
    /**
     * 对接应用域名
     */
    private String domain;
    /**
     * 对接应用启用状态
     */
    private String status;
    /**
     * token
     */
    private String token;
    /**
     * 模式类型
     */
    private String grantType;
    /**
     * 权限范围
     */
    private String scope;
    /**
     * 客户端ip
     */
    private String clientIp;
    /**
     * 资源编码
     */
    private String resourceCode;
    /**
     * 资源系统类型
     */
    private String resourceOwner;

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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getCusRelIp() {
        return cusRelIp;
    }

    public void setCusRelIp(String cusRelIp) {
        this.cusRelIp = cusRelIp;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getResourceOwner() {
        return resourceOwner;
    }

    public void setResourceOwner(String resourceOwner) {
        this.resourceOwner = resourceOwner;
    }
}
