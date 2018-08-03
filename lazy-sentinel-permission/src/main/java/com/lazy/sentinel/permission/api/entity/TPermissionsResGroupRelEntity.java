package com.lazy.sentinel.permission.api.entity;



import com.lazy.cheetah.core.entity.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

/**
 * <p>系统权限-资源和用户组多对多关联关系实体类</p>
 *
 * @author laizhiyuan
 * @date 2018/03/23
 */
@Entity
@Table(name = "t_permissions_res_group_rel", schema = "lazy-sentinel", catalog = "")
public class TPermissionsResGroupRelEntity extends BaseEntity {
    private static final long serialVersionUID = 6756778999456L;

    /**
     * 资源表主键
     */
    private Long rid;

    /**
     * 用户组主键
     */
    private Long gid;

    @Basic
    @Column(name = "rid")
    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    @Basic
    @Column(name = "gid")
    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TPermissionsResGroupRelEntity that = (TPermissionsResGroupRelEntity) o;
        return id == that.id &&
                rid == that.rid &&
                gid == that.gid &&
                Objects.equals(validStatus, that.validStatus) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(lastUpdateTime, that.lastUpdateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, rid, gid, validStatus, createTime, lastUpdateTime);
    }
}
