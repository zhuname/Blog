package com.cz.mts.cms.base.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cz.mts.cms.base.directive.abs.AbstractChannelDirective;
import com.cz.mts.cms.base.directive.util.DirectiveUtils;
import com.cz.mts.cms.base.entity.CmsChannel;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 栏目列表标签
 */
@Component("channelListDirective")
public class ChannelListDirective extends AbstractChannelDirective {
	/**
	 * 模板名称
	 */
	public static final String TPL_NAME = "cms_channel_list";

	@SuppressWarnings("rawtypes")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		List<CmsChannel> list=new ArrayList<CmsChannel>();
		Object object = params.get("p");
		System.out.println("-----------------------:"+object);
		for(int i=0;i<5;i++){
			CmsChannel c=new CmsChannel();
			c.setId("c"+i);
			c.setName("name"+i);
			list.add(c);
		}
		env.setVariable("channel_list", DirectiveUtils.wrap(list));
		if (body != null) { 
			body.render(env.getOut());  
		}
		
	
		
	}
}
