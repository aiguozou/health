package cn.skunk.controller;

import cn.skunk.constant.MessageConstant;
import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.entity.Result;
import cn.skunk.pojo.Setmeal;
import cn.skunk.service.OrderManagerSercive;
import cn.skunk.service.OrderService;
import cn.skunk.service.SetmealService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orderManager")
public class OrderManagerController {
    @Reference
    private OrderManagerSercive orderManagerSercive;

    @Reference
    private SetmealService setmealService;

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult page = orderManagerSercive.findPage(queryPageBean);
        return page;
    }

    @RequestMapping("/findAll")
    public Result findAll() {
        try {
            List<Setmeal> list = setmealService.findAll();
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Map map, Integer[] setmealIds){
        try {
            map.put("setmealIds",setmealIds);
            return orderManagerSercive.order(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"预约失败");
        }
    }

    @RequestMapping("/orderOk")
    public Result orderOk(int id){
        try {
            orderManagerSercive.orderOk(id);
            return new Result(true,"确定预约成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"确定预约失败");
        }
    }

    @RequestMapping("/orderCancle")
    public Result orderClane(int id){
        try {
            orderManagerSercive.orderCancle(id);
            return new Result(true,"取消预约成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"取消预约失败");
        }
    }

}
