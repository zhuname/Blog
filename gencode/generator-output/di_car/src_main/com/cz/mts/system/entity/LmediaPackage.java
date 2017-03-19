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
 * @version  2017-03-17 21:28:32
 * @see com.cz.mts.system.entity.LmediaPackage
 */
@Table(name="t_l_media_package")
public class LmediaPackage  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "小视频红包";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_PACKAGEID = "被抢红包ID";
	public static final String ALIAS_USERID = "抢红包人ID";
	public static final String ALIAS_MONEY = "红包金额";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 被抢红包ID
	 */
	private java.lang.Integer packageId;
	/**
	 * 抢红包人ID
	 */
	private java.lang.Integer userId;
	/**
	 * 红包金额
	 */
	private java.lang.Double money;
	//columns END 数据库字段结束
	
	//concstructor

	public LmediaPackage(){
	}

	public LmediaPackage(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:LmediaPackage_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setPackageId(java.lang.Integer value) {
		this.packageId = value;
	}
	
     @WhereSQL(sql="packageId=:LmediaPackage_packageId")
	public java.lang.Integer getPackageId() {
		return this.packageId;
	}
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
     @WhereSQL(sql="userId=:LmediaPackage_userId")
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	public void setMoney(java.lang.Double value) {
		this.money = value;
	}
	
     @WhereSQL(sql="money=:LmediaPackage_money")
	public java.lang.Double getMoney() {
		return this.money;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("被抢红包ID[").append(getPackageId()).append("],")
			.append("抢红包人ID[").append(getUserId()).append("],")
			.append("红包金额[").append(getMoney()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LmediaPackage == false) return false;
		if(this == obj) return true;
		LmediaPackage other = (LmediaPackage)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
