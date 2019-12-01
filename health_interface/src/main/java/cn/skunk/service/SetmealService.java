package cn.skunk.service;

import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealService {

    public void add(Setmeal setmeal, Integer[] checkgroupIds);

    PageResult findPage(QueryPageBean queryPageBean);

    List<Setmeal> findAll();

    Setmeal findById(int id);

    List<Map<String, Object>> findSetmealCount();
}
