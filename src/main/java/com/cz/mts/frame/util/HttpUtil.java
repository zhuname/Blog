package com.cz.mts.frame.util;

import java.util.Collections;
import java.util.Map;

import javax.servlet.ServletRequest;

public class HttpUtil {

	public static Map<String , String[]> getRequestMap(ServletRequest request) {
        try {
            Object innerRequest = GlobalStatic.requestField.get(request);
            GlobalStatic.parametersParsedField.setBoolean(innerRequest, true);
            Object coyoteRequestObject = GlobalStatic.coyoteRequestField.get(innerRequest);
            Object parameterObject = GlobalStatic.parametersField.get(coyoteRequestObject);
            return (Map<String,String[]>)GlobalStatic.hashTabArrField.get(parameterObject);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }
}
