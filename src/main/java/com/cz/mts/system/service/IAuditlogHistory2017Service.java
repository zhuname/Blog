package com.cz.mts.system.service;

import com.cz.mts.system.entity.AuditlogHistory2017;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 11:23:49
 * @see com.cz.mts.system.service.AuditlogHistory2017
 */
public interface IAuditlogHistory2017Service extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	AuditlogHistory2017 findAuditlogHistory2017ById(Object id) throws Exception;
	
	
	
}
