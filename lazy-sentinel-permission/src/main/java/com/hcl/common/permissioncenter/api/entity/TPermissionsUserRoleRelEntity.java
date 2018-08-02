package com.hcl.common.permissioncenter.api.entity;



import com.lazy.cheetah.core.entity.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

/**
 * <p>系统权限-用户和角色多对多关联关系实体类</p>
 *
 * @author laizhiyuan
 * @date 2018/03/23
 */
@Entity
@Table(name = "t_permissions_user_role_rel", schema = "sanitation-admin", catalog = "")
public class TPermissionsUserRoleRelEntity extends BaseEntity {
    private static final long serialVersionUID = -9567889787653445L;

    /**
     * 用户表主键
     */
    private Long uid;

    /**
     * 角色表主键
     */
    private Long rid;

    @Basic
    @Column(name = "uid")
    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "rid")
    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TPermissionsUserRoleRelEntity that = (TPermissionsUserRoleRelEntity) o;
        return id == that.id &&
                uid == that.uid &&
                rid == that.rid &&
                Objects.equals(validStatus, that.validStatus) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(lastUpdateTime, that.lastUpdateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, uid, rid, validStatus, createTime, lastUpdateTime);
    }
}
