package cn.skunk.dao;


import cn.skunk.pojo.Menu;
import cn.skunk.pojo.OBJECT_Menu;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface MenuDao {

    public Page<Menu> findPage(String queryString);

    List<Menu> findAll();

    void add(Menu menu);

    void setMenuAndRole(Map map);

    Integer[] selectLevelRoot();

   OBJECT_Menu selectMenuById(Integer i);

    void delete(Integer id);

    void deleteMenuByParentId(Integer id);
}
