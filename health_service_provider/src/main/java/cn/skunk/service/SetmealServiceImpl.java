package cn.skunk.service;

import cn.skunk.constant.RedisConstant;
import cn.skunk.dao.SetmealDao;
import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.pojo.Setmeal;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealDao setmealDao;
    @Autowired
    private JedisPool jedisPool;


    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealDao.add(setmeal);
        Integer setmealId = setmeal.getId();
        if (checkgroupIds != null && checkgroupIds.length > 0) {
            for (Integer checkgroupId : checkgroupIds) {
                Map<String, Integer> map = new HashMap<>();
                map.put("setmealId", setmealId);
                map.put("checkgroupId", checkgroupId);
                setmealDao.setSetmealAndCheckGroup(map);
            }
        }
        String fileName = setmeal.getImg();
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES, fileName);
    }

    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);
        Page<Setmeal> page = setmealDao.findPage(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    public List<Setmeal> findAll() {
        return setmealDao.findAll();
    }

    public Setmeal findById(int id) {
        return setmealDao.findById(id);
    }

    public List<Map<String, Object>> findSetmealCount() {
        return setmealDao.findSetmealCount();
    }




    @Override
    public List<Integer> findCheckgroupIds(Integer id) {


        return  setmealDao.findCheckgroupIds(id);
    }

    @Override
    public void edit(Setmeal setmeal, Integer[] checkgroupIds) {

        //根据套餐ID删除 中间表数据
        setmealDao.deleteAssociation(setmeal.getId());
        //建立套餐和检查组关联关系
        Integer setmealId = setmeal.getId();
        this.setSetmealAndCheckGroup(setmealId,checkgroupIds);
        //编辑
        setmealDao.edit(setmeal);
    }


    //删除套餐
    @Override
    public void delete(Integer id) {

        //删除中间关系表
        setmealDao.deleteBySetmealId(id);

        setmealDao.deleteById(id);
    }

    //建立套餐和检查组关联关系
    public void setSetmealAndCheckGroup(Integer setmealId,Integer[] checkgroupIds){
        if (checkgroupIds!=null && checkgroupIds.length>0){
            for (Integer checkgroupId : checkgroupIds) {
                Map<String, Integer> map = new HashMap<>();
                map.put("setmealId",setmealId);
                map.put("checkgroupId",checkgroupId);
                setmealDao.setSetmealAndCheckGroup(map);
            }
        }
    }
}
