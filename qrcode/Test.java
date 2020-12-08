package com.hotel.utils.qrcode;

import com.hotel.utils.qrcode.QRCodeUtil;

/**
 * @author wxy
 */
public class Test {
	public static void main(String[] args)  throws Exception{
		String imgPath = "D:/我的文档/Pictures/Camera Roll/code.png";
		//扫描二维码后，网页跳转
		String content =  "http://www.baidu.com";
		//生成二维码
		/*
		 * 加密：  文字信息 ->二维码
		 * 解密：  二维码 -> 文字信息
		 */
		QRCodeUtil qrUtil = new QRCodeUtil();
		//加密：  文字信息 ->二维码
		qrUtil.encoderQRCode(content, imgPath, "png", 17);
//		   TwoDimensionCode handler = new TwoDimensionCode();
//		   handler.encoderQRCode(content, imgPath, "png", 7);
		//解密：  二维码 -> 文字信息
		String imgContent = qrUtil.decoderQRCode(imgPath) ;
		System.out.println(imgContent);
	}
}
