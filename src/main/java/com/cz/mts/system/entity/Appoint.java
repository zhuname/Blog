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
 * @version  2017-07-06 14:42:38
 * @see com.cz.mts.system.entity.Appoint
 */
@Table(name="t_appoint")
public class Appoint  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "预约表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ITEMID = "红包id";
	public static final String ALIAS_MONEY = "money";
	public static final String ALIAS_PHONE = "预约电话";
	public static final String ALIAS_PAYTYPE = "支付方式：1支付宝 2微信 3余额支付";
	public static final String ALIAS_USERID = "预约人id";
	public static final String ALIAS_PAYTIME = "支付时间";
	public static final String ALIAS_PAYMONEY = "支付金额";
	public static final String ALIAS_WXCODE = "wxCode";
	public static final String ALIAS_TRADENO = "tradeNo";
	public static final String ALIAS_STATUS = "0 待支付 1未兑换2已兑换";
	public static final String ALIAS_CREATETIME = "createTime";
	public static final String ALIAS_CHANGETIME = "changeTime";
	public static final String ALIAS_CODE = "订单编码";
	public static final String ALIAS_CARDCODE = "兑换券编码";
	public static final String ALIAS_TYPE = "1海报 2视频";
	public static final String ALIAS_PACKAGEUSERID = "红包发布人id";
    */
	//date formats
	//public static final String FORMAT_PAYTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_CHANGETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 红包id
	 */
	private java.lang.Integer itemId;
	/**
	 * money
	 */
	private java.lang.Double money;
	/**
	 * 预约电话
	 */
	private java.lang.String phone;
	/**
	 * 支付方式：1支付宝 2微信 3余额支付
	 */
	private java.lang.Integer payType;
	/**
	 * 预约人id
	 */
	private java.lang.Integer userId;
	/**
	 * 支付时间
	 */
	private java.util.Date payTime;
	/**
	 * 支付金额
	 */
	private java.lang.Double payMoney;
	/**
	 * wxCode
	 */
	private java.lang.String wxCode;
	/**
	 * tradeNo
	 */
	private java.lang.String tradeNo;
	/**
	 * 0 待支付 1未兑换2已兑换
	 */
	private java.lang.Integer status;
	/**
	 * createTime
	 */
	private java.util.Date createTime;
	/**
	 * changeTime
	 */
	private java.util.Date changeTime;
	/**
	 * 订单编码
	 */
	private java.lang.String code;
	/**
	 * 兑换券编码
	 */
	private java.lang.String cardCode;
	/**
	 * 1海报 2视频
	 */
	private java.lang.Integer type;
	/**
	 * 红包发布人id
	 */
	private java.lang.Integer packageUserId;
	//columns END 数据库字段结束
	
	private PosterPackage posterPackage;
	
	private MediaPackage mediaPackage;
	
	private AppUser appUser;
	
	private String title;
	
	private Integer appointCount;
	private Double appintMoney;
	
	
	private String osType;
	
	
	//concstructor

	
	@Transient
	public Integer getAppointCount() {
		return appointCount;
	}

	public void setAppointCount(Integer appointCount) {
		this.appointCount = appointCount;
	}

	@Transient
	public Double getAppintMoney() {
		return appintMoney;
	}

	public void setAppintMoney(Double appintMoney) {
		this.appintMoney = appintMoney;
	}

	@Transient
	public AppUser getAppUser() {
		return appUser;
	}

	@Transient
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public Appoint(){
	}

	@Transient
	public PosterPackage getPosterPackage() {
		return posterPackage;
	}

	public void setPosterPackage(PosterPackage posterPackage) {
		this.posterPackage = posterPackage;
	}

	@Transient
	public MediaPackage getMediaPackage() {
		return mediaPackage;
	}

	public void setMediaPackage(MediaPackage mediaPackage) {
		this.mediaPackage = mediaPackage;
	}

	public Appoint(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:Appoint_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setItemId(java.lang.Integer value) {
		this.itemId = value;
	}
	
	
	 @WhereSQL(sql="osType=:Appoint_osType")
     public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	@WhereSQL(sql="itemId=:Appoint_itemId")
	public java.lang.Integer getItemId() {
		return this.itemId;
	}
	public void setMoney(java.lang.Double value) {
		this.money = value;
	}
	
     @WhereSQL(sql="money=:Appoint_money")
	public java.lang.Double getMoney() {
		return this.money;
	}
	public void setPhone(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.phone = value;
	}
	
     @WhereSQL(sql="phone=:Appoint_phone")
	public java.lang.String getPhone() {
		return this.phone;
	}
	public void setPayType(java.lang.Integer value) {
		this.payType = value;
	}
	
     @WhereSQL(sql="payType=:Appoint_payType")
	public java.lang.Integer getPayType() {
		return this.payType;
	}
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
     @WhereSQL(sql="userId=:Appoint_userId")
	public java.lang.Integer getUserId() {
		return this.userId;
	}
		/*
	public String getpayTimeString() {
		return DateUtils.convertDate2String(FORMAT_PAYTIME, getpayTime());
	}
	public void setpayTimeString(String value) throws ParseException{
		setpayTime(DateUtils.convertString2Date(FORMAT_PAYTIME,value));
	}*/
	
	public void setPayTime(java.util.Date value) {
		this.payTime = value;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
     @WhereSQL(sql="payTime=:Appoint_payTime")
	public java.util.Date getPayTime() {
		return this.payTime;
	}
	public void setPayMoney(java.lang.Double value) {
		this.payMoney = value;
	}
	
     @WhereSQL(sql="payMoney=:Appoint_payMoney")
	public java.lang.Double getPayMoney() {
		return this.payMoney;
	}
	public void setWxCode(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.wxCode = value;
	}
	
     @WhereSQL(sql="wxCode=:Appoint_wxCode")
	public java.lang.String getWxCode() {
		return this.wxCode;
	}
	public void setTradeNo(java.lang.String value) {
		this.tradeNo = value;
	}
	
     @WhereSQL(sql="tradeNo=:Appoint_tradeNo")
	public java.lang.String getTradeNo() {
		return this.tradeNo;
	}
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
     @WhereSQL(sql="status=:Appoint_status")
	public java.lang.Integer getStatus() {
		return this.status;
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
     @WhereSQL(sql="createTime=:Appoint_createTime")
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
		/*
	public String getchangeTimeString() {
		return DateUtils.convertDate2String(FORMAT_CHANGETIME, getchangeTime());
	}
	public void setchangeTimeString(String value) throws ParseException{
		setchangeTime(DateUtils.convertString2Date(FORMAT_CHANGETIME,value));
	}*/
	
	public void setChangeTime(java.util.Date value) {
		this.changeTime = value;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
     @WhereSQL(sql="changeTime=:Appoint_changeTime")
	public java.util.Date getChangeTime() {
		return this.changeTime;
	}
	public void setCode(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.code = value;
	}
	
     @WhereSQL(sql="code=:Appoint_code")
	public java.lang.String getCode() {
		return this.code;
	}
	public void setCardCode(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.cardCode = value;
	}
	
     @WhereSQL(sql="cardCode=:Appoint_cardCode")
	public java.lang.String getCardCode() {
		return this.cardCode;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
     @WhereSQL(sql="type=:Appoint_type")
	public java.lang.Integer getType() {
		return this.type;
	}
	public void setPackageUserId(java.lang.Integer value) {
		this.packageUserId = value;
	}
	
     @WhereSQL(sql="packageUserId=:Appoint_packageUserId")
	public java.lang.Integer getPackageUserId() {
		return this.packageUserId;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("红包id[").append(getItemId()).append("],")
			.append("money[").append(getMoney()).append("],")
			.append("预约电话[").append(getPhone()).append("],")
			.append("支付方式：1支付宝 2微信 3余额支付[").append(getPayType()).append("],")
			.append("预约人id[").append(getUserId()).append("],")
			.append("支付时间[").append(getPayTime()).append("],")
			.append("支付金额[").append(getPayMoney()).append("],")
			.append("wxCode[").append(getWxCode()).append("],")
			.append("tradeNo[").append(getTradeNo()).append("],")
			.append("0 待支付 1未兑换2已兑换[").append(getStatus()).append("],")
			.append("createTime[").append(getCreateTime()).append("],")
			.append("changeTime[").append(getChangeTime()).append("],")
			.append("订单编码[").append(getCode()).append("],")
			.append("兑换券编码[").append(getCardCode()).append("],")
			.append("1海报 2视频[").append(getType()).append("],")
			.append("红包发布人id[").append(getPackageUserId()).append("],")
			.append("操作系统[").append(getOsType()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Appoint == false) return false;
		if(this == obj) return true;
		Appoint other = (Appoint)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
