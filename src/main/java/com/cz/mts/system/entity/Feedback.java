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
 * @version  2017-02-24 15:17:26
 * @see com.cz.mts.system.entity.Feedback
 */
@Table(name="t_feedback")
public class Feedback  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = " 意见反馈表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_CONTENT = "反馈内容";
	public static final String ALIAS_USERID = "用户id";
	public static final String ALIAS_CREATETIME = "创建时间";
    */
	//date formats
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 反馈内容
	 */
	private java.lang.String content;
	/**
	 * 用户id
	 */
	private java.lang.Integer userId;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	
	private String userName;
	
	private java.lang.String osType;
	
	 @WhereSQL(sql="osType=:MediaPackage_osType")
	public java.lang.String getOsType() {
		return osType;
	}

	public void setOsType(java.lang.String osType) {
		this.osType = osType;
	}
	
	//columns END 数据库字段结束
	
	
	
	//concstructor

	public Feedback(){
	}

	@Transient
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Feedback(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:Feedback_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setContent(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.content = value;
	}
	
     @WhereSQL(sql="content=:Feedback_content")
	public java.lang.String getContent() {
		return this.content;
	}
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
     @WhereSQL(sql="userId=:Feedback_userId")
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
     @WhereSQL(sql="createTime=:Feedback_createTime")
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("反馈内容[").append(getContent()).append("],")
			.append("用户id[").append(getUserId()).append("],")
			.append("创建时间[").append(getCreateTime()).append("],")
			.append("操作系统").append(getOsType()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Feedback == false) return false;
		if(this == obj) return true;
		Feedback other = (Feedback)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
