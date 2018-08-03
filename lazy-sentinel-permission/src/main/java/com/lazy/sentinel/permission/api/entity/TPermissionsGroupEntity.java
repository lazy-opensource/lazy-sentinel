package com.lazy.sentinel.permission.api.entity;



import com.lazy.cheetah.core.entity.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

/**
 * <p>系统权限-用户组实体类</p>
 *
 * @author laizhiyuan
 * @date 2018/03/23
 */
@Entity
@Table(name = "t_permissions_group",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_group_code", columnNames = {"groupCode"})
        },
        schema = "lazy-sentinel", catalog = "")
public class TPermissionsGroupEntity extends BaseEntity {
    private static final long serialVersionUID = 6722222456L;

    /**
     * 父级用户组id
     */
    private Long pid;

    /**
     * 用户组名称
     */
    private String groupName;

    /**
     * 用户组code
     */
    private String groupCode;

    @Basic
    @Column(name = "pid")
    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "group_name")
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Basic
    @Column(name = "group_code")
    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TPermissionsGroupEntity that = (TPermissionsGroupEntity) o;
        return id == that.id &&
                pid == that.pid &&
                Objects.equals(groupName, that.groupName) &&
                Objects.equals(groupCode, that.groupCode) &&
                Objects.equals(validStatus, that.validStatus) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(lastUpdateTime, that.lastUpdateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, pid, groupName, groupCode, validStatus, createTime, lastUpdateTime);
    }
}
