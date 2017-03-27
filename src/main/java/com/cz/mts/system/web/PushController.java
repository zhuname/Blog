package com.cz.mts.system.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cz.mts.frame.controller.BaseController;
import com.cz.mts.frame.util.Finder;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.system.entity.AppUser;
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
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String content = request.getParameter("content");
		String url = request.getParameter("url");
		if(StringUtils.isBlank(content) || StringUtils.isBlank(url)){
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("参数缺失");
		}else{
			//查询所有用户
			notificationService.notify(1, null, null, content,url);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,"推送成功");
		
	}
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) 
			throws Exception {
		return "/push/url";
	}
	

}
