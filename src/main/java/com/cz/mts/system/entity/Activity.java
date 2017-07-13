package com.cz.mts.system.entity;

import java.util.List;

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
 * @version  2017-07-03 16:06:04
 * @see com.cz.mts.system.entity.Activity
 */
@Table(name="t_activity")
public class Activity  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "同城活动表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_TYPE = "1图片 2视频";
	public static final String ALIAS_IMAGE = "图片";
	public static final String ALIAS_MEDIAURL = "视频url";
	public static final String ALIAS_CONTENT = "主题";
	public static final String ALIAS_MEDIAIMAGE = "视频封面";
	public static final String ALIAS_JOINEXPLAIN = "参与说明";
	public static final String ALIAS_ENDTIME = "截止时间";
	public static final String ALIAS_ADDRESS = "地址";
	public static final String ALIAS_LONGITUDE = "经度";
	public static final String ALIAS_LATITUDE = "纬度";
	public static final String ALIAS_PHONE = "电话";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_USERID = "发布人id";
	public static final String ALIAS_TOPCOUNT = "点赞次数";
	public static final String ALIAS_COMMENTCOUNT = "评论次数";
	public static final String ALIAS_WINPRIZEPERSON = "中奖人数";
	public static final String ALIAS_SUMWINPRIZEPERSON = "总的中奖人数";
	public static final String ALIAS_STATUS = "1待审核 2失败 3通过 5过期";
	public static final String ALIAS_ADUITSUCCESSTIME = "审核成功时间";
	public static final String ALIAS_REASON = "拒绝原因";
	public static final String ALIAS_ADUITFAILTIME = "审核失败时间";
    */
	//date formats
	//public static final String FORMAT_ENDTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_ADUITSUCCESSTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_ADUITFAILTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 1图片 2视频
	 */
	private java.lang.Integer type;
	/**
	 * 图片
	 */
	private java.lang.String image;
	/**
	 * 视频url
	 */
	private java.lang.String mediaUrl;
	/**
	 * 主题
	 */
	private java.lang.String content;
	/**
	 * 视频封面
	 */
	private java.lang.String mediaImage;
	/**
	 * 参与说明
	 */
	private java.lang.String joinExplain;
	/**
	 * 截止时间
	 */
	private java.util.Date endTime;
	/**
	 * 地址
	 */
	private java.lang.String address;
	/**
	 * 经度
	 */
	private java.lang.Double longitude;
	/**
	 * 纬度
	 */
	private java.lang.Double latitude;
	/**
	 * 电话
	 */
	private java.lang.String phone;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 发布人id
	 */
	private java.lang.Integer userId;
	/**
	 * 点赞次数
	 */
	private java.lang.Integer topCount;
	/**
	 * 评论次数
	 */
	private java.lang.Integer commentCount;
	/**
	 * 中奖人数
	 */
	private java.lang.Integer winPrizePerson;
	/**
	 * 总的中奖人数
	 */
	private java.lang.Integer sumWinPrizePerson;
	/**
	 * 1待审核 2失败 3通过 5过期
	 */
	private java.lang.Integer status;
	/**
	 * 审核成功时间
	 */
	private java.util.Date aduitSuccessTime;
	/**
	 * 拒绝原因
	 */
	private java.lang.String reason;
	/**
	 * 审核失败时间
	 */
	private java.util.Date aduitFailTime;
	//columns END 数据库字段结束
	
	private List<Awards> awardss;
	
	private Integer isPart;
	
	private AppUser appUser;
	
	
	private Integer isDel;
	
	private Integer viewedCount;
	
	private Integer attenedCount;
	
	private Integer isAttr;
	private Integer isColl;
	
	private Integer joinCount;
	
	
	private String osType;
	
	
	
	
	//concstructor

	@WhereSQL(sql="joinCount=:Activity_joinCount")
	public Integer getJoinCount() {
		return joinCount;
	}





	public void setJoinCount(Integer joinCount) {
		this.joinCount = joinCount;
	}




	@WhereSQL(sql="osType=:Activity_osType")
	public String getOsType() {
		return osType;
	}





	public void setOsType(String osType) {
		this.osType = osType;
	}





	public Activity(){
	}

	
	
	
	
	@Transient
	public Integer getIsAttr() {
		return isAttr;
	}

	public void setIsAttr(Integer isAttr) {
		this.isAttr = isAttr;
	}

	@Transient
	public Integer getIsColl() {
		return isColl;
	}

	public void setIsColl(Integer isColl) {
		this.isColl = isColl;
	}

	public Activity(
		java.lang.Integer id
	){
		this.id = id;
	}
	
	@Transient
	public Integer getAttenedCount() {
		return attenedCount;
	}

	public void setAttenedCount(Integer attenedCount) {
		this.attenedCount = attenedCount;
	}

	@WhereSQL(sql="viewedCount=:Activity_viewedCount")
	public Integer getViewedCount() {
		return viewedCount;
	}

	public void setViewedCount(Integer viewedCount) {
		this.viewedCount = viewedCount;
	}

	@WhereSQL(sql="isDel=:Activity_isDel")
	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	@Transient
	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@Transient
	public List<Awards> getAwardss() {
		return awardss;
	}

	public void setAwardss(List<Awards> awardss) {
		this.awardss = awardss;
	}

	@Transient
	public Integer getIsPart() {
		return isPart;
	}

	public void setIsPart(Integer isPart) {
		this.isPart = isPart;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:Activity_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
     @WhereSQL(sql="type=:Activity_type")
	public java.lang.Integer getType() {
		return this.type;
	}
	public void setImage(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.image = value;
	}
	
     @WhereSQL(sql="image=:Activity_image")
	public java.lang.String getImage() {
		return this.image;
	}
	public void setMediaUrl(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.mediaUrl = value;
	}
	
     @WhereSQL(sql="mediaUrl=:Activity_mediaUrl")
	public java.lang.String getMediaUrl() {
		return this.mediaUrl;
	}
	public void setContent(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.content = value;
	}
	
     @WhereSQL(sql="content=:Activity_content")
	public java.lang.String getContent() {
		return this.content;
	}
	public void setMediaImage(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.mediaImage = value;
	}
	
     @WhereSQL(sql="mediaImage=:Activity_mediaImage")
	public java.lang.String getMediaImage() {
		return this.mediaImage;
	}
	public void setJoinExplain(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.joinExplain = value;
	}
	
     @WhereSQL(sql="joinExplain=:Activity_joinExplain")
	public java.lang.String getJoinExplain() {
		return this.joinExplain;
	}
		/*
	public String getendTimeString() {
		return DateUtils.convertDate2String(FORMAT_ENDTIME, getendTime());
	}
	public void setendTimeString(String value) throws ParseException{
		setendTime(DateUtils.convertString2Date(FORMAT_ENDTIME,value));
	}*/
	
	public void setEndTime(java.util.Date value) {
		this.endTime = value;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
     @WhereSQL(sql="endTime=:Activity_endTime")
	public java.util.Date getEndTime() {
		return this.endTime;
	}
	public void setAddress(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.address = value;
	}
	
     @WhereSQL(sql="address=:Activity_address")
	public java.lang.String getAddress() {
		return this.address;
	}
	public void setLongitude(java.lang.Double value) {
		this.longitude = value;
	}
	
     @WhereSQL(sql="longitude=:Activity_longitude")
	public java.lang.Double getLongitude() {
		return this.longitude;
	}
	public void setLatitude(java.lang.Double value) {
		this.latitude = value;
	}
	
     @WhereSQL(sql="latitude=:Activity_latitude")
	public java.lang.Double getLatitude() {
		return this.latitude;
	}
	public void setPhone(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.phone = value;
	}
	
     @WhereSQL(sql="phone=:Activity_phone")
	public java.lang.String getPhone() {
		return this.phone;
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
	
     @WhereSQL(sql="createTime=:Activity_createTime")
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
     @WhereSQL(sql="userId=:Activity_userId")
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	public void setTopCount(java.lang.Integer value) {
		this.topCount = value;
	}
	
     @WhereSQL(sql="topCount=:Activity_topCount")
	public java.lang.Integer getTopCount() {
		return this.topCount;
	}
	public void setCommentCount(java.lang.Integer value) {
		this.commentCount = value;
	}
	
     @WhereSQL(sql="commentCount=:Activity_commentCount")
	public java.lang.Integer getCommentCount() {
		return this.commentCount;
	}
	public void setWinPrizePerson(java.lang.Integer value) {
		this.winPrizePerson = value;
	}
	
     @WhereSQL(sql="winPrizePerson=:Activity_winPrizePerson")
	public java.lang.Integer getWinPrizePerson() {
		return this.winPrizePerson;
	}
	public void setSumWinPrizePerson(java.lang.Integer value) {
		this.sumWinPrizePerson = value;
	}
	
     @WhereSQL(sql="sumWinPrizePerson=:Activity_sumWinPrizePerson")
	public java.lang.Integer getSumWinPrizePerson() {
		return this.sumWinPrizePerson;
	}
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
     @WhereSQL(sql="status=:Activity_status")
	public java.lang.Integer getStatus() {
		return this.status;
	}
		/*
	public String getaduitSuccessTimeString() {
		return DateUtils.convertDate2String(FORMAT_ADUITSUCCESSTIME, getaduitSuccessTime());
	}
	public void setaduitSuccessTimeString(String value) throws ParseException{
		setaduitSuccessTime(DateUtils.convertString2Date(FORMAT_ADUITSUCCESSTIME,value));
	}*/
	
	public void setAduitSuccessTime(java.util.Date value) {
		this.aduitSuccessTime = value;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
     @WhereSQL(sql="aduitSuccessTime=:Activity_aduitSuccessTime")
	public java.util.Date getAduitSuccessTime() {
		return this.aduitSuccessTime;
	}
	public void setReason(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.reason = value;
	}
	
     @WhereSQL(sql="reason=:Activity_reason")
	public java.lang.String getReason() {
		return this.reason;
	}
		/*
	public String getaduitFailTimeString() {
		return DateUtils.convertDate2String(FORMAT_ADUITFAILTIME, getaduitFailTime());
	}
	public void setaduitFailTimeString(String value) throws ParseException{
		setaduitFailTime(DateUtils.convertString2Date(FORMAT_ADUITFAILTIME,value));
	}*/
	
	public void setAduitFailTime(java.util.Date value) {
		this.aduitFailTime = value;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
     @WhereSQL(sql="aduitFailTime=:Activity_aduitFailTime")
	public java.util.Date getAduitFailTime() {
		return this.aduitFailTime;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("1图片 2视频[").append(getType()).append("],")
			.append("图片[").append(getImage()).append("],")
			.append("视频url[").append(getMediaUrl()).append("],")
			.append("主题[").append(getContent()).append("],")
			.append("视频封面[").append(getMediaImage()).append("],")
			.append("参与说明[").append(getJoinExplain()).append("],")
			.append("截止时间[").append(getEndTime()).append("],")
			.append("地址[").append(getAddress()).append("],")
			.append("经度[").append(getLongitude()).append("],")
			.append("纬度[").append(getLatitude()).append("],")
			.append("电话[").append(getPhone()).append("],")
			.append("创建时间[").append(getCreateTime()).append("],")
			.append("发布人id[").append(getUserId()).append("],")
			.append("点赞次数[").append(getTopCount()).append("],")
			.append("评论次数[").append(getCommentCount()).append("],")
			.append("中奖人数[").append(getWinPrizePerson()).append("],")
			.append("总的中奖人数[").append(getSumWinPrizePerson()).append("],")
			.append("1待审核 2失败 3通过 5过期[").append(getStatus()).append("],")
			.append("审核成功时间[").append(getAduitSuccessTime()).append("],")
			.append("拒绝原因[").append(getReason()).append("],")
			.append("审核失败时间[").append(getAduitFailTime()).append("],")
			.append("是否删除[").append(getIsDel()).append("],")
			.append("参与人数[").append(getJoinCount()).append("],")
			.append("操作系统[").append(getOsType()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Activity == false) return false;
		if(this == obj) return true;
		Activity other = (Activity)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
