package com.hcl.common.permissioncenter.api.entity;



import com.lazy.cheetah.core.entity.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

/**
 * <p>系统权限-用户和用户组多对多关联关系实体类</p>
 *
 * @author laizhiyuan
 * @date 2018/03/23
 */
@Entity
@Table(name = "t_permissions_user_group_rel", schema = "sanitation-admin", catalog = "")
public class TPermissionsUserGroupRelEntity extends BaseEntity {
    private static final long serialVersionUID = 96356413667858466L;

    /**
     * 用户主键
     */
    private Long uid;

    /**
     * 用户组主键
     */
    private Long gid;

    @Basic
    @Column(name = "uid")
    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
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
        TPermissionsUserGroupRelEntity that = (TPermissionsUserGroupRelEntity) o;
        return id == that.id &&
                uid == that.uid &&
                gid == that.gid &&
                Objects.equals(validStatus, that.validStatus) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(lastUpdateTime, that.lastUpdateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, uid, gid, validStatus, createTime, lastUpdateTime);
    }
}
