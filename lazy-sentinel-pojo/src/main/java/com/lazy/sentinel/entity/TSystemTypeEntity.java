package com.lazy.sentinel.entity;



import com.lazy.cheetah.core.entity.BaseEntity;

import javax.persistence.*;

/**
 * @author laizhiyuan
 * @date 2018/1/5.
 * <p>对接服务方系统类型实体</p>
 */
@Entity
@Table(name = "t_system_type",
        uniqueConstraints = {
                @UniqueConstraint(name = "uniq_sys_type_code", columnNames = {"sysTypeCode"})
        },
        schema = "lazy_senitnel", catalog = "")
public class TSystemTypeEntity extends BaseEntity {

    private static final long serialVersionUID = 98657L;

    private String sysTypeName;
    private String sysTypeCode;

    @Basic
    @Column(name = "sys_type_name", nullable = false, length = 40)
    public String getSysTypeName() {
        return sysTypeName;
    }

    public void setSysTypeName(String sysTypeName) {
        this.sysTypeName = sysTypeName;
    }

    @Basic
    @Column(name = "sys_type_code", nullable = false, length = 40)
    public String getSysTypeCode() {
        return sysTypeCode;
    }

    public void setSysTypeCode(String sysTypeCode) {
        this.sysTypeCode = sysTypeCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TSystemTypeEntity that = (TSystemTypeEntity) o;

        if (id != that.id) return false;
        if (sysTypeName != null ? !sysTypeName.equals(that.sysTypeName) : that.sysTypeName != null) return false;
        if (sysTypeCode != null ? !sysTypeCode.equals(that.sysTypeCode) : that.sysTypeCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (sysTypeName != null ? sysTypeName.hashCode() : 0);
        result = 31 * result + (sysTypeCode != null ? sysTypeCode.hashCode() : 0);
        return result;
    }
}
