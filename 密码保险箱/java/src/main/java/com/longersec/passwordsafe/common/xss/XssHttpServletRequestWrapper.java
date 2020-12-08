package com.longersec.passwordsafe.common.xss;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * XSS过滤处理
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
	// 没被包装过的HttpServletRequest（特殊场景，需要自己过滤）
	HttpServletRequest orgRequest;
	// html过滤
	private final static HTMLFilter htmlFilter = new HTMLFilter();

	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		orgRequest = request;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		// 非json类型，直接返回
		if (!MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(super.getHeader(HttpHeaders.CONTENT_TYPE))) {
			return super.getInputStream();
		}

		// 为空，直接返回
		String json = IOUtils.toString(super.getInputStream(), "utf-8");
		if (StringUtils.isBlank(json)) {
			return super.getInputStream();
		}

		// xss过滤
		json = xssEncode(json);
		final ByteArrayInputStream bis = new ByteArrayInputStream(json.getBytes("utf-8"));
		return new ServletInputStream() {
			@Override
			public boolean isFinished() {
				return true;
			}

			@Override
			public boolean isReady() {
				return true;
			}

			@Override
			public void setReadListener(ReadListener readListener) {
			}

			@Override
			public int read() throws IOException {
				return bis.read();
			}
		};
	}

	/** 获取前台传参进行处理-wxy**/
	@Override
	public String getParameter(String name) {
		String value = super.getParameter(xssEncode(name));
		if (StringUtils.isNotBlank(value)) {
			value = xssEncode(value);
		}
		return value;
	}

	/** 进行css过滤处理-wxy**/
	@Override
	public String[] getParameterValues(String name) {
		String[] parameters = super.getParameterValues(name);
		if (parameters == null || parameters.length == 0) {
			return null;
		}
		for (int i = 0; i < parameters.length; i++) {
			parameters[i] = xssEncode(parameters[i]);
		}
		return parameters;
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> map = new LinkedHashMap<>();
		Map<String, String[]> parameters = super.getParameterMap();
		Iterator<Map.Entry<String, String[]>> it=parameters.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, String[]> et=it.next();
			String[] values = et.getValue();
			for (int i = 0; i < values.length; i++) {
				values[i] = xssEncode(values[i]);
			}
			map.put(et.getKey(), values);
		}
		return map;
	}

	/** 获取头部**/
	@Override
	public String getHeader(String name) {
		String value = super.getHeader(xssEncode(name));
		if (StringUtils.isNotBlank(value)) {
			value = xssEncode(value);
		}
		return value;
	}

	/** 进行xss过滤**/
	private String xssEncode(String input) {
		return htmlFilter.filter(input);
	}

	/**
	 * 获取最原始的request
	 */
	public HttpServletRequest getOrgRequest() {
		return orgRequest;
	}

	/**
	 * 获取最原始的request
	 */
	public static HttpServletRequest getOrgRequest(HttpServletRequest request) {
		if (request instanceof XssHttpServletRequestWrapper) {
			return ((XssHttpServletRequestWrapper) request).getOrgRequest();
		}
		return request;
	}

}
