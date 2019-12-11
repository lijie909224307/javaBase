package com.ys.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;


	public User() {	}
	public User(String userName, String sex, Date birthday, String address) {
		super();
		this.userName = userName;
		this.sex = sex;
		this.birthday = birthday;
		this.address = address;
	}
	
	private List<Integer> ids;
	
	
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	
	private List<Order> orders;
	

	private Integer id;
	private String userName;
	private String sex;
	private Date birthday;
	private String address;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", sex=" + sex + ", birthday=" + birthday + ", address="
				+ address + "]";
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
	
	
}
