package com.hcl.common.permissioncenter.api.entity;



import com.lazy.cheetah.core.entity.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

/**
 * <p>
 *     系统权限-资源实体类
 * </p>
 *
 * @author laizhiyuan
 * @date 2018/03/23
 */
@Entity
@Table(name = "t_permissions_resource", schema = "sanitation-admin", catalog = "")
public class TPermissionsResourceEntity extends BaseEntity {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 908999999456000000L;
    /**
     * 父级资源
     */
    private Long pid;

    /**
     * 资源名称
     */
    private String resName;

    /**
     * 资源描述
     */
    private String resDesc;

    /**
     * 资源编码
     */
    private String resCode;

    /**
     * 排序
     */
    private int resOrder;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 资源图标
     */
    private String icon;

    /**
     * 资源URI
     */
    private String uri;

    /**
     * 资源类型menu:菜单uri:后台路由
     */
    private String resType;
    /**
     * 是否打开新的窗口 默认不打开
     */
    private String openWindow = "N";

    @Basic
    @Column(name = "open_window")
    public String getOpenWindow() {
        return openWindow;
    }

    public void setOpenWindow(String openWindow) {
        this.openWindow = openWindow;
    }

    @Basic
    @Column(name = "pid")
    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "res_name")
    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    @Basic
    @Column(name = "res_desc")
    public String getResDesc() {
        return resDesc;
    }

    public void setResDesc(String resDesc) {
        this.resDesc = resDesc;
    }

    @Basic
    @Column(name = "res_code")
    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    @Basic
    @Column(name = "method")
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Basic
    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Basic
    @Column(name = "uri")
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Basic
    @Column(name = "res_type")
    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType;
    }

    @Basic
    @Column(name = "res_order")
    public int getResOrder() {
        return resOrder;
    }

    public void setResOrder(int resOrder) {
        this.resOrder = resOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TPermissionsResourceEntity that = (TPermissionsResourceEntity) o;
        return id == that.id &&
                pid == that.pid &&
                resOrder == that.resOrder &&
                Objects.equals(resName, that.resName) &&
                Objects.equals(openWindow, that.openWindow) &&
                Objects.equals(resDesc, that.resDesc) &&
                Objects.equals(resCode, that.resCode) &&
                Objects.equals(method, that.method) &&
                Objects.equals(icon, that.icon) &&
                Objects.equals(uri, that.uri) &&
                Objects.equals(resType, that.resType) &&
                Objects.equals(validStatus, that.validStatus) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(lastUpdateTime, that.lastUpdateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, pid, resName,openWindow, resDesc, resCode, resOrder, method, icon, uri, resType, validStatus, createTime, lastUpdateTime);
    }
}
