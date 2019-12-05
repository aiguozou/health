package cn.skunk.dao;

import cn.skunk.pojo.User;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface UserManageDao {

    void add(User user);
    void setUserAndRole(Map map);
    Page<User> findPage(String queryString);
    User findById(Integer id);
    List<Integer> findUserIdsByUserId(Integer id);
    void edit(User user);
    void delete(Integer id);
    List<User> findAll();
    long findCountById(Integer id);
    void deleteById(Integer id);
}
