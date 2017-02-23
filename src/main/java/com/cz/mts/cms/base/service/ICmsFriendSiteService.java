package com.cz.mts.cms.base.service;

import com.cz.mts.cms.base.entity.CmsFriendSite;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2016-11-10 11:55:19
 * @see org.springrain.demo.service.CmsFriendSite
 */
public interface ICmsFriendSiteService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	CmsFriendSite findCmsFriendSiteById(String id) throws Exception;
	
	
	
}
