package cn.skunk.service;

import cn.skunk.dao.OrderSettingDao;
import cn.skunk.pojo.OrderSetting;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    private OrderSettingDao orderSettingDao;

    public void add(List<OrderSetting> list) {
        if (list!=null&&list.size()>0) {
            for (OrderSetting orderSetting : list) {
                long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
                if (count>0) {
                    orderSettingDao.editNumberByOrderDate(orderSetting);
                }else {
                    orderSettingDao.add(orderSetting);
                }
            }
        }
    }

    public List<Map> getOrderSettingByMonth(String date) {
        String begin=date+"-1";
        String end=date+"-31";
        Map<String,String> map=new HashMap<>();
        map.put("begin",begin);
        map.put("end",end);
        List<OrderSetting> list=orderSettingDao.getOrderSettingByMonth(map);
        List<Map> result=new ArrayList<>();
        if (list!=null&&list.size()>0) {
            for (OrderSetting orderSetting : list) {
                Map<String,Object> m=new HashMap<>();
                m.put("date",orderSetting.getOrderDate().getDate());
                m.put("number",orderSetting.getNumber());
                m.put("reservations",orderSetting.getReservations());
                result.add(m);
            }
        }
        return result;
    }

    public void editNumberByDate(OrderSetting orderSetting) {
        Date orderDate = orderSetting.getOrderDate();
        long count = orderSettingDao.findCountByOrderDate(orderDate);
        if (count>0) {
            orderSettingDao.editNumberByOrderDate(orderSetting);
        }else {
            orderSettingDao.add(orderSetting);
        }
    }
}
