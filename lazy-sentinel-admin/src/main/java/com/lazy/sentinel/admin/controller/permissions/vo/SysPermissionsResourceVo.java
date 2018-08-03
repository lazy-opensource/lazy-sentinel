package com.lazy.sentinel.admin.controller.permissions.vo;


import com.lazy.sentinel.permission.api.entity.TPermissionsResourceEntity;

import java.io.Serializable;

/**
 * <p>系统权限-资源视图实体</p>
 *
 * @author laizhiyuan
 * @date 2018/4/9.
 */
public class SysPermissionsResourceVo implements Serializable {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 9989944366242L;
    /**
     * 实体对象
     */
    private TPermissionsResourceEntity entity;
    /**
     * 主键
     */
    private String id;

    public TPermissionsResourceEntity getEntity() {
        return entity;
    }

    public void setEntity(TPermissionsResourceEntity entity) {
        this.entity = entity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
