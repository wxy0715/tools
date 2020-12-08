package com.array.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.prism.Image;

/**
 * @author Array
 *
 */
@Controller
public class CheckCodeManagerController {

	private static String STR = "0123456789";
	
	/*
	 * 1+2=?类型的问题验证码后台
	 * 
	 */
	@RequestMapping("getCkCOne.do")
	public void getCkCOne(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//设置页面不保留缓存
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		
		//在内存中创建对象（图片对象）
		int width =90;
		int height =40;
		
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		
		// 获取图像的上下文
		Graphics g = image.getGraphics();
		Random random = new Random();
		//设置背景颜色
		g.setColor(new Color(239, 255, 255));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Arial", 0, 39));
		//设置一些随机干扰线
		for(int i =0;i<155;i++) {
			int x = random.nextInt(width);
			int y =  random.nextInt(height);
			int x1 = random.nextInt(12);
			int y1= random.nextInt(12);
			g.drawLine(x, y, x+x1, y+y1); 
		}
		// 设置1+2=?的形式
		int randomOne = random.nextInt(9);
		int randomTwo = random.nextInt(9);
		StringBuffer sbf = new StringBuffer(5);
		// 创建数字验证码的数字池
		String[] arr = new String[] {STR.charAt(randomOne)+"","+",STR.charAt(randomTwo)+"","=","?"				
		};
		// 讲验证码添加到图像区域中去
		for(int i = 0;i<arr.length;i++){
			String str = arr[i];
			sbf.append(str);
			// 可以对图像的颜色和字体重新覆盖
			g.setColor(new Color(28+random.nextInt(100), 28+random.nextInt(100), 20+random.nextInt(100)));
			g.setFont(new Font("Arial", Font.ITALIC, 28));
			g.drawString(str, 13*i, 28); 
		}
		
		// 求和  为了后来做验证使用
		int sum = Integer.parseInt(STR.charAt(randomOne)+"")+Integer.parseInt(STR.charAt(randomTwo)+"");
		
		// 放入session
		HttpSession session = request.getSession();
		session.removeAttribute("arraysum");
		session.setAttribute("arraysum", String.valueOf(sum));
		g.dispose();
		// 输出图像到页面
		ImageIO.write(image, "jpeg", response.getOutputStream());
		response.getOutputStream().flush();
	}
	
	
	
	/*
	 * 验证验证码的数字的正确性
	 * 
	 */
	
	@RequestMapping("doCKC.do")
	@ResponseBody
	public String doCKC(HttpSession session,String nsum){
		
		if(nsum.equalsIgnoreCase(session.getAttribute("arraysum").toString())) {
			return "yes";
		} else {
			return "no";
		}
	}
}
