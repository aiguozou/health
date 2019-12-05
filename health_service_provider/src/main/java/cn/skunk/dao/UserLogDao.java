package cn.skunk.dao;


import cn.skunk.pojo.UserLog;
import com.github.pagehelper.Page;


public interface UserLogDao {
    void save(UserLog userLog);

    Page<UserLog> findByCondition(String queryString);
}
