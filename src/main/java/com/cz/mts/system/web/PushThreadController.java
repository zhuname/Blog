package com.cz.mts.system.web;

import java.util.List;

import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.service.NotificationService;

public class PushThreadController extends Thread{
	
	public PushThreadController(List<AppUser> appUsers,
			NotificationService notificationService,String content,String url) {
		super();
		this.appUsers = appUsers;
		this.notificationService = notificationService;
		this.content = content;
		this.url = url;
	}
	
	public void run(){
		try{
			if(null != appUsers && appUsers.size() > 0){
				for (AppUser appUser : appUsers) {
					if(1 == appUser.getIsPush()){
						notificationService.notify(1, null, appUser.getId(), content,url);
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public List<AppUser> appUsers;
	public NotificationService notificationService;
	public String content;
	public String url;

}
