package com.ys.test;

import static org.junit.Assert.*;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ys.dao.OrderDao;
import com.ys.pojo.Order;
import com.ys.pojo.User;

public class RelationTest {

	private static SqlSession sqlSession;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			String resource = "mybatis-configuration.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			sqlSession = sqlSessionFactory.openSession();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("读取配置文件成功~~~~~~~~~~~~~");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		sqlSession.close();
		System.out.println("关闭资源~~~~~~~~~~~~~");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("方法开始~~~~~~~~~~~~~");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("方法结束~~~~~~~~~~~~~");
	}

	@Test
	public void simpleSelect() {
		try {
			OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
			Order order = orderDao.selectOrderById(1001);
			System.out.println(order);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void lazyLoadSelect(){
		OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
		Order order = orderDao.lazyLoadSelect(1001);
		System.out.println(order.getNumber());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		User user = order.getUser();
		System.out.println(user.getBirthday());
	}
	
	
	

}
