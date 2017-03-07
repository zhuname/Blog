package com.cz.mts.frame.util;

import java.security.Key;  

import javax.crypto.Cipher;  
import javax.crypto.SecretKeyFactory;  
import javax.crypto.spec.DESedeKeySpec;  
import javax.crypto.spec.IvParameterSpec;  
        
/** 
  * 3DES加密工具类 
  *  
  * @author wangjing  
  * @date 2016-07-05 
  */ 
public class Des3 {  
     // 密钥  
     private final static String secretKey = "liuyunqiang@lx100$#365#$" ;  
     // 向量  
     private final static String iv = "01234567" ;  
     // 加解密统一使用的编码方式  
     private final static String encoding = "utf-8" ;  
        
     /** 
      * 3DES加密 
      *  
      * @param plainText 普通文本 
      * @return 
      * @throws Exception  
      */ 
     public static String encode(String plainText) throws Exception {  
         Key deskey = null ;  
         DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());  
         SecretKeyFactory keyfactory = SecretKeyFactory.getInstance( "desede" );  
         deskey = keyfactory.generateSecret(spec);  
        
         Cipher cipher = Cipher.getInstance( "desede/CBC/PKCS5Padding" );  
         IvParameterSpec ips = new IvParameterSpec(iv.getBytes());  
         cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);  
         byte [] encryptData = cipher.doFinal(plainText.getBytes(encoding));  
         return Base64.encode(encryptData);  
     }  
        
     /** 
      * 3DES解密 
      *  
      * @param encryptText 加密文本 
      * @return 
      * @throws Exception 
      */ 
     public static String decode(String encryptText) throws Exception {  
         Key deskey = null ;  
         DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());  
         SecretKeyFactory keyfactory = SecretKeyFactory.getInstance( "desede" );  
         deskey = keyfactory.generateSecret(spec);  
         Cipher cipher = Cipher.getInstance( "desede/CBC/PKCS5Padding" );  
         IvParameterSpec ips = new IvParameterSpec(iv.getBytes());  
         cipher.init(Cipher.DECRYPT_MODE, deskey, ips);  
        
         byte [] decryptData = cipher.doFinal(Base64.decode(encryptText));  
        
         return new String(decryptData, encoding);  
     }  
     
     public static void main(String[] args) throws Exception {
    	 String c = "wx6yqMfzbfDvfGGlGclgXc4mMKV8ob5GIwAprXmvd7mpJ38ML+coxIZ7FF8p*SqGsm4UnUnXQxJBs37jim3SOaayb6k1kZhRy6lQ+DJ09iTH1k8XrpPeKsQHi*xN5oWUXN0Yg/q3tviUsX6eXv8FtGXczNJJr3EGW3";
    	 System.out.println(decode(c));    
	}
} 