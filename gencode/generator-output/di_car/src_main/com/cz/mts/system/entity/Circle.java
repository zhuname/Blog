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
 * @version  2017-07-13 14:27:38
 * @see com.cz.mts.system.entity.Circle
 */
@Table(name="t_circle")
public class Circle  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "城事圈表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_TYPE = "1图片 2视频";
	public static final String ALIAS_IMAGE = "图片";
	public static final String ALIAS_MEDIAURL = "视频url";
	public static final String ALIAS_CONTENT = "主题";
	public static final String ALIAS_MEDIAIMAGE = "视频封面";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_USERID = "用户id";
	public static final String ALIAS_AWARDID = "奖项id";
	public static final String ALIAS_TOPCOUNT = "点赞次数";
	public static final String ALIAS_COMMENTCOUNT = "评论次数";
	public static final String ALIAS_SUMMONEY = "打赏总金额";
	public static final String ALIAS_COUNT = "打赏次数";
	public static final String ALIAS_CITYID = "城市id";
	public static final String ALIAS_HEIGHT = "单张图片的高";
	public static final String ALIAS_WIDTH = "单张图片的宽";
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
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 用户id
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
	/**
	 * 打赏总金额
	 */
	private java.lang.Double sumMoney;
	/**
	 * 打赏次数
	 */
	private java.lang.Integer count;
	/**
	 * 城市id
	 */
	private java.lang.Integer cityId;
	/**
	 * 单张图片的高
	 */
	private java.lang.Integer height;
	/**
	 * 单张图片的宽
	 */
	private java.lang.Integer width;
	/**
	 * 操作系统
	 */
	private java.lang.String osType;
	//columns END 数据库字段结束
	
	//concstructor

	public Circle(){
	}

	public Circle(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:Circle_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
     @WhereSQL(sql="type=:Circle_type")
	public java.lang.Integer getType() {
		return this.type;
	}
	public void setImage(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.image = value;
	}
	
     @WhereSQL(sql="image=:Circle_image")
	public java.lang.String getImage() {
		return this.image;
	}
	public void setMediaUrl(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.mediaUrl = value;
	}
	
     @WhereSQL(sql="mediaUrl=:Circle_mediaUrl")
	public java.lang.String getMediaUrl() {
		return this.mediaUrl;
	}
	public void setContent(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.content = value;
	}
	
     @WhereSQL(sql="content=:Circle_content")
	public java.lang.String getContent() {
		return this.content;
	}
	public void setMediaImage(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.mediaImage = value;
	}
	
     @WhereSQL(sql="mediaImage=:Circle_mediaImage")
	public java.lang.String getMediaImage() {
		return this.mediaImage;
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
	
     @WhereSQL(sql="createTime=:Circle_createTime")
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
     @WhereSQL(sql="userId=:Circle_userId")
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	public void setAwardId(java.lang.Integer value) {
		this.awardId = value;
	}
	
     @WhereSQL(sql="awardId=:Circle_awardId")
	public java.lang.Integer getAwardId() {
		return this.awardId;
	}
	public void setTopCount(java.lang.Integer value) {
		this.topCount = value;
	}
	
     @WhereSQL(sql="topCount=:Circle_topCount")
	public java.lang.Integer getTopCount() {
		return this.topCount;
	}
	public void setCommentCount(java.lang.Integer value) {
		this.commentCount = value;
	}
	
     @WhereSQL(sql="commentCount=:Circle_commentCount")
	public java.lang.Integer getCommentCount() {
		return this.commentCount;
	}
	public void setSumMoney(java.lang.Double value) {
		this.sumMoney = value;
	}
	
     @WhereSQL(sql="sumMoney=:Circle_sumMoney")
	public java.lang.Double getSumMoney() {
		return this.sumMoney;
	}
	public void setCount(java.lang.Integer value) {
		this.count = value;
	}
	
     @WhereSQL(sql="count=:Circle_count")
	public java.lang.Integer getCount() {
		return this.count;
	}
	public void setCityId(java.lang.Integer value) {
		this.cityId = value;
	}
	
     @WhereSQL(sql="cityId=:Circle_cityId")
	public java.lang.Integer getCityId() {
		return this.cityId;
	}
	public void setHeight(java.lang.Integer value) {
		this.height = value;
	}
	
     @WhereSQL(sql="height=:Circle_height")
	public java.lang.Integer getHeight() {
		return this.height;
	}
	public void setWidth(java.lang.Integer value) {
		this.width = value;
	}
	
     @WhereSQL(sql="width=:Circle_width")
	public java.lang.Integer getWidth() {
		return this.width;
	}
	public void setOsType(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.osType = value;
	}
	
     @WhereSQL(sql="osType=:Circle_osType")
	public java.lang.String getOsType() {
		return this.osType;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("1图片 2视频[").append(getType()).append("],")
			.append("图片[").append(getImage()).append("],")
			.append("视频url[").append(getMediaUrl()).append("],")
			.append("主题[").append(getContent()).append("],")
			.append("视频封面[").append(getMediaImage()).append("],")
			.append("创建时间[").append(getCreateTime()).append("],")
			.append("用户id[").append(getUserId()).append("],")
			.append("奖项id[").append(getAwardId()).append("],")
			.append("点赞次数[").append(getTopCount()).append("],")
			.append("评论次数[").append(getCommentCount()).append("],")
			.append("打赏总金额[").append(getSumMoney()).append("],")
			.append("打赏次数[").append(getCount()).append("],")
			.append("城市id[").append(getCityId()).append("],")
			.append("单张图片的高[").append(getHeight()).append("],")
			.append("单张图片的宽[").append(getWidth()).append("],")
			.append("操作系统[").append(getOsType()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Circle == false) return false;
		if(this == obj) return true;
		Circle other = (Circle)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
