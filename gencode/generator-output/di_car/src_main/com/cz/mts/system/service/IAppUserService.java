package com.cz.mts.system.service;

import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:23
 * @see com.cz.mts.system.service.AppUser
 */
public interface IAppUserService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	AppUser findAppUserById(Object id) throws Exception;
	
	
	
}
