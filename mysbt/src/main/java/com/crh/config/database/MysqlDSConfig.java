package com.crh.config.database;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.github.pagehelper.PageInterceptor;
/**
 * 通过jdni配置数据源+mybatis整合；不需要jndi时，当前注册类无需扫描
 * @author user
 *
 */
@Configuration
@MapperScan(basePackages = "com.crh.dao", sqlSessionFactoryRef = "sqlSessionFactoryMysql")
public class MysqlDSConfig {
	//yml文件中配置mapper的xml路径
	@Value("${mybatisp.mapperpath}")
	private String classpath;

	//自定义配置的数据源
	@Resource(name = "dataSourceMysql")
	private DataSource dsMysql;

	@Bean
	public DataSourceTransactionManager masterTransactionManager() {
		return new DataSourceTransactionManager(dsMysql);
	}

	@Bean
	@Primary // springboot2.0+的改动点
	public SqlSessionFactory sqlSessionFactoryMysql() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		//mybatis的mapper的文件扫描
		sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(classpath));
		sessionFactory.setDataSource(dsMysql);
		// 设置MyBatis分页插件  springboot默认配置了分页插件，自行配置会报错有多个分页插件
//		PageInterceptor pageInterceptor = this.initPageInterceptor();
//		sessionFactory.setPlugins(new Interceptor[] { pageInterceptor });
		return sessionFactory.getObject();
	}

	@Bean
	@Primary // springboot2.0+的改动点
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactoryMysql());
	}

//初始化插件的参数，数据库自适应
	public PageInterceptor initPageInterceptor() {
		PageInterceptor pageInterceptor = new PageInterceptor();
		Properties properties = new Properties();
//        properties.setProperty("helperDialect", "mysql");
		properties.setProperty("offsetAsPageNum", "true");
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("autoRuntimeDialect", "true");//自适应数据库
		pageInterceptor.setProperties(properties);
		return pageInterceptor;
	}

}
