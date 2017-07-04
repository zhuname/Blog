package com.cz.mts.system.service;

import com.cz.mts.system.entity.Activity;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-07-03 16:06:04
 * @see com.cz.mts.system.service.Activity
 */
public interface IActivityService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Activity findActivityById(Object id) throws Exception;
	
	
	Activity addOrUpdate(Object entity,String awards,String citys)throws Exception;
	
}
