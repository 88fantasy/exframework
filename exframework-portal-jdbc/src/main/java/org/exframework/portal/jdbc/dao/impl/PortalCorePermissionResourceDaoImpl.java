package org.exframework.portal.jdbc.dao.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.exframework.portal.dao.PortalCorePermissionDao;
import org.exframework.portal.dao.PortalCorePermissionResourceDao;
import org.exframework.portal.jdbc.entity.PermissionResourceDO;
import org.exframework.portal.jdbc.mapper.PermissionResourceMapper;
import org.exframework.portal.permission.PermissionResource;
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
public class PortalCorePermissionResourceDaoImpl extends PortalCoreMetaDaoImpl<PermissionResourceDO, PermissionResource> implements PortalCorePermissionResourceDao {

    @Autowired
    PermissionResourceMapper permissionResourceMapper;

    @Autowired
    PortalCorePermissionDao permissionDao;

    @Override
    public BaseMapper<PermissionResourceDO> getBaseMapper() {
        return permissionResourceMapper;
    }

    @Override
    public PermissionResourceDO genInstance() {
        return new PermissionResourceDO();
    }

    @Override
    public PermissionResource transform(PermissionResourceDO permissionResourceDO) {
        PermissionResource group = BeanUtils.copyTo(permissionResourceDO, PermissionResource.class);
        group.setPermissions(permissionResourceDO.getPermissionKeys().stream().map(key -> permissionDao.findByKey(key)).collect(Collectors.toList()));
        return group;
    }


    @Override
    public Map<String, PermissionResource> allGroups() {
        List<PermissionResourceDO> groups = permissionResourceMapper.selectList(Wrappers.emptyWrapper());
        return groups.stream().collect(Collectors.toMap(PermissionResourceDO::getCode, this::transform));
    }
}
