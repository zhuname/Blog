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
 * @version  2017-07-05 15:58:36
 * @see com.cz.mts.system.entity.Shield
 */
@Table(name="t_shield")
public class Shield  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "屏蔽表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ITEMID = "被屏蔽的用户id";
	public static final String ALIAS_USERID = "操作人用户id";
	public static final String ALIAS_CREATETIME = "屏蔽时间";
    */
	//date formats
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 被屏蔽的用户id
	 */
	private java.lang.Integer itemId;
	/**
	 * 操作人用户id
	 */
	private java.lang.Integer userId;
	/**
	 * 屏蔽时间
	 */
	private java.util.Date createTime;
	
	private String osType;
	//columns END 数据库字段结束
	
	//concstructor

	public Shield(){
	}

	public Shield(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:Shield_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setItemId(java.lang.Integer value) {
		this.itemId = value;
	}
	
	
	 @WhereSQL(sql="osType=:Shield_osType")
     public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	@WhereSQL(sql="itemId=:Shield_itemId")
	public java.lang.Integer getItemId() {
		return this.itemId;
	}
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
     @WhereSQL(sql="userId=:Shield_userId")
	public java.lang.Integer getUserId() {
		return this.userId;
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
     @WhereSQL(sql="createTime=:Shield_createTime")
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("被屏蔽的用户id[").append(getItemId()).append("],")
			.append("操作人用户id[").append(getUserId()).append("],")
			.append("屏蔽时间[").append(getCreateTime()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Shield == false) return false;
		if(this == obj) return true;
		Shield other = (Shield)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
