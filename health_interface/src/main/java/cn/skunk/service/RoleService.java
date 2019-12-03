package cn.skunk.service;

import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.pojo.Role;

import java.util.List;

public interface RoleService {
    PageResult findPage(QueryPageBean queryPageBean);

    void update(Integer[] permissionIds,Integer roleId);

    void add(Role role, Integer[] permissionIds);

    void deleteById(Integer id);

    List<Role> findAll();
}
