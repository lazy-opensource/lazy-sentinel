package com.lazy.sentinel.dto;

import java.io.Serializable;

/**
 * @author laizhiyuan
 * @date 2018/1/10.
 * <p>Simple 客户端信息数据传输(Data Transfer Object) 类</p>
 */
public class SimpleClientInfoDto implements Serializable {
    /**
     * 序列化版本Id
     */
    static final long serialVersionUID = 6983521L;
    /**
     * client_id
     */
    private String key;
    /**
     * client_secret
     */
    private String secret;
    /**
     * 客户端ip
     */
    private String ip;
    /**
     * 实体主键
     */
    private Long entityId;

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

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }
}
