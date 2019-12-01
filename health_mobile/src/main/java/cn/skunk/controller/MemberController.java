package cn.skunk.controller;

import cn.skunk.constant.MessageConstant;
import cn.skunk.constant.RedisMessageConstant;
import cn.skunk.entity.Result;
import cn.skunk.pojo.Member;
import cn.skunk.service.MemberService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private JedisPool jedisPool;
    @Reference
    private MemberService memberService;

    @RequestMapping("/login")
    public Result login(@RequestBody Map map, HttpServletResponse response){
        String telephone = (String) map.get("telephone");
        String codeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_LOGIN);
        String validateCode = (String) map.get("validateCode");
        if (codeInRedis!=null&&validateCode!=null&&codeInRedis.equals(validateCode)) {
            Member member = memberService.findByTelephone(telephone);
            if (member==null) {
                member.setPhoneNumber(telephone);
                member.setRegTime(new Date());
                memberService.add(member);
            }
            //写入Cookie，跟踪用户
            Cookie cookie=new Cookie("login_member_telephone",telephone);
            cookie.setPath("/");
            cookie.setMaxAge(60*60*24*30);
            response.addCookie(cookie);
            //保存会员信息到Redis中
            String json = JSON.toJSON(member).toString();
            jedisPool.getResource().setex(telephone,60*30,json);
            return new Result(true,MessageConstant.LOGIN_SUCCESS);
        }else {
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
    }
}
