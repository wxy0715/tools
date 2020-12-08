package cn.java.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: 自定义的servlet模板<br/>
 * Date: 2017年10月16日下午5:08:19 <br/>
 * 
 * @author dingP
 * @version
 * @see
 */
@WebServlet("/CreateCookieServlet")
public class CreateCookieServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 解决POST请求方式的中文乱码问题
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // 1、创建一个cookie容器，然后往容器中添加数据
        Cookie ck = new Cookie("dt48_history", "巨型娃娃、lenovo");
        ck.setMaxAge(24 * 3600);

        // 2、将cookie发送给浏览器
        response.addCookie(ck);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}