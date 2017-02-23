package com.cz.mts.system.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.system.service.BaseSpringrainServiceImpl;
import com.cz.mts.system.service.IStaticHtmlService;


/**
 * TODO 在此加入类描述
 * @copyright {@link springrain}
 * @author 9iu.org<Auto generate>
 * @version  2013-07-29 11:36:44
 * @see org.springrain.springrain.service.impl.Fwlog
 */
@Service("staticHtmlService")
public class StaticHtmlServiceImpl extends BaseSpringrainServiceImpl implements IStaticHtmlService {

	@Override
	@Cacheable(value = GlobalStatic.staticHtmlCacheKey, key = "'findHtmlPathByURI_'+#uri")
	public String findHtmlPathByURI(String uri) throws Exception {
		
		return "static/1.html";
	}

	



   


}
