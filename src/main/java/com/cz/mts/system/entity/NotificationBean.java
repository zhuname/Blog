package com.cz.mts.system.entity;

import java.io.Serializable;

public class NotificationBean implements Serializable {

	public NotificationBean(){
		super();
	}
	
	private Integer posterpackageCount;
	private Integer mediapackageCount;
	private Integer cardCount;
	private Integer applyMedalCount;
	private Integer applyWithdrawCount;
	private Integer sumCount;
	
	
	public Integer getSumCount() {
		return sumCount;
	}
	public void setSumCount(Integer sumCount) {
		this.sumCount = sumCount;
	}
	public Integer getPosterpackageCount() {
		return posterpackageCount;
	}
	public void setPosterpackageCount(Integer posterpackageCount) {
		this.posterpackageCount = posterpackageCount;
	}
	public Integer getMediapackageCount() {
		return mediapackageCount;
	}
	public void setMediapackageCount(Integer mediapackageCount) {
		this.mediapackageCount = mediapackageCount;
	}
	public Integer getCardCount() {
		return cardCount;
	}
	public void setCardCount(Integer cardCount) {
		this.cardCount = cardCount;
	}
	public Integer getApplyMedalCount() {
		return applyMedalCount;
	}
	public void setApplyMedalCount(Integer applyMedalCount) {
		this.applyMedalCount = applyMedalCount;
	}
	public Integer getApplyWithdrawCount() {
		return applyWithdrawCount;
	}
	public void setApplyWithdrawCount(Integer applyWithdrawCount) {
		this.applyWithdrawCount = applyWithdrawCount;
	}
	
	
	
}
