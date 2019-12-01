package cn.skunk.dao;

import cn.skunk.pojo.Permission;

import java.util.Set;

public interface PermissionDao {
    public Set<Permission> findByRoleId(int roleId);
}
