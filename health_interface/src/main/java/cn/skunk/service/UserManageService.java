package cn.skunk.service;

import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.pojo.User;

import java.util.List;

public interface UserManageService {

    public  void add(User user, Integer[] userIds);

    PageResult findPage(QueryPageBean queryPageBean);

    User findById(Integer id);

    List<Integer> findUserIdsByUserId(Integer id);

    void edit(User user, Integer[] userIds);

    List<User> findAll();

    void deleteById(Integer id);
}
