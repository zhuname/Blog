package com.cz.mts.system.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cz.mts.frame.annotation.SecurityApi;
import com.cz.mts.frame.controller.BaseController;
import com.cz.mts.frame.util.ReturnDatas;
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
	ReturnDatas listjson() throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		NotificationBean notificationBean = new NotificationBean();
		Integer posterpackageCount = posterPackageService.statics();
		notificationBean.setPosterpackageCount(posterpackageCount);
		returnObject.setData(notificationBean);
		return returnObject;
	}

}
