package com.cz.mts.system.entity;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @see com.cz.mts.system.entity.City
 */
@Table(name="t_city")
public class City  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "城市表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "城市名";
	public static final String ALIAS_CAPITAL = "拼音首字母";
	public static final String ALIAS_OPEN = "是否开通  1为开通0否";
	public static final String ALIAS_FATHERID = "父类id";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 城市名
	 */
	private java.lang.String name;
	/**
	 * 拼音首字母
	 */
	private java.lang.String capital;
	/**
	 * 是否开通  1为开通0否
	 */
	private java.lang.Integer open;
	/**
	 * 父类id
	 */
	private java.lang.Integer fatherId;
	//columns END 数据库字段结束
	
	//concstructor
	
	

	public City(){
	}

	public City(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:City_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
     @WhereSQL(sql="name=:City_name")
	public java.lang.String getName() {
		return this.name;
	}
	public void setCapital(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.capital = value;
	}
	
     @WhereSQL(sql="capital=:City_capital")
	public java.lang.String getCapital() {
		return this.capital;
	}
	public void setOpen(java.lang.Integer value) {
		this.open = value;
	}
	
     @WhereSQL(sql="open=:City_open")
	public java.lang.Integer getOpen() {
		return this.open;
	}
	public void setFatherId(java.lang.Integer value) {
		this.fatherId = value;
	}
	
     @WhereSQL(sql="fatherId=:City_fatherId")
	public java.lang.Integer getFatherId() {
		return this.fatherId;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("城市名[").append(getName()).append("],")
			.append("拼音首字母[").append(getCapital()).append("],")
			.append("是否开通  1为开通0否[").append(getOpen()).append("],")
			.append("父类id[").append(getFatherId()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof City == false) return false;
		if(this == obj) return true;
		City other = (City)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
