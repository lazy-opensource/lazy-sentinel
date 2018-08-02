package com.hcl.common.permissioncenter.service;


import com.hcl.common.permissioncenter.api.IPermissionsGroupService;
import com.hcl.common.permissioncenter.api.constants.PermissionConstants;
import com.hcl.common.permissioncenter.api.dto.SysPermissionTreeDto;
import com.hcl.common.permissioncenter.api.entity.TPermissionsGroupEntity;
import com.hcl.common.permissioncenter.dao.IPermissionsGroupRepository;
import com.lazy.cheetah.common.tools.AssertUtils;
import com.lazy.cheetah.common.tools.SetsUtils;
import com.lazy.cheetah.core.enums.QueryRuleEnum;
import com.lazy.cheetah.core.enums.ValidStatusEnum;
import com.lazy.cheetah.core.helper.EntityHelper;
import com.lazy.cheetah.core.helper.PagingSortHelper;
import com.lazy.cheetah.core.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>系统权限-用户组原子服务实现类</p>
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
public class PermissionsGroupServiceImpl extends BaseServiceImpl<TPermissionsGroupEntity, Long> implements IPermissionsGroupService {

    /**
     * 数据仓库接口
     */
    private IPermissionsGroupRepository repository;

    /**
     * 构造器注入数据仓库接口
     * @param repository 数据仓库接口
     */
    @Autowired
    public PermissionsGroupServiceImpl(IPermissionsGroupRepository repository) {
        super();
        this.setRepository(repository);
        this.repository = repository;
    }

    /**
     * 获取系统用户组树列表
     * @return 树列表
     */
    @Override
    public List<SysPermissionTreeDto> tree() {
        TPermissionsGroupEntity condition = new TPermissionsGroupEntity();
        condition.setValidStatus(ValidStatusEnum.VALID.getStatus());
        PagingSortHelper<TPermissionsGroupEntity> pagingSortHelper = new PagingSortHelper<>();
        pagingSortHelper.getConditionsMap().put("validStatus", QueryRuleEnum.EQ);
        pagingSortHelper.onlySimpleSelect();

        List<TPermissionsGroupEntity> groupEntityList = this.findByMultiCiretConditions(condition, pagingSortHelper).getRows();
        List<TPermissionsGroupEntity> rootTreeList = SetsUtils.list();
        for (TPermissionsGroupEntity groupEntity : groupEntityList){
            if (PermissionConstants.ROOT_PID.equals(groupEntity.getPid())){
                rootTreeList.add(groupEntity);
            }
        }
        SysPermissionTreeDto rootTree;
        List<SysPermissionTreeDto> treeDtoList = SetsUtils.list();
        for (TPermissionsGroupEntity groupEntity : rootTreeList){
            rootTree = new SysPermissionTreeDto();
            rootTree.setId(String.valueOf(groupEntity.getId()));
            rootTree.setName(groupEntity.getGroupName());
            rootTree.setCode(groupEntity.getGroupCode());
            this.recursionTree(rootTree, groupEntityList);
            treeDtoList.add(rootTree);
        }
        return treeDtoList;
    }

    /**
     * 通过组名称查数据
     * @param groupName 组名称
     * @return 实体对象
     */
    @Override
    public TPermissionsGroupEntity findByGroupName(String groupName) {
        AssertUtils.isBlank(groupName, "param groupName is blank");
        return repository.findByGroupName(groupName);
    }

    /**
     * 通过组编码查数据
     * @param groupCode 组编码
     * @return 实体对象
     */
    @Override
    public TPermissionsGroupEntity findByGroupCode(String groupCode) {
        AssertUtils.isBlank(groupCode, "param groupCode is blank");
        return repository.findByGroupCode(groupCode);
    }

    /**
     * 根据父级用户组id集合查出所有子用户组
     * @param pidList 父级用户组id集合
     * @return 实体对象
     */
    @Override
    public List<TPermissionsGroupEntity> findByPidIn(Iterable<Long> pidList) {
        AssertUtils.isNull(pidList, "param pidList is null");
        return repository.findByPidIn(pidList);
    }

    /**
     * 根据父级递归出所有的下级
     * @param pids 父级id
     * @return 所有下级id
     */
    @Override
    public List<Long> recursionSubIdByPids(List<Long> pids) {
        AssertUtils.isNull(pids, "param pid is null");
        if (pids.isEmpty()){
            return new ArrayList<>();
        }
        List<Long> allSubs = SetsUtils.list();
        List<TPermissionsGroupEntity> subs = repository.findByPidIn(pids);
        List<Long> currentPids;
        while (!subs.isEmpty()){
            currentPids = EntityHelper.toIds(subs);
            allSubs.addAll(currentPids);
            subs = repository.findByPidIn(currentPids);
        }
        return allSubs;
    }

    /**
     * 递归用户组树
     * @param parent 父级树
     * @param sources 支持递归源数据
     */
    private void recursionTree(SysPermissionTreeDto parent, List<TPermissionsGroupEntity> sources){
        Long currentId = Long.valueOf(parent.getId());
        SysPermissionTreeDto childTree;
        for (TPermissionsGroupEntity entity : sources){
            if (currentId.equals(entity.getPid())){
                childTree = new SysPermissionTreeDto();
                childTree.setId(String.valueOf(entity.getId()));
                childTree.setName(entity.getGroupName());
                childTree.setCode(entity.getGroupCode());
                parent.getChildren().add(childTree);
                this.recursionTree(childTree, sources);
            }
        }
    }
}
