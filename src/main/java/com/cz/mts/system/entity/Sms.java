package com.cz.mts.system.entity;

import java.text.ParseException;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.cz.mts.frame.annotation.WhereSQL;
import com.cz.mts.frame.entity.BaseEntity;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:28
 * @see com.cz.mts.system.entity.Sms
 */
@Table(name="t_sms")
public class Sms  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "验证码表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_CONTENT = "验证码";
	public static final String ALIAS_CREATETIME = "createTime";
	public static final String ALIAS_TYPE = "类型：1注册 2修改绑定原手机号 3绑定新手机号 4找回密码";
	public static final String ALIAS_PHONE = "手机号";
    */
	//date formats
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 验证码
	 */
	private java.lang.String content;
	/**
	 * createTime
	 */
	private java.util.Date createTime;
	/**
	 * 类型：1注册 2修改绑定原手机号 3绑定新手机号 4找回密码
	 */
	private java.lang.Integer type;
	/**
	 * 手机号
	 */
	private java.lang.String phone;
	//columns END 数据库字段结束
	
	//concstructor

	public Sms(){
	}

	public Sms(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:Sms_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setContent(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.content = value;
	}
	
     @WhereSQL(sql="content=:Sms_content")
	public java.lang.String getContent() {
		return this.content;
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
	
     @WhereSQL(sql="createTime=:Sms_createTime")
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
     @WhereSQL(sql="type=:Sms_type")
	public java.lang.Integer getType() {
		return this.type;
	}
	public void setPhone(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.phone = value;
	}
	
     @WhereSQL(sql="phone=:Sms_phone")
	public java.lang.String getPhone() {
		return this.phone;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("验证码[").append(getContent()).append("],")
			.append("createTime[").append(getCreateTime()).append("],")
			.append("类型：1注册 2修改绑定原手机号 3绑定新手机号 4找回密码[").append(getType()).append("],")
			.append("手机号[").append(getPhone()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Sms == false) return false;
		if(this == obj) return true;
		Sms other = (Sms)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
