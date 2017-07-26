package com.cz.mts.system.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cz.mts.frame.annotation.SecurityApi;
import com.cz.mts.frame.controller.BaseController;
import com.cz.mts.frame.util.GlobalStatic;
import com.cz.mts.frame.util.ReturnDatas;
import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.NotificationBean;
import com.cz.mts.system.entity.PosterPackage;
import com.cz.mts.system.service.IApplyMedalService;
import com.cz.mts.system.service.ICardService;
import com.cz.mts.system.service.IMediaPackageService;
import com.cz.mts.system.service.IPosterPackageService;
import com.cz.mts.system.service.IWithdrawService;
@Controller
@RequestMapping(value="/system/notification")
public class NotificationController extends BaseController{
	
	@Resource
	private IPosterPackageService posterPackageService;
	@Resource
	private IMediaPackageService mediaPackageService;
	@Resource
	private ICardService cardService;
	@Resource
	private IApplyMedalService applyMedalService;
	@Resource
	private IWithdrawService withdrawService;
	
	@RequestMapping("/list/json")
	public @ResponseBody
	ReturnDatas listjson(HttpServletRequest request, Model model,NotificationBean notificationBean) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String countNum = posterPackageService.statics();
		String arr[] = countNum.split("=");
		String result = arr[1];
		String arr1[] = result.split("}");
		String result1 = arr1[0];
		String res[] = result1.split(",");
		Integer posterpackageCount = Integer.parseInt(res[0]);
		notificationBean.setPosterpackageCount(posterpackageCount);
		
		Integer mediapackageCount = Integer.parseInt(res[1]);
		notificationBean.setMediapackageCount(mediapackageCount);
		
		Integer cardCount = Integer.parseInt(res[2]);
		notificationBean.setCardCount(cardCount);
		
		Integer applyMedalCount = Integer.parseInt(res[3]);
		notificationBean.setApplyMedalCount(applyMedalCount);
		
		Integer applyWithdrawCount = Integer.parseInt(res[4]);
		notificationBean.setApplyWithdrawCount(applyWithdrawCount);
		
		Integer activityCount = Integer.parseInt(res[5]);
		notificationBean.setActivityCount(activityCount);
		
		Integer sumCount = posterpackageCount+mediapackageCount+cardCount+applyMedalCount+applyWithdrawCount+activityCount;
		notificationBean.setSumCount(sumCount);
		
		returnObject.setData(notificationBean);
		return returnObject;
	}
	
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,NotificationBean notificationBean) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, notificationBean);
		returnObject.setData(returnObject.getData());
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/common/head";
	}

}
