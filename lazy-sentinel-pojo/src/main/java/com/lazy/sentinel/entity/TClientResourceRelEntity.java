package com.lazy.sentinel.entity;



import com.lazy.cheetah.core.entity.BaseEntity;

import javax.persistence.*;

/**
 * @author laizhiyuan
 * @date 2018/1/5.
 * <p>对接客户端-资源关联关系实体</p>
 */
@Entity
@Table(
        name = "t_client_resource_rel",
        uniqueConstraints = {
                @UniqueConstraint(name = "uniq_cli_res_rel", columnNames = {"cliId", "resId"})
        },
        schema = "lazy_senitnel",
        catalog = "")
public class TClientResourceRelEntity extends BaseEntity {

    /**
     * 序列化后的版本标识符
     */
    protected static final long serialVersionUID = 5639852L;

    private long cliId;
    private long resId;

    @Basic
    @Column(name = "cli_id", nullable = false)
    public long getCliId() {
        return cliId;
    }

    public void setCliId(long cliId) {
        this.cliId = cliId;
    }

    @Basic
    @Column(name = "res_id", nullable = false)
    public long getResId() {
        return resId;
    }

    public void setResId(long resId) {
        this.resId = resId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TClientResourceRelEntity that = (TClientResourceRelEntity) o;

        if (id != that.id) return false;
        if (cliId != that.cliId) return false;
        if (resId != that.resId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (cliId ^ (cliId >>> 32));
        result = 31 * result + (int) (resId ^ (resId >>> 32));
        return result;
    }
}
