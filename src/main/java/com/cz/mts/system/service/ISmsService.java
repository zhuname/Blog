package com.cz.mts.system.service;

import com.cz.mts.system.entity.Sms;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:28
 * @see com.cz.mts.system.service.Sms
 */
public interface ISmsService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Sms findSmsById(Object id) throws Exception;
	
	/**
	 * 获取验证码
	 * @author wj
	 * @param sms
	 * @return
	 * @throws Exception
	 */
	Sms saveContent(Sms sms) throws Exception;
	
	
	
}
