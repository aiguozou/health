package cn.skunk.dao;

import cn.skunk.pojo.CheckGroup;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {

    public void add(CheckGroup checkGroup);

    public void setCheckGroupAndCheckItem(Map<String, Integer> map);

    public Page<CheckGroup> findPage(String queryString);

    public CheckGroup findById(Integer id);

    public List<CheckGroup> findCheckItemIdsByCheckGroupId(Integer id);

    void edit(CheckGroup checkGroup);

    void delete(Integer id);

    public List<CheckGroup> findAll();

}
