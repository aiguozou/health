package cn.skunk.service;

import cn.skunk.dao.CheckItemDao;
import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.pojo.CheckItem;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;

    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        PageHelper.startPage(currentPage,pageSize);
        Page<CheckItem> page = checkItemDao.findPage(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    public void deleteById(Integer id) {
        long count = checkItemDao.findCountById(id);
        if (count>0) {
            throw new RuntimeException("当前检查项被引用，不能删除");
        }
        checkItemDao.deleteById(id);
    }

    public CheckItem findById(Integer id) {
        return checkItemDao.findById(id);
    }

    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }

    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }
}
