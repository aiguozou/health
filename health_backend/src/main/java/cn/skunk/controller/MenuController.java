package cn.skunk.controller;

import cn.skunk.constant.MessageConstant;
import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.entity.Result;
import cn.skunk.pojo.Menu;
import cn.skunk.service.MenuService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Reference
    private MenuService menuService;

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return menuService.findPage(queryPageBean);
    }

    @RequestMapping("/findAll")
    public Result findAll(){
        try {
            List<Menu> list=menuService.findAll();
            return new Result(true, MessageConstant.QUERY_MENU_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_MENU_FAIL);
        }
    }
}
