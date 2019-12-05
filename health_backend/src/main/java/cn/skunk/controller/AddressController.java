package cn.skunk.controller;


import cn.skunk.constant.MessageConstant;
import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.entity.Result;
import cn.skunk.pojo.Address;
import cn.skunk.service.AddressService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
@RequestMapping("/address")
public class AddressController {
    @Reference
    AddressService addressService;

    @RequestMapping("/getCompanyAddress")
    public Result getCompanyAddress() {
        try {
            ArrayList<Address> list = addressService.findAll();
            return new Result(true, MessageConstant.QUERY_ADDRESS_SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_ADDRESS_FAIL, "fail");
        }
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return addressService.findPage(queryPageBean);
    }

    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            addressService.deleteById(id);
        } catch (Exception e) {
            return new Result(false, MessageConstant.DELETE_ADDRESS_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_ADDRESS_SUCCESS);
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Address address) {
        try {
            addressService.add(address);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_ADDRESS_FAIL);
        }
        return new Result(true, MessageConstant.ADD_ADDRESS_SUCCESS);
    }

}
