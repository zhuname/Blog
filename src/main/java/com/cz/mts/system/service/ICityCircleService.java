package com.cz.mts.system.service;

import com.cz.mts.system.entity.CityCircle;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-07-06 09:32:38
 * @see com.cz.mts.system.service.CityCircle
 */
public interface ICityCircleService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	CityCircle findCityCircleById(Object id) throws Exception;
	
	
	
}
