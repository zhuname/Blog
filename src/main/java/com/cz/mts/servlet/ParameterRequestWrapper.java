package com.cz.mts.servlet;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class ParameterRequestWrapper extends HttpServletRequestWrapper {
	
	
	public ParameterRequestWrapper(HttpServletRequest request,
			Map<String, String[]> newParams) {
		super(request);

		this.params = newParams;
	}

	private Map<String, String[]> params;

	private String[] exc = { "ticketId" };

	public ParameterRequestWrapper(HttpServletRequest request)
			throws UnsupportedEncodingException {
		super(request);
		Enumeration params = request.getParameterNames();
		Map<String, String[]> values = new HashMap<String, String[]>();
		while (params.hasMoreElements()) {
			Object object = (Object) params.nextElement();
			String key = object + "";
			String[] vas = request.getParameterValues(object + "");
			if (exception(key)) {// 判断是否包含例外的字段，若包含不剔除_
				// 将key中的包含_剔除，转换成为小写字符
				key = _2Came(key);
				for (int i = 0; i < vas.length; i++) {
					vas[i] = _2Came(vas[i]);

				}
			}
			values.put(key, vas);
		}
		this.params = values;
	}

	// 将_转化为驼峰形式
	public String _2Came(String str) {
		String ret = "";
		String[] values = str.split("_");
		if (values.length > 1) {
			for (String string : values) {
				ret = ret + string.substring(0, 1).toUpperCase()
						+ string.substring(1, string.length());
			}
		}
		if (ret.length() > 0) {
			ret = ret.substring(0, 1).toLowerCase()
					+ ret.substring(1, ret.length());
		} else {
			ret = str;
		}
		return ret;
	}

	@Override
	public String getParameter(String name) {
		String result = "";

		Object v = params.get(name);
		if (v == null) {
			result = null;
		} else if (v instanceof String[]) {
			String[] strArr = (String[]) v;
			if (strArr.length > 0) {
				result = strArr[0];
			} else {
				result = null;
			}
		} else if (v instanceof String) {
			result = (String) v;
		} else {
			result = v.toString();
		}

		return result;
	}

	@Override
	public Map getParameterMap() {
		return params;
	}

	@Override
	public Enumeration getParameterNames() {
		return null;
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] result = null;

		Object v = params.get(name);
		if (v == null) {
			result = null;
		} else if (v instanceof String[]) {
			result = (String[]) v;
		} else if (v instanceof String) {
			result = new String[] { (String) v };
		} else {
			result = new String[] { v.toString() };
		}

		return result;
	}

	public boolean exception(String key) {
		for (int i = 0; i < exc.length; i++) {
			if (exc[i].equals(key)) {
				return false;
			}
		}
		return true;
	}

}
