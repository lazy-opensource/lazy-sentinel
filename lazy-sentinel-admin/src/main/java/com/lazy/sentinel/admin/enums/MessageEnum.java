package com.lazy.sentinel.admin.enums;

/**
 * <p></p>
 *
 * @author laizhiyuan
 * @date 2018/8/2.
 */
public enum MessageEnum {


    LOGIN_NAME_ERROR("1000", "账号不存在"),
    LOGIN_PASSWORD_ERROR("1001", "登录密码错误"),
    CAPTCHA_ERROR("1002", "验证码错误"),
    LOGIN_NAME_BLANK("1003", "账号不能为空"),
    LOGIN_PASSWORD_BLANK("1004", "密码不能为空"),

    DEL_IDS_PARAMS_BLANK("1005", "不支持全表删除"),


    SYSTEM_ERROR("500", "系统错误"),
    PARAM_ERROR("400", "参数错误"),
    SUCCESS("200", "成功")
    ;

    private String code;
    private String desc;

    MessageEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
