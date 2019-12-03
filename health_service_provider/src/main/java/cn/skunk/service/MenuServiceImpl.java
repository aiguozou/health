package cn.skunk.service;

import cn.skunk.dao.MenuDao;
import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.pojo.Menu;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = MenuService.class)
@Transactional
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage,pageSize);
        Page<Menu> page = menuDao.findPage(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    public List<Menu> findAll() {
        return menuDao.findAll();
    }
}
