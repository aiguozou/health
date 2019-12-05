package cn.skunk.service;

import cn.skunk.pojo.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll();

    List<Permission> findPermissionIdsByRoleId(Integer id);
}
