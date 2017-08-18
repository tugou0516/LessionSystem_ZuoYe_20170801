package org.forten.zuoye.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.forten.zuoye.dto.student.LoginStudent;

/**
 * 认证过滤器
 * 
 * 拦截要访问stident路径下所有资源和根路径下index.html的请求
 * 校验其是否已经通过登录
 */
//@WebFilter(urlPatterns={"/comanage/*","/course/*"})
public class Auth1Filter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		LoginStudent user = (LoginStudent) session.getAttribute("loginStudent");
		if (user == null) {
			res.sendRedirect("/student/login.html");
		} else {
			req.setAttribute("loginStudent", user);
			chain.doFilter(req, response);
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("认证过滤器初始化完毕...");
	}

}
