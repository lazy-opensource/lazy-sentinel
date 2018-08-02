package com.hcl.common.permissioncenter.api.entity;



import com.lazy.cheetah.core.entity.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

/**
 * <p>系统权限-资源角色多对多关联关系实体类</p>
 *
 * @author laizhiyuan
 * @date 2018/03/23
 */
@Entity
@Table(name = "t_permissions_res_role_rel", schema = "sanitation-admin", catalog = "")
public class TPermissionsResRoleRelEntity extends BaseEntity {
    private static final long serialVersionUID = 9912222999456L;

    /**
     * 资源表主键
     */
    private Long resId;

    /**
     * 角色表主键
     */
    private Long roleId;

    /**
     * CAN: 可以再次配置 CAN_NOT：不可以再次配置
     */
    private String allocated;

    @Basic
    @Column(name = "res_id")
    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }

    @Basic
    @Column(name = "role_id")
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "allocated")
    public String getAllocated() {
        return allocated;
    }

    public void setAllocated(String allocated) {
        this.allocated = allocated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TPermissionsResRoleRelEntity that = (TPermissionsResRoleRelEntity) o;
        return id == that.id &&
                resId == that.resId &&
                roleId == that.roleId &&
                Objects.equals(allocated, that.allocated) &&
                Objects.equals(validStatus, that.validStatus) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(lastUpdateTime, that.lastUpdateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, resId, roleId, allocated, validStatus, createTime, lastUpdateTime);
    }
}
