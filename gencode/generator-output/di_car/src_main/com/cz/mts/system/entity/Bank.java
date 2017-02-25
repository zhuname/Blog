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
 * @version  2017-02-24 15:17:25
 * @see com.cz.mts.system.entity.Bank
 */
@Table(name="t_bank")
public class Bank  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "银行表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "名称";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 名称
	 */
	private java.lang.String name;
	//columns END 数据库字段结束
	
	//concstructor

	public Bank(){
	}

	public Bank(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:Bank_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
     @WhereSQL(sql="name=:Bank_name")
	public java.lang.String getName() {
		return this.name;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("名称[").append(getName()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Bank == false) return false;
		if(this == obj) return true;
		Bank other = (Bank)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
