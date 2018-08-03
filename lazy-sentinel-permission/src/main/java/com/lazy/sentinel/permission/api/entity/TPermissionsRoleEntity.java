package com.lazy.sentinel.permission.api.entity;



import com.lazy.cheetah.core.entity.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

/**
 * <p>系统权限-角色实体类</p>
 *
 * @author laizhiyuan
 * @date 2018/03/23
 */
@Entity
@Table(
        name = "t_permissions_role",
        schema = "lazy-sentinel",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_role_code", columnNames = {"roleCode"})
        },
        catalog = ""
)
public class TPermissionsRoleEntity extends BaseEntity {
    private static final long serialVersionUID = 98777342456L;

    /**
     * 上级角色id
     */
    private Long pid;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编码
     */
    private String roleCode;

    @Basic
    @Column(name = "pid")
    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "role_code")
    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TPermissionsRoleEntity that = (TPermissionsRoleEntity) o;
        return id == that.id &&
                pid == that.pid &&
                Objects.equals(roleName, that.roleName) &&
                Objects.equals(roleCode, that.roleCode) &&
                Objects.equals(validStatus, that.validStatus) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(lastUpdateTime, that.lastUpdateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, pid, roleName, roleCode, validStatus, createTime, lastUpdateTime);
    }
}
