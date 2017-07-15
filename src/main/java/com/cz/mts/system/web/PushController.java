package com.cz.mts.system.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cz.mts.frame.controller.BaseController;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.system.service.IAppUserService;
import com.cz.mts.system.service.NotificationService;

@Controller
@RequestMapping(value="/system/push")
public class PushController extends BaseController{
	
	@Resource
	private NotificationService notificationService;
	@Resource
	private IAppUserService appUserService;
	
	/**
	 * url推送
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/url")
	public @ResponseBody
	ReturnDatas pushUrl(HttpServletRequest request, Model model) throws Exception {
		String content = request.getParameter("content");
		String url = request.getParameter("url");
		String itemId = request.getParameter("itemId");
		String pushType = request.getParameter("pushType");
		//查询所有用户
		//2海报 3视频 5同城活动 1url 4文字
		if(StringUtils.isNotBlank(pushType) && ("1".equals(pushType) || "4".equals(pushType))){
			notificationService.notify(1, null, null, content,url);
		}
		if(StringUtils.isNotBlank(pushType) && "2".equals(pushType)){
			notificationService.notify(38, Integer.parseInt(itemId), null, content,itemId);
		}
		if(StringUtils.isNotBlank(pushType) && "3".equals(pushType)){
			notificationService.notify(39, Integer.parseInt(itemId), null, content,itemId);
		}
		if(StringUtils.isNotBlank(pushType) && "5".equals(pushType)){
			notificationService.notify(40, Integer.parseInt(itemId), null, content,itemId);
		}
		
		
		return new ReturnDatas(ReturnDatas.SUCCESS,"推送成功");
		
	}
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) 
			throws Exception {
		return "/push/url";
	}
	
	@RequestMapping("/pushList")
	public String pushList(HttpServletRequest request, Model model) 
			throws Exception {
		return "/push/push";
	}
	

}
