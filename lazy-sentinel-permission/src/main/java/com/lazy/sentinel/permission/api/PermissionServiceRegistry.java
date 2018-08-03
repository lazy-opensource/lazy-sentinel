package com.lazy.sentinel.permission.api;

import com.lazy.cheetah.common.annotation.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * <p>
 *     环卫权限中心原子服务注册表
 *     管理所有的服务组件，任何地方需要权限中心原子服务均通过该注册表获取
 * </p>
 *
 * @author laizhiyuan
 * @date 2018/3/24.
 */
@ServiceRegistry
public class PermissionServiceRegistry {


    /*************************** 服务注册Start ***************************/
    //系统权限-普通用户原子服务接口
    @Autowired  public IPermissionsUserService iPermissionsUserService;
    //系统权限-用户组原子服务接口
    @Autowired  public IPermissionsGroupService iPermissionsGroupService;
    //系统权限-资源和用户组多对多关联关系原子服务接口
    @Autowired  public IPermissionsResGroupRelService iPermissionsResGroupRelService;
    //系统权限-资源原子服务接口
    @Autowired  public IPermissionsResourceService iPermissionsResourceService;
    //系统权限-资源和角色多对多关联关系原子服务接口
    @Autowired  public IPermissionsResRoleRelService iPermissionsResRoleRelService;
    //系统权限-角色和用户组多对多关联关系原子服务接口
    @Autowired  public IPermissionsRoleGroupRelService iPermissionsRoleGroupRelService;
    //系统权限-角色原子服务接口
    @Autowired  public IPermissionsRoleService iPermissionsRoleService;
    //系统权限-用户和用户组原子服务接口
    @Autowired  public IPermissionsUserGroupRelService iPermissionsUserGroupRelService;
    //系统权限-用户和角色多对多关联关系原子服务接口
    @Autowired  public IPermissionsUserRoleRelService iPermissionsUserRoleRelService;
    /*************************** 服务注册End ***************************/
    /**
     * 单例对象
     */
    private static PermissionServiceRegistry singleton;

    /**
     * 构造钩子函数
     */
    @PostConstruct
    public void init() {
        if (singleton == null) {
            singleton = this;
        }
    }
    /**
     * 获取单例对象
     * @return self
     */
    public static PermissionServiceRegistry getInstance() {
        return singleton;
    }
}
