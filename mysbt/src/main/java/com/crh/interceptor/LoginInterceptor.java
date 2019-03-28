package com.crh.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.crh.entity.User;
import com.crh.redis.RedisUtil;
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
		log.info("进入LoginInterceptor");
 		String login=request.getHeader("REFERER");
 		String url=request.getRequestURL().toString();
 		log.info("login:"+login+",url:"+url);
// 		if(url!=null&&url.endsWith("login.html")) {
//			log.info("访问login");
//			return true;
//		}
		if(url!=null&&url.endsWith("dologin.do")) {
			log.info(request.getParameter("username")+":dologin--------");
			return true;
		}
		User user = (User) request.getSession().getAttribute("user");
		if (user== null) {
	 		String name=request.getParameter("username");
	 		boolean islogin=true;
	 		if(name==null) {
	 			islogin=false;
				log.error("未能读取：name");
	 		}
 			if(islogin&&(user=(User) redsiU.get(name))==null) {
	 			islogin=false;
  				log.info("redis不存在:"+name);
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
 			log.info("redis存在:"+name);
  			request.getSession().setAttribute("user", user);
		}

		return super.preHandle(request, response, handler);
	}

}
