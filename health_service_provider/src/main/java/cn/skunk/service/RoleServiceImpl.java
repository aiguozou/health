package cn.skunk.service;

import cn.skunk.dao.PermissionDao;
import cn.skunk.dao.RoleDao;
import cn.skunk.dao.UserDao;
import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.pojo.Role;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service(interfaceClass = RoleService.class)
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);
        Page<Role> page = roleDao.findPage(queryString);

        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void update(Integer[] permissionIds, Integer roleId) {
        roleDao.delete(roleId);
        if (permissionIds != null && permissionIds.length > 0) {
            for (Integer permissionId : permissionIds) {
                Map<String, Integer> map = new HashMap<>();
                map.put("role_id", roleId);
                map.put("permission_id", permissionId);
                roleDao.setRoleAndPermission(map);
            }
        }
    }

    @Override
    public void add(Role role, Integer[] permissionIds) {
        roleDao.add(role);
        Integer role_id = role.getId();
        if (permissionIds!=null && permissionIds.length>0){
            for (Integer permissionId : permissionIds) {
                Map<String,Integer> map=new HashMap<>();
                map.put("role_id",role_id);
                map.put("permission_id",permissionId);
                roleDao.setRoleAndPermission(map);
            }

        }
    }

    @Override
    public void deleteById(Integer id) {
        int count = userDao.findCountById(id);
        if (count>0) {
            roleDao.deleteUser_RoleById(id);
        }
        int count1=permissionDao.findCountById(id);
        if (count1>0){
            roleDao.deleteRole_PermissionById(id);
        }

        roleDao.deleteById(id);
    }
}
