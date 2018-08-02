package com.lazy.sentinel.dto;

import java.io.Serializable;

/**
 * @author laizhiyuan
 * @date 2018/1/7.
 * <p>Simple Http响应数据传输(Data Transfer Object) 类</p>
 */
public class SimpleHttpResponseDto implements Serializable{

    /**
     * 序列化版本Id
     */
    static final long serialVersionUID = 996872L;

    /**
     * 错误码
     */
    private String error;
    /**
     * 错误描述
     */
    private String error_description;

    public SimpleHttpResponseDto() {
        super();
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

}
