package com.lazy.sentinel.common.exception;


import com.lazy.cheetah.core.dto.SimpleResCodeDto;
import com.lazy.cheetah.core.exception.BasicBusinessRuntimeException;

/**
 * <p>当需要描述访问token异常时，可以选择抛出该异常</p>
 *
 * @author laizhiyuan
 * @date 2018/3/2.
 */
public class AccessTokenException extends BasicBusinessRuntimeException {

    public AccessTokenException(SimpleResCodeDto resCodeDto) {
        super(resCodeDto);
    }

    public AccessTokenException(SimpleResCodeDto resCodeDto, Throwable cause) {
        super(resCodeDto, cause);
    }

    public AccessTokenException(SimpleResCodeDto resCodeDto, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(resCodeDto, cause, enableSuppression, writableStackTrace);
    }
}
