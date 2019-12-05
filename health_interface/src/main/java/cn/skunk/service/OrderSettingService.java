package cn.skunk.service;

import cn.skunk.entity.Result;
import cn.skunk.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderSettingService {

    public void add(List<OrderSetting> data);

    public List<Map> getOrderSettingByMonth(String date);

    public void editNumberByDate(OrderSetting orderSetting);

    public Result deleteOrderSetting(Date orderDate);
}
