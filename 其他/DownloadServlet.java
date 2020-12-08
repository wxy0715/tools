package cn.java.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * Servlet implementation class DownloadServlet
 * @author Administrator
 */
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html,charset=UTF-8");
		//1.获取要下载的文件的绝对路径
		String path = request.getServletContext().getRealPath("/resources/image/MIME.png");
		//2.获取要下载的文件名
		String fileName = path.substring(path.lastIndexOf("\\")+1);
		//下载文件：需要设置 消息头
		response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
		
		//Servlet通过文件的地址  将文件转为输入流 读到Servlet中
		InputStream in=new FileInputStream(path);
		//通过输出流 将 刚才已经转为输入流的文件  输出给用户
		ServletOutputStream out = response.getOutputStream() ;
		byte[] bs = new byte[1024];
		int len = -1;
		while(  (len=in.read(bs)) != -1) {
			out.write(bs,0,len);
		}
		out.close();
		in.close();
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}