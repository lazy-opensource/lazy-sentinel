package com.lazy.sentinel.entity;



import com.lazy.cheetah.core.entity.BaseEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author laizhiyuan
 * @date 2018/1/6.
 * <p>token生命周期实体</p>
 */
@Entity
@Table(name = "t_token_cycle_rule", schema = "lazy_senitnel", catalog = "")
public class TTokenCycleRuleEntity extends BaseEntity {

    private static final long serialVersionUID = 9865728L;

    private long cycle;
    private int initRefreshTokenCount;
    private int refreshTokenCount;
    private long tokenExpires;
    private long refreshTokenExpires;

    @Basic
    @Column(name = "cycle", nullable = false)
    public long getCycle() {
        return cycle;
    }

    public void setCycle(long cycle) {
        this.cycle = cycle;
    }

    @Basic
    @Column(name = "init_refresh_token_count", nullable = false)
    public int getInitRefreshTokenCount() {
        return initRefreshTokenCount;
    }

    public void setInitRefreshTokenCount(int initRefreshTokenCount) {
        this.initRefreshTokenCount = initRefreshTokenCount;
    }

    @Basic
    @Column(name = "refresh_token_count", nullable = false)
    public int getRefreshTokenCount() {
        return refreshTokenCount;
    }

    public void setRefreshTokenCount(int refreshTokenCount) {
        this.refreshTokenCount = refreshTokenCount;
    }

    @Basic
    @Column(name = "token_expires", nullable = false)
    public long getTokenExpires() {
        return tokenExpires;
    }

    public void setTokenExpires(long tokenExpires) {
        this.tokenExpires = tokenExpires;
    }

    @Basic
    @Column(name = "refresh_token_expires", nullable = false)
    public long getRefreshTokenExpires() {
        return refreshTokenExpires;
    }

    public void setRefreshTokenExpires(long refreshTokenExpires) {
        this.refreshTokenExpires = refreshTokenExpires;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TTokenCycleRuleEntity that = (TTokenCycleRuleEntity) o;

        if (id != that.id) return false;
        if (cycle != that.cycle) return false;
        if (initRefreshTokenCount != that.initRefreshTokenCount) return false;
        if (refreshTokenCount != that.refreshTokenCount) return false;
        if (tokenExpires != that.tokenExpires) return false;
        if (refreshTokenExpires != that.refreshTokenExpires) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = result + (int) (cycle ^ (cycle >>> 32));
        result = 31 * result + initRefreshTokenCount;
        result = 31 * result + refreshTokenCount;
        result = 31 * result + (int) (tokenExpires ^ (tokenExpires >>> 32));
        result = 31 * result + (int) (refreshTokenExpires ^ (refreshTokenExpires >>> 32));
        return result;
    }
}
