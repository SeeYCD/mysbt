package com.crh.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * session的监听器，可以监听到session的创建与销毁
 * @author user
 *
 */
//@WebListener 可以扫描这个注解也可以通过；bean注册
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {
	public static final Logger logger = LoggerFactory.getLogger(SessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		logger.info("【sessionCreated】");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		logger.info("【sessionDestroyed】");
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		logger.info("【attributeAdded】");
		logger.info("key----:" + se.getName());
		logger.info("value---:" + se.getValue());

	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		logger.info("【attributeRemoved】");
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		logger.info("【attributeReplaced】");

	}

}
