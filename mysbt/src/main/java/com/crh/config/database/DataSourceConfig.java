package com.crh.config.database;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * 配置JNDI数据源，如果采用yml文件内配置数据源，就不需要这个配置类
 * 这个配置类配合JNDI
 * @author crh 2019年3月21日
 *
 */

@EnableMBeanExport(registration=RegistrationPolicy.IGNORE_EXISTING)
@Configuration
public class DataSourceConfig {
	@Value("${spring.datasource.jndi-name}")
	private String mysqlJndiName;
	
	//可以配置mysql
	@Bean("dataSourceMysql")
	public DataSource mysqlJNDISource() {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		dataSourceLookup.setResourceRef(true);
 		return dataSourceLookup.getDataSource(mysqlJndiName);
	}
	//可以配置oracle多个数据源
	@Bean("dataSourceOracle")
	public DataSource mysqlJNDISource2() {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		dataSourceLookup.setResourceRef(true);
 		return dataSourceLookup.getDataSource(mysqlJndiName);
	}
}
