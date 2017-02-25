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
 * @version  2017-02-24 15:17:28
 * @see com.cz.mts.system.entity.Province
 */
@Table(name="t_province")
public class Province  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "省级表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "name";
	public static final String ALIAS_OPEN = "省是否开通(1是0否)";
	public static final String ALIAS_CAPITAL = "拼音";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * name
	 */
	private java.lang.String name;
	/**
	 * 省是否开通(1是0否)
	 */
	private java.lang.Integer open;
	/**
	 * 拼音
	 */
	private java.lang.String capital;
	//columns END 数据库字段结束
	
	//concstructor

	public Province(){
	}

	public Province(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:Province_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
     @WhereSQL(sql="name=:Province_name")
	public java.lang.String getName() {
		return this.name;
	}
	public void setOpen(java.lang.Integer value) {
		this.open = value;
	}
	
     @WhereSQL(sql="open=:Province_open")
	public java.lang.Integer getOpen() {
		return this.open;
	}
	public void setCapital(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.capital = value;
	}
	
     @WhereSQL(sql="capital=:Province_capital")
	public java.lang.String getCapital() {
		return this.capital;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("name[").append(getName()).append("],")
			.append("省是否开通(1是0否)[").append(getOpen()).append("],")
			.append("拼音[").append(getCapital()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Province == false) return false;
		if(this == obj) return true;
		Province other = (Province)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
