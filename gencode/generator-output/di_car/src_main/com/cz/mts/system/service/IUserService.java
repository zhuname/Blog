package com.cz.mts.system.service;

import com.cz.mts.system.entity.User;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 11:23:54
 * @see com.cz.mts.system.service.User
 */
public interface IUserService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	User findUserById(Object id) throws Exception;
	
	
	
}
