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
 * @see com.cz.mts.system.entity.RedCity
 */
@Table(name="t_red_city")
public class RedCity  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "红包城市";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_CITYID = "城市id";
	public static final String ALIAS_PACKAGEID = "红包id";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 城市id
	 */
	private java.lang.Integer cityId;
	/**
	 * 红包id
	 */
	private java.lang.Integer packageId;
	//columns END 数据库字段结束
	
	
	private Integer type;
	
	//concstructor

	public RedCity(){
	}

	
	
	
	public Integer getType() {
		return type;
	}




	public void setType(Integer type) {
		this.type = type;
	}




	public RedCity(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:RedCity_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setCityId(java.lang.Integer value) {
		this.cityId = value;
	}
	
     @WhereSQL(sql="cityId=:RedCity_cityId")
	public java.lang.Integer getCityId() {
		return this.cityId;
	}
	public void setPackageId(java.lang.Integer value) {
		this.packageId = value;
	}
	
     @WhereSQL(sql="packageId=:RedCity_packageId")
	public java.lang.Integer getPackageId() {
		return this.packageId;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("城市id[").append(getCityId()).append("],")
			.append("红包id[").append(getPackageId()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof RedCity == false) return false;
		if(this == obj) return true;
		RedCity other = (RedCity)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
