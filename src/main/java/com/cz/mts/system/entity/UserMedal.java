package com.cz.mts.system.entity;

import java.text.ParseException;

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
 * @version  2017-02-24 15:17:29
 * @see com.cz.mts.system.entity.UserMedal
 */
@Table(name="t_user_medal")
public class UserMedal  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "我的勋章表";
	public static final String ALIAS_ID = "用户id";
	public static final String ALIAS_USERID = "用户id";
	public static final String ALIAS_MEDALID = "勋章id";
	public static final String ALIAS_CREATETIME = "创建时间";
    */
	//date formats
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * 用户id
	 */
	private java.lang.Integer id;
	/**
	 * 用户id
	 */
	private java.lang.Integer userId;
	/**
	 * 勋章id
	 */
	private java.lang.Integer medalId;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	//columns END 数据库字段结束
	
	private Medal medal;
	
	
	
	//concstructor
	@Transient
	public Medal getMedal() {
		return medal;
	}

	public void setMedal(Medal medal) {
		this.medal = medal;
	}

	public UserMedal(){
	}

	public UserMedal(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:UserMedal_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
     @WhereSQL(sql="userId=:UserMedal_userId")
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	public void setMedalId(java.lang.Integer value) {
		this.medalId = value;
	}
	
     @WhereSQL(sql="medalId=:UserMedal_medalId")
	public java.lang.Integer getMedalId() {
		return this.medalId;
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
	
     @WhereSQL(sql="createTime=:UserMedal_createTime")
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("用户id[").append(getId()).append("],")
			.append("用户id[").append(getUserId()).append("],")
			.append("勋章id[").append(getMedalId()).append("],")
			.append("创建时间[").append(getCreateTime()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserMedal == false) return false;
		if(this == obj) return true;
		UserMedal other = (UserMedal)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
