package cn.skunk.controller;

import cn.skunk.constant.MessageConstant;
import cn.skunk.entity.Result;
import cn.skunk.service.UserService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {
    @Reference
    private UserService userService;

    @RequestMapping("/getUsername")
    public Result getUsername(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user!=null) {
            String username = user.getUsername();
            return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,username);
        }
        return new Result(false,MessageConstant.GET_USERNAME_FAIL);
    }
    @RequestMapping("/register")
    @ResponseBody
    public void register(cn.skunk.pojo.User user, HttpServletResponse response){
        try {
            userService.register(user);
            response.sendRedirect("/login.html");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
