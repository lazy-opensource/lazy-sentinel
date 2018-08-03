package com.lazy.sentinel.permission.api.dto;


import com.lazy.cheetah.common.tools.SetsUtils;

import java.io.Serializable;
import java.util.List;

/**
 * <p>系统权限树 DTO</p>
 *
 * @author laizhiyuan
 * @date 2018/3/28.
 */
public class SysPermissionTreeDto implements Serializable, Comparable<SysPermissionTreeDto>{
    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = -9630657824L;
    /**
     * id
     */
    private String id;
    private String value;
    /**
     * name
     */
    private String name;
    private String title;

    /**
     * desc
     */
    private String desc;
    /**
     * code
     */
    private String code;
    /**
     * uri
     */
    private String uri;
    /**
     * icon
     */
    private String icon;
    /**
     * 资源类型
     */
    private String resType;
    /**
     * 排序
     */
    private Integer order;
    /**
     * 是否新打开窗口
     */
    private String openWindow;
    /**
     * children
     */
    private List<SysPermissionTreeDto> children = SetsUtils.list();
    private List<SysPermissionTreeDto> data = SetsUtils.list();

    public String getOpenWindow() {
        return openWindow;
    }

    public void setOpenWindow(String openWindow) {
        this.openWindow = openWindow;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<SysPermissionTreeDto> getChildren() {
        return children;
    }

    public void setChildren(List<SysPermissionTreeDto> children) {
        this.children = children;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SysPermissionTreeDto> getData() {
        return data;
    }

    public void setData(List<SysPermissionTreeDto> data) {
        this.data = data;
    }

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType;
    }

    @Override
    public int compareTo(SysPermissionTreeDto o) {
        return this.getOrder() - o.getOrder();
    }
}
