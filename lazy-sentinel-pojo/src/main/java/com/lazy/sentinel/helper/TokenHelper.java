package com.lazy.sentinel.helper;


import com.lazy.cheetah.common.tools.AssertUtils;
import com.lazy.cheetah.common.tools.SpringUtils;
import com.lazy.sentinel.common.enums.CommonEnum;
import com.lazy.sentinel.dto.CacheRefreshTokenDto;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author laizhiyuan
 * @date 2018/1/15.
 * <p>token助手</p>
 */
public class TokenHelper {

    /**
     * 通用Redis模板
     */
    private static RedisTemplate<String, Object> commonRedisTemplate = SpringUtils.getBean("commonRedisTemplate");

    /**
     * 通过客户端id清除所有与该客户端id的token相关的缓存数据
     * @param clientId 客户端id
     */
    public static void cleanAllTokenByClientId(String clientId){
        AssertUtils.isNull(clientId, "param clientId is null");
        Object preRefreshToken = commonRedisTemplate.opsForValue().get(getRefreshTokenClientIdCacheKey(clientId));
        if (preRefreshToken != null){
            //删除key为client_id的数据
            commonRedisTemplate.delete(getRefreshTokenClientIdCacheKey(clientId));
            Object preRefreshTokenDto = commonRedisTemplate.opsForValue().get(getRefreshTokenCacheKey(preRefreshToken.toString()));
            if (preRefreshTokenDto != null){
                //删除key为refresh_token的数据
                commonRedisTemplate.delete(getRefreshTokenCacheKey(preRefreshToken.toString()));
                CacheRefreshTokenDto preRefreshTokenDtoTrans = (CacheRefreshTokenDto) preRefreshTokenDto;
                Object preToken = commonRedisTemplate.opsForValue().get(getAccessTokenCacheKey(preRefreshTokenDtoTrans.getLastToken()));
                if (preToken != null){
                    //删除key为access_token的数据
                    commonRedisTemplate.delete(getAccessTokenCacheKey(preRefreshTokenDtoTrans.getLastToken()));
                }
            }
        }
    }

    /**
     * 获取保存refresh_token时连带保存的client_id缓存key
     * @param clientId client_id
     * @return 拼接好的缓存key
     */
    public static String getRefreshTokenClientIdCacheKey(String clientId){
        return CommonEnum.REFRESH_TOKEN_CLIENT_ID_CACHE_PREFIX.getValue() + clientId;
    }

    /**
     * 获取refresh_token缓存key
     * @param refreshToken refresh_token
     * @return 拼接好的缓存key
     */
    public static String getRefreshTokenCacheKey(String refreshToken){
        return CommonEnum.REFRESH_TOKEN_CACHE_PREFIX.getValue() + refreshToken;
    }

    /**
     * 获取access_token缓存key
     * @param accessToken access_token
     * @return 拼接好的缓存key
     */
    public static String getAccessTokenCacheKey(String accessToken){
        return CommonEnum.ACCESS_TOKEN_CACHE_PREFIX.getValue() + accessToken;
    }
}
