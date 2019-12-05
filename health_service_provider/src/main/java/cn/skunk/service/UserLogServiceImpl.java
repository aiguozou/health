package cn.skunk.service;

import cn.skunk.dao.UserLogDao;
import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.pojo.UserLog;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Service(interfaceClass = UserLogService.class)
@Transactional
public class UserLogServiceImpl implements UserLogService {
    @Autowired
    private UserLogDao userLogDao;
    @Override
    public void save(UserLog userLog) {
        userLogDao.save(userLog);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage,pageSize);
        Page<UserLog> page = userLogDao.findByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }
}
