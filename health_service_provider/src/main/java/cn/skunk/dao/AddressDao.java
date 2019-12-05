package cn.skunk.dao;

import cn.skunk.pojo.Address;
import com.github.pagehelper.Page;

import java.util.ArrayList;
import java.util.List;

public interface AddressDao {
    public ArrayList<Address> findAll();

    Page<Address> findPage(String queryString);

    void deleteById(Integer id);

    void add(Address address);

    List<String> findAddressNames();
}
