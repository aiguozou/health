package cn.skunk.dao;

import cn.skunk.pojo.Permission;

import java.util.List;
import java.util.Set;

public interface PermissionDao {
    public Set<Permission> findByRoleId(int roleId);

    List<Permission> findAll();

    List<Permission> findPermissionIdsByRoleId(Integer id);

    int findCountById(Integer id);
}
