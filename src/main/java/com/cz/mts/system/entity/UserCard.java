package com.cz.mts.system.entity;

import java.text.ParseException;

import javax.persistence.Id;
import javax.persistence.Table;

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
 * @version  2017-02-24 15:17:29
 * @see com.cz.mts.system.entity.UserCard
 */
@Table(name="t_user_card")
public class UserCard  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "卡券兑换表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_CARDID = "卡券id";
	public static final String ALIAS_USERID = "用户id";
	public static final String ALIAS_STATUS = "状态：0 待支付 1未兑换2已兑换3已过期";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_CHANGETIME = "兑换时间";
	public static final String ALIAS_CODE = "订单编号";
	public static final String ALIAS_CARDCODE = "兑换编码";
	public static final String ALIAS_ADRESS = "兑换地址";
	public static final String ALIAS_PHONE = "联系电话";
	public static final String ALIAS_SUMMONEY = "总金额";
	public static final String ALIAS_PAYMONEY = "支付金额";
	public static final String ALIAS_PAYTYPE = "支付类型：1支付宝 2微信 3余额支付";
	public static final String ALIAS_EXPTIME = "到期时间";
	public static final String ALIAS_PAYTIME = "支付时间";
	public static final String ALIAS_WXCODE = "微信交易流水号";
	public static final String ALIAS_TRADENO = "交易流水号";
    */
	//date formats
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_CHANGETIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_EXPTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_PAYTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 卡券id
	 */
	private java.lang.Integer cardId;
	/**
	 * 用户id
	 */
	private java.lang.Integer userId;
	/**
	 * 状态：0 待支付 1未兑换2已兑换3已过期
	 */
	private java.lang.Integer status;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 兑换时间
	 */
	private java.util.Date changeTime;
	/**
	 * 订单编号
	 */
	private java.lang.String code;
	/**
	 * 兑换编码
	 */
	private java.lang.String cardCode;
	/**
	 * 兑换地址
	 */
	private java.lang.String adress;
	/**
	 * 联系电话
	 */
	private java.lang.String phone;
	/**
	 * 总金额
	 */
	private java.lang.Double sumMoney;
	/**
	 * 支付金额
	 */
	private java.lang.Double payMoney;
	/**
	 * 支付类型：1支付宝 2微信 3余额支付
	 */
	private java.lang.Integer payType;
	/**
	 * 到期时间
	 */
	private java.util.Date expTime;
	/**
	 * 支付时间
	 */
	private java.util.Date payTime;
	/**
	 * 微信交易流水号
	 */
	private java.lang.String wxCode;
	/**
	 * 交易流水号
	 */
	private java.lang.String tradeNo;
	//columns END 数据库字段结束
	
	//concstructor

	public UserCard(){
	}

	public UserCard(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:UserCard_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setCardId(java.lang.Integer value) {
		this.cardId = value;
	}
	
     @WhereSQL(sql="cardId=:UserCard_cardId")
	public java.lang.Integer getCardId() {
		return this.cardId;
	}
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
     @WhereSQL(sql="userId=:UserCard_userId")
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
     @WhereSQL(sql="status=:UserCard_status")
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
     @WhereSQL(sql="createTime=:UserCard_createTime")
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
     @WhereSQL(sql="changeTime=:UserCard_changeTime")
	public java.util.Date getChangeTime() {
		return this.changeTime;
	}
	public void setCode(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.code = value;
	}
	
     @WhereSQL(sql="code=:UserCard_code")
	public java.lang.String getCode() {
		return this.code;
	}
	public void setCardCode(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.cardCode = value;
	}
	
     @WhereSQL(sql="cardCode=:UserCard_cardCode")
	public java.lang.String getCardCode() {
		return this.cardCode;
	}
	public void setAdress(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.adress = value;
	}
	
     @WhereSQL(sql="adress=:UserCard_adress")
	public java.lang.String getAdress() {
		return this.adress;
	}
	public void setPhone(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.phone = value;
	}
	
     @WhereSQL(sql="phone=:UserCard_phone")
	public java.lang.String getPhone() {
		return this.phone;
	}
	public void setSumMoney(java.lang.Double value) {
		this.sumMoney = value;
	}
	
     @WhereSQL(sql="sumMoney=:UserCard_sumMoney")
	public java.lang.Double getSumMoney() {
		return this.sumMoney;
	}
	public void setPayMoney(java.lang.Double value) {
		this.payMoney = value;
	}
	
     @WhereSQL(sql="payMoney=:UserCard_payMoney")
	public java.lang.Double getPayMoney() {
		return this.payMoney;
	}
	public void setPayType(java.lang.Integer value) {
		this.payType = value;
	}
	
     @WhereSQL(sql="payType=:UserCard_payType")
	public java.lang.Integer getPayType() {
		return this.payType;
	}
		/*
	public String getexpTimeString() {
		return DateUtils.convertDate2String(FORMAT_EXPTIME, getexpTime());
	}
	public void setexpTimeString(String value) throws ParseException{
		setexpTime(DateUtils.convertString2Date(FORMAT_EXPTIME,value));
	}*/
	
	public void setExpTime(java.util.Date value) {
		this.expTime = value;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
     @WhereSQL(sql="expTime=:UserCard_expTime")
	public java.util.Date getExpTime() {
		return this.expTime;
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
     @WhereSQL(sql="payTime=:UserCard_payTime")
	public java.util.Date getPayTime() {
		return this.payTime;
	}
	public void setWxCode(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.wxCode = value;
	}
	
     @WhereSQL(sql="wxCode=:UserCard_wxCode")
	public java.lang.String getWxCode() {
		return this.wxCode;
	}
	public void setTradeNo(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.tradeNo = value;
	}
	
     @WhereSQL(sql="tradeNo=:UserCard_tradeNo")
	public java.lang.String getTradeNo() {
		return this.tradeNo;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("卡券id[").append(getCardId()).append("],")
			.append("用户id[").append(getUserId()).append("],")
			.append("状态：0 待支付 1未兑换2已兑换3已过期[").append(getStatus()).append("],")
			.append("创建时间[").append(getCreateTime()).append("],")
			.append("兑换时间[").append(getChangeTime()).append("],")
			.append("订单编号[").append(getCode()).append("],")
			.append("兑换编码[").append(getCardCode()).append("],")
			.append("兑换地址[").append(getAdress()).append("],")
			.append("联系电话[").append(getPhone()).append("],")
			.append("总金额[").append(getSumMoney()).append("],")
			.append("支付金额[").append(getPayMoney()).append("],")
			.append("支付类型：1支付宝 2微信 3余额支付[").append(getPayType()).append("],")
			.append("到期时间[").append(getExpTime()).append("],")
			.append("支付时间[").append(getPayTime()).append("],")
			.append("微信交易流水号[").append(getWxCode()).append("],")
			.append("交易流水号[").append(getTradeNo()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserCard == false) return false;
		if(this == obj) return true;
		UserCard other = (UserCard)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
