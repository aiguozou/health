package cn.skunk.dao;

import cn.skunk.pojo.OrderManager;
import com.github.pagehelper.Page;

public interface OrderManagerDao {

    public Page<OrderManager> findPage(String queryString);

    public void orderOk(int id);

    public void orderCancle(int id);

}
