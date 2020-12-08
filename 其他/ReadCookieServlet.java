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
 * Date: 2017年10月16日下午5:22:35 <br/>
 * 
 * @author dingP
 * @version
 * @see
 */
@WebServlet("/ReadCookieServlet")
public class ReadCookieServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 解决POST请求方式的中文乱码问题
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // 读取浏览器端传递过来的cookie
        Cookie[] cks = request.getCookies();
        if (cks != null) {
            for (Cookie cookie : cks) {
                // 获取当前cookie的名字
                String cookieName = cookie.getName();
                // 将名字与dt48_history进行对比
                if ("dt48_history".equals(cookieName)) {
                    // 取出name=dt48_history中的值
                    String cookieValue = cookie.getValue();
                    System.out.println("您最近浏览了" + cookieValue + "商品");
                }
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}