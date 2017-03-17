package com.cz.mts.system.service;

//通知辅助类
/**
* 通过调用sms、jpush、message来进行通知
* @author Administrator
*
*/

/**
* 通知类型矩阵
* 
*
*/
public interface NotificationService {
	
	/**
	 * jpush通知
	 * @Description
	 * @author wxy
	 * @param type  跟客户端自定义的推送类型
	 * @param id	要
	 * @param userId
	 * @param appType
	 * @param extend
	 * @return
	 * @throws Exception
	 * Integer
	 */
	Integer notify(Integer type,Integer id,Integer userId, String... extend ) throws Exception;
}
