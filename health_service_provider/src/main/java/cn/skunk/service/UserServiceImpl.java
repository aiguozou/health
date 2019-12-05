package cn.skunk.service;

import cn.skunk.dao.PermissionDao;
import cn.skunk.dao.RoleDao;
import cn.skunk.dao.UserDao;
import cn.skunk.pojo.Permission;
import cn.skunk.pojo.Role;
import cn.skunk.pojo.User;
import cn.skunk.utils.MD5Utils;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public User findByUsername(String username) {
        User user = userDao.findByUsername(username);
        if (user==null) {
            return null;
        }
        Integer userId = user.getId();
        Set<Role> roles = roleDao.findByUserId(userId);
        for (Role role : roles) {
            Integer roleId = role.getId();
            Set<Permission> permissions = permissionDao.findByRoleId(roleId);
            role.setPermissions(permissions);
        }
        user.setRoles(roles);
        return user;
    }

    @Override
    public void register(User user) {
        String password = new BCryptPasswordEncoder().encode(user.getPassword());
        String username = user.getUsername();

        User user1 = new User();
        user1.setUsername(username);
        user1.setPassword(password);
        userDao.register(user1);
    }

}
