package cn.skunk.service;

import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.pojo.Permission;

public interface PermissionService {

    public void add(Permission permission);

    PageResult findPage(QueryPageBean queryPageBean);

    Permission findById(Integer id);

    void edit(Permission permission);

    void deleteById(Integer id);
}
