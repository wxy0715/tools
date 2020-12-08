/**
 * Project Name:dt48_chapter3
 * File Name:LanjieFilter.java
 * Package Name:cn.java.filters
 * Date:2017年10月28日下午2:21:37
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
*/

package cn.java.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Description: <br/>
 * Date: 2017年10月28日 下午2:21:37 <br/>
 * 
 * @author dingP
 * @version
 * @see
 */
public class LanjieFilter implements Filter {

    @Override // HttpServletRequest
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        System.out.println("哈哈，我是过滤器---------------");
        // 将request和response放行
        // chain.doFilter(req, resp);
        HttpSession session = req.getSession();
        Object username = session.getAttribute("username");
        // 如果用户已经登录了，则放行
        if (username != null) {
            chain.doFilter(req, resp);
        } else {
            // 如果用户没有登录则跳转到denglu.jsp
            resp.sendRedirect("/dt48_chapter3/pages/admin/denglu.jsp");
        }
    }

}
