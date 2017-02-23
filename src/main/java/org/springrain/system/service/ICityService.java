package org.springrain.system.service;

import org.springrain.system.entity.City;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-21 09:29:30
 * @see org.springrain.demo.service.City
 */
public interface ICityService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	City findCityById(Object id) throws Exception;
	
	
	
}
