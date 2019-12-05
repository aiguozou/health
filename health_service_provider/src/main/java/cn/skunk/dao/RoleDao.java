package cn.skunk.dao;

import cn.skunk.entity.QueryPageBean;
import cn.skunk.pojo.Role;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RoleDao {

    public Set<Role> findByUserId(int userId);

    Page<Role> findPage(String queryString);

    void delete(Integer roleId);

    void setRoleAndPermission(Map<String, Integer> map);

    void add(Role role);

    void deleteUser_RoleById(Integer id);

    void deleteRole_PermissionById(Integer id);

    void deleteById(Integer id);

    List<Role> findAll();
}
