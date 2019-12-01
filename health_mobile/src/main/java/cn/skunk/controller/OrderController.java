package cn.skunk.controller;

import cn.skunk.constant.MessageConstant;
import cn.skunk.constant.RedisMessageConstant;
import cn.skunk.entity.Result;
import cn.skunk.pojo.Order;
import cn.skunk.service.OrderService;
import cn.skunk.utils.SMSUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private JedisPool jedisPool;
    @Reference
    private OrderService orderService;

    @RequestMapping("/submit")
    public Result submit(@RequestBody Map map){
        String telephone = (String) map.get("telephone");
        String codeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_ORDER);
        String validateCode = (String) map.get("validateCode");
        if (codeInRedis!=null&&validateCode!=null&&codeInRedis.equals(validateCode)){
            map.put("orderType",Order.ORDERTYPE_WEIXIN);
            Result result =null;
            try {
                result = orderService.order(map);
            } catch (Exception e) {
                e.printStackTrace();
                return result;
            }
            if (result.isFlag()){
                try {
                    SMSUtils.sendShortMessage(SMSUtils.ORDER_NOTICE,telephone, (String) map.get("orderDate"));
                } catch (ClientException e) {
                    e.printStackTrace();
                }
            }
            return result;
        } else {
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
    }
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            Map map=orderService.findById(id);
            return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ORDER_FAIL);
        }
    }
}
