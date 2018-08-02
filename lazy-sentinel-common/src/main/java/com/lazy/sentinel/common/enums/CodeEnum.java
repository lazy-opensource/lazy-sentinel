package com.lazy.sentinel.common.enums;

/**
 * @author laizhiyuan
 * @date 2018/1/6.
 * <p>定义code枚举</p>
 */
public enum CodeEnum {
    SUCCESS("8200", "请求处理成功"),
    TOKEN_ROURCE_PERMI_VALID("8201", "token和对应资源权限有效"),

    GRANT_TYPE_EMPTY("8400", "请求参数grant_type为空"),
    CLIENT_ID_EMPTY("8401", "请求参数client_id为空"),
    CLIENT_SECRET_EMPTY("8402", "请求参数client_secret为空"),
    REFRESH_TOKEN_EMPTY("8403", "请求参数refresh_token为空"),
    ACCESS_TOKEN_EMPTY("8404", "请求参数access_token参数为空"),
    RESOURCE_OWNER_EMPTY("8405", "请求参数resource_owner为空"),
    RESOURCE_CODE_EMPTY("8406", "请求参数resource_code为空"),
    INVALID_RESOURCE("8407", "资源未注册"),
    GRANT_TYPE_NOT_SUPPER("8408", "不支持的grant_type类型"),
    CLIENT_ID_NOT_REGISTER("8409", "client_id未注册"),
    CLIENT_SECRET_ERROR("8410", "client_secret动态安全码错误"),
    CLIENT_ID_STOP("8411", "该客户端处于停用状态"),
    ILLEG_IP("8412", "客户端IP不合法"),
    INIT_TOKEN_COUNT_OUT_OF_BOUNDS("8413", "初始化token次数超出规定的规则范围"),
    REFRESH_TOKEN_COUNT_OUT_OF_BOUNDS("8414", "刷新token次数超出规定的规则范围"),
    INVALID_TOKEN("8415", "token过期"),
    INVALID_REFRESH_TOKEN("8416", "无效的refresh_token令牌"),
    SYSTEM_ERROR("8417", "服务器系统错误"),
    CLIENT_IP_EMPTY("8419", "请求参数client_ip为空"),
    ILLEG_APP_IP("8420", "不合法应用IP"),
    RESOURCE_PERMISSION_DENIED("8421", "资源权限不足"),
    RESOURCE_OWNER_NOT_REGISTER("8422", "资源所属类型编码未注册"),
    CLIENT_IP_NOT_REGISTER("8423", "该客户端未注册ip信息"),

    OAUTH_SYSTEM_ERROR("8500", "OAuth认证服务器系统错误"),
    ;

    private String value;
    private String desc;

    CodeEnum(String value, String desc) {
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
