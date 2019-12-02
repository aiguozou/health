package cn.skunk.service;

import cn.skunk.dao.PermissionDao;
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
    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public List<Permission> findCheckItemIdsByCheckGroupId(Integer id) {
        return permissionDao.findCheckItemIdsByCheckGroupId(id);
    }
}
