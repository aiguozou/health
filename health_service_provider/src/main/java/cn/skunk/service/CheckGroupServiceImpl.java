package cn.skunk.service;

import cn.skunk.dao.CheckGroupDao;
import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.pojo.CheckGroup;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;

    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkGroupDao.add(checkGroup);
        Integer checkGroupId = checkGroup.getId();
        if (checkitemIds!=null&&checkitemIds.length>0) {
            for (Integer checkitemId : checkitemIds) {
                Map<String,Integer> map=new HashMap<>();
                map.put("checkitemId",checkitemId);
                map.put("checkGroupId",checkGroupId);
                checkGroupDao.setCheckGroupAndCheckItem(map);
            }
        }
    }

    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckGroup> page = checkGroupDao.findPage(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById(id);
    }

    public List<CheckGroup> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }


    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkGroupDao.edit(checkGroup);
        checkGroupDao.delete(checkGroup.getId());
        Integer checkGroupId = checkGroup.getId();
        if (checkitemIds!=null&&checkitemIds.length>0) {
            for (Integer checkitemId : checkitemIds) {
                Map<String,Integer> map=new HashMap<>();
                map.put("checkitemId",checkitemId);
                map.put("checkGroupId",checkGroupId);
                checkGroupDao.setCheckGroupAndCheckItem(map);
            }
        }
    }

    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }

}
