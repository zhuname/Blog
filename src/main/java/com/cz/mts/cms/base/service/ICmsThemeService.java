package com.cz.mts.cms.base.service;

import com.cz.mts.cms.base.entity.CmsTheme;
import com.cz.mts.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2016-11-10 11:55:22
 * @see org.springrain.demo.service.CmsTheme
 */
public interface ICmsThemeService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	CmsTheme findCmsThemeById(String id) throws Exception;
	
	
	
}
