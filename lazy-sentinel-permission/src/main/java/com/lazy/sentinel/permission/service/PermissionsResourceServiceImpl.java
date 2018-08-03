package com.lazy.sentinel.permission.service;


import com.lazy.sentinel.permission.api.IPermissionsResourceService;
import com.lazy.sentinel.permission.api.PermissionServiceRegistry;
import com.lazy.sentinel.permission.api.constants.PermissionConstants;
import com.lazy.sentinel.permission.api.dto.SysPermissionTreeDto;
import com.lazy.sentinel.permission.api.entity.TPermissionsResRoleRelEntity;
import com.lazy.sentinel.permission.api.entity.TPermissionsResourceEntity;
import com.lazy.sentinel.permission.api.entity.TPermissionsUserRoleRelEntity;
import com.lazy.sentinel.permission.dao.IPermissionsResourceRepository;
import com.lazy.cheetah.common.tools.AssertUtils;
import com.lazy.cheetah.common.tools.SetsUtils;
import com.lazy.cheetah.common.tools.StringUtils;
import com.lazy.cheetah.core.helper.EntityHelper;
import com.lazy.cheetah.core.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

/**
 * <p>系统权限-资源原子服务实现类</p>
 *
 * @author laizhiyuan
 * @date 2018/03/23
 */
@Service
@Transactional(
        readOnly = true,
        rollbackFor = {RuntimeException.class},
        isolation = Isolation.DEFAULT,
        propagation = Propagation.REQUIRED
)
public class PermissionsResourceServiceImpl extends BaseServiceImpl<TPermissionsResourceEntity, Long> implements IPermissionsResourceService {

    /**
     * 数据仓库接口
     */
    private IPermissionsResourceRepository repository;

    /**
     * 构造器注入数据仓库接口
     * @param repository 数据仓库接口
     */
    @Autowired
    public PermissionsResourceServiceImpl(IPermissionsResourceRepository repository) {
        super();
        this.setRepository(repository);
        this.repository = repository;
    }

    /**
     * 获取系统资源树列表
     * @return 树列表
     */
    @Override
    public List<SysPermissionTreeDto> tree() {
        List<TPermissionsResourceEntity> resourceEntityList = (List<TPermissionsResourceEntity>) this.findAll();
        return this.genTreeByPid(resourceEntityList, PermissionConstants.ROOT_PID);
    }

    /**
     * 生成树
     * @param resourceEntityList 资源列表
     *@param pid 父级id
     * @return 树
     */
    private List<SysPermissionTreeDto> genTreeByPid(List<TPermissionsResourceEntity> resourceEntityList, Long pid){
        List<TPermissionsResourceEntity> rootTreeList = SetsUtils.list();
        for (TPermissionsResourceEntity resourceEntity : resourceEntityList){
            if (pid.equals(resourceEntity.getPid())){
                rootTreeList.add(resourceEntity);
            }
        }
        SysPermissionTreeDto rootTree;
        List<SysPermissionTreeDto> treeDtoList = SetsUtils.list();
        for (TPermissionsResourceEntity resourceEntity : rootTreeList){
            rootTree = new SysPermissionTreeDto();
            rootTree.setId(String.valueOf(resourceEntity.getId()));
            rootTree.setValue(rootTree.getId());
            rootTree.setName(resourceEntity.getResName());
            rootTree.setTitle(rootTree.getName());
            rootTree.setResType(resourceEntity.getResType());
            rootTree.setDesc(resourceEntity.getResDesc());
            rootTree.setUri(resourceEntity.getUri());
            rootTree.setOrder(resourceEntity.getResOrder());
            rootTree.setIcon(resourceEntity.getIcon());
            rootTree.setOpenWindow(resourceEntity.getOpenWindow());
            recursionTree(rootTree, resourceEntityList);
            treeDtoList.add(rootTree);
        }
        treeDtoList.sort(Comparator.comparingInt(SysPermissionTreeDto::getOrder));
        return treeDtoList;
    }

