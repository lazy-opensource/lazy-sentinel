package com.hcl.common.permissioncenter.api;


import com.hcl.common.permissioncenter.api.entity.TPermissionsUserEntity;
import com.lazy.cheetah.core.service.IBaseService;

/**
 * <p>系统权限-普通用户原子服务接口</p>
 *
 * @author laizhiyuan
 * @date 2018/03/23
 */
public interface IPermissionsUserService extends IBaseService<TPermissionsUserEntity, Long> {

    /**
     * 根据登录名称获取一条数据
     * @param loginName 登录名
     * @return 超级用户实体
     */
    TPermissionsUserEntity findOneByLoginName(String loginName);
}
