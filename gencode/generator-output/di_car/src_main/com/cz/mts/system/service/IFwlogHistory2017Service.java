package com.cz.mts.system.service;

import com.cz.mts.system.entity.FwlogHistory2017;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 11:23:51
 * @see com.cz.mts.system.service.FwlogHistory2017
 */
public interface IFwlogHistory2017Service extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	FwlogHistory2017 findFwlogHistory2017ById(Object id) throws Exception;
	
	
	
}
