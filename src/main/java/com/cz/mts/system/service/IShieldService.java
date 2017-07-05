package com.cz.mts.system.service;

import com.cz.mts.system.entity.Shield;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-07-05 15:58:36
 * @see com.cz.mts.system.service.Shield
 */
public interface IShieldService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Shield findShieldById(Object id) throws Exception;
	
	
	
}
