package com.crh.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.crh.entity.Constants;
import com.crh.entity.User;
import com.crh.redis.RedisUtil;
import com.crh.utils.CookieUtil;
import com.crh.utils.SessionUtils;
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
	private Logger log=LoggerFactory.getLogger(LoginInterceptor.class);
	@Autowired
	private RedisUtil redsiU;
	
	// preHandle在业务处理器处理请求之前被调用，
	// postHandle在业务处理器处理请求执行完成后,生成视图之前执行
	// afterCompletion在DispatcherServlet完全处理完请求后被调用
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("【进入LoginInterceptor】");
 		String login=request.getHeader("REFERER");
 		String url=request.getRequestURL().toString();
 		log.info("login:"+login+",url:"+url);
// 		if(url!=null&&url.endsWith("login.html")) {
//			log.info("访问login");
//			return true;
//		}
		if(url!=null&&url.endsWith("dologin.do")) {
			log.info(request.getParameter("name")+":【doinglogin】");
			return true;
		}
		User user = (User) request.getSession().getAttribute("user");
		if (user== null) {
			log.info("【Session】中没有user");
  	 		String name=CookieUtil.getValue(CookieUtil.getCookieByName(request, "username"));
 	 		boolean islogin=true;
	 		if(name==null) {//1.未登录
	 			islogin=false;
				log.info("【未登录】,cookie中未能读取：name");
	 		}
  			if(islogin&&redsiU.get(name)==null) {//2.redis可能已经过期
	 			islogin=false;
  				log.info("【redis】不存在:"+name);
			}
 			if(!islogin) {
 				PrintWriter pw=null;
				try {
					pw= response.getWriter();
					pw.write("unligon");
				}finally {
					pw.close();
 				}
			
				return false;
 			}
 			log.info("【redis】存在:"+name);
 			//session中不存在，redis中存在，添加seesion；直接采用redis判断也可以
 			user=new User();user.setName(name);
  			request.getSession().setAttribute("user", user);
  			SessionUtils.setSessionTime(request.getSession());
  			redsiU.set(name, name,(long) Constants.SESSIONTIME);
		}

		return super.preHandle(request, response, handler);
	}

	/**
	 * 获取cookie中用户名
	 * @param request
	 * @return
	 */
	public String getUserName(HttpServletRequest request) {
		Cookie[] cookies=request.getCookies();
		for(Cookie ck:cookies) {
			if("username".equalsIgnoreCase(ck.getName())) {
				return ck.getValue();
			}
		}
		return null;
 	}
}
