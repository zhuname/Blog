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
 * @version  2017-02-24 11:23:50
 * @see com.cz.mts.system.entity.Collect
 */
@Table(name="t_collect")
public class Collect  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "收藏表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USERID = "用户id";
	public static final String ALIAS_TYPE = "类型：1海报红包 2视频红包 3卡券";
	public static final String ALIAS_ITEMID = "目标id";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_OSTYPE = "操作系统";
    */
	//date formats
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 用户id
	 */
	private java.lang.Integer userId;
	/**
	 * 类型：1海报红包 2视频红包 3卡券
	 */
	private java.lang.Integer type;
	/**
	 * 目标id
	 */
	private java.lang.Integer itemId;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 操作系统
	 */
	private java.lang.String osType;
	//columns END 数据库字段结束
	
	//concstructor

	public Collect(){
	}

	public Collect(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:Collect_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
     @WhereSQL(sql="userId=:Collect_userId")
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
     @WhereSQL(sql="type=:Collect_type")
	public java.lang.Integer getType() {
		return this.type;
	}
	public void setItemId(java.lang.Integer value) {
		this.itemId = value;
	}
	
     @WhereSQL(sql="itemId=:Collect_itemId")
	public java.lang.Integer getItemId() {
		return this.itemId;
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
	
     @WhereSQL(sql="createTime=:Collect_createTime")
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setOsType(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.osType = value;
	}
	
     @WhereSQL(sql="osType=:Collect_osType")
	public java.lang.String getOsType() {
		return this.osType;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("用户id[").append(getUserId()).append("],")
			.append("类型：1海报红包 2视频红包 3卡券[").append(getType()).append("],")
			.append("目标id[").append(getItemId()).append("],")
			.append("创建时间[").append(getCreateTime()).append("],")
			.append("操作系统[").append(getOsType()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Collect == false) return false;
		if(this == obj) return true;
		Collect other = (Collect)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
