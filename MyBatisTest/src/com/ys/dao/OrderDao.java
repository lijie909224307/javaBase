package com.ys.dao;

import com.ys.pojo.Order;
import com.ys.pojo.User;

public interface OrderDao {
	
	/**
	 * 简单查询
	 * @param id
	 * @return
	 */
	public Order getOrderByUserId(Integer userId);
	
	
	/**
	 * 结果映射,resultMap
	 * @param id
	 * @return
	 */
	public Order selectOrderById(Integer id);
	
	/**
	 * 测试懒加载
	 * @param id
	 * @return
	 */
	public Order lazyLoadSelect(Integer id);
	
	/**
	 * 测试一级缓存, User中多个order,查出user的orders后,查询order里面的user信息 , 一 多 一
	 * @param userId
	 * @return
	 */
	public Order cacheTestSelect(Integer userId);
	
	
	public User getUserById(Integer userId);
	
	
	
}
