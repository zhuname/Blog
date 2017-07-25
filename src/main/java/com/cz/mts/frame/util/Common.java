package com.cz.mts.frame.util;


import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Common {
	
	/**
     * 将bean转化为json字符串
     * @param bean
     * @return
     */
    public static String toJson(Object bean){
        return new Gson().toJson(bean);
    }

    /**
     * 将json字符串转化为对应的bean
     * @param jsonStr
     * @param objClass
     * @return
     */
    public static Object fromJson(String jsonStr, Class objClass){
    	
    	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.fromJson(jsonStr,objClass);
    }

	public static List fromJList(String jsonStr, Type type) {
		return new Gson().fromJson(jsonStr,type);
	}
    
    
//    public static MessageReqBean getMessageReqBean(HttpServletRequest request){
//        String strParam = request.getParameter(PhoneConstantParam.REQ_PARAM);
//        if(strParam!=null && !strParam.equals("")){
//            return  (MessageReqBean)fromJson(strParam,MessageReqBean.class);
//        }else
//            return null;
//    }
    
}
