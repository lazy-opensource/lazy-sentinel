package com.lazy.sentinel.entity;


import com.lazy.cheetah.core.entity.BaseEntity;

import javax.persistence.*;

/**
 * @author laizhiyuan
 * @date 2018/1/5.
 * <p>资源类型实体</p>
 */
@Entity
@Table(name = "t_resource_type",
        uniqueConstraints = {
                @UniqueConstraint(name = "uniq_resource_type_code", columnNames = {"resTypeCode"})
        },
        schema = "lazy_senitnel", catalog = "")
public class TResourceTypeEntity extends BaseEntity {

    private static final long serialVersionUID = -9865321L;

    private String resTypeName;
    private String resTypeCode;

    @Basic
    @Column(name = "res_type_name", nullable = false, length = 40)
    public String getResTypeName() {
        return resTypeName;
    }

    public void setResTypeName(String resTypeName) {
        this.resTypeName = resTypeName;
    }

    @Basic
    @Column(name = "res_type_code", nullable = false, length = 40)
    public String getResTypeCode() {
        return resTypeCode;
    }

    public void setResTypeCode(String resTypeCode) {
        this.resTypeCode = resTypeCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TResourceTypeEntity that = (TResourceTypeEntity) o;

        if (id != that.id) return false;
        if (resTypeName != null ? !resTypeName.equals(that.resTypeName) : that.resTypeName != null) return false;
        if (resTypeCode != null ? !resTypeCode.equals(that.resTypeCode) : that.resTypeCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (resTypeName != null ? resTypeName.hashCode() : 0);
        result = 31 * result + (resTypeCode != null ? resTypeCode.hashCode() : 0);
        return result;
    }
}
