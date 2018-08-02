package com.lazy.sentinel.common.exception;


import com.lazy.cheetah.core.dto.SimpleResCodeDto;
import com.lazy.cheetah.core.exception.BasicBusinessRuntimeException;

/**
 * <p>当需要描述刷新token异常时，可以选择抛出该异常</p>
 *
 * @author laizhiyuan
 * @date 2018/3/2.
 */
public class RefreshTokenException extends BasicBusinessRuntimeException {

    public RefreshTokenException(SimpleResCodeDto resCodeDto) {
        super(resCodeDto);
    }

    public RefreshTokenException(SimpleResCodeDto resCodeDto, Throwable cause) {
        super(resCodeDto, cause);
    }

    public RefreshTokenException(SimpleResCodeDto resCodeDto, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(resCodeDto, cause, enableSuppression, writableStackTrace);
    }
}
