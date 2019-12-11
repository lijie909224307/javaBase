package com.ys.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;

import javax.sound.midi.Soundbank;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ys.mapper.UserDao;
import com.ys.pojo.User;

public class AnnotationTest {

	static SqlSession sqlSession = null;
	
	@BeforeClass
	public static void beforeClass(){
		System.out.println("测试类启动!~~~~~~~~~~~~~~");
		System.out.println("加载配置文件~~~~~~~~~~~~~~");
		
		try {
			InputStream inputStream = Resources.getResourceAsStream("mybatis-configuration.xml");
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			sqlSession = sqlSessionFactory.openSession();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Before
	public void before(){
		System.out.println("开始执行方法~~~~~~~~~~~~");
	}
	
	@Test
	public void testSelect(){
		UserDao userDao = sqlSession.getMapper(UserDao.class);
		User user = userDao.selectUserById(1);
		System.out.println(user);
	}
	
	@Test
	public void testInsert(){
		User user = new User("张小敬","男",new Date(),"长安");
		UserDao userDao = sqlSession.getMapper(UserDao.class);
		userDao.insertOne(user);
		sqlSession.commit();
		System.out.println(user);
	}
	
	@Test
	public void testUpdate(){
		User user = new User();
		user.setId(10);
		user.setUserName("李必小狐狸");
		UserDao userDao = sqlSession.getMapper(UserDao.class);
		userDao.updateUserById(user);
		sqlSession.commit();
		System.out.println(user);
	}
	
	@Test
	public void testDelete(){
		String userName = "狐狸";
		UserDao userDao = sqlSession.getMapper(UserDao.class);
		userDao.deleteByUserName(userName);
		sqlSession.commit();
	}
	
	@After
	public void after(){
		System.out.println("执行方法结束~~~~~~~~~~~~");
	}
	
	@AfterClass
	public static void afterClass(){
		sqlSession.close();
		System.out.println("关闭资源!~~~~~~~~~~~~~~~~~~~");
		System.out.println("测试类结束!~~~~~~~~~~~~~~~~~~~");
	}
	

}
