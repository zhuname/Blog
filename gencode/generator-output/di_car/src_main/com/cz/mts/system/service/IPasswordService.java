package com.cz.mts.system.service;

import com.cz.mts.system.entity.Password;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-03-03 15:28:50
 * @see com.cz.mts.system.service.Password
 */
public interface IPasswordService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Password findPasswordById(Object id) throws Exception;
	
	
	
}
