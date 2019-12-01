package cn.skunk.dao;

import cn.skunk.pojo.User;

public interface UserDao {
    public User findByUsername(String username);
}
