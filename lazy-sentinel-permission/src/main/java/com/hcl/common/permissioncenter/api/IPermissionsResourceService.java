package com.hcl.common.permissioncenter.api;


import com.hcl.common.permissioncenter.api.dto.SysPermissionTreeDto;
import com.hcl.common.permissioncenter.api.entity.TPermissionsResourceEntity;
import com.lazy.cheetah.core.service.IBaseService;

import java.util.List;

/**
 * <p>系统权限-资源原子服务接口</p>
 *
 * @author laizhiyuan
 * @date 2018/03/23
 */
public interface IPermissionsResourceService extends IBaseService<TPermissionsResourceEntity, Long> {

    /**
     * 获取系统资源树列表
     * @return 树列表
     */
    List<SysPermissionTreeDto> tree();

    /**
     * 获取某个用户的某个资源类型列表
     * @param uId 用户id
     * @param resType 资源类型
     * @return 资源列表
     */
    List<SysPermissionTreeDto> treeByUidAndResType(Long uId, String resType);

    /**
     * 获取某个用户某个父级资源下的某个资源类型列表
     * @param uId 用户id
     * @param pId 父级id
     * @param resType 资源类型
     * @return 资源列表
     */
    List<SysPermissionTreeDto> treeByPidAndUidAndResType(Long uId, Long pId, String resType);

    /**
     * 获取某个用户某个资源类型某个父级菜单下的子菜单列表
     * @param uId 用户id
     * @param pId 父级id
     * @param resType 资源类型
     * @return 导航栏菜单列表
     */
    List<TPermissionsResourceEntity> findAllByPidAndUidAndResType(Long uId, Long pId, String resType);

    /**
     * 通过资源编码查数据
     * @param resCode 资源编码
     * @return 实体对象
     */
    TPermissionsResourceEntity findByResCode(String resCode);

    /**
     * 根据资源id生成面包屑
     * @param id 资源id
     * @return 面包屑列表
     */
    List<SysPermissionTreeDto> genCrumbs(Long id);
}
