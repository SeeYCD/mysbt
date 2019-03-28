package com.crh.config.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.crh.interceptor.LoginInterceptor;
/**
 * 拦截器配置类
 * @author user
 *
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	//添加登录拦截
	@Autowired
	private LoginInterceptor loginInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor);
	}
	
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		//开启路径后缀匹配
        configurer.setUseRegisteredSuffixPatternMatch(true);
	}
}
