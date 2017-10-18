package com.cz.mts.system.web;

import org.apache.commons.lang3.StringUtils;

import com.cz.mts.system.entity.Activity;
import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.Attention;
import com.cz.mts.system.entity.Card;
import com.cz.mts.system.entity.Circle;
import com.cz.mts.system.entity.MediaPackage;
import com.cz.mts.system.entity.PosterPackage;
import com.cz.mts.system.service.NotificationService;

public class AttenThreadController extends Thread{
	
	
	public AttenThreadController( PosterPackage posterPackage, MediaPackage mediaPackage,Attention attention,Card card,Activity activity,Circle circle,
			NotificationService notificationService,AppUser appUser) {
		super();
		this.posterPackage = posterPackage;
		this.mediaPackage = mediaPackage;
		this.attention = attention;
		this.card = card;
		this.activity = activity;
		this.circle = circle;
		this.notificationService = notificationService;
		this.appUser = appUser;
	}
	
	public void run(){
		try {
			String name = "";
			if(null != posterPackage && null != appUser && 1 == appUser.getIsPush()){
				if(StringUtils.isNotBlank(appUser.getName())){
					name = appUser.getName();
				}else{
					name = appUser.getPhone();
				}
				notificationService.notify(10,posterPackage.getUserId(),attention.getUserId(),name,posterPackage.getTitle(),posterPackage.getBalance()+"");
			}
			if(null != mediaPackage && null != appUser && 1 == appUser.getIsPush()){
				if(StringUtils.isNotBlank(appUser.getName())){
					name = appUser.getName();
				}else{
					name = appUser.getPhone();
				}
				notificationService.notify(10,mediaPackage.getUserId(),attention.getUserId(),name,mediaPackage.getTitle(),mediaPackage.getBalance()+"");
			}
			if(null != card && null != appUser && 1 == appUser.getIsPush()){
				if(StringUtils.isNotBlank(appUser.getName())){
					name = appUser.getName();
				}else{
					name = appUser.getPhone();
				}
				notificationService.notify(11,card.getUserId(),attention.getUserId(),name,card.getTitle());
			}
			if(null != activity && null != appUser && 1 == appUser.getIsPush()){
				if(StringUtils.isNotBlank(appUser.getName())){
					name = appUser.getName();
				}else{
					name = appUser.getPhone();
				}
				notificationService.notify(44,activity.getUserId(),attention.getUserId(),name,activity.getContent());
			}
			if(null != circle && null != appUser && 1 == appUser.getIsPush()){
				if(StringUtils.isNotBlank(appUser.getName())){
					name = appUser.getName();
				}else{
					name = appUser.getPhone();
				}
				notificationService.notify(45,circle.getUserId(),attention.getUserId(),name,circle.getContent());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Attention attention;
	public NotificationService notificationService;
	public PosterPackage posterPackage;
	public MediaPackage mediaPackage;
	public Card card;
	public AppUser appUser;
	public Activity activity;
	public Circle circle;
	
}
