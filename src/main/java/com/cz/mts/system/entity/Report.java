
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
 * @version  2017-07-07 14:31:44
 * @see com.cz.mts.system.entity.Report
 */
@Table(name="t_report")
public class Report  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "举报表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_OPERUSERID = "举报人id";
	public static final String ALIAS_ITEMID = "同城活动参与id/城事圈id";
	public static final String ALIAS_CREATETIME = "举报时间";
	public static final String ALIAS_CONTENT = "举报内容";
	public static final String ALIAS_TYPE = "1.同城活动参与举报  2城事圈举报";
	public static final String ALIAS_REPORTEDUSERID = "被举报人的id";
    */
	//date formats
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 举报人id
	 */
	private java.lang.Integer operUserId;
	/**
	 * 同城活动参与id/城事圈id
	 */
	private java.lang.Integer itemId;
	/**
	 * 举报时间
	 */
	private java.util.Date createTime;
	/**
	 * 举报内容
	 */
	private java.lang.String content;
	/**
	 * 1.同城活动参与举报  2城事圈举报
	 */
	private java.lang.Integer type;
	/**
	 * 被举报人的id
	 */
	private java.lang.Integer reportedUserId;
	
	
	private String osType;
	//columns END 数据库字段结束
	
	private String operUserName;
	private String reportedUserName;
	
	private String itemContent;
	
	
	@Transient
	public String getItemContent() {
		return itemContent;
	}

	public void setItemContent(String itemContent) {
		this.itemContent = itemContent;
	}

	//concstructor
    @Transient
	public String getOperUserName() {
		return operUserName;
	}

	public void setOperUserName(String operUserName) {
		this.operUserName = operUserName;
	}

	 @Transient
	public String getReportedUserName() {
		return reportedUserName;
	}

	public void setReportedUserName(String reportedUserName) {
		this.reportedUserName = reportedUserName;
	}

	public Report(){
	}

	public Report(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:Report_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setOperUserId(java.lang.Integer value) {
		this.operUserId = value;
	}
	
	
	@WhereSQL(sql="osType=:Report_osType")
     public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	@WhereSQL(sql="operUserId=:Report_operUserId")
	public java.lang.Integer getOperUserId() {
		return this.operUserId;
	}
	public void setItemId(java.lang.Integer value) {
		this.itemId = value;
	}
	
     @WhereSQL(sql="itemId=:Report_itemId")
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
     @WhereSQL(sql="createTime=:Report_createTime")
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setContent(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.content = value;
	}
	
     @WhereSQL(sql="content=:Report_content")
	public java.lang.String getContent() {
		return this.content;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
     @WhereSQL(sql="type=:Report_type")
	public java.lang.Integer getType() {
		return this.type;
	}
	public void setReportedUserId(java.lang.Integer value) {
		this.reportedUserId = value;
	}
	
     @WhereSQL(sql="reportedUserId=:Report_reportedUserId")
	public java.lang.Integer getReportedUserId() {
		return this.reportedUserId;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("举报人id[").append(getOperUserId()).append("],")
			.append("同城活动参与id/城事圈id[").append(getItemId()).append("],")
			.append("举报时间[").append(getCreateTime()).append("],")
			.append("举报内容[").append(getContent()).append("],")
			.append("1.同城活动参与举报  2城事圈举报[").append(getType()).append("],")
			.append("被举报人的id[").append(getReportedUserId()).append("],")
			.append("操作系统[").append(getOsType()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Report == false) return false;
		if(this == obj) return true;
		Report other = (Report)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
