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
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-07-05 15:15:34
 * @see com.cz.mts.system.entity.Oper
 */
@Table(name="t_oper")
public class Oper  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "点赞、评论表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USERID = "点赞人id";
	public static final String ALIAS_ITEMID = "红包id";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_TYPE = " 1海报点赞  2海报评论 3视频点赞  4视频评论 5同城活动参与评论 6同城活动参与点赞 7城事圈点赞 8城事圈评论";
	public static final String ALIAS_CONTENT = "评论内容";
    */
	//date formats
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 点赞人id
	 */
	private java.lang.Integer userId;
	/**
	 * 红包id
	 */
	private java.lang.Integer itemId;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 *  1海报点赞  2海报评论 3视频点赞  4视频评论 5同城活动参与评论 6同城活动参与点赞 7城事圈点赞 8城事圈评论
	 */
	private java.lang.Integer type;
	/**
	 * 评论内容
	 */
	private java.lang.String content;
	
	private AppUser appUser;
	
	private String nickName;
	
	private String osType;
	
	//columns END 数据库字段结束
	
	//concstructor

	
	
	
	
	@Transient
	public AppUser getAppUser() {
		return appUser;
	}

	@Transient
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public Oper(){
	}

	public Oper(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:Oper_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	
	
	@WhereSQL(sql="osType=:Oper_osType")
     public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	@WhereSQL(sql="userId=:Oper_userId")
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	public void setItemId(java.lang.Integer value) {
		this.itemId = value;
	}
	
     @WhereSQL(sql="itemId=:Oper_itemId")
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
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
     @WhereSQL(sql="createTime=:Oper_createTime")
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
     @WhereSQL(sql="type=:Oper_type")
	public java.lang.Integer getType() {
		return this.type;
	}
	public void setContent(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.content = value;
	}
	
     @WhereSQL(sql="content=:Oper_content")
	public java.lang.String getContent() {
		return this.content;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("点赞人id[").append(getUserId()).append("],")
			.append("红包id[").append(getItemId()).append("],")
			.append("创建时间[").append(getCreateTime()).append("],")
			.append(" 1海报点赞  2海报评论 3视频点赞  4视频评论 5同城活动参与评论 6同城活动参与点赞 7城事圈点赞 8城事圈评论[").append(getType()).append("],")
			.append("评论内容[").append(getContent()).append("],")
			.append("操作系统[").append(getOsType()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Oper == false) return false;
		if(this == obj) return true;
		Oper other = (Oper)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
