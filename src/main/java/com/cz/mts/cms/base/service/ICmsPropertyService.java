package com.cz.mts.cms.base.service;

import com.cz.mts.cms.base.entity.CmsProperty;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2016-11-10 11:55:20
 * @see org.springrain.demo.service.CmsProperty
 */
public interface ICmsPropertyService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	CmsProperty findCmsPropertyById(String id) throws Exception;
	
	
	
}
