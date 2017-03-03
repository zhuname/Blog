package com.cz.mts.system.entity;

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
 * @version  2017-03-03 15:28:50
 * @see com.cz.mts.system.entity.Password
 */
@Table(name="t_password")
public class Password  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "Password";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_MDBEFOREPASS = "加密前";
	public static final String ALIAS_MDAFTERPASS = "加密后的密码";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 加密前
	 */
	private java.lang.String mdBeforePass;
	/**
	 * 加密后的密码
	 */
	private java.lang.String mdAfterPass;
	//columns END 数据库字段结束
	
	//concstructor

	public Password(){
	}

	public Password(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:Password_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setMdBeforePass(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.mdBeforePass = value;
	}
	
     @WhereSQL(sql="mdBeforePass=:Password_mdBeforePass")
	public java.lang.String getMdBeforePass() {
		return this.mdBeforePass;
	}
	public void setMdAfterPass(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.mdAfterPass = value;
	}
	
     @WhereSQL(sql="mdAfterPass=:Password_mdAfterPass")
	public java.lang.String getMdAfterPass() {
		return this.mdAfterPass;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("加密前[").append(getMdBeforePass()).append("],")
			.append("加密后的密码[").append(getMdAfterPass()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Password == false) return false;
		if(this == obj) return true;
		Password other = (Password)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
