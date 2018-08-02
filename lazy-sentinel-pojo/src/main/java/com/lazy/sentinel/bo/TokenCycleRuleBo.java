package com.lazy.sentinel.bo;


import com.lazy.sentinel.entity.TTokenCycleRuleEntity;

/**
 * @author laizhiyuan
 * @date 2018/1/9.
 * <p>token生命周期规则业务(business object)类</p>
 */
public class TokenCycleRuleBo {

    /**
     * 生命周期
     */
    private long cycle;
    /**
     * 一个周期内初始化初始化refresh_token次数
     */
    private int initRefreshTokenCount;
    /**
     * 在一个周期内刷新token次数
     */
    private int refreshTokenCount;
    /**
     * token有效期 单位:s
     */
    private long tokenExpires;
    /**
     * refresh_token有效期 单位:s
     */
    private long refreshTokenExpires;
    /**
     * 启用状态
     */
    private String status;

    public long getCycle() {
        return cycle;
    }

    public void setCycle(long cycle) {
        this.cycle = cycle;
    }

    public int getInitRefreshTokenCount() {
        return initRefreshTokenCount;
    }

    public void setInitRefreshTokenCount(int initRefreshTokenCount) {
        this.initRefreshTokenCount = initRefreshTokenCount;
    }

    public int getRefreshTokenCount() {
        return refreshTokenCount;
    }

    public void setRefreshTokenCount(int refreshTokenCount) {
        this.refreshTokenCount = refreshTokenCount;
    }

    public long getTokenExpires() {
        return tokenExpires;
    }

    public void setTokenExpires(long tokenExpires) {
        this.tokenExpires = tokenExpires;
    }

    public long getRefreshTokenExpires() {
        return refreshTokenExpires;
    }

    public void setRefreshTokenExpires(long refreshTokenExpires) {
        this.refreshTokenExpires = refreshTokenExpires;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * entity to business object
     * @param entity 实体
     * @return com.tcl.b2bapi.oauth2.bo.TokenCycleRuleBo
     */
    public static TokenCycleRuleBo entity2Bo(TTokenCycleRuleEntity entity){
        if (entity == null){
            return null;
        }
        TokenCycleRuleBo bo = new TokenCycleRuleBo();
        bo.setCycle(entity.getCycle());
        bo.setInitRefreshTokenCount(entity.getInitRefreshTokenCount());
        bo.setRefreshTokenCount(entity.getRefreshTokenCount());
        bo.setStatus(entity.getValidStatus());
        bo.setRefreshTokenExpires(entity.getRefreshTokenExpires());
        bo.setTokenExpires(entity.getTokenExpires());
        return bo;
    }

    @Override
    public String toString() {
        return "TokenCycleRuleBo{" +
                "cycle=" + cycle +
                ", initRefreshTokenCount=" + initRefreshTokenCount +
                ", refreshTokenCount=" + refreshTokenCount +
                ", tokenExpires=" + tokenExpires +
                ", refreshTokenExpires=" + refreshTokenExpires +
                ", status='" + status + '\'' +
                '}';
    }
}
