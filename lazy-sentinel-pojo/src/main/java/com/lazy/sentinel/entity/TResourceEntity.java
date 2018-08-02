package com.lazy.sentinel.entity;


import com.lazy.cheetah.core.entity.BaseEntity;

import javax.persistence.*;

/**
 * @author laizhiyuan
 * @date 2018/1/5.
 * <p>对接服务方资源实体</p>
 */
@Entity
@Table(
        name = "t_resource",
        uniqueConstraints = {
                @UniqueConstraint(name = "uniq_res_code", columnNames = {"resCode", "ownSysId"})
        },
        schema = "lazy_senitnel",
        catalog = "")
public class TResourceEntity extends BaseEntity {

    private static final long serialVersionUID = 965813257L;


    private String resName;
    private String resCode;
    private String resServeName;
    private String resUri;
    private long ownSysId;
    private long resTypeId;
    private String validStatus;

    @Basic
    @Column(name = "res_name", nullable = false, length = 60)
    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    @Basic
    @Column(name = "res_code", nullable = false, length = 60)
    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    @Basic
    @Column(name = "res_serve_name", nullable = true, length = 60)
    public String getResServeName() {
        return resServeName;
    }

    public void setResServeName(String resServeName) {
        this.resServeName = resServeName;
    }

    @Basic
    @Column(name = "res_uri", nullable = true, length = 100)
    public String getResUri() {
        return resUri;
    }

    public void setResUri(String resUri) {
        this.resUri = resUri;
    }

    @Basic
    @Column(name = "own_sys_id", nullable = false)
    public long getOwnSysId() {
        return ownSysId;
    }

    public void setOwnSysId(long ownSysId) {
        this.ownSysId = ownSysId;
    }

    @Basic
    @Column(name = "res_type_id", nullable = false)
    public long getResTypeId() {
        return resTypeId;
    }

    public void setResTypeId(long resTypeId) {
        this.resTypeId = resTypeId;
    }

    @Basic
    @Column(name = "valid_status", nullable = false, length = 5)
    public String getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TResourceEntity that = (TResourceEntity) o;

        if (id != that.id) return false;
        if (ownSysId != that.ownSysId) return false;
        if (resTypeId != that.resTypeId) return false;
        if (resName != null ? !resName.equals(that.resName) : that.resName != null) return false;
        if (resCode != null ? !resCode.equals(that.resCode) : that.resCode != null) return false;
        if (resServeName != null ? !resServeName.equals(that.resServeName) : that.resServeName != null) return false;
        if (resUri != null ? !resUri.equals(that.resUri) : that.resUri != null) return false;
        if (validStatus != null ? !validStatus.equals(that.validStatus) : that.validStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (resName != null ? resName.hashCode() : 0);
        result = 31 * result + (resCode != null ? resCode.hashCode() : 0);
        result = 31 * result + (resServeName != null ? resServeName.hashCode() : 0);
        result = 31 * result + (resUri != null ? resUri.hashCode() : 0);
        result = 31 * result + (int) (ownSysId ^ (ownSysId >>> 32));
        result = 31 * result + (int) (resTypeId ^ (resTypeId >>> 32));
        result = 31 * result + (validStatus != null ? validStatus.hashCode() : 0);
        return result;
    }
}
