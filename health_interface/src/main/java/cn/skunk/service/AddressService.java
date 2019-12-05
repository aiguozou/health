package cn.skunk.service;

import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.pojo.Address;

import java.util.ArrayList;
import java.util.List;


public interface AddressService {
    ArrayList<Address> findAll();

    PageResult findPage(QueryPageBean queryPageBean);

    void deleteById(Integer id);

    void add(Address address);

    List<String> findAddressNames();
}
