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
 * @version  2017-07-03 16:07:17
 * @see com.cz.mts.system.entity.Awards
 */
@Table(name="t_awards")
public class Awards  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "同城活动奖项表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ACTIVITYID = "活动id";
	public static final String ALIAS_TITLE = "标题";
	public static final String ALIAS_CONTENT = "内容";
	public static final String ALIAS_SUMCOUNT = "发布时候名额";
	public static final String ALIAS_REMAINCOUNT = "剩余名额";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 活动id
	 */
	private java.lang.Integer activityId;
	/**
	 * 标题
	 */
	private java.lang.String title;
	/**
	 * 内容
	 */
	private java.lang.String content;
	/**
	 * 发布时候名额
	 */
	private java.lang.Integer sumCount;
	/**
	 * 剩余名额
	 */
	private java.lang.Integer remainCount;
	//columns END 数据库字段结束
	
	//concstructor

	public Awards(){
	}

	public Awards(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:Awards_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setActivityId(java.lang.Integer value) {
		this.activityId = value;
	}
	
     @WhereSQL(sql="activityId=:Awards_activityId")
	public java.lang.Integer getActivityId() {
		return this.activityId;
	}
	public void setTitle(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.title = value;
	}
	
     @WhereSQL(sql="title=:Awards_title")
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setContent(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.content = value;
	}
	
     @WhereSQL(sql="content=:Awards_content")
	public java.lang.String getContent() {
		return this.content;
	}
	public void setSumCount(java.lang.Integer value) {
		this.sumCount = value;
	}
	
     @WhereSQL(sql="sumCount=:Awards_sumCount")
	public java.lang.Integer getSumCount() {
		return this.sumCount;
	}
	public void setRemainCount(java.lang.Integer value) {
		this.remainCount = value;
	}
	
     @WhereSQL(sql="remainCount=:Awards_remainCount")
	public java.lang.Integer getRemainCount() {
		return this.remainCount;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("活动id[").append(getActivityId()).append("],")
			.append("标题[").append(getTitle()).append("],")
			.append("内容[").append(getContent()).append("],")
			.append("发布时候名额[").append(getSumCount()).append("],")
			.append("剩余名额[").append(getRemainCount()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Awards == false) return false;
		if(this == obj) return true;
		Awards other = (Awards)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
