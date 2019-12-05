package cn.skunk.controller;

import cn.skunk.constant.MessageConstant;
import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.entity.Result;
import cn.skunk.pojo.User;
import cn.skunk.service.UserManageService;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//用户管理
@RestController
@RequestMapping("/users")
public class UserManageController {
    @Reference
    private UserManageService userManageService;

    @RequestMapping("/add")
    public Result add(@RequestBody User user, Integer[] UserIds) {
        try {
            userManageService.add(user, UserIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_USERINFO_FAIL);//新增成功
        }
        return new Result(true, MessageConstant.ADD_USERINFO_SUCCESS);//新增失败

    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return userManageService.findPage(queryPageBean);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            User user = userManageService.findById(id);
            return new Result(true, MessageConstant.QUERY_USERINFO_SUCCESS, user);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_USERINFO_FAIL);
        }
    }

    @RequestMapping("/findUserIdsByUserId")
    public Result findUserIdsByUserId(Integer id) {
        try {
            List<Integer> UserIds = userManageService.findUserIdsByUserId(id);
            return new Result(true, MessageConstant.QUERY_USERINFO_SUCCESS, UserIds);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_USERINFO_FAIL);
        }
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody User user, Integer[] UserIds) {
        try {
            userManageService.edit(user, UserIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_USERINFO_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_USERINFO_SUCCESS);
    }

    @RequestMapping("/findAll")
    public Result findAll() {
        try {
            List<User> checkGroup = userManageService.findAll();
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            userManageService.deleteById(id);
        } catch (RuntimeException e) {
            return new Result(false, e.getMessage());
        } catch (Exception e) {
            return new Result(false, MessageConstant.DELETE_USERINFO_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_USERINFO_SUCCESS);
    }
}
