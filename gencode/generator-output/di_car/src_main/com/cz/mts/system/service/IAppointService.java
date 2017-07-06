package com.cz.mts.system.service;

import com.cz.mts.system.entity.Appoint;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-07-06 14:42:38
 * @see com.cz.mts.system.service.Appoint
 */
public interface IAppointService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Appoint findAppointById(Object id) throws Exception;
	
	
	
}
