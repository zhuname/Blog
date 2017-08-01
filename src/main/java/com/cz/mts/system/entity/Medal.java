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
 * @version  2017-02-24 15:17:26
 * @see com.cz.mts.system.entity.Medal
 */
@Table(name="t_medal")
public class Medal  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "勋章表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "勋章名称";
	public static final String ALIAS_IMAGE = "勋章图片";
	public static final String ALIAS_DESCR = "简介";
	public static final String ALIAS_TYPE = "类型：1图片认证 2视频认证";
	public static final String ALIAS_APPLYINSTRUCTIONS = "申请说明";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 勋章名称
	 */
	private java.lang.String name;
	/**
	 * 勋章图片
	 */
	private java.lang.String image;
	/**
	 * 简介
	 */
	private java.lang.String descr;
	/**
	 * 类型：1图片认证 2视频认证
	 */
	private java.lang.Integer type;
	/**
	 * 申请说明
	 */
	private java.lang.String applyInstructions;
	//columns END 数据库字段结束
	
	//concstructor
	private Integer userId;
	
	private Integer isAvoidAudit;
	
	private Integer status;
	
	@WhereSQL(sql="status=:Medal_status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Transient
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Medal(){
	}

	public Medal(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:Medal_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
     @WhereSQL(sql="name=:Medal_name")
	public java.lang.String getName() {
		return this.name;
	}
	public void setImage(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.image = value;
	}
	
     @WhereSQL(sql="image=:Medal_image")
	public java.lang.String getImage() {
		return this.image;
	}
	public void setDescr(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.descr = value;
	}
	
     @WhereSQL(sql="descr=:Medal_descr")
	public java.lang.String getDescr() {
		return this.descr;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
     @WhereSQL(sql="type=:Medal_type")
	public java.lang.Integer getType() {
		return this.type;
	}
	public void setApplyInstructions(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.applyInstructions = value;
	}
	
     @WhereSQL(sql="applyInstructions=:Medal_applyInstructions")
	public java.lang.String getApplyInstructions() {
		return this.applyInstructions;
	}
     
     @WhereSQL(sql="isAvoidAudit=:Medal_isAvoidAudit")
	public Integer getIsAvoidAudit() {
		return isAvoidAudit;
	}

	public void setIsAvoidAudit(Integer isAvoidAudit) {
		this.isAvoidAudit = isAvoidAudit;
	}

	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("勋章名称[").append(getName()).append("],")
			.append("勋章图片[").append(getImage()).append("],")
			.append("简介[").append(getDescr()).append("],")
			.append("类型：1图片认证 2视频认证[").append(getType()).append("],")
			.append("申请说明[").append(getApplyInstructions()).append("],")
			.append("是否免审核[").append(getIsAvoidAudit()).append("],")
			.append("审核状态[").append(getStatus()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Medal == false) return false;
		if(this == obj) return true;
		Medal other = (Medal)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
