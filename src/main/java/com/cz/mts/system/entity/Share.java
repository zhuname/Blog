package com.cz.mts.system.entity;

import java.text.ParseException;

import javax.persistence.Id;
import javax.persistence.Table;

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
 * @version  2017-02-24 15:17:28
 * @see com.cz.mts.system.entity.Share
 */
@Table(name="t_share")
public class Share  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "分享表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_SHARETIME = "分享时间";
	public static final String ALIAS_USERID = "用户id";
	public static final String ALIAS_SHARETYPE = "分享类型 ";
	public static final String ALIAS_ISNUM = "是否增加红包领取次数";
    */
	//date formats
	//public static final String FORMAT_SHARETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 分享时间
	 */
	private java.util.Date shareTime;
	/**
	 * 用户id
	 */
	private java.lang.Integer userId;
	/**
	 * 分享类型 
	 */
	private java.lang.String shareType;
	/**
	 * 是否增加红包领取次数
	 */
	private java.lang.Integer isNum;
	//columns END 数据库字段结束
	
	//concstructor

	public Share(){
	}

	public Share(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:Share_id")
	public java.lang.Integer getId() {
		return this.id;
	}
		/*
	public String getshareTimeString() {
		return DateUtils.convertDate2String(FORMAT_SHARETIME, getshareTime());
	}
	public void setshareTimeString(String value) throws ParseException{
		setshareTime(DateUtils.convertString2Date(FORMAT_SHARETIME,value));
	}*/
	
	public void setShareTime(java.util.Date value) {
		this.shareTime = value;
	}
	
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
     @WhereSQL(sql="shareTime=:Share_shareTime")
	public java.util.Date getShareTime() {
		return this.shareTime;
	}
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
     @WhereSQL(sql="userId=:Share_userId")
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	public void setShareType(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.shareType = value;
	}
	
     @WhereSQL(sql="shareType=:Share_shareType")
	public java.lang.String getShareType() {
		return this.shareType;
	}
	public void setIsNum(java.lang.Integer value) {
		this.isNum = value;
	}
	
     @WhereSQL(sql="isNum=:Share_isNum")
	public java.lang.Integer getIsNum() {
		return this.isNum;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("分享时间[").append(getShareTime()).append("],")
			.append("用户id[").append(getUserId()).append("],")
			.append("分享类型 [").append(getShareType()).append("],")
			.append("是否增加红包领取次数[").append(getIsNum()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Share == false) return false;
		if(this == obj) return true;
		Share other = (Share)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
