package com.cz.mts.system.entity;
/**
 * 抢红包的bean
 * @author Michael
 *
 */
public class Snatch {

	private Integer userId ;   //抢红包人的id
	private Integer packageId;   //待抢红包id
	
	
	public Snatch() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Snatch(Integer userId, Integer packageId) {
		super();
		this.userId = userId;
		this.packageId = packageId;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public Integer getPackageId() {
		return packageId;
	}


	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}
	
	
}
