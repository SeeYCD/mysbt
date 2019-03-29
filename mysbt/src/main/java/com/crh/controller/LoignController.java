package com.crh.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crh.entity.Constants;
import com.crh.entity.Response;
import com.crh.entity.User;
import com.crh.redis.RedisUtil;
import com.crh.utils.CookieUtil;
import com.crh.utils.SessionUtils;

//@Controller
@RestController
@RequestMapping("/")
public class LoignController {
	private Logger log = LoggerFactory.getLogger(LoignController.class);

	@Autowired
	private RedisUtil redsiU;

	@GetMapping("/dologin.do")
	public Response login(User use, HttpServletRequest req, HttpServletResponse res) {
		String name = use.getName();
		String passw = use.getPassword();
		log.info("登陆name：" + name + ",psd:" + passw);
		// 设置redis
 		redsiU.set(name, name, (long) Constants.SESSIONTIME);
		CookieUtil.setCookie(res, "username", name, -1);
		// 设置session
		req.getSession().setAttribute("user", use);
		// 设置session超时
		SessionUtils.setSessionTime(req.getSession());
		log.info("登陆success：" + name );
   		return new Response("ok", "suc", use);
	}
}
