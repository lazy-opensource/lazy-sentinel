package com.lazy.sentinel.entity;


import com.lazy.cheetah.core.entity.BaseEntity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * <p>客户端信息实体</p>
 *
 * @author laizhiyuan
 * @date 2018/3/21.
 */
@Entity
@Table(
        name = "t_client_info",
        uniqueConstraints = {
                @UniqueConstraint(name = "uniq_client_info_client_id", columnNames = {"cliId"})
        },
        schema = "lazy_senitnel",
        catalog = "")
public class TClientInfoEntity extends BaseEntity {
    /**
     * 序列化后的版本标识符
     */
    private static final long serialVersionUID = -69396832516L;
    private String cliName;
    private String cliId;
    private String cliSecret;
    private String useStatus;
    private Integer initRefreshTokenCount;
    private Timestamp initRefreshTokenBeginTime;

    @Basic
    @Column(name = "cli_name", nullable = false, length = 40)
    public String getCliName() {
        return cliName;
    }

    public void setCliName(String cliName) {
        this.cliName = cliName;
    }

    @Basic
    @Column(name = "cli_id", nullable = false, length = 60)
    public String getCliId() {
        return cliId;
    }

    public void setCliId(String cliId) {
        this.cliId = cliId;
    }

    @Basic
    @Column(name = "cli_secret", nullable = false, length = 60)
    public String getCliSecret() {
        return cliSecret;
    }

    public void setCliSecret(String cliSecret) {
        this.cliSecret = cliSecret;
    }

    @Basic
    @Column(name = "use_status", nullable = false, length = 5)
    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    @Basic
    @Column(name = "init_refresh_token_count", nullable = true)
    public Integer getInitRefreshTokenCount() {
        return initRefreshTokenCount;
    }

    public void setInitRefreshTokenCount(Integer initRefreshTokenCount) {
        this.initRefreshTokenCount = initRefreshTokenCount;
    }

    @Basic
    @Column(name = "init_refresh_token_begin_time", nullable = true)
    public Timestamp getInitRefreshTokenBeginTime() {
        return initRefreshTokenBeginTime;
    }

    public void setInitRefreshTokenBeginTime(Timestamp initRefreshTokenBeginTime) {
        this.initRefreshTokenBeginTime = initRefreshTokenBeginTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TClientInfoEntity that = (TClientInfoEntity) o;

        if (id != that.id) return false;
        if (cliName != null ? !cliName.equals(that.cliName) : that.cliName != null) return false;
        if (cliId != null ? !cliId.equals(that.cliId) : that.cliId != null) return false;
        if (cliSecret != null ? !cliSecret.equals(that.cliSecret) : that.cliSecret != null) return false;
        if (useStatus != null ? !useStatus.equals(that.useStatus) : that.useStatus != null) return false;
        if (initRefreshTokenCount != null ? !initRefreshTokenCount.equals(that.initRefreshTokenCount) : that.initRefreshTokenCount != null)
            return false;
        if (initRefreshTokenBeginTime != null ? !initRefreshTokenBeginTime.equals(that.initRefreshTokenBeginTime) : that.initRefreshTokenBeginTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (cliName != null ? cliName.hashCode() : 0);
        result = 31 * result + (cliId != null ? cliId.hashCode() : 0);
        result = 31 * result + (cliSecret != null ? cliSecret.hashCode() : 0);
        result = 31 * result + (useStatus != null ? useStatus.hashCode() : 0);
        result = 31 * result + (initRefreshTokenCount != null ? initRefreshTokenCount.hashCode() : 0);
        result = 31 * result + (initRefreshTokenBeginTime != null ? initRefreshTokenBeginTime.hashCode() : 0);
        return result;
    }
}
