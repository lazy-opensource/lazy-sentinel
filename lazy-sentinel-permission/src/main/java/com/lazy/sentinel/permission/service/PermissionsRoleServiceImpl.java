package com.lazy.sentinel.permission.service;

import com.lazy.sentinel.permission.api.IPermissionsRoleService;
import com.lazy.sentinel.permission.api.constants.PermissionConstants;
import com.lazy.sentinel.permission.api.dto.SysPermissionTreeDto;
import com.lazy.sentinel.permission.api.entity.TPermissionsRoleEntity;
import com.lazy.sentinel.permission.dao.IPermissionsRoleRepository;
import com.lazy.cheetah.common.tools.AssertUtils;
import com.lazy.cheetah.common.tools.SetsUtils;
import com.lazy.cheetah.core.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>系统权限-角色原子服务实现类</p>
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
public class PermissionsRoleServiceImpl extends BaseServiceImpl<TPermissionsRoleEntity, Long> implements IPermissionsRoleService {

    /**
     * 数据仓库接口
     */
    private IPermissionsRoleRepository repository;

    /**
     * 构造器注入数据仓库接口
     * @param repository 数据仓库接口
     */
    @Autowired
    public PermissionsRoleServiceImpl(IPermissionsRoleRepository repository) {
        super();
        this.setRepository(repository);
        this.repository = repository;
    }

    /**
     * 获取系统角色树列表
     *
     * @return 树列表
     */
    @Override
    public List<SysPermissionTreeDto> tree() {
        List<TPermissionsRoleEntity> roleEntityList = (List<TPermissionsRoleEntity>) this.findAll();
        List<TPermissionsRoleEntity> rootTreeList = SetsUtils.list();
        for (TPermissionsRoleEntity roleEntity : roleEntityList){
            if (PermissionConstants.ROOT_PID.equals(roleEntity.getPid())){
                rootTreeList.add(roleEntity);
            }
        }
        SysPermissionTreeDto rootTree;
        List<SysPermissionTreeDto> treeDtoList = SetsUtils.list();
        for (TPermissionsRoleEntity roleEntity : rootTreeList){
            rootTree = new SysPermissionTreeDto();
            rootTree.setId(String.valueOf(roleEntity.getId()));
            rootTree.setName(roleEntity.getRoleName());
            rootTree.setDesc(roleEntity.getRoleCode());
            this.recursionTree(rootTree, roleEntityList);
            treeDtoList.add(rootTree);
        }
        return treeDtoList;
    }

    /**
     * 通过角色编码查数据
     * @param roleCode 角色编码
     * @return 角色实体对象
     */
    @Override
    public TPermissionsRoleEntity findByRoleCode(String roleCode) {
        AssertUtils.isBlank(roleCode, "param roleCode is blank");
        return repository.findByRoleCode(roleCode);
    }

    /**
     * 递归资源树
     * @param parent 父级树
     * @param sources 支持递归源数据
     */
    private void recursionTree(SysPermissionTreeDto parent, List<TPermissionsRoleEntity> sources){
        Long currentId = Long.valueOf(parent.getId());
        SysPermissionTreeDto childTree;
        for (TPermissionsRoleEntity entity : sources){
            if (currentId.equals(entity.getPid())){
                childTree = new SysPermissionTreeDto();
                childTree.setId(String.valueOf(entity.getId()));
                childTree.setName(entity.getRoleName());
                childTree.setDesc(entity.getRoleCode());
                parent.getChildren().add(childTree);
                this.recursionTree(childTree, sources);
            }
        }
    }
}
