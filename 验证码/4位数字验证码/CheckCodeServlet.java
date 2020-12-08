package cn.java.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Administrator
 * @data 2020/2/6 - 15:02
 **/
@WebServlet("/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resultTip = null;
        //获取用户输入验证码
        String checkcodeClient =  request.getParameter("checkcode");
        //真实的验证码值
        String checkcodeServer = (String) request.getSession().getAttribute("CKECKCODE");
        if(checkcodeServer.equals(checkcodeClient)) {
            resultTip = "/resources/image/right.jpg";
        }else {
            resultTip = "/resources/image/wrong.jpg";
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(resultTip);
        writer.close();
        writer.flush();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
