package com.ys.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.cache.impl.PerpetualCache;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ys.dao.UserMapper;
import com.ys.mapper.UserDao;
import com.ys.pojo.Order;
import com.ys.pojo.User;

public class CacheTest {

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
	public void cacheOneTest1(){
		
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.getUserById(1);
		System.out.println(user.getUserName());
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
		List<Order> orders = user.getOrders();
		for(Order order:orders){
			// 下面的延迟加载,sql只执行了一次, 一级缓存.. 
			System.out.println(order.getUser());
		}
	}
	
	@Test
	public void cacheOneTestSqlSessionClear(){
		Configuration configuration = sqlSession.getConfiguration();
		configuration.getCaches();
		
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.getUserById(1);
		System.out.println(user.getUserName());
		
		user = userMapper.getUserById(2);	// 重新执行
		System.out.println(user.getUserName());
		
//		sqlSession.clearCache();   // 清除缓存或者关闭 close ,重新执行sql
		user = userMapper.getUserById(1);	// 缓存获取,不再次执行sql
		System.out.println(user.getUserName());
		
	}
	
	
	@Test
	public void cacheOneTestUpdate(){
		UserDao userDao = sqlSession.getMapper(UserDao.class);
		
		User user = userDao.selectUserById(1);
		System.out.println(user);
		
		// 如果惊醒了增删改操作 , sqlSession 被提交 , 一级缓存被清空  , 确保缓存中存储的是最新的信息,避免脏读!!!
		user.setUserName("地球");
		userDao.updateUserById(user);
		sqlSession.commit();
		
		userDao.selectUserById(1);
		System.out.println(user);
		
		
	}
	
	
	@Test
	public void cacheTwoTest(){
		// 1, mybatis 配置文件开启二级缓存
		// 2, xml 配置文件开启二级缓存
		// 3, 相关实体类必须全部 实现序列化!!
		// 4, 想要执行二级缓存,sqlSession必须关闭
		
		SqlSessionFactory sqlSessionFactory = null;
		try {
			String resource = "mybatis-configuration.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			SqlSession sqlSession1 = sqlSessionFactory.openSession();
			SqlSession sqlSession2 = sqlSessionFactory.openSession();
		
			UserMapper userDao1 = sqlSession1.getMapper(UserMapper.class);
			UserMapper userDao2 = sqlSession2.getMapper(UserMapper.class);
			
			User user1 = userDao1.getUserById(1);
			System.out.println(user1);
	
			
			sqlSession1.clearCache();// 并不能清除二级缓存
//			sqlSession1.close();
			
			user1.setUserName("阿萨德阿萨德");
			user1.setId(2);
//			userDao1.updateUserById(user1);	// 增删改将清除二级缓存
			sqlSession1.close();	//想要执行二级缓存,sqlSession必须关闭
//			sqlSession1.commit();	
			
			user1 = userDao2.getUserById(1);
			System.out.println(user1);

		
	}

	

}
