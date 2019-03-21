package com.crh.demo.springdemo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class Demo1 extends Demo2{
	private Demo3 d=new Demo3();
	public static void main(String[] args) {
		Demo1 d=new Demo1("1");
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				AppConfig.class);
		
		//从bean工厂取bean，如果没有就创建这个bean
		BeanFactory factory = new XmlBeanFactory( new ClassPathResource ( "com/res/beans.xml" ) ) ;
		factory.getBean("beanId");//实时创建
		//从ApplicationContext 中取 bean，会把beans.xml中的bean都实例化了；
		ApplicationContext ac = new ClassPathXmlApplicationContext ( "com/res/beans.xml" ) ;
		ac.getBean("beanId");
		
		ApplicationListener<ApplicationEvent> al=new ApplicationListener<ApplicationEvent>() {
			@Override
			public void onApplicationEvent(ApplicationEvent event) {
 				
			}
		};
	}
	Demo1(String n){
 		System.out.println("demo1");
	}

}
class Demo2 {
	Demo2(){
		System.out.println("demo2");
	}

}
class Demo3 {
	Demo3(){
		System.out.println("demo3");
	}

}
