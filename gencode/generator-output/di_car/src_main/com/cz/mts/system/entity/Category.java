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
 * @version  2017-02-24 11:23:50
 * @see com.cz.mts.system.entity.Category
 */
@Table(name="t_category")
public class Category  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "分类";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "分类名称";
	public static final String ALIAS_TYPE = "类型：1海报红包 2视频红包 3卡券";
	public static final String ALIAS_IMAGE = "分类图片";
	public static final String ALIAS_ISDEL = "0否1是";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 分类名称
	 */
	private java.lang.String name;
	/**
	 * 类型：1海报红包 2视频红包 3卡券
	 */
	private java.lang.Integer type;
	/**
	 * 分类图片
	 */
	private java.lang.String image;
	/**
	 * 0否1是
	 */
	private java.lang.Integer isDel;
	//columns END 数据库字段结束
	
	//concstructor

	public Category(){
	}

	public Category(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:Category_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
     @WhereSQL(sql="name=:Category_name")
	public java.lang.String getName() {
		return this.name;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
     @WhereSQL(sql="type=:Category_type")
	public java.lang.Integer getType() {
		return this.type;
	}
	public void setImage(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.image = value;
	}
	
     @WhereSQL(sql="image=:Category_image")
	public java.lang.String getImage() {
		return this.image;
	}
	public void setIsDel(java.lang.Integer value) {
		this.isDel = value;
	}
	
     @WhereSQL(sql="isDel=:Category_isDel")
	public java.lang.Integer getIsDel() {
		return this.isDel;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("分类名称[").append(getName()).append("],")
			.append("类型：1海报红包 2视频红包 3卡券[").append(getType()).append("],")
			.append("分类图片[").append(getImage()).append("],")
			.append("0否1是[").append(getIsDel()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Category == false) return false;
		if(this == obj) return true;
		Category other = (Category)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
