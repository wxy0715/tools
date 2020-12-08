package com.longersec.passwordsafe.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.longersec.passwordsafe.common.config.RedisService;
import com.longersec.passwordsafe.common.result.BusinessException;
import com.longersec.passwordsafe.common.result.code.BaseResponseCode;
import com.zhenzi.sms.ZhenziSmsClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

	public static void sendCode(String phone, RedisService redisService){
		JSONObject json;
		//生成6位验证码
		String code = String.format("%06d",new Random().nextInt(999999));
		try {
			//发送短信
			ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
			Map<String, Object> params = new HashMap<>(16);
			params.put("number", phone);
			params.put("templateId", "1976");
			//这个参数就是短信模板上那两个参数
			params.put("templateParams",new String[]{code, "5分钟"});
			String result = client.send(params);
			json = JSONObject.parseObject(result);
		} catch (Exception e) {
			throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
		}
		//发送短信失败
		int code1 = json.getIntValue("code");
		if(code1 == 108) {
			throw new BusinessException(BaseResponseCode.PHONE_IS_MONEY_NULL);
		}if(code1 == 100) {
			throw new BusinessException(BaseResponseCode.PHONE_IS_MISTAKE);
		}if(code1 == 109) {
			throw new BusinessException(BaseResponseCode.PHONE_IS_OVER);
		}else if(code1 == 0){
			//存放到redis里,时间为5分钟
			redisService.set(Constant.PHONE_CODE_SEND+phone,code,5, TimeUnit.MINUTES);
		}
	}

	public static void lostInfo(String phone,String name, RedisService redisService){
		JSONObject json;
		try {
			//发送短信
			ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
			Map<String, Object> params = new HashMap<>(16);
			params.put("number", phone);
			params.put("templateId", "1976");
			//这个参数就是短信模板上那两个参数
			params.put("templateParams",new String[]{name, "5分钟"});
			String result = client.send(params);
			json = JSONObject.parseObject(result);
		} catch (Exception e) {
			throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
		}
		//发送短信失败
		int code1 = json.getIntValue("code");
		if(code1 == 108) {
			throw new BusinessException(BaseResponseCode.PHONE_IS_MONEY_NULL);
		}if(code1 == 100) {
			throw new BusinessException(BaseResponseCode.PHONE_IS_MISTAKE);
		}if(code1 == 109) {
			throw new BusinessException(BaseResponseCode.PHONE_IS_OVER);
		}else if(code1 == 0){
			//存放到redis里,时间为5分钟
			redisService.set(Constant.PHONE_CODE_SEND+phone,name,5, TimeUnit.MINUTES);
		}
	}
}
