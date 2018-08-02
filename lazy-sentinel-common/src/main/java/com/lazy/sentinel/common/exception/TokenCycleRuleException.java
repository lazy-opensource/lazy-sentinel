package com.lazy.sentinel.common.exception;

/**
 * @author laizhiyuan
 * @date 2018/1/9.
 * <p>当需要描述token生命周期规则异常时，可以选择抛出该异常</p>
 */
public class TokenCycleRuleException extends RuntimeException{

    public TokenCycleRuleException(String message) {
        super(message);
    }

    public TokenCycleRuleException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenCycleRuleException(Throwable cause) {
        super(cause);
    }
}
