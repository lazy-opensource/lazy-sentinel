package com.lazy.sentinel.api;


import com.lazy.cheetah.core.service.IBaseService;
import com.lazy.sentinel.entity.TResourceEntity;

/**
 * @author laizhiyuan
 * @date 2018/1/3.
 * <p>资源服务层接口</p>
 */
public interface IResourceService extends IBaseService<TResourceEntity, Long> {

    /**
     * 通过资源编码和所属系统类型查找
     * @param resCode 资源编码
     * @param ownSysId 资源归属系统表主键
     * @return 资源实体对象
     */
    TResourceEntity findByResCodeAndOwnSysId(String resCode, Long ownSysId);
}
