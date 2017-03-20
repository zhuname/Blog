package com.cz.mts.system.service;

import com.cz.mts.system.entity.LmediaPackage;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-03-17 21:28:32
 * @see com.cz.mts.system.service.LmediaPackage
 */
public interface ILmediaPackageService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	LmediaPackage findLmediaPackageById(Object id) throws Exception;
	
	
	
}
