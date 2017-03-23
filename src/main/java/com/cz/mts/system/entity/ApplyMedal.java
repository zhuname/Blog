package com.cz.mts.system.entity;

import java.text.ParseException;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.cz.mts.frame.annotation.WhereSQL;
import com.cz.mts.frame.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:24
 * @see com.cz.mts.system.entity.ApplyMedal
 */
@Table(name="t_apply_medal")
public class ApplyMedal  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "申请勋章表\r\n";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USERID = "用户id";
	public static final String ALIAS_MEDALID = "勋章id";
	public static final String ALIAS_STATUS = "状态：1申请中 2申请成功 3申请失败";
	public static final String ALIAS_APPLYTIME = "申请时间";
	public static final String ALIAS_OPERTIME = "审核时间";
	public static final String ALIAS_REASON = "失败原因";
	public static final String ALIAS_MULTIADDRESS = "多媒体地址（图片以分号分开）";
	public static final String ALIAS_TYPE = "类型：1图片 2视频";
    */
	//date formats
	//public static final String FORMAT_APPLYTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_OPERTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 用户id
	 */
	private java.lang.Integer userId;
	/**
	 * 勋章id
	 */
	private java.lang.Integer medalId;
	/**
	 * 状态：1申请中 2申请成功 3申请失败
	 */
	private java.lang.Integer status;
	/**
	 * 申请时间
	 */
	private java.util.Date applyTime;
	/**
	 * 审核时间
	 */
	private java.util.Date operTime;
	/**
	 * 失败原因
	 */
	private java.lang.String reason;
	/**
	 * 多媒体地址（图片以分号分开）
	 */
	private java.lang.String multiAddress;
	/**
	 * 类型：1图片 2视频
	 */
	private java.lang.Integer type;
	
	private java.lang.String mediaFirstImg;
	
	private java.lang.String introduction;
	
	private String userName;
	private String medalName;
	
	
	@Transient
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Transient
	public String getMedalName() {
		return medalName;
	}

	public void setMedalName(String medalName) {
		this.medalName = medalName;
	}

	//columns END 数据库字段结束
	 @WhereSQL(sql="introduction=:ApplyMedal_introduction")
	public java.lang.String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(java.lang.String introduction) {
		this.introduction = introduction;
	}

	//concstructor
	 @WhereSQL(sql="mediaFirstImg=:ApplyMedal_mediaFirstImg")
	public java.lang.String getMediaFirstImg() {
		return mediaFirstImg;
	}

	public void setMediaFirstImg(java.lang.String mediaFirstImg) {
		this.mediaFirstImg = mediaFirstImg;
	}

	public ApplyMedal(){
	}

	public ApplyMedal(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:ApplyMedal_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
     @WhereSQL(sql="userId=:ApplyMedal_userId")
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	public void setMedalId(java.lang.Integer value) {
		this.medalId = value;
	}
	
     @WhereSQL(sql="medalId=:ApplyMedal_medalId")
	public java.lang.Integer getMedalId() {
		return this.medalId;
	}
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
     @WhereSQL(sql="status=:ApplyMedal_status")
	public java.lang.Integer getStatus() {
		return this.status;
	}
		/*
	public String getapplyTimeString() {
		return DateUtils.convertDate2String(FORMAT_APPLYTIME, getapplyTime());
	}
	public void setapplyTimeString(String value) throws ParseException{
		setapplyTime(DateUtils.convertString2Date(FORMAT_APPLYTIME,value));
	}*/
	
	public void setApplyTime(java.util.Date value) {
		this.applyTime = value;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
     @WhereSQL(sql="applyTime=:ApplyMedal_applyTime")
	public java.util.Date getApplyTime() {
		return this.applyTime;
	}
		/*
	public String getoperTimeString() {
		return DateUtils.convertDate2String(FORMAT_OPERTIME, getoperTime());
	}
	public void setoperTimeString(String value) throws ParseException{
		setoperTime(DateUtils.convertString2Date(FORMAT_OPERTIME,value));
	}*/
	
	public void setOperTime(java.util.Date value) {
		this.operTime = value;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
     @WhereSQL(sql="operTime=:ApplyMedal_operTime")
	public java.util.Date getOperTime() {
		return this.operTime;
	}
	public void setReason(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.reason = value;
	}
	
     @WhereSQL(sql="reason=:ApplyMedal_reason")
	public java.lang.String getReason() {
		return this.reason;
	}
	public void setMultiAddress(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.multiAddress = value;
	}
	
     @WhereSQL(sql="multiAddress=:ApplyMedal_multiAddress")
	public java.lang.String getMultiAddress() {
		return this.multiAddress;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
     @WhereSQL(sql="type=:ApplyMedal_type")
	public java.lang.Integer getType() {
		return this.type;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("用户id[").append(getUserId()).append("],")
			.append("勋章id[").append(getMedalId()).append("],")
			.append("状态：1申请中 2申请成功 3申请失败[").append(getStatus()).append("],")
			.append("申请时间[").append(getApplyTime()).append("],")
			.append("审核时间[").append(getOperTime()).append("],")
			.append("失败原因[").append(getReason()).append("],")
			.append("多媒体地址（图片以分号分开）[").append(getMultiAddress()).append("],")
			.append("类型：1图片 2视频[").append(getType()).append("],")
			.append("视频第一帧图片").append(getMediaFirstImg()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ApplyMedal == false) return false;
		if(this == obj) return true;
		ApplyMedal other = (ApplyMedal)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
