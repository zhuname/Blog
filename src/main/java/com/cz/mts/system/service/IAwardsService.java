package com.cz.mts.system.service;

import com.cz.mts.system.entity.Awards;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-07-03 16:07:17
 * @see com.cz.mts.system.service.Awards
 */
public interface IAwardsService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Awards findAwardsById(Object id) throws Exception;
	
	
	
}
