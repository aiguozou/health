package cn.skunk.service;

import cn.skunk.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

public interface OrderSettingService {

    public void add(List<OrderSetting> data);

    public List<Map> getOrderSettingByMonth(String date);

    void editNumberByDate(OrderSetting orderSetting);
}
