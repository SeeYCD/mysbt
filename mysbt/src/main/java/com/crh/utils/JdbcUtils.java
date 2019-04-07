package com.crh.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

/**
 * JDBC-demo
 * @author chen
 *
 */
public class JdbcUtils {
	public static void main(String[] args) throws ClassNotFoundException {
		System.out.println(JdbcUtils.class+":demo begin");
		String name="c##root";
		String psw="root";
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
 		Class.forName("oracle.jdbc.driver.OracleDriver");
 		String sql="select id from demo1 t where id = ? ";
		try {
 			Connection con=DriverManager.getConnection(url, name, psw) ;
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setObject(1, 1);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt(1));
 			}
 			System.out.println(JdbcUtils.class+":demo success");
		} catch (SQLException e) {
 			System.out.println(JdbcUtils.class+":demo error");
  			e.printStackTrace();
		}
		 
		
	}
	
	/**
	 * class.forname测试
	 */
	@Test
	public void test1() {
		try {
			//加载类会默认进行初始化
			Class.forName("com.crh.utils.SessionUtils");
			//加载类，根据第二个参数，true是初始化，false不进行初始化
			Class.forName("com.crh.utils.SessionUtils",false,JdbcUtils.class.getClassLoader());
			ClassLoader classl=JdbcUtils.class.getClassLoader();
			//加载类不会进行初始化
			classl.loadClass("com.crh.utils.SessionUtils");
		} catch (ClassNotFoundException e) {
 			e.printStackTrace();
		}
	}

}
