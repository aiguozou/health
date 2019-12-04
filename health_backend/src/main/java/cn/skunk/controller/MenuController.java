package cn.skunk.controller;

import cn.skunk.constant.MessageConstant;
import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.entity.Result;
import cn.skunk.pojo.Menu;
import cn.skunk.pojo.OBJECT_Menu;
import cn.skunk.pojo.Setmeal;
import cn.skunk.service.MenuService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
    public Result findAll() {
        try {
            List<Menu> list = menuService.findAll();
            return new Result(true, MessageConstant.QUERY_MENU_SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_MENU_FAIL);
        }
    }

    @RequestMapping("/add")
    public Result add(Integer parentMenuId, Integer level, @RequestBody Menu menu) {
        menu.setParentMenuId(parentMenuId);
        menu.setLevel(level);

        try {
            menuService.add(menu);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_MENU_FAIL);
        }
        return new Result(true, MessageConstant.ADD_MENU_SUCCESS);
    }
    @RequestMapping("/getMenu")
    public Result getMenu() {
        List<OBJECT_Menu > list = menuService.getMenu();
        return new Result(true, "success", list);
    }
   @RequestMapping("/delete")
    public Result delete(Integer id){
       try {
           menuService.delete(id);
           return new Result(true, "success");
       } catch (Exception e) {
           e.printStackTrace();
           return new Result(false, "fail");
       }
    }

}
