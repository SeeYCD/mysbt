package com.crh.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * cookis的相关操作
 * 
 * @author user
 *
 */
public class CookieUtil {

	/**
	 * 根据名称获取cookie
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static  Cookie getCookieByName(HttpServletRequest request, String name) {
		if (name == null) {
			return null;
		}
		Cookie[] cookies = request.getCookies();
		for (Cookie ck : cookies) {
			if (name.equalsIgnoreCase(ck.getName())) {
				return ck;
			}
		}
		return null;
	}
	/**
	 * 获取cookie的value
	 * @param cookie
	 * @return
	 */
	public static  String  getValue(Cookie cookie) {
		if (cookie == null) {
			return null;
		}
 		return cookie.getValue();
	}
	/**
	 * 保存Cookies
	 * @param response
	 * @param name
	 * @param value
	 * @param time
	 * @return
	 */
	public static HttpServletResponse setCookie(HttpServletResponse response, String name, String value, int time) {
		// new一个Cookie对象,键值对为参数
		Cookie cookie = new Cookie(name, value);
		// tomcat下多应用共享
		cookie.setPath("/");
		// 如果cookie的值中含有中文时，需要对cookie进行编码，不然会产生乱码
		try {
			URLEncoder.encode(value, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 单位：秒
		cookie.setMaxAge(time);
		// 将Cookie添加到Response中,使之生效
		response.addCookie(cookie); // addCookie后，如果已经存在相同名字的cookie，则最新的覆盖旧的cookie
		return response;
	}

	/**
	 * 删除cookie
	 * @param request
	 * @param response
	 * @param deleteKey
	 */
	@SuppressWarnings("unused")
	private void delectCookieByName(HttpServletRequest request, HttpServletResponse response, String deleteKey) {
		Cookie cookie = getCookieByName(request, deleteKey);
		if (cookie == null) {
			return;
		}
		// 默认值是-1，即：关闭浏览器时就清除cookie;
		// 当设置为0的时候：创建完cookie，使用后马上就删除;
		// 因为时间到了，又因为，cookie没有清除方法，所以设置为 0，就相当于清除方法;
		// 当设置时间大于0，当时间到达后就会自动删除
		cookie.setMaxAge(0);// 设置cookie有效时间为0
		cookie.setPath("/");// 不设置存储路径
		response.addCookie(cookie);
	}
}
