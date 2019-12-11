package com.ys.test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sound.midi.Soundbank;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.ognl.OgnlContext;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ys.pojo.User;

public class XMLTest {
	
	static SqlSession sqlSession = null;
	
	
	@Before
	public void init(){
		System.out.println("加载配置文件~~~~~~~~~~~~~~~~~~");
		try {
			String resource = "mybatis-configuration.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			sqlSession = sqlSessionFactory.openSession();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@BeforeClass
	public static void before(){
		System.out.println("开始运行,叮~~~~~~~~~~~~~~");
	}
	
	@AfterClass
	public static void close(){
		System.out.println("结束了,关闭资源~~~~~~~~~~~~~~~~~~");
		sqlSession.close();
	}
	
	@Test
	public void selectUserById(){
		String statement = "com.ys.mapper.UserMapper.selectUserById";
		
		User user = null;
		try {
			user = sqlSession.selectOne(statement, 2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(user);
	}
	
	@Test
	public void selectuserByIds(){
		String statement = "com.ys.mapper.UserMapper.selectUserByIds";
		User user = new User();
		user.setIds(new ArrayList<>());
		user.getIds().add(1);
		user.getIds().add(2);
		List<User> list = sqlSession.selectList(statement, user);
		for(User u : list){
			System.out.println(u);
		}
	}
	
	
	
	@Test
	public void insertOneUser(){
		String statement = "com.ys.mapper.UserMapper.insertOne";
		User user = new User();
		user.setAddress("伦敦");
		user.setBirthday(new Date());
		user.setSex("男");
		user.setUserName("阿凡达");
		sqlSession.insert(statement, user);
		System.out.println(user.getId());
		sqlSession.commit();
	}
	
	
	@Test
	public void updateUserById(){
		String statement = "com.ys.mapper.UserMapper.updateUserById";
		User user = new User();
		user.setUserName("王八蛋");
		user.setId(10);
		sqlSession.update(statement,user);
		sqlSession.commit();
	}
	
	@Test
	public void deleteByUserName(){
		String statement = "com.ys.mapper.UserMapper.deleteByUserName";
		User user = new User();
		user.setUserName("阿凡达");
		sqlSession.delete(statement, user);
		sqlSession.commit();
	}
	
	
	
	
	
	
}
