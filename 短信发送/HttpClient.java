package com.hotel.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**  
 * @author wxy
 * @Title: http://www.smschinese.cn/api.shtml
 * @date 2011-3-22
 * @version V1.2  
 */
public class HttpClient {

	private static String Uid = "qq2357191256";

	private static String Key = "d41d8cd98f00b204e980";

	private static String smsMob = "18955050308";

	private static String smsText = String.format("%04d",new Random().nextInt(9999));


	public static void main(String[] args) {
		HttpClientUtil client = HttpClientUtil.getInstance();
		//UTF发送
		int result = client.sendMsgUtf8(Uid, Key, smsText, smsMob);
		if(result>0){
			System.out.println("UTF8成功发送条数=="+result);
		}else{
			System.out.println(client.getErrorMsg(result));
		}
	}
}
