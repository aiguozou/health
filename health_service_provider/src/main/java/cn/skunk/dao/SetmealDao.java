package cn.skunk.dao;

import cn.skunk.pojo.Setmeal;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface SetmealDao {

    public void add(Setmeal setmeal);

    public void setSetmealAndCheckGroup(Map<String, Integer> map);

    public Page<Setmeal> findPage(String queryString);

    List<Setmeal> findAll();

    Setmeal findById(int id);

    List<Map<String, Object>> findSetmealCount();
}
