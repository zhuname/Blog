package com.cz.mts.frame.util;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 采用MD5加密解密
 * 
 * 
 * @datetime 2011-10-13
 */
public class MD5Util {

	/***
	 * MD5加码 生成32位md5码
	 */
	public static String string2MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = (md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}

	/**
	 * 加密解密算法 执行一次加密，两次解密
	 */
	public static String convertMD5(String inStr) {

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;
	}

	// 测试主函数
	public static void main(String args[]) {
//		String s = new String("SDK-BSY-010-00071707483");
		String s = new String("123456");
		System.out.println("原始：" + s);
		System.out.println("MD5后：" + string2MD5(s));
		System.out.println("加密的：" + convertMD5(s));
		System.out.println("解密的：" + convertMD5(convertMD5(s)));
		
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyyMMdd");
	    String nowTime = formatter.format(now);
	    System.out.println(nowTime);
	    
	    System.out.println(Math.round(Math.random()*(999999)+1000000));
	    
	    String sss = "2015041700001000710050820893^0.01^SUCCESS" ;
	    System.out.println(sss.substring(sss.lastIndexOf("^")));
		/*
		 * 20b75697d8bf931a6730662ae117c3bf e3738a23bee9c45c767dd75b3890a028
		 */
	}
}
