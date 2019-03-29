package com.crh;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

import com.crh.interceptor.LoginInterceptor;
import com.crh.redis.RedisUtil;

/**
 * springboot测试 导出war包： 继承SpringBootServletInitializer
 * 重写SpringApplicationBuilder方法
 *
 * @author Chen
 *
 */
//scanBasePackages 扫描路径  @ComponentScan(basePackages = {"com.crh.controller", "com.crh.redis"})
@SpringBootApplication(scanBasePackages = { 
		"com.crh.controller", 
		"com.crh.redis", 
		"com.crh.service",
		"com.crh.interceptor", // 拦截器扫描
 		"com.crh.config.webconfig" }) // web启动统一bean注册、监听、拦截、后缀设置等
//		"com.crh.config.database" })JNDI配置的数据源，不需要时，不用扫描
@MapperScan("com.crh.dao") // mybatis扫描dao接口路径

public class GitDemoApplication extends SpringBootServletInitializer {

	private static Logger log = LoggerFactory.getLogger(GitDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GitDemoApplication.class, args);
		log.info("GitDemoApplication is begin---inner-----start-------");

	}

	/**
	 * 部署tomcat
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		log.info("GitDemoApplication is begin---outer-----start-------");
		return super.configure(builder);
	}

}
