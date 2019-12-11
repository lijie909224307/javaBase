package com.h2.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DemoMain {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		//Embedded 模式， 在指定位置创建了库和数据文件。
		Connection connection = DriverManager.getConnection("jdbc:h2:E:/research/workspace/H2Test/db/test", "sa", "");
		Statement stmt = connection.createStatement();
		String sql = "DROP TABLE IF EXISTS TEST;CREATE TABLE TEST(ID INT PRIMARY KEY,NAME VARCHAR(255));INSERT INTO TEST VALUES(1, 'Hello');INSERT INTO TEST VALUES(2, 'World');";
		stmt.execute(sql);
		ResultSet rs = stmt.executeQuery("SELECT * FROM TEST");
		while (rs.next()) {
			System.out.println(rs.getInt("ID") + "," + rs.getString("NAME"));
		}
		connection.close();
		
	}
}
