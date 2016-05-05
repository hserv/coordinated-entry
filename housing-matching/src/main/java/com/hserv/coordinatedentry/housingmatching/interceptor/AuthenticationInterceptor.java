package com.hserv.coordinatedentry.housingmatching.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

/*		1.Before making any call from controller, we need to have user role check,
		 please write that logic as a dispatcher/or whatever you feel is the best,
		 so only if user has correct user privilages calls should be triggered.
		 This should be at the base url "/".*/
		
		System.out.println("AuthenticationInterceptor : successful");
		return true;
	}
}
