package com.lazy.sentinel.admin.controller.permissions.vo;


import com.lazy.sentinel.permission.api.entity.TPermissionsUserEntity;

import java.io.Serializable;

/**
 * <p>用户信息vo</p>
 *
 * @author laizhiyuan
 * @date 2018/4/6.
 */
public class SysPermissionsUserVo implements Serializable {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 99896999944366242L;

    /**
     * 实体对象
     */
    private TPermissionsUserEntity entity;

    /**
     * 角色名称
     */
    private String roleName;

    public TPermissionsUserEntity getEntity() {
        return entity;
    }

    public void setEntity(TPermissionsUserEntity entity) {
        this.entity = entity;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
