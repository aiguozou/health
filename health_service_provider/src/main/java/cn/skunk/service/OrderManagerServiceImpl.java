package cn.skunk.service;

import cn.skunk.constant.MessageConstant;
import cn.skunk.dao.MemberDao;
import cn.skunk.dao.OrderDao;
import cn.skunk.dao.OrderManagerDao;
import cn.skunk.dao.OrderSettingDao;
import cn.skunk.entity.PageResult;
import cn.skunk.entity.QueryPageBean;
import cn.skunk.entity.Result;
import cn.skunk.pojo.Member;
import cn.skunk.pojo.Order;
import cn.skunk.pojo.OrderManager;
import cn.skunk.pojo.OrderSetting;
import cn.skunk.utils.DateUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service(interfaceClass = OrderManagerSercive.class)
@Transactional
public class OrderManagerServiceImpl implements OrderManagerSercive {
	@Autowired
	private OrderManagerDao orderManagerDao;
	@Autowired
	private OrderSettingDao orderSettingDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private OrderDao orderDao;

	public PageResult findPage(QueryPageBean queryPageBean) {
		Integer currentPage = queryPageBean.getCurrentPage();
		Integer pageSize = queryPageBean.getPageSize();
		String queryString = queryPageBean.getQueryString();

		PageHelper.startPage(currentPage, pageSize);
		Page<OrderManager> page = orderManagerDao.findPage(queryString);
		return new PageResult(page.getTotal(), page.getResult());
	}

	public Result order(Map map) throws Exception {
		//1、检查用户所选择的预约日期是否已经提前进行了预约设置，如果没有设置则无法进行预约
		String orderDate = (String) map.get("orderDate");
		OrderSetting orderSetting = orderSettingDao.findByOrderDate(DateUtils.parseString2Date(orderDate));
		if (orderSetting == null) {
			return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
		}
		//2、检查用户所选择的预约日期是否已经约满，如果已经约满则无法预约
		int number = orderSetting.getNumber();
		int reservations = orderSetting.getReservations();
		if (reservations >= number) {
			return new Result(false, MessageConstant.ORDER_FULL);
		}
		//3、检查用户是否重复预约（同一个用户在同一天预约了同一个套餐），如果是重复预约则无法完成再次预约
		String telephone = (String) map.get("phoneNumber");
		Member member = memberDao.findByTelephone(telephone);
		if (member != null) {
			Integer memberId = member.getId();
			Date order_Date = DateUtils.parseString2Date(orderDate);
			Integer[] setmealIds = (Integer[]) map.get("setmealIds");
			String setmealId = String.valueOf((Integer) setmealIds[0]);
			Order order = new Order(memberId, order_Date, Integer.parseInt(setmealId));
			List<Order> list = orderDao.findByCondition(order);
			if (list != null && list.size() > 0) {
				return new Result(false, MessageConstant.HAS_ORDERED);
			}
		}
		//4、检查当前用户是否为会员，如果是会员则直接完成预约，如果不是会员则自动完成注册并进行预约
		member = new Member();
		Date birthday = DateUtils.parseString2Date((String) map.get("birthday"));
		member.setBirthday(birthday);
		String fileNumber = new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date());
		member.setFileNumber(fileNumber);
		member.setName((String) map.get("name"));
		member.setPhoneNumber(telephone);
		member.setIdCard((String) map.get("idCard"));
		member.setSex((String) map.get("sex"));
		member.setRegTime(new Date());

		Order order = new Order();
		order.setOrderDate(DateUtils.parseString2Date(orderDate));//预约日期
		order.setOrderType("电话预约");//预约类型
		order.setOrderStatus(Order.ORDERSTATUS_NO);//到诊状态
		Integer[] setmealIds = (Integer[]) map.get("setmealIds");
		if (setmealIds == null || setmealIds.length == 0){
			return new Result(false,"请选择套餐");
		}
		order.setSetmealId((Integer) setmealIds[0]);

		memberDao.add(member);
		order.setMemberId(member.getId());//设置会员ID
		orderDao.add(order);

		//5、预约成功，更新当日的已预约人数
		orderSetting.setReservations(orderSetting.getReservations() + 1);
		orderSettingDao.editReservationsByOrderDate(orderSetting);
		return new Result(true, MessageConstant.ORDER_SUCCESS, order.getId());
	}

	@Override
	public void orderOk(int id) {
		orderManagerDao.orderOk(id);
	}


	public void orderCancle(int id) {
		orderManagerDao.orderCancle(id);
	}

}
