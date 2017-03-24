package com.cz.mts.system.web;

import com.cz.mts.system.entity.AppUser;
import com.cz.mts.system.entity.Attention;
import com.cz.mts.system.entity.Card;
import com.cz.mts.system.entity.MediaPackage;
import com.cz.mts.system.entity.PosterPackage;
import com.cz.mts.system.service.NotificationService;

public class AttenThreadController extends Thread{
	
	
	public AttenThreadController( PosterPackage posterPackage, MediaPackage mediaPackage,Attention attention,Card card,
			NotificationService notificationService,AppUser appUser) {
		super();
		this.posterPackage = posterPackage;
		this.mediaPackage = mediaPackage;
		this.attention = attention;
		this.card = card;
		this.notificationService = notificationService;
		this.appUser = appUser;
	}
	
	public void run(){
		try {
			if(null != posterPackage && 1 == appUser.getIsPush()){
				notificationService.notify(10,posterPackage.getId(),attention.getId(),appUser.getName(),posterPackage.getTitle(),posterPackage.getBalance()+"");
			}
			if(null != mediaPackage && 1 == appUser.getIsPush()){
				notificationService.notify(10,mediaPackage.getId(),attention.getId(),appUser.getName(),mediaPackage.getTitle(),mediaPackage.getBalance()+"");
			}
			if(null != card && 1 == appUser.getIsPush()){
				notificationService.notify(11,card.getId(),attention.getId(),appUser.getName(),card.getTitle());
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

}
