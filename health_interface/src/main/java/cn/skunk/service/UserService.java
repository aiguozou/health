package cn.skunk.service;

import cn.skunk.pojo.User;

public interface UserService {
    public User findByUsername(String username);

    void register(User user);
}
