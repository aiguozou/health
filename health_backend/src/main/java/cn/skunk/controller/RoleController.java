package cn.skunk.controller;

import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.entity.Result;
import cn.skunk.log.MyLog;
import cn.skunk.pojo.Role;
import cn.skunk.service.RoleService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Reference
    private RoleService roleService;

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return roleService.findPage(queryPageBean);
    }
    @RequestMapping("/update")
    public Result update(Integer[] permissionIds,Integer roleId){
        try {
            roleService.update(permissionIds,roleId);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"修改失败");
        }
        return new Result(true,"修改成功");
    }
    @RequestMapping("/add")
    public Result add(@RequestBody Role role, Integer[] permissionIds){
        System.out.println(Arrays.toString(permissionIds));
        try {
            roleService.add(role,permissionIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"新增角色失败");
        }
        return new Result(true,"新增角色成功");
    }
    @RequestMapping("/delete")
    @MyLog(value = "删除角色")
    public Result delete(Integer id) {
        try {
            roleService.deleteById(id);
        } catch (RuntimeException e) {
            return new Result(false,e.getMessage());
        }catch (Exception e) {
            return new Result(false,"删除角色失败");
        }
        return new Result(true,"删除角色成功");
    }
}
