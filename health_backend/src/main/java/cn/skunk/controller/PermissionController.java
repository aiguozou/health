package cn.skunk.controller;

import cn.skunk.constant.MessageConstant;
import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.entity.Result;
import cn.skunk.pojo.Permission;
import cn.skunk.service.PermissionService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Reference
    private PermissionService permissionService;

    @RequestMapping("/add")
    public Result add(@RequestBody Permission permission){
        try {
            permissionService.add(permission);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_PERMISSION_FAIL);
        }
        return new Result(true,MessageConstant.ADD_PERMISSION_SUCCESS);
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult page = permissionService.findPage(queryPageBean);
        return page;
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            Permission permission = permissionService.findById(id);
            return new Result(true,MessageConstant.QUERY_PERMISSION_SUCCESS,permission);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_PERMISSION_FAIL);
        }
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody Permission permission) {
        try {
            permissionService.edit(permission);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_PERMISSION_FAIL);
        }
        return new Result(true,MessageConstant.EDIT_PERMISSION_SUCCESS);
    }

    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            permissionService.deleteById(id);
        } catch (RuntimeException e) {
            return new Result(false,e.getMessage());
        }catch (Exception e) {
            return new Result(false, MessageConstant.DELETE_PERMISSION_FAIL);
        }
        return new Result(true,MessageConstant.DELETE_PERMISSION_SUCCESS);
    }

}
