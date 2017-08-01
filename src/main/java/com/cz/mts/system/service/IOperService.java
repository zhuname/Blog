package com.cz.mts.system.service;

import com.cz.mts.system.entity.Oper;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-07-05 15:15:34
 * @see com.cz.mts.system.service.Oper
 */
public interface IOperService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Oper findOperById(Object id) throws Exception;
	
	
	
}