    /**
     * 获取某个用户的某个资源类型列表
     * @param uId 用户id
     * @param resType 资源类型
     * @return 资源列表
     */
    @Override
    public List<SysPermissionTreeDto> treeByUidAndResType(Long uId, String resType) {
        List<SysPermissionTreeDto> treeDtoList = SetsUtils.list();
        if (uId == null || StringUtils.isBlank(resType)){
            return treeDtoList;
        }
        List<TPermissionsUserRoleRelEntity> userRoleRelEntityList = PermissionServiceRegistry.getInstance().iPermissionsUserRoleRelService.findAllByUid(uId);
        List<Long> roleIds = EntityHelper.toIds(userRoleRelEntityList);
        if (roleIds == null || roleIds.isEmpty()){
            logger.info("listByUidAndResType not rel role");
            return treeDtoList;
        }
        List<TPermissionsResRoleRelEntity> resRoleRelEntityList = PermissionServiceRegistry.getInstance().iPermissionsResRoleRelService.findAllByRoleIdIn(roleIds);
        List<Long> resIds = EntityHelper.toIds(resRoleRelEntityList);
        if (resIds == null || resIds.isEmpty()){
            logger.info("listByUidAndResType not rel resource");
            return treeDtoList;
        }
        List<TPermissionsResourceEntity> resourceEntityList = (List<TPermissionsResourceEntity>) this.findAll(resIds);
        if (resourceEntityList == null || resourceEntityList.isEmpty()){
            logger.info("listByUidAndResType not resource list by ids");
            return treeDtoList;
        }
        return this.genTreeByPid(resourceEntityList, PermissionConstants.ROOT_PID);
    }

    /**
     * 获取某个用户某个父级资源下的某个资源类型列表
     * @param uId 用户id
     * @param pId 父级id
     * @param resType 资源类型
     * @return 资源列表
     */
    @Override
    public List<SysPermissionTreeDto> treeByPidAndUidAndResType(Long uId, Long pId, String resType) {
        List<SysPermissionTreeDto> treeDtoList = SetsUtils.list();
        if (uId == null || pId == null){
            logger.error("param uId or pId is null");
            return treeDtoList;
        }
        List<TPermissionsUserRoleRelEntity> userRoleRelEntityList = PermissionServiceRegistry.getInstance().iPermissionsUserRoleRelService.findAllByUid(uId);
        List<Long> roleIds = SetsUtils.list();
        for (TPermissionsUserRoleRelEntity entity : userRoleRelEntityList){
            roleIds.add(entity.getRid());
        }
        if (roleIds.isEmpty()){
            logger.info("listByUidAndResType not rel role");
            return treeDtoList;
        }
        List<TPermissionsResRoleRelEntity> resRoleRelEntityList = PermissionServiceRegistry.getInstance().iPermissionsResRoleRelService.findAllByRoleIdIn(roleIds);
        List<Long> resIds = SetsUtils.list();
        for (TPermissionsResRoleRelEntity entity : resRoleRelEntityList){
            resIds.add(entity.getResId());
        }
        if (resIds.isEmpty()){
            logger.info("listByUidAndResType not rel resource");
            return treeDtoList;
        }
        List<TPermissionsResourceEntity> resourceEntityList = (List<TPermissionsResourceEntity>) this.findAll(resIds);
        return this.genTreeByPid(resourceEntityList, pId);
    }

