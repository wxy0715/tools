package com.longersec.passwordsafe.common.utils;

import com.longersec.passwordsafe.common.config.RedisService;
import com.longersec.passwordsafe.common.result.BusinessException;
import com.longersec.passwordsafe.common.result.code.BaseResponseCode;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author wxy
 * @description 算术验证码
 * @data 2020/11/13
 */
public class VertifyCode {

	public BufferedImage createVerifyCode() {
		int width = 80;
		int height = 32;
		//create the image
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		// set the background color
		g.setColor(new Color(0xDCDCDC));
		g.fillRect(0, 0, width, height);
		// draw the border
		g.setColor(Color.black);
		g.drawRect(0, 0, width - 1, height - 1);
		// create a random instance to generate the codes
		Random rdm = new Random();
		// make some confusion
		for (int i = 0; i < 50; i++) {
			int x = rdm.nextInt(width);
			int y = rdm.nextInt(height);
			g.drawOval(x, y, 0, 0);
		}
		// generate a random code
		String verifyCode = generateVerifyCode(rdm);
		g.setColor(new Color(0, 100, 0));
		g.setFont(new Font("Candara", Font.BOLD, 24));
		g.drawString(verifyCode, 8, 24);
		g.dispose();
		//把验证码存到常量中
		Constant.VERTIFY_CODE = calc(verifyCode);
		//输出图片
		return image;
	}

	public boolean checkVerifyCode(int verifyCode) {
		Integer codeOld = Constant.VERTIFY_CODE;
		if(codeOld == null || codeOld - verifyCode != 0 ) {
			throw new BusinessException(BaseResponseCode.VERTIFY_CODE_ERROR);
		}
		Constant.VERTIFY_CODE = null;
		return true;
	}

	private int calc(String exp) {
		try {
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("JavaScript");
			return (Integer)engine.eval(exp);
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	private char[] ops = new char[] {'+', '-', '*'};

	private String generateVerifyCode(Random rdm) {
		int num1 = rdm.nextInt(10);
		int num2 = rdm.nextInt(10);
		int num3 = rdm.nextInt(10);
		char op1 = ops[rdm.nextInt(3)];
		char op2 = ops[rdm.nextInt(3)];
		String exp = ""+ num1 + op1 + num2 + op2 + num3;
		return exp;
	}
}
