package com.lazy.sentinel.entity;


import com.lazy.cheetah.core.entity.BaseEntity;

import javax.persistence.*;

/**
 * @author laizhiyuan
 * @date 2018/1/10.
 * <p>对接应用实体</p>
 */
@Entity
@Table(
        name = "t_app_info",
        uniqueConstraints = {
                @UniqueConstraint(name = "uniq_app_info_ip", columnNames = {"appIp"})
        },
        schema = "lazy_senitnel",
        catalog = "")
public class TAppInfoEntity extends BaseEntity {

    /**
     * 序列化后的版本标识符
     */
    private static final long serialVersionUID = 968532158L;


    private String appName;
    private String appIp;
    private String appDomain;

    @Basic
    @Column(name = "app_name", nullable = false, length = 50)
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Basic
    @Column(name = "app_ip", nullable = false, length = 40)
    public String getAppIp() {
        return appIp;
    }

    public void setAppIp(String appIp) {
        this.appIp = appIp;
    }

    @Basic
    @Column(name = "app_domain", nullable = true, length = 40)
    public String getAppDomain() {
        return appDomain;
    }

    public void setAppDomain(String appDomain) {
        this.appDomain = appDomain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TAppInfoEntity that = (TAppInfoEntity) o;

        if (id != that.id) return false;
        if (appName != null ? !appName.equals(that.appName) : that.appName != null) return false;
        if (appIp != null ? !appIp.equals(that.appIp) : that.appIp != null) return false;
        if (appDomain != null ? !appDomain.equals(that.appDomain) : that.appDomain != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (appName != null ? appName.hashCode() : 0);
        result = 31 * result + (appIp != null ? appIp.hashCode() : 0);
        result = 31 * result + (appDomain != null ? appDomain.hashCode() : 0);
        return result;
    }
}
