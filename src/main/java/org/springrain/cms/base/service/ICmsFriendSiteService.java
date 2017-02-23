package org.springrain.cms.base.service;

import org.springrain.cms.base.entity.CmsFriendSite;
import org.springrain.system.service.IBaseSpringrainService;
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
