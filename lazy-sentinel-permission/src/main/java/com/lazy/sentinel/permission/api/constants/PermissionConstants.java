package com.lazy.sentinel.permission.api.constants;

/**
 * <p>系统权限-常量类</p>
 *
 * @author laizhiyuan
 * @date 2018/3/28.
 */
public class PermissionConstants {

    /** 后台路由资源*/
    public static final String RES_TYPE_URI = "uri";
    /** 菜单资源*/
    public static final String RES_TYPE_MENU = "menu";
    /** 没有父级 根 超级管理员 超级管理员角色等标识*/
    public static final Long ROOT_PID = -1L;
    /** 角色和资源关联关系定义可以再次分配*/
    public static final String ALLOCATED_CAN = "CAN";
    /** 角色和资源关联关系定义不可以再次分配*/
    public static final String ALLOCATED_NOT_CAN = "NOT_CAN";
    /** 系统默认菜单数量*/
    public static final int SYS_DEFAULT_MENU_NUM = 10;
}
