package cn.skunk.service;

import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.entity.Result;

import java.util.Map;

public interface OrderManagerSercive {

	public PageResult findPage(QueryPageBean queryPageBean);

	public Result order(Map map) throws Exception;

	public void orderOk(int id);

	public void orderCancle(int id);
}
