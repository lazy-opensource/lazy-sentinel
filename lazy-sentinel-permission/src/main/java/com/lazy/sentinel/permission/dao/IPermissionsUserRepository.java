package com.lazy.sentinel.permission.dao;


import com.lazy.sentinel.permission.api.entity.TPermissionsUserEntity;
import com.lazy.cheetah.core.repository.IBaseRepository;

/**
 * <p>系统权限-普通用户数据仓库接口</p>
 *
 * @author laizhiyuan
 * @date 2018/03/23
 */
public interface IPermissionsUserRepository extends IBaseRepository<TPermissionsUserEntity, Long> {

    /**
     * 根据登录名称获取一条数据
     * @param loginName 登录名
     * @return 超级用户实体
     */
    TPermissionsUserEntity findOneByLoginName(String loginName);
}
