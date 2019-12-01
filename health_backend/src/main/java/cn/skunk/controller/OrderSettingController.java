package cn.skunk.controller;

import cn.skunk.constant.MessageConstant;
import cn.skunk.entity.Result;
import cn.skunk.pojo.OrderSetting;
import cn.skunk.service.OrderSettingService;
import cn.skunk.utils.POIUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
    @Reference
    private OrderSettingService orderSettingService;

    @RequestMapping("/upload")
    public Result upload(@RequestParam("excelFile")MultipartFile excelFile){
        try {
            List<String[]> list = POIUtils.readExcel(excelFile);
            List<OrderSetting> data=new ArrayList<>();
            for (String[] strings : list) {
                String orderDate = strings[0];
                String number = strings[1];
                OrderSetting orderSetting = new OrderSetting(new Date(orderDate),Integer.parseInt(number));
                data.add(orderSetting);
            }
            orderSettingService.add(data);
            return new Result(true,MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }
    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date){
        try {
            List<Map> list = orderSettingService.getOrderSettingByMonth(date);
            return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }
    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting) {
        try {
            orderSettingService.editNumberByDate(orderSetting);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }
        return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
    }
}
