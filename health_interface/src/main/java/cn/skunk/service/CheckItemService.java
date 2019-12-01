package cn.skunk.service;

import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {

    public void add(CheckItem checkItem);

    public PageResult findPage(QueryPageBean queryPageBean);

    public void deleteById(Integer id);

    public CheckItem findById(Integer id);

    public void edit(CheckItem checkItem);

    public List<CheckItem> findAll();
}
