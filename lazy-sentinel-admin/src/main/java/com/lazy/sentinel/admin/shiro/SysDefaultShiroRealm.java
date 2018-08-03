package com.lazy.sentinel.admin.shiro;

import com.lazy.cheetah.common.tools.AssertUtils;
import com.lazy.cheetah.common.tools.SetsUtils;
import com.lazy.cheetah.core.shiro.SysDefaultShiroSimpleByteSource;
import com.lazy.sentinel.permission.api.PermissionServiceRegistry;
import com.lazy.sentinel.permission.api.entity.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *     系统默认认证授权默认实现
 * </p>
 *
 * @author laizhiyuan
 * @date 2018/8/2.
 */
public class SysDefaultShiroRealm extends AuthorizingRealm {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SysDefaultShiroRealm.class);

    /**
     * 清空授权缓存
     */
    public void clearAllAuthorizationInfo(){
        super.doClearCache(SecurityUtils.getSubject().getPrincipals());
    }

    /**
     * 登录后授权
     * 为当前subject授权
     * @param principals 令牌
     * @return AuthorizationInfo 授权对象
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        this.authorization(username, authorizationInfo);
        return authorizationInfo;
    }

    /**
     * 登录时认证
     * 认证登陆subject身份
     * @param token
     * @return AuthenticationInfo
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        TPermissionsUserEntity user = PermissionServiceRegistry.getInstance().iPermissionsUserService.findOneByLoginName(username);
        if(user == null) {
            //没找到帐号
            throw new UnknownAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getLoginName(),
                user.getPassword(),
                new SysDefaultShiroSimpleByteSource(user.getLoginName() + user.getSalt()),
                getName()
        );
        return authenticationInfo;
    }

    /**
     * 授权
     * @param loginName 登录用户名
     * @param authorizationInfo 授权对象
     */
    public void authorization(String loginName, SimpleAuthorizationInfo authorizationInfo){
        AssertUtils.isNull(authorizationInfo, "params authorizationInfo is blank");
        Set<String> roleCodes = SetsUtils.set();
        Set<String> resourceCodes = SetsUtils.set();
        TPermissionsUserEntity userEntity = PermissionServiceRegistry.getInstance().iPermissionsUserService.findOneByLoginName(loginName);
        List<TPermissionsUserRoleRelEntity> userRoleRelEntityList = PermissionServiceRegistry.getInstance().iPermissionsUserRoleRelService.findAllByUid(userEntity.getId());
        if (userRoleRelEntityList != null && !userRoleRelEntityList.isEmpty()){
            Set<Long> roleIds = SetsUtils.set(userRoleRelEntityList.size());
            for (TPermissionsUserRoleRelEntity entity : userRoleRelEntityList){
                roleIds.add(entity.getRid());
            }
            List<TPermissionsRoleEntity> roleEntityList = (List<TPermissionsRoleEntity>) PermissionServiceRegistry.getInstance().iPermissionsRoleService.findAll(roleIds);
            if (roleEntityList != null && !roleEntityList.isEmpty()){
                for (TPermissionsRoleEntity entity : roleEntityList){
                    roleCodes.add(entity.getRoleCode());
                }
            }else {
                LOGGER.info(String.format("loginName: %s 关联的角色id不存在", loginName));
            }
            List<TPermissionsResRoleRelEntity> resRoleRelEntityList = PermissionServiceRegistry.getInstance().iPermissionsResRoleRelService.findAllByRoleIdIn(roleIds);
            if (resRoleRelEntityList != null && !resRoleRelEntityList.isEmpty()){
                Set<Long> resIds = SetsUtils.set(resRoleRelEntityList.size());
                for (TPermissionsResRoleRelEntity entity : resRoleRelEntityList){
                    resIds.add(entity.getResId());
                }
                List<TPermissionsResourceEntity> resourceEntityList = (List<TPermissionsResourceEntity>) PermissionServiceRegistry.getInstance().iPermissionsResourceService.findAll(resIds);
                if (resourceEntityList != null && !resourceEntityList.isEmpty()){
                    for (TPermissionsResourceEntity entity : resourceEntityList){
                        resourceCodes.add(entity.getResCode());
                    }
                }else {
                    LOGGER.info(String.format("loginName: %s 关联的资源id不存在", loginName));
                }
            }else {
                LOGGER.info(String.format("loginName: %s 没有查到关联的资源", loginName));
            }
        }else {
            LOGGER.info(String.format("loginName: %s 没有查到关联的角色", loginName));
        }
        authorizationInfo.addRoles(roleCodes);
        authorizationInfo.addStringPermissions(resourceCodes);
    }

}
