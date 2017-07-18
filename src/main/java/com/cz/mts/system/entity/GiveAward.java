package com.cz.mts.system.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.cz.mts.frame.annotation.WhereSQL;
import com.cz.mts.frame.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-07-03 16:07:00
 * @see com.cz.mts.system.entity.GiveAward
 */
@Table(name="t_give_award")
public class GiveAward  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "颁奖表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USERID = "发布人的userId";
	public static final String ALIAS_JOINUSERID = "参与者id";
	public static final String ALIAS_CREATETIME = "颁奖时间";
	public static final String ALIAS_AWARDID = "奖项id";
    */
	//date formats
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 发布人的userId
	 */
	private java.lang.Integer userId;
	/**
	 * 参与者id
	 */
	private java.lang.Integer joinUserId;
	/**
	 * 颁奖时间
	 */
	private java.util.Date createTime;
	/**
	 * 奖项id
	 */
	private java.lang.Integer awardId;
	//columns END 数据库字段结束
	
	private AppUser appUser;
	
	private Awards awards;
	
	private String osType;
	
	private String awardName;
	
	private String userName;
	
	private String joinUserName;
	
	
	
	//concstructor
	
	

	@Transient
	public String getAwardName() {
		return awardName;
	}

	@Transient
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Transient
	public String getJoinUserName() {
		return joinUserName;
	}

	public void setJoinUserName(String joinUserName) {
		this.joinUserName = joinUserName;
	}

	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}

	public GiveAward(){
	}
	
	@Transient
	public Awards getAwards() {
		return awards;
	}

	public void setAwards(Awards awards) {
		this.awards = awards;
	}

	@Transient
	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public GiveAward(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:GiveAward_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	
	
	@WhereSQL(sql="osType=:GiveAward_osType")
     public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	@WhereSQL(sql="userId=:GiveAward_userId")
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	public void setJoinUserId(java.lang.Integer value) {
		this.joinUserId = value;
	}
	
     @WhereSQL(sql="joinUserId=:GiveAward_joinUserId")
	public java.lang.Integer getJoinUserId() {
		return this.joinUserId;
	}
		/*
	public String getcreateTimeString() {
		return DateUtils.convertDate2String(FORMAT_CREATETIME, getcreateTime());
	}
	public void setcreateTimeString(String value) throws ParseException{
		setcreateTime(DateUtils.convertString2Date(FORMAT_CREATETIME,value));
	}*/
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
     @WhereSQL(sql="createTime=:GiveAward_createTime")
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setAwardId(java.lang.Integer value) {
		this.awardId = value;
	}
	
     @WhereSQL(sql="awardId=:GiveAward_awardId")
	public java.lang.Integer getAwardId() {
		return this.awardId;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("发布人的userId[").append(getUserId()).append("],")
			.append("参与者id[").append(getJoinUserId()).append("],")
			.append("颁奖时间[").append(getCreateTime()).append("],")
			.append("奖项id[").append(getAwardId()).append("],")
			.append("操作系统[").append(getOsType()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof GiveAward == false) return false;
		if(this == obj) return true;
		GiveAward other = (GiveAward)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
