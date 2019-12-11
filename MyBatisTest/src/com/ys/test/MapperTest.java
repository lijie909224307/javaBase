package com.ys.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ys.mapper.PersonDao;
import com.ys.pojo.Person;

public class MapperTest {

	private static SqlSession sqlSession;
	
	@BeforeClass
	public static void beforeClass(){
		try {
			String resources = "mybatis-configuration.xml";
			InputStream inputStream = Resources.getResourceAsStream(resources);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			sqlSession = sqlSessionFactory.openSession();
			System.out.println("加载配置文件成功!~~~~~~~~~~~~~~~~~");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Before
	public void before(){
		System.out.println("执行方法~~~~~~~~~~~~");
	}
	
	@Test
	public void test(){
		
		PersonDao personDao = sqlSession.getMapper(PersonDao.class);
		Person person = personDao.selectById(1);
		System.out.println(person);
		
	}
	
	
	@After
	public void after(){
		System.out.println("方法结束~~~~~~~~~~~~~");
	}
	
	@AfterClass
	public static void afterClass(){
		Assert.assertNotNull(sqlSession);
		sqlSession.close();
		System.out.println("关闭资源~~~~~~~~~~~~");
	}
	

}
