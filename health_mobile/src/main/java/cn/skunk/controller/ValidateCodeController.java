package cn.skunk.controller;

import cn.skunk.constant.MessageConstant;
import cn.skunk.constant.RedisMessageConstant;
import cn.skunk.entity.Result;
import cn.skunk.utils.SMSUtils;
import cn.skunk.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {
    @Autowired
    private JedisPool jedisPool;
    @RequestMapping("/sendOrder")
    public Result sendOrder(String telephone) {
        Integer validateCode = ValidateCodeUtils.generateValidateCode(4);
        try {
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,validateCode.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        jedisPool.getResource().setex(telephone+ RedisMessageConstant.SENDTYPE_ORDER,300,validateCode.toString());
        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }

    @RequestMapping("/sendLogin")
    public Result sendLogin(String telephone) {
        Integer validateCode = ValidateCodeUtils.generateValidateCode(6);
        try {
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,validateCode.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        jedisPool.getResource().setex(telephone+ RedisMessageConstant.SENDTYPE_LOGIN,300,validateCode.toString());
        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }
}
