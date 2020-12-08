package com.hotel.utils.榛子云;

import com.alibaba.fastjson.JSONObject;
import com.zhenzi.sms.ZhenziSmsClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author wxy
 * @description
 * @data 2020/9/16
 */
public class Sms {
	private static String apiUrl = "https://sms_developer.zhenzikj.com";
	//榛子云系统上获取
	private static String appId = "106169";
	private static String appSecret = "ODYyZjhkZTgtODc1Yi00NDFlLTlhNGEtNDZkMTE1NmViYjEy";
	public static void main(String[] args) {
		try {
			JSONObject json;
			//生成6位验证码
			String code = String.format("%06d",new Random().nextInt(999999));
			//发送短信
			ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("number", "17375215186");
			params.put("templateId", "808");
			//这个参数就是短信模板上那两个参数
			params.put("templateParams",new String[]{code, "2分钟"});
			String result = client.send(params);
			json = JSONObject.parseObject(result);
			if(json.getIntValue("code") != 0) {//发送短信失败
				System.out.println(json);
				System.out.println("失败");
			}else {
				System.out.println("成功");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