    /**
     * 获取某个用户某个资源类型某个父级菜单下的子菜单列表
     * @param uId 用户id
     * @param pId 父级id
     * @param resType 资源类型
     * @return 导航栏菜单列表
     */
    @Override
    public List<TPermissionsResourceEntity> findAllByPidAndUidAndResType(Long uId, Long pId, String resType) {
        List<TPermissionsResourceEntity> resourceEntityList = SetsUtils.list();
        if (uId == null || pId == null){
            logger.error("param uId or pId is null");
            return resourceEntityList;
        }
        List<TPermissionsUserRoleRelEntity> userRoleRelEntityList = PermissionServiceRegistry.getInstance().iPermissionsUserRoleRelService.findAllByUid(uId);
        List<Long> roleIds = SetsUtils.list();
        for (TPermissionsUserRoleRelEntity entity : userRoleRelEntityList){
            roleIds.add(entity.getRid());
        }
        if (roleIds.isEmpty()){
            logger.info("listByUidAndResType not rel role");
            return resourceEntityList;
        }
        List<TPermissionsResRoleRelEntity> resRoleRelEntityList = PermissionServiceRegistry.getInstance().iPermissionsResRoleRelService.findAllByRoleIdIn(roleIds);
        List<Long> resIds = SetsUtils.list();
        for (TPermissionsResRoleRelEntity entity : resRoleRelEntityList){
            resIds.add(entity.getResId());
        }
        if (resIds.isEmpty()){
            logger.info("listByUidAndResType not rel resource");
            return resourceEntityList;
        }
        resourceEntityList = (List<TPermissionsResourceEntity>) this.findAll(resIds);
        List<TPermissionsResourceEntity> byPidMenuList = SetsUtils.list();
        for (TPermissionsResourceEntity entity : resourceEntityList){
            if (pId.equals(entity.getPid()) && PermissionConstants.RES_TYPE_MENU.equalsIgnoreCase(entity.getResType())){
                byPidMenuList.add(entity);
            }
        }
        byPidMenuList.sort(new Comparator<TPermissionsResourceEntity>() {
            @Override
            public int compare(TPermissionsResourceEntity o1, TPermissionsResourceEntity o2) {
                return o1.getResOrder() - o2.getResOrder();
            }
        });
        return byPidMenuList;
    }

    /**
     * 通过资源编码查数据
     * @param resCode 资源编码
     * @return 实体对象
     */
    @Override
    public TPermissionsResourceEntity findByResCode(String resCode) {
        AssertUtils.isBlank(resCode, "param resCode is blank");
        return repository.findByResCode(resCode);
    }

    /**
     * 根据资源id生成面包屑 支持10级菜单，超过不支持
     * @param id 资源id
     * @return 面包屑列表
     */
    @Override
    public List<SysPermissionTreeDto> genCrumbs(Long id) {
        AssertUtils.isNull(id, "param id is null");
        List<SysPermissionTreeDto> crumbsList = SetsUtils.linkedList();
        TPermissionsResourceEntity currentEntity;
        SysPermissionTreeDto currentDto;
        int i = 0;
        while (i < 10) {
            i++;
            currentEntity = repository.findById(id).orElse(null);
            if (currentEntity == null){
                break;
            }
            currentDto = new SysPermissionTreeDto();
            currentDto.setName(currentEntity.getResName());
            currentDto.setOpenWindow(currentEntity.getOpenWindow());
            currentDto.setUri(currentEntity.getUri());
            currentDto.setId(String.valueOf(currentEntity.getId()));
            crumbsList.add(currentDto);
            id = currentEntity.getPid();
            if (PermissionConstants.ROOT_PID.equals(currentEntity.getPid())){
                break;
            }
        }
        return crumbsList;
    }

    /**
     * 递归资源树
     * @param parent 父级树
     * @param sources 支持递归源数据
     */
    private void recursionTree(SysPermissionTreeDto parent, List<TPermissionsResourceEntity> sources){
        Long currentId = Long.valueOf(parent.getId());
        SysPermissionTreeDto childTree;
        for (TPermissionsResourceEntity entity : sources){
            if (currentId.equals(entity.getPid())){
                childTree = new SysPermissionTreeDto();
                childTree.setId(String.valueOf(entity.getId()));
                childTree.setValue(childTree.getId());
                childTree.setName(entity.getResName());
                childTree.setTitle(childTree.getName());
                childTree.setDesc(entity.getResDesc());
                childTree.setOrder(entity.getResOrder());
                childTree.setResType(entity.getResType());
                childTree.setIcon(entity.getIcon());
                childTree.setUri(entity.getUri());
                childTree.setOpenWindow(entity.getOpenWindow());
                parent.getChildren().add(childTree);
//                parent.getData().add(childTree);
                recursionTree(childTree, sources);
            }
        }
        parent.getChildren().sort(Comparator.comparingInt(SysPermissionTreeDto::getOrder));
        parent.setData(parent.getChildren());
    }
}
