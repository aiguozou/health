package cn.skunk.dao;

import cn.skunk.pojo.CheckItem;
import com.github.pagehelper.Page;

import java.util.List;

public interface CheckItemDao {

    public void add(CheckItem checkItem);

    public Page<CheckItem> findPage(String queryString);

    public long findCountById(Integer CheckItemId);

    public void deleteById(Integer id);

    public CheckItem findById(Integer id);

    public void edit(CheckItem checkItem);

    public List<CheckItem> findAll();
}
