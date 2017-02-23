package org.springrain.cms.base.service.impl;

import org.springframework.stereotype.Service;
import org.springrain.cms.base.entity.CmsLink;
import org.springrain.cms.base.service.ICmsLinkService;
import org.springrain.system.service.BaseSpringrainServiceImpl;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2016-11-10 11:55:20
 * @see org.springrain.demo.service.impl.CmsLink
 */
@Service("cmsLinkService")
public class CmsLinkServiceImpl extends BaseSpringrainServiceImpl implements ICmsLinkService {

   
    @Override
	public String  saveCmsLink(CmsLink cmsLink ) throws Exception{
	       return super.save(cmsLink).toString();
	}

    
	@Override
    public Integer updateCmsLink(CmsLink cmsLink ) throws Exception{
	return super.update(cmsLink);
    }
    @Override
	public CmsLink findCmsLinkById(String id) throws Exception{
	 return super.findById(id,CmsLink.class);
	}


}
