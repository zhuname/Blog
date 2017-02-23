package com.cz.mts.cms.base.service;

import com.cz.mts.cms.base.entity.CmsContent;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2016-11-10 11:55:19
 * @see org.springrain.demo.service.CmsContent
 */
public interface ICmsContentService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	CmsContent findCmsContentById(String id) throws Exception;

	String saveContent(CmsContent cmsContent) throws Exception;

	Integer updateCmsContent(CmsContent cmsContent) throws Exception;
	
	
	
}
