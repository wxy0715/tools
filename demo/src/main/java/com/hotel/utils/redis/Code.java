package com.hotel.utils.redis;

import java.util.Random;

/**
 * @author wxy
 * @description
 * @data 2020/7/25
 */
public class Code {
	public static void main(String[] args) {
		String smsText = String.format("%04d",new Random().nextInt(9999));
		System.out.println(smsText);
		String code = "";
		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			int i1 = random.nextInt(10);
			code += i1;
		}
		System.out.println(code);
	}
}
