package com.longersec.passwordsafe.common.xss;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * XSS过滤
 * @author Administrator
 */
@Component
public class XssFilter implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
		HttpServletResponse response1 = (HttpServletResponse) response;
		response1.setHeader("Access-Control-Allow-Origin", "*");
		response1.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, HEAD");
		response1.setHeader("Access-Control-Max-Age", "3600");
		response1.setHeader("Access-Control-Allow-Headers", "access-control-allow-origin, authority, content-type, version-info, X-Requested-With");
		chain.doFilter(xssRequest, response);
	}

	@Override
	public void destroy() {
	}

}