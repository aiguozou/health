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


    List<Map<String, Object>> findSetmealCount();

    Setmeal findById(int id);

    List<Integer> findCheckgroupIds(Integer id);

    void edit(Setmeal setmeal, Integer[] checkgroupIds);

    void delete(Integer id);
}
