package cn.skunk.service;

import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {

    public void add(CheckGroup checkGroup,Integer[] checkitemIds);

    PageResult findPage(QueryPageBean queryPageBean);

    public CheckGroup findById(Integer id);

    public List<CheckGroup> findCheckItemIdsByCheckGroupId(Integer id);

    void edit(CheckGroup checkGroup, Integer[] checkitemIds);

    public List<CheckGroup> findAll();
}
