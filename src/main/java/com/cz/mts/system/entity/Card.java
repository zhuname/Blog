package com.cz.mts.system.entity;

import java.text.ParseException;
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
 * @version  2017-02-24 15:17:25
 * @see com.cz.mts.system.entity.Card
 */
@Table(name="t_card")
public class Card  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "卡券表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_IMAGES = "图片地址";
	public static final String ALIAS_CATERGORYID = "分类id";
	public static final String ALIAS_USERID = "用户id";
	public static final String ALIAS_TITLE = "标题";
	public static final String ALIAS_DESCR = "简介";
	public static final String ALIAS_CONVERTMONEY = "兑换金额";
	public static final String ALIAS_CONVERTNUM = "兑换数量";
	public static final String ALIAS_ADDRESS = "领取地址";
	public static final String ALIAS_LAT = "纬度";
	public static final String ALIAS_LOT = "经度";
	public static final String ALIAS_PHONE = "联系电话";
	public static final String ALIAS_ENDTIME = "到期时间";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_FAILTIME = "失败时间";
	public static final String ALIAS_SUCCTIME = "通过时间";
	public static final String ALIAS_FAILREASON = "失败原因";
	public static final String ALIAS_NUM = "剩余兑换次数";
	public static final String ALIAS_ISDEL = "是否删除： 0否 1是";
    */
	//date formats
	//public static final String FORMAT_ENDTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_FAILTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_SUCCTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 图片地址
	 */
	private java.lang.String images;
	/**
	 * 分类id
	 */
	private java.lang.Integer catergoryId;
	/**
	 * 用户id
	 */
	private java.lang.Integer userId;
	/**
	 * 标题
	 */
	private java.lang.String title;
	/**
	 * 简介
	 */
	private java.lang.String descr;
	/**
	 * 兑换金额
	 */
	private java.lang.Double convertMoney;
	/**
	 * 兑换数量
	 */
	private java.lang.Integer convertNum;
	/**
	 * 领取地址
	 */
	private java.lang.String address;
	/**
	 * 纬度
	 */
	private java.lang.Double lat;
	/**
	 * 经度
	 */
	private java.lang.Double lot;
	/**
	 * 联系电话
	 */
	private java.lang.String phone;
	/**
	 * 到期时间
	 */
	private java.util.Date endTime;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 失败时间
	 */
	private java.util.Date failTime;
	/**
	 * 通过时间
	 */
	private java.util.Date succTime;
	/**
	 * 失败原因
	 */
	private java.lang.String failReason;
	/**
	 * 剩余兑换次数
	 */
	private java.lang.Integer num;
	/**
	 * 是否删除： 0否 1是
	 */
	private java.lang.Integer isDel;
	
	private java.lang.Integer status;
	
	
	private java.lang.String location;
	
	
	//columns END 数据库字段结束
	@WhereSQL(sql="location=:Card_location")
	public java.lang.String getLocation() {
		return location;
	}

	public void setLocation(java.lang.String location) {
		this.location = location;
	}

	private AppUser appUser;
	private List<UserMedal> userMedals;
	
	
	private List<UserCard> userCards;
	
	//concstructor
	@Transient
	public List<UserCard> getUserCards() {
		return userCards;
	}

	public void setUserCards(List<UserCard> userCards) {
		this.userCards = userCards;
	}

	@Transient
	public List<UserMedal> getUserMedals() {
		return userMedals;
	}

	public void setUserMedals(List<UserMedal> userMedals) {
		this.userMedals = userMedals;
	}

	@Transient
	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public Card(){
	}

	public Card(
		java.lang.Integer id
	){
		this.id = id;
	}

	
	@WhereSQL(sql="status=:Card_status")
	public java.lang.Integer getStatus() {
		return status;
	}

	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:Card_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setImages(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.images = value;
	}
	
     @WhereSQL(sql="images=:Card_images")
	public java.lang.String getImages() {
		return this.images;
	}
	public void setCatergoryId(java.lang.Integer value) {
		this.catergoryId = value;
	}
	
     @WhereSQL(sql="catergoryId=:Card_catergoryId")
	public java.lang.Integer getCatergoryId() {
		return this.catergoryId;
	}
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
     @WhereSQL(sql="userId=:Card_userId")
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	public void setTitle(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.title = value;
	}
	
     @WhereSQL(sql="title=:Card_title")
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setDescr(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.descr = value;
	}
	
     @WhereSQL(sql="descr=:Card_descr")
	public java.lang.String getDescr() {
		return this.descr;
	}
	public void setConvertMoney(java.lang.Double value) {
		this.convertMoney = value;
	}
	
     @WhereSQL(sql="convertMoney=:Card_convertMoney")
	public java.lang.Double getConvertMoney() {
		return this.convertMoney;
	}
	public void setConvertNum(java.lang.Integer value) {
		this.convertNum = value;
	}
	
     @WhereSQL(sql="convertNum=:Card_convertNum")
	public java.lang.Integer getConvertNum() {
		return this.convertNum;
	}
	public void setAddress(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.address = value;
	}
	
     @WhereSQL(sql="address=:Card_address")
	public java.lang.String getAddress() {
		return this.address;
	}
	public void setLat(java.lang.Double value) {
		this.lat = value;
	}
	
     @WhereSQL(sql="lat=:Card_lat")
	public java.lang.Double getLat() {
		return this.lat;
	}
	public void setLot(java.lang.Double value) {
		this.lot = value;
	}
	
     @WhereSQL(sql="lot=:Card_lot")
	public java.lang.Double getLot() {
		return this.lot;
	}
	public void setPhone(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.phone = value;
	}
	
     @WhereSQL(sql="phone=:Card_phone")
	public java.lang.String getPhone() {
		return this.phone;
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
     @WhereSQL(sql="endTime=:Card_endTime")
	public java.util.Date getEndTime() {
		return this.endTime;
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
     @WhereSQL(sql="createTime=:Card_createTime")
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
		/*
	public String getfailTimeString() {
		return DateUtils.convertDate2String(FORMAT_FAILTIME, getfailTime());
	}
	public void setfailTimeString(String value) throws ParseException{
		setfailTime(DateUtils.convertString2Date(FORMAT_FAILTIME,value));
	}*/
	
	public void setFailTime(java.util.Date value) {
		this.failTime = value;
	}
	
     @WhereSQL(sql="failTime=:Card_failTime")
	public java.util.Date getFailTime() {
		return this.failTime;
	}
		/*
	public String getsuccTimeString() {
		return DateUtils.convertDate2String(FORMAT_SUCCTIME, getsuccTime());
	}
	public void setsuccTimeString(String value) throws ParseException{
		setsuccTime(DateUtils.convertString2Date(FORMAT_SUCCTIME,value));
	}*/
	
	public void setSuccTime(java.util.Date value) {
		this.succTime = value;
	}
	
     @WhereSQL(sql="succTime=:Card_succTime")
	public java.util.Date getSuccTime() {
		return this.succTime;
	}
	public void setFailReason(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.failReason = value;
	}
	
     @WhereSQL(sql="failReason=:Card_failReason")
	public java.lang.String getFailReason() {
		return this.failReason;
	}
	public void setNum(java.lang.Integer value) {
		this.num = value;
	}
	
     @WhereSQL(sql="num=:Card_num")
	public java.lang.Integer getNum() {
		return this.num;
	}
	public void setIsDel(java.lang.Integer value) {
		this.isDel = value;
	}
	
     @WhereSQL(sql="isDel=:Card_isDel")
	public java.lang.Integer getIsDel() {
		return this.isDel;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("图片地址[").append(getImages()).append("],")
			.append("分类id[").append(getCatergoryId()).append("],")
			.append("用户id[").append(getUserId()).append("],")
			.append("标题[").append(getTitle()).append("],")
			.append("简介[").append(getDescr()).append("],")
			.append("兑换金额[").append(getConvertMoney()).append("],")
			.append("兑换数量[").append(getConvertNum()).append("],")
			.append("领取地址[").append(getAddress()).append("],")
			.append("纬度[").append(getLat()).append("],")
			.append("经度[").append(getLot()).append("],")
			.append("联系电话[").append(getPhone()).append("],")
			.append("到期时间[").append(getEndTime()).append("],")
			.append("创建时间[").append(getCreateTime()).append("],")
			.append("失败时间[").append(getFailTime()).append("],")
			.append("通过时间[").append(getSuccTime()).append("],")
			.append("失败原因[").append(getFailReason()).append("],")
			.append("剩余兑换次数[").append(getNum()).append("],")
			.append("是否删除： 0否 1是[").append(getIsDel()).append("],")
			.append("状态：[").append(getStatus()).append("],")
			.append("定位地址：[").append(getLocation()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Card == false) return false;
		if(this == obj) return true;
		Card other = (Card)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
