package com.hotel.utils.Zxing;

import java.io.File;
import java.io.IOException;

import com.google.zxing.WriterException;
import com.hotel.utils.Zxing.ZXingUtil;

/**
 * @author wxy
 */
public class Test {
	public static void main(String[] args) throws Exception {
		String imgPath = "D:/我的文档/Pictures/Camera Roll/code.png" ;
		String content = "hello你好" ;
		String logo = "D:/我的文档/Pictures/Camera Roll/code.png" ;
		//加密：文字->二维码
		ZXingUtil.encodeImg(imgPath,"gif",content,430,430,logo);
		//解密：二维码->文字
		ZXingUtil.decodeImg(new File(imgPath));
	}
}
