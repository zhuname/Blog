package com.cz.mts.system.service;

import com.cz.mts.system.entity.LposterPackage;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-11 19:16:15
 * @see com.cz.meitianshang.service.LposterPackage
 */
public interface ILposterPackageService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	LposterPackage findLposterPackageById(Object id) throws Exception;
	
	
	
}
