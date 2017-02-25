package com.cz.mts.system.service;

import com.cz.mts.system.entity.UserRole;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:29
 * @see com.cz.mts.system.service.UserRole
 */
public interface IUserRoleService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	UserRole findUserRoleById(Object id) throws Exception;
	
	
	
}
