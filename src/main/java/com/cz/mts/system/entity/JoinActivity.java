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
 * @version  2017-07-03 16:06:21
 * @see com.cz.mts.system.entity.JoinActivity
 */
@Table(name="t_join_activity")
public class JoinActivity  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "同城活动参与表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_TYPE = "1图片 2视频";
	public static final String ALIAS_IMAGE = "图片";
	public static final String ALIAS_MEDIAURL = "视频url";
	public static final String ALIAS_MEDIAIMAGE = "视频封面";
	public static final String ALIAS_CONTENT = "内容";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_USERID = "参与人的userId";
	public static final String ALIAS_AWARDID = "奖项id";
	public static final String ALIAS_TOPCOUNT = "点赞次数";
	public static final String ALIAS_COMMENTCOUNT = "评论次数";
    */
	//date formats
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	
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
	 * 视频封面
	 */
	private java.lang.String mediaImage;
	/**
	 * 内容
	 */
	private java.lang.String content;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 参与人的userId
	 */
	private java.lang.Integer userId;
	/**
	 * 奖项id
	 */
	private java.lang.Integer awardId;
	/**
	 * 点赞次数
	 */
	private java.lang.Integer topCount;
	/**
	 * 评论次数
	 */
	private java.lang.Integer commentCount;
	
	
	private Integer activityId;
	
	
	//columns END 数据库字段结束
	
	private Awards awards;
	
	private AppUser appUser;
	
	private List<Oper> opers;
	
	private Integer isAttr;
	private Integer isColl;
	private Integer activityUserId;
	
	
	private Integer height;
	private Integer width;
	
	private String osType;
	
	
	//concstructor
	@WhereSQL(sql="height=:JoinActivity_height")
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	
	
	@WhereSQL(sql="osType=:JoinActivity_osType")
	public String getOsType() {
		return osType;
	}
	public void setOsType(String osType) {
		this.osType = osType;
	}
	@WhereSQL(sql="width=:JoinActivity_width")
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public JoinActivity(){
	}
	@Transient
	public Integer getIsAttr() {
		return isAttr;
	}

	public void setIsAttr(Integer isAttr) {
		this.isAttr = isAttr;
	}

	
	@Transient
	public Integer getActivityUserId() {
		return activityUserId;
	}
	public void setActivityUserId(Integer activityUserId) {
		this.activityUserId = activityUserId;
	}
	@Transient
	public Integer getIsColl() {
		return isColl;
	}

	public void setIsColl(Integer isColl) {
		this.isColl = isColl;
	}
	public JoinActivity(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	@WhereSQL(sql="activityId=:JoinActivity_activityId")
	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	@Id
     @WhereSQL(sql="id=:JoinActivity_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
	@Transient
	public List<Oper> getOpers() {
		return opers;
	}

	public void setOpers(List<Oper> opers) {
		this.opers = opers;
	}

	@Transient
	public Awards getAwards() {
		return awards;
	}

	public void setAwards(Awards awards) {
		this.awards = awards;
	}

	@Transient
     public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@WhereSQL(sql="type=:JoinActivity_type")
	public java.lang.Integer getType() {
		return this.type;
	}
	public void setImage(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.image = value;
	}
	
     @WhereSQL(sql="image=:JoinActivity_image")
	public java.lang.String getImage() {
		return this.image;
	}
	public void setMediaUrl(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.mediaUrl = value;
	}
	
     @WhereSQL(sql="mediaUrl=:JoinActivity_mediaUrl")
	public java.lang.String getMediaUrl() {
		return this.mediaUrl;
	}
	public void setMediaImage(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.mediaImage = value;
	}
	
     @WhereSQL(sql="mediaImage=:JoinActivity_mediaImage")
	public java.lang.String getMediaImage() {
		return this.mediaImage;
	}
	public void setContent(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.content = value;
	}
	
     @WhereSQL(sql="content=:JoinActivity_content")
	public java.lang.String getContent() {
		return this.content;
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
     @WhereSQL(sql="createTime=:JoinActivity_createTime")
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
     @WhereSQL(sql="userId=:JoinActivity_userId")
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	public void setAwardId(java.lang.Integer value) {
		this.awardId = value;
	}
	
     @WhereSQL(sql="awardId=:JoinActivity_awardId")
	public java.lang.Integer getAwardId() {
		return this.awardId;
	}
	public void setTopCount(java.lang.Integer value) {
		this.topCount = value;
	}
	
     @WhereSQL(sql="topCount=:JoinActivity_topCount")
	public java.lang.Integer getTopCount() {
		return this.topCount;
	}
	public void setCommentCount(java.lang.Integer value) {
		this.commentCount = value;
	}
	
     @WhereSQL(sql="commentCount=:JoinActivity_commentCount")
	public java.lang.Integer getCommentCount() {
		return this.commentCount;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("1图片 2视频[").append(getType()).append("],")
			.append("图片[").append(getImage()).append("],")
			.append("视频url[").append(getMediaUrl()).append("],")
			.append("视频封面[").append(getMediaImage()).append("],")
			.append("内容[").append(getContent()).append("],")
			.append("创建时间[").append(getCreateTime()).append("],")
			.append("参与人的userId[").append(getUserId()).append("],")
			.append("奖项id[").append(getAwardId()).append("],")
			.append("点赞次数[").append(getTopCount()).append("],")
			.append("评论次数[").append(getCommentCount()).append("],")
			.append("高度[").append(getHeight()).append("],")
			.append("宽度[").append(getWidth()).append("],")
			.append("操作系统[").append(getOsType()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof JoinActivity == false) return false;
		if(this == obj) return true;
		JoinActivity other = (JoinActivity)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
