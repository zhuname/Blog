package com.cz.mts.system.service;

import com.cz.mts.system.entity.Circle;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-07-13 14:27:38
 * @see com.cz.mts.system.service.Circle
 */
public interface ICircleService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Circle findCircleById(Object id) throws Exception;
	
	
	
}
