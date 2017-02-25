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
 * @see com.cz.mts.system.entity.LunboPic
 */
@Table(name="t_lunbo_pic")
public class LunboPic  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "轮播图表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_IMAGE = "图片";
	public static final String ALIAS_TYPE = "1.url 2红包 3视频 4卡券";
	public static final String ALIAS_CREATETIME = "createTime";
	public static final String ALIAS_ITEMID = "对应的项目id，如type=1则会在商品列表内获取";
	public static final String ALIAS_POSITION = "1红包 2视频 3卡券";
	public static final String ALIAS_URL = "url";
	public static final String ALIAS_CITYIDS = "如果为空，则是全国，","区分";
    */
	//date formats
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 图片
	 */
	private java.lang.String image;
	/**
	 * 1.url 2红包 3视频 4卡券
	 */
	private java.lang.Integer type;
	/**
	 * createTime
	 */
	private java.util.Date createTime;
	/**
	 * 对应的项目id，如type=1则会在商品列表内获取
	 */
	private java.lang.Integer itemId;
	/**
	 * 1红包 2视频 3卡券
	 */
	private java.lang.Integer position;
	/**
	 * url
	 */
	private java.lang.String url;
	/**
	 * 如果为空，则是全国，","区分
	 */
	private java.lang.String cityIds;
	//columns END 数据库字段结束
	
	//concstructor

	public LunboPic(){
	}

	public LunboPic(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:LunboPic_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setImage(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.image = value;
	}
	
     @WhereSQL(sql="image=:LunboPic_image")
	public java.lang.String getImage() {
		return this.image;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
     @WhereSQL(sql="type=:LunboPic_type")
	public java.lang.Integer getType() {
		return this.type;
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
	
     @WhereSQL(sql="createTime=:LunboPic_createTime")
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setItemId(java.lang.Integer value) {
		this.itemId = value;
	}
	
     @WhereSQL(sql="itemId=:LunboPic_itemId")
	public java.lang.Integer getItemId() {
		return this.itemId;
	}
	public void setPosition(java.lang.Integer value) {
		this.position = value;
	}
	
     @WhereSQL(sql="position=:LunboPic_position")
	public java.lang.Integer getPosition() {
		return this.position;
	}
	public void setUrl(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.url = value;
	}
	
     @WhereSQL(sql="url=:LunboPic_url")
	public java.lang.String getUrl() {
		return this.url;
	}
	public void setCityIds(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.cityIds = value;
	}
	
     @WhereSQL(sql="cityIds=:LunboPic_cityIds")
	public java.lang.String getCityIds() {
		return this.cityIds;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("图片[").append(getImage()).append("],")
			.append("1.url 2红包 3视频 4卡券[").append(getType()).append("],")
			.append("createTime[").append(getCreateTime()).append("],")
			.append("对应的项目id，如type=1则会在商品列表内获取[").append(getItemId()).append("],")
			.append("1红包 2视频 3卡券[").append(getPosition()).append("],")
			.append("url[").append(getUrl()).append("],")
			.append("如果为空，则是全国，"+"区分[").append(getCityIds()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LunboPic == false) return false;
		if(this == obj) return true;
		LunboPic other = (LunboPic)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
