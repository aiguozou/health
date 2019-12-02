package cn.skunk.service;

import cn.skunk.dao.PermissionDao;
import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.pojo.Permission;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.skunk.pojo.Permission;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = PermissionService.class)
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    public void add(Permission permission) {
        permissionDao.add(permission);
    }

    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage,pageSize);
        Page<Permission> page=permissionDao.findPage(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    public Permission findById(Integer id) {
        return permissionDao.findById(id);
    }

    public void edit(Permission permission) {
        permissionDao.edit(permission);
    }

    public void deleteById(Integer id) {
        long count = permissionDao.findCountById(id);
        if (count>0) {
            throw new RuntimeException("当前权限被引用,不能删除");
        }
        permissionDao.deleteById(id);
        }
    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public List<Permission> findCheckItemIdsByCheckGroupId(Integer id) {
        return permissionDao.findCheckItemIdsByCheckGroupId(id);
    }
}
