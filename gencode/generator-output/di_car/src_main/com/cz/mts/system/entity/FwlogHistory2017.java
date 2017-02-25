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
 * @version  2017-02-24 15:17:26
 * @see com.cz.mts.system.entity.FwlogHistory2017
 */
@Table(name="t_fwlog_history_2017")
public class FwlogHistory2017  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "访问日志";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_STARTDATE = "访问时间";
	public static final String ALIAS_STRDATE = "访问时间(毫秒)";
	public static final String ALIAS_TOMCAT = "Tomcat";
	public static final String ALIAS_USERCODE = "登陆账号";
	public static final String ALIAS_USERNAME = "姓名";
	public static final String ALIAS_SESSIONID = "sessionId";
	public static final String ALIAS_IP = "IP";
	public static final String ALIAS_FWURL = "访问菜单";
	public static final String ALIAS_MENUNAME = "菜单名称";
	public static final String ALIAS_ISQX = "是否有权限访问";
    */
	//date formats
	//public static final String FORMAT_STARTDATE = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * ID
	 */
	private java.lang.String id;
	/**
	 * 访问时间
	 */
	private java.util.Date startDate;
	/**
	 * 访问时间(毫秒)
	 */
	private java.lang.String strDate;
	/**
	 * Tomcat
	 */
	private java.lang.String tomcat;
	/**
	 * 登陆账号
	 */
	private java.lang.String userCode;
	/**
	 * 姓名
	 */
	private java.lang.String userName;
	/**
	 * sessionId
	 */
	private java.lang.String sessionId;
	/**
	 * IP
	 */
	private java.lang.String ip;
	/**
	 * 访问菜单
	 */
	private java.lang.String fwUrl;
	/**
	 * 菜单名称
	 */
	private java.lang.String menuName;
	/**
	 * 是否有权限访问
	 */
	private java.lang.String isqx;
	//columns END 数据库字段结束
	
	//concstructor

	public FwlogHistory2017(){
	}

	public FwlogHistory2017(
		java.lang.String id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:FwlogHistory2017_id")
	public java.lang.String getId() {
		return this.id;
	}
		/*
	public String getstartDateString() {
		return DateUtils.convertDate2String(FORMAT_STARTDATE, getstartDate());
	}
	public void setstartDateString(String value) throws ParseException{
		setstartDate(DateUtils.convertString2Date(FORMAT_STARTDATE,value));
	}*/
	
	public void setStartDate(java.util.Date value) {
		this.startDate = value;
	}
	
     @WhereSQL(sql="startDate=:FwlogHistory2017_startDate")
	public java.util.Date getStartDate() {
		return this.startDate;
	}
	public void setStrDate(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.strDate = value;
	}
	
     @WhereSQL(sql="strDate=:FwlogHistory2017_strDate")
	public java.lang.String getStrDate() {
		return this.strDate;
	}
	public void setTomcat(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.tomcat = value;
	}
	
     @WhereSQL(sql="tomcat=:FwlogHistory2017_tomcat")
	public java.lang.String getTomcat() {
		return this.tomcat;
	}
	public void setUserCode(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.userCode = value;
	}
	
     @WhereSQL(sql="userCode=:FwlogHistory2017_userCode")
	public java.lang.String getUserCode() {
		return this.userCode;
	}
	public void setUserName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.userName = value;
	}
	
     @WhereSQL(sql="userName=:FwlogHistory2017_userName")
	public java.lang.String getUserName() {
		return this.userName;
	}
	public void setSessionId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.sessionId = value;
	}
	
     @WhereSQL(sql="sessionId=:FwlogHistory2017_sessionId")
	public java.lang.String getSessionId() {
		return this.sessionId;
	}
	public void setIp(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.ip = value;
	}
	
     @WhereSQL(sql="ip=:FwlogHistory2017_ip")
	public java.lang.String getIp() {
		return this.ip;
	}
	public void setFwUrl(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.fwUrl = value;
	}
	
     @WhereSQL(sql="fwUrl=:FwlogHistory2017_fwUrl")
	public java.lang.String getFwUrl() {
		return this.fwUrl;
	}
	public void setMenuName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.menuName = value;
	}
	
     @WhereSQL(sql="menuName=:FwlogHistory2017_menuName")
	public java.lang.String getMenuName() {
		return this.menuName;
	}
	public void setIsqx(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.isqx = value;
	}
	
     @WhereSQL(sql="isqx=:FwlogHistory2017_isqx")
	public java.lang.String getIsqx() {
		return this.isqx;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("ID[").append(getId()).append("],")
			.append("访问时间[").append(getStartDate()).append("],")
			.append("访问时间(毫秒)[").append(getStrDate()).append("],")
			.append("Tomcat[").append(getTomcat()).append("],")
			.append("登陆账号[").append(getUserCode()).append("],")
			.append("姓名[").append(getUserName()).append("],")
			.append("sessionId[").append(getSessionId()).append("],")
			.append("IP[").append(getIp()).append("],")
			.append("访问菜单[").append(getFwUrl()).append("],")
			.append("菜单名称[").append(getMenuName()).append("],")
			.append("是否有权限访问[").append(getIsqx()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof FwlogHistory2017 == false) return false;
		if(this == obj) return true;
		FwlogHistory2017 other = (FwlogHistory2017)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
