package cn.skunk.dao;

import cn.skunk.pojo.Permission;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Set;

public interface PermissionDao {
    public Set<Permission> findByRoleId(int roleId);

    public void add(Permission permission);

    Page<Permission> findPage(String queryString);

    Permission findById(Integer id);

    void edit(Permission permission);

    public long findCountById(Integer permissionId);

    public void deleteById(Integer id);
    List<Permission> findAll();

    List<Permission> findCheckItemIdsByCheckGroupId(Integer id);

    int findCountById(Integer id);
}
