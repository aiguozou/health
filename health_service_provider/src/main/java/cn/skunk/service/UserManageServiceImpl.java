package cn.skunk.service;

import cn.skunk.dao.UserManageDao;
import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.pojo.User;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = UserManageService.class)
@Transactional
public class UserManageServiceImpl implements  UserManageService {
    @Autowired
    private UserManageDao userManageDao;
    @Override
    public void add(User user, Integer[] userIds) {
        userManageDao.add(user);
        Integer userid = user.getId();
        if (userIds!=null&&userIds.length>0) {
            for (Integer roleid : userIds) {
                Map<String,Integer> map=new HashMap<>();
                map.put("roleid",roleid);
                map.put("userid",userid);
                userManageDao.setUserAndRole(map);
            }
        }
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage,pageSize);
        Page<User> page=userManageDao.findPage(queryString);
        return new PageResult(page.getTotal(),page.getResult());

    }

    @Override
    public User findById(Integer id) {
        return userManageDao.findById(id);
    }

    @Override
    public List<Integer> findUserIdsByUserId(Integer id) {
        return userManageDao.findUserIdsByUserId(id);

    }

    @Override
    public void edit(User user, Integer[] userIds) {
        userManageDao.edit(user);
        userManageDao.delete(user.getId());
        Integer userid= user.getId();
        if (userIds!=null&&userIds.length>0) {
            for (Integer roleid : userIds) {
                Map<String,Integer> map=new HashMap<>();
                map.put("roleid",roleid);
                map.put("userid",userid);
                userManageDao.setUserAndRole(map);
            }
        }
    }

    @Override
    public List<User> findAll() {
        return userManageDao.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        long count = userManageDao.findCountById(id);
        if (count>0) {
            throw new RuntimeException("当前数据被引用，不能删除");
        }
        userManageDao.deleteById(id);
    }

}
