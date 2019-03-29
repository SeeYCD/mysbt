package com.crh.utils;

import javax.servlet.http.HttpSession;

import com.crh.entity.Constants;

public class SessionUtils {
	public static void setSessionTime(HttpSession session,int time) {
		session.setMaxInactiveInterval(time);
	}
	public static void setSessionTime(HttpSession session) {
		session.setMaxInactiveInterval(Constants.SESSIONTIME);
	}
}
