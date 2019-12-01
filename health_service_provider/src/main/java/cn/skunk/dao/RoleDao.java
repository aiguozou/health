package cn.skunk.dao;

import cn.skunk.pojo.Role;

import java.util.Set;

public interface RoleDao {
    public Set<Role> findByUserId(int userId);
}
