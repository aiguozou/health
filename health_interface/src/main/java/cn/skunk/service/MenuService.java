package cn.skunk.service;

import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.pojo.Menu;

import java.util.List;

public interface MenuService {

    PageResult findPage(QueryPageBean queryPageBean);

    List<Menu> findAll();
}
