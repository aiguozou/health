package cn.skunk.controller;

import cn.skunk.service.CheckItemService;
import cn.skunk.constant.MessageConstant;
import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.entity.Result;
import cn.skunk.pojo.CheckItem;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/checkItem")
public class CheckItemController {
    @Reference
    private CheckItemService checkItemService;

    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem) {
        try {
            checkItemService.add(checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult page = checkItemService.findPage(queryPageBean);
        return page;
    }

    @PreAuthorize("hasAuthority('CHECKITEM_DELETE')")//权限校验
    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            checkItemService.deleteById(id);
        } catch (RuntimeException e) {
            return new Result(false,e.getMessage());
        }catch (Exception e) {
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            CheckItem checkitem = checkItemService.findById(id);
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkitem);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckItem checkItem) {
        try {
            checkItemService.edit(checkItem);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }


    @RequestMapping("/findAll")
    public Result findAll() {
        try {
            List<CheckItem> list = checkItemService.findAll();
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

}
