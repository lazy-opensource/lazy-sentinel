package com.lazy.sentinel.common.enums;

/**
 * @author laizhiyuan
 * @date 2018/1/7.
 * <p>定义通用枚举</p>
 */
public enum  CommonEnum {
    ENABLE_STATUS("Y", "启用状态"),
    STOP_STATUS("N", "停用状态"),
    IS_USE("Y", "使用过"),
    IS_NOT_USE("N", "从未使用过"),

    ACCESS_TOKEN_CACHE_PREFIX("ACCESS_TOKEN", "access_token缓存前缀"),
    REFRESH_TOKEN_CACHE_PREFIX("REFRESH_TOKEN", "refresh_token缓存前缀"),
    REFRESH_TOKEN_CLIENT_ID_CACHE_PREFIX("REFRESH_TOKEN_CLIENT_ID", "保存refresh_token时连带保存的client_id缓存key"),

    ACCESS_TOKEN_PREFIX("access_token_20160108_", "token前缀"),
    REFRESH_ACCESS_TOKEN_PREFIX("refresh_access_token_20160108_", "刷新token前缀")
    ;

    private String value;
    private String desc;

    CommonEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
