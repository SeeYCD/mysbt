package com.crh.config.webconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.crh.interceptor.LoginInterceptor;
import com.crh.listener.SessionListener;

/**
 * 拦截器配置类
 * 
 * @author user
 *
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	// 添加登录拦截
	@Autowired
	private LoginInterceptor loginInterceptor;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor);
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		// 开启路径后缀匹配
		configurer.setUseRegisteredSuffixPatternMatch(true);
	}

	/**
	 * 注册监听器
	 * @return
	 */
	@Bean
	public ServletListenerRegistrationBean<SessionListener> listenerRegist() {
		ServletListenerRegistrationBean<SessionListener> srb = new ServletListenerRegistrationBean<SessionListener>();
		srb.setListener(new SessionListener());
		System.out.println("listener");
		return srb;
	}

	/**
	 * 注册匹配*.do后缀请求
	 * 
	 * @param dispatcherServlet
	 * @return
	 */
	@Bean
	public ServletRegistrationBean<DispatcherServlet> servletRegistrationBean(DispatcherServlet dispatcherServlet) {
		ServletRegistrationBean<DispatcherServlet> servletServletRegistrationBean = new ServletRegistrationBean<>(
				dispatcherServlet);
		servletServletRegistrationBean.addUrlMappings("*.do");
		return servletServletRegistrationBean;
	}

}
