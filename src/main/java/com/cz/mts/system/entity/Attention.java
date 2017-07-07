package com.cz.mts.system.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.cz.mts.frame.annotation.WhereSQL;
import com.cz.mts.frame.entity.BaseEntity;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-24 15:17:24
 * @see com.cz.mts.system.entity.Attention
 */
@Table(name="t_attention")
public class Attention  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "关注表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USERID = "关注人的id";
	public static final String ALIAS_ITEMID = "被关注人的id";
	public static final String ALIAS_ISUPDATE = "是否有信息更新: 0否 1是";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 关注人的id
	 */
	private java.lang.Integer userId;
	/**
	 * 被关注人的id
	 */
	private java.lang.Integer itemId;
	/**
	 * 是否有信息更新: 0否 1是
	 */
	private java.lang.Integer isUpdate;
	//columns END 数据库字段结束
	
	private AppUser appUser;
	
	
	//concstructor

	public Attention(){
	}

	public Attention(
		java.lang.Integer id
	){
		this.id = id;
	}

	@Transient
	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:Attention_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
     @WhereSQL(sql="userId=:Attention_userId")
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	public void setItemId(java.lang.Integer value) {
		this.itemId = value;
	}
	
     @WhereSQL(sql="itemId=:Attention_itemId")
	public java.lang.Integer getItemId() {
		return this.itemId;
	}
	public void setIsUpdate(java.lang.Integer value) {
		this.isUpdate = value;
	}
	
     @WhereSQL(sql="isUpdate=:Attention_isUpdate")
	public java.lang.Integer getIsUpdate() {
		return this.isUpdate;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("关注人的id[").append(getUserId()).append("],")
			.append("被关注人的id[").append(getItemId()).append("],")
			.append("是否有信息更新: 0否 1是[").append(getIsUpdate()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Attention == false) return false;
		if(this == obj) return true;
		Attention other = (Attention)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
