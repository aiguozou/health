package cn.skunk.controller;

import cn.skunk.constant.MessageConstant;
import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.entity.Result;
import cn.skunk.pojo.CheckGroup;
import cn.skunk.pojo.OBJECT_MEMBER;
import cn.skunk.pojo.Sort;
import cn.skunk.service.MemberSortService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/memberSort")
public class MemberSortController {
    @Reference
    private MemberSortService memberSortService;

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return memberSortService.findPage(queryPageBean);
    }

    @RequestMapping("/findAll")
    public Result findAll() {
        try {
            List<Sort> list = memberSortService.findAll();
            return new Result(true, MessageConstant.QUERY_SORT_SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SORT_FAIL);
        }
    }
//    findById

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            OBJECT_MEMBER object_member = memberSortService.findById(id);
            return new Result(true, MessageConstant.QUERY_SORT_SUCCESS, object_member);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SORT_FAIL);
        }
    }

    //    save
    @RequestMapping("/edit")
    public Result edit(@RequestBody OBJECT_MEMBER object_member, Integer[] checkList) {
        try {
            memberSortService.edit(object_member, checkList);
            return new Result(true, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"fail");
        }
    }
}
