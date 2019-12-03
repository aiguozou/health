package cn.skunk.dao;


import cn.skunk.pojo.Menu;
import com.github.pagehelper.Page;

import java.util.List;

public interface MenuDao {

    public Page<Menu> findPage(String queryString);

    List<Menu> findAll();
}
