package com.cz.mts.system.service;

import com.cz.mts.system.entity.Role;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 11:23:53
 * @see com.cz.mts.system.service.Role
 */
public interface IRoleService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Role findRoleById(Object id) throws Exception;
	
	
	
}
