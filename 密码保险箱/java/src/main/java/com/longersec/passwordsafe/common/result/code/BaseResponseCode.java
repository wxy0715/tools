package com.longersec.passwordsafe.common.result.code;

/**
 * @author wxy
 * @description 实现自定义错误返回码接口
 * @data 2020/11/11
 */
public enum BaseResponseCode implements ResponseCodeInterface {
	/** 成功和失败 **/
	SUCCESS(2000,"操作成功"),
	SYSTEM_ERROR(2001,"系统异常,请联系系统管理员"),
	OPERATION_ERROR(2002,"操作失败"),
	/** 文件类 **/
	UPLOAD_FILE_ERROR(2100,"上传失败"),
	FILE_TOO_LARGE(2101,"上传的文件超出范围"),
	FILE_ID_NULL(2102,"文件内容不能为空"),
	/** 手机号**/
	PHONE_IS_NULL(2200,"该手机号未注册"),
	PHONE_IS_MONEY_NULL(2201,"短信余额不足"),
	PHONE_IS_OVER(2202,"今日发送超过限额"),
	PHONE_IS_MISTAKE(2203,"手机号码格式错误"),
	PHONE_CODE_NOT_SEND(2204,"您还未发送验证码或验证码失效!请先发送!"),
	PHONE_IS_EXISTS(2205,"该手机号已被注册"),
	PHONE_CODE_MISTAKE(2206,"验证码输入错误"),
	/** 表单 **/
	DATA_ERROR(2300,"传入数据异常"),
	METHOD_IDENTITY_ERROR(2302,"数据校验异常"),
	ACCOUNT_ERROR(2303,"该账号不存在"),
	ACCOUNT_LOCK(2304,"该账号被锁定,请联系系统管理员"),
	ACCOUNT_PASSWORD_ERROR(2305,"用户名密码不匹配"),
	ACCOUNT_EXISTS(2306,"该账号已存在"),
	ACCOUNT_HAS_DELETED_ERROR(2307,"该账号已被删除，请联系系统管理员"),
	VERTIFY_CODE_ERROR(2308,"验证码出错了"),
	/** shiro **/
	TOKEN_ERROR(2400,"用户未登录，请重新登录"),
	TOKEN_NOT_NULL(2401,"token 不能为空"),
	SHIRO_AUTHENTICATION_ERROR(2402,"用户认证异常"),
	TOKEN_PAST_DUE(2403,"token失效,请刷新token"),
	NOT_PERMISSION(2404,"没有权限访问该资源"),
	;

	/**
	 * 响应状态码
	 */
	private final int code;

	/**
	 * 响应提示
	 * @return
	 */
	private final String msg;

	BaseResponseCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getMsg() {
		return msg;
	}
}
