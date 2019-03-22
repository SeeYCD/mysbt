//package com.crh.database;
//
//import org.apache.tomcat.jdbc.pool.DataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
///**
// * 配置数据源
// * @author crh 2019年3月21日
// *
// */
////@Configuration
//public class DataSourceConfig {
// 
////	@Autowired
////	private Environment env;
//// 
////	@Bean
////	public DataSource getDataSource() {
////		DataSource dataSource = new DataSource();
////		dataSource.setUrl(env.getProperty("spring.datasource.url"));
////		dataSource.setUsername(env.getProperty("spring.datasource.username"));
////		dataSource.setPassword(env.getProperty("spring.datasource.password"));
////		dataSource.setInitialSize(20);
////		return dataSource;
////	}
//}
