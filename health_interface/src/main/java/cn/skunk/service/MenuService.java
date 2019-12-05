package cn.skunk.service;

import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.pojo.Menu;
import cn.skunk.pojo.OBJECT_Menu;

import java.util.List;
import java.util.Map;

public interface MenuService {

    PageResult findPage(QueryPageBean queryPageBean);

    List<Menu> findAll();

    void add(Menu menu);

    List<OBJECT_Menu > getMenu();

    void delete(Integer id);
}
