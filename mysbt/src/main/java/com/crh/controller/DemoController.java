package com.crh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.crh.entity.User;
import com.crh.redis.RedisUtil;
import com.crh.service.ServiceUser;
import com.crh.service.impl.ServiceUserImpl;

@RestController //集成了responsebody
@RequestMapping(value="/demo")
public class DemoController {
	private Logger log=LoggerFactory.getLogger(DemoController.class);
	@Autowired
	private RedisUtil ru;
	
	@Autowired
	ServiceUser serviceUser;
	@GetMapping(value="/helloWorld")
	public String helloWorld(String ss) {
		String re=null;
// 		ru.set(ss, 0);
//		re=ss+" is save in redis is fial!";
// 		if(ss.equals(ru.get(ss))) {
//			re=ss+" is save in redis is ok!";
//			log.info(ss+" is save in redis is ok!");
//		}
 		testRedis(ss);
 		log.info("------------------;;;"+ru.get(ss).toString());
		return ru.get(ss).toString();
 	}
	public void testRedis(String ss) {
		Thread th=null;
		for(int i=0;i<10;i++) {
			th=new Thread(new Runnable() {
 				@Override
				public void run() {
 					for(int i=0;i<10000;i++) {
 						ru.incr(ss, 1);
 					}
				}
			});
			th.start();
		}
		if(Thread.activeCount()>1) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
 				e.printStackTrace();
			}
		}
		
	}
	@GetMapping(value="/userGet")
	public String getUser(String ss){
		User u=null;
		try{
			log.info("1========================="+serviceUser.getClass());
			log.info("userGet-------------------id:"+ss);
			u=serviceUser.selectByPrimaryKey(Integer.valueOf(ss));
			log.info("userGet-------------------user:"+u);
			u.setAge(Integer.valueOf(ss)+100);
			log.info("userGet-------------------user:"+u);
//	 		serviceUser.updateByPrimaryKeySelective(u);
//	 		serviceUser.insert(u);
		}catch(Exception e){
			log.error("qqqqqqqqqq"+e.getMessage());
		}
 		return u.toString();
 	}
	
	public RedisUtil getRu() {
		return ru;
	}
	public void setRu(RedisUtil ru) {
		this.ru = ru;
	}
}
