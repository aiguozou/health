package cn.skunk.controller;

import cn.skunk.constant.MessageConstant;
import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.entity.Result;
import cn.skunk.pojo.Member;
import cn.skunk.service.MemberPCService;
import com.alibaba.dubbo.config.annotation.Reference;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/memberPC")
public class MemberPCController {
    @Reference
    private MemberPCService memberPCService;

    @RequestMapping("/pageQuery")
    public PageResult pageQuery(@RequestBody QueryPageBean queryPageBean) {
        PageResult page = memberPCService.pageQuery(queryPageBean);
        return page;
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Member member) {
        try {
            member.setRegTime(new Date());
            memberPCService.add(member);
            return new Result(true, MessageConstant.ADD_MEMBER_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_MEMBER_FAIL);
        }
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            Member member = memberPCService.findById(id);
            return new Result(true, MessageConstant.QUERY_MEMBER_SUCCESS, member);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_MEMBER_FAIL);
        }
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody Member member) {
        try {
            memberPCService.edit(member);
            return new Result(true, MessageConstant.EDIT_MEMBER_SUCCESS);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_MEMBER_FAIL);
        }
    }

    @RequestMapping("/deleteById")
    public Result deleteById(Integer id) {
        try {
            memberPCService.deleteById(id);
            return new Result(true, MessageConstant.DELETE_MEMBER_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_MEMBER_FAIL);
        }
    }

}
