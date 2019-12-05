package cn.skunk.pojo;

import java.io.Serializable;

/**
 * 预约管理
 */
public class OrderManager implements Serializable {
	private String id;
	private String orderDate;
	private String orderAddress;
	private String name;
	private String phoneNumber;
	private String orderType;
	private String orderStatus;
	private String addressName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	@Override
	public String toString() {
		return "OrderManager{" +
				"id='" + id + '\'' +
				", orderDate='" + orderDate + '\'' +
				", orderAddress='" + orderAddress + '\'' +
				", name='" + name + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", orderType='" + orderType + '\'' +
				", orderStatus='" + orderStatus + '\'' +
				", addressName='" + addressName + '\'' +
				'}';
	}
}
