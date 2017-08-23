package org.forten.zuoye.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.forten.zuoye.dto.student.LoginedStudent;
import org.forten.zuoye.dto.student.RenShiAdmin;

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
		LoginedStudent user = (LoginedStudent) session.getAttribute("logined");
		RenShiAdmin user02 = (RenShiAdmin) session.getAttribute("renShiAdmin");
		RenShiAdmin user03 = (RenShiAdmin) session.getAttribute("jiJiaoAdmin");
		if (user == null) {
			res.sendRedirect("/student/login.html");
		} else {
			req.setAttribute("logined", user);
			chain.doFilter(req, response);
		}
		if (user02 == null) {
			res.sendRedirect("/student/login.html");
		} else {
			req.setAttribute("logined", user02);
			chain.doFilter(req, response);
		}
		if (user03 == null) {
			res.sendRedirect("/student/login.html");
		} else {
			req.setAttribute("logined", user03);
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
