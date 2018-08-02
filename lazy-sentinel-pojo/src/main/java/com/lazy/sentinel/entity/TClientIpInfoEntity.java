package com.lazy.sentinel.entity;


import com.lazy.cheetah.core.entity.BaseEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p></p>
 *
 * @author laizhiyuan
 * @date 2018/3/21.
 */
@Entity
@Table(name = "t_client_ip_info", schema = "lazy_senitnel", catalog = "")
public class TClientIpInfoEntity extends BaseEntity {
    /**
     * 序列化后的版本标识符
     */
    private static final long serialVersionUID = 6532985671L;
    private String domain;
    private String ip;
    private String cliId;

    @Basic
    @Column(name = "domain", nullable = false, length = 30)
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Basic
    @Column(name = "ip", nullable = false, length = 30)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "cli_id", nullable = false, length = 60)
    public String getCliId() {
        return cliId;
    }

    public void setCliId(String cliId) {
        this.cliId = cliId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TClientIpInfoEntity that = (TClientIpInfoEntity) o;

        if (id != that.id) return false;
        if (domain != null ? !domain.equals(that.domain) : that.domain != null) return false;
        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;
        if (cliId != null ? !cliId.equals(that.cliId) : that.cliId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (domain != null ? domain.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (cliId != null ? cliId.hashCode() : 0);
        return result;
    }
}
