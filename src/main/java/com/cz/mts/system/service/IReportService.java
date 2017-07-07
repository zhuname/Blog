package com.cz.mts.system.service;

import com.cz.mts.system.entity.Report;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-07-07 14:31:44
 * @see com.cz.mts.system.service.Report
 */
public interface IReportService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Report findReportById(Object id) throws Exception;
	
	
	
}
