package com.crh.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crh.entity.Response;
import com.crh.entity.User;
import com.crh.interceptor.LoginInterceptor;
import com.crh.redis.RedisUtil;

//@Controller
@RestController
@RequestMapping("/")
public class LoignController {
	private Logger log=LoggerFactory.getLogger(LoignController.class);

	@Autowired
	private RedisUtil redsiU;
	
	@GetMapping("/dologin.do")
	public Response login(User use,HttpServletRequest req,HttpServletResponse res) {
		String name=use.getName();
		String passw=use.getPassword();
		log.info("第一次登陆："+name+":"+passw);
		redsiU.set(name, name, 60l);
		req.getSession().setAttribute("user", use);
 		return new Response("ok", "suc", use);
	}
}
