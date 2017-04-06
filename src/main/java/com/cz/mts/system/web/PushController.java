package com.cz.mts.system.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
		//查询所有用户
		notificationService.notify(1, null, null, content,url);
		return new ReturnDatas(ReturnDatas.SUCCESS,"推送成功");
		
	}
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) 
			throws Exception {
		return "/push/url";
	}
	

}
