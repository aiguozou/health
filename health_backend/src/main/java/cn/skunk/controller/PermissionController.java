package cn.skunk.controller;

import cn.skunk.constant.MessageConstant;
import cn.skunk.entity.Result;
import cn.skunk.pojo.Permission;
import cn.skunk.service.PermissionService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Reference
    private PermissionService permissionService;

    @RequestMapping("/findAll")
    public Result findAll(){
        try {
            List<Permission> permissions = permissionService.findAll();
            return new Result(true,"权限加载成功",permissions);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "权限加载异常");
        }
    }
    @RequestMapping("/findPermissionIdsByRoleId")
    public Result findCheckItemIdsByCheckGroupId(Integer id) {
        try {
            List<Permission> checkItemIds = permissionService.findCheckItemIdsByCheckGroupId(id);
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItemIds);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }
}
