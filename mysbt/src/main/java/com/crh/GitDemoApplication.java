package com.crh;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * springboot测试
 * 导出war包：
 * 继承SpringBootServletInitializer
 * 重写SpringApplicationBuilder方法
 *
 * @author Chen
 *
 */
//scanBasePackages 扫描路径
@SpringBootApplication(scanBasePackages = { "com.crh.controller",
		"com.crh.redis", "com.crh.service" })
//mybatis扫描dao接口路径
@MapperScan("com.crh.dao")
// @ComponentScan(basePackages = {"com.crh.controller", "com.crh.redis"})


public class GitDemoApplication extends SpringBootServletInitializer {
	private static Logger log = LoggerFactory
			.getLogger(GitDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GitDemoApplication.class, args);
		log.info("GitDemoApplication is begin-------111111---");

	}

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder builder) {
		log.info("GitDemoApplication is begin-------222222---");
		return super.configure(builder);
	}
	
}
