package org.exframework.portal.jdbc.dao.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.exframework.portal.dao.PortalCorePermissionDao;
import org.exframework.portal.dao.PortalCorePermissionGroupDao;
import org.exframework.portal.jdbc.entity.PermissionGroupDO;
import org.exframework.portal.jdbc.mapper.PermissionGroupMapper;
import org.exframework.portal.permission.PermissionGroup;
import org.exframework.support.common.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 权限组实现
 *
 * @author rwe
 * @date 2021/7/5 09:47
 **/
@Repository
public class PortalCorePermissionGroupDaoImpl extends PortalCoreMetaDaoImpl<PermissionGroupDO, PermissionGroup> implements PortalCorePermissionGroupDao {

    @Autowired
    PermissionGroupMapper permissionGroupMapper;

    @Autowired
    PortalCorePermissionDao permissionDao;

    @Override
    public BaseMapper<PermissionGroupDO> getBaseMapper() {
        return permissionGroupMapper;
    }

    @Override
    public PermissionGroupDO genInstance() {
        return new PermissionGroupDO();
    }

    @Override
    public PermissionGroup transform(PermissionGroupDO permissionGroupDO) {
        PermissionGroup group = BeanUtils.copyTo(permissionGroupDO, PermissionGroup.class);
        group.setPermissions(permissionGroupDO.getPermissionKeys().stream().map(key -> permissionDao.findByKey(key)).collect(Collectors.toList()));
        return group;
    }


    @Override
    public Map<String, PermissionGroup> allGroups() {
        List<PermissionGroupDO> groups = permissionGroupMapper.selectList(Wrappers.emptyWrapper());
        return groups.stream().collect(Collectors.toMap(PermissionGroupDO::getCode, this::transform));
    }
}
