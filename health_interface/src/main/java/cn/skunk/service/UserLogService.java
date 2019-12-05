package cn.skunk.service;

import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.pojo.UserLog;


public interface UserLogService {
    void save(UserLog userLog);

    PageResult findPage(QueryPageBean queryPageBean);
}
