package com.muyu.bms.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerIntercepor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Object loginSession = request.getSession().getAttribute("loginSession");
		if(loginSession!=null){
			return true;
		}else{
			request.setAttribute("loginErrMsg","当前用户无权限！");
			request.getRequestDispatcher("/login.html").forward(request,response);
			return false;
		}

	}
}
