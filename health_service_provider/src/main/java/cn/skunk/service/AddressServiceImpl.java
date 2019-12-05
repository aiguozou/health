package cn.skunk.service;

import cn.skunk.dao.AddressDao;
import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.pojo.Address;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(interfaceClass = AddressService.class)
@Transactional
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressDao addressDao;

    @Override
    public ArrayList<Address> findAll() {
        return addressDao.findAll();

    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        PageHelper.startPage(currentPage,pageSize);
        Page<Address> page = addressDao.findPage(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void deleteById(Integer id) {
        addressDao.deleteById(id);
    }

    @Override
    public void add(Address address) {
        addressDao.add(address);
    }

    @Override
    public List<String> findAddressNames() {
        return addressDao.findAddressNames();
    }


}
