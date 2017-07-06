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
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-07-06 09:32:38
 * @see com.cz.mts.system.entity.CityCircle
 */
@Table(name="t_city_circle")
public class CityCircle  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "同城圈打赏表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ITEMID = "同城圈id";
	public static final String ALIAS_MONEY = "打赏金额";
	public static final String ALIAS_CONTENT = "打赏留言";
	public static final String ALIAS_PAYTYPE = "支付方式：1支付宝 2微信 3余额支付";
	public static final String ALIAS_USERID = "打赏用户id";
	public static final String ALIAS_PAYTIME = "支付时间";
	public static final String ALIAS_PAYMONEY = "支付金额";
	public static final String ALIAS_WXCODE = "wxCode";
	public static final String ALIAS_TRADENO = "tradeNo";
	public static final String ALIAS_STATUS = "0 待支付 1已支付";
	public static final String ALIAS_CREATETIME = "createTime";
	public static final String ALIAS_CODE = "订单编号";
    */
	//date formats
	//public static final String FORMAT_PAYTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 同城圈id
	 */
	private java.lang.Integer itemId;
	/**
	 * 打赏金额
	 */
	private java.lang.Double money;
	/**
	 * 打赏留言
	 */
	private java.lang.String content;
	/**
	 * 支付方式：1支付宝 2微信 3余额支付
	 */
	private java.lang.Integer payType;
	/**
	 * 打赏用户id
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
	 * 0 待支付 1已支付
	 */
	private java.lang.Integer status;
	/**
	 * createTime
	 */
	private java.util.Date createTime;
	/**
	 * 订单编号
	 */
	private java.lang.String code;
	//columns END 数据库字段结束
	
	private AppUser appUser;
	
	//concstructor

	public CityCircle(){
	}

	public CityCircle(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	@Transient
	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	@Id
     @WhereSQL(sql="id=:CityCircle_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setItemId(java.lang.Integer value) {
		this.itemId = value;
	}
	
     @WhereSQL(sql="itemId=:CityCircle_itemId")
	public java.lang.Integer getItemId() {
		return this.itemId;
	}
	public void setMoney(java.lang.Double value) {
		this.money = value;
	}
	
     @WhereSQL(sql="money=:CityCircle_money")
	public java.lang.Double getMoney() {
		return this.money;
	}
	public void setContent(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.content = value;
	}
	
     @WhereSQL(sql="content=:CityCircle_content")
	public java.lang.String getContent() {
		return this.content;
	}
	public void setPayType(java.lang.Integer value) {
		this.payType = value;
	}
	
     @WhereSQL(sql="payType=:CityCircle_payType")
	public java.lang.Integer getPayType() {
		return this.payType;
	}
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
     @WhereSQL(sql="userId=:CityCircle_userId")
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
	
     @WhereSQL(sql="payTime=:CityCircle_payTime")
	public java.util.Date getPayTime() {
		return this.payTime;
	}
	public void setPayMoney(java.lang.Double value) {
		this.payMoney = value;
	}
	
     @WhereSQL(sql="payMoney=:CityCircle_payMoney")
	public java.lang.Double getPayMoney() {
		return this.payMoney;
	}
	public void setWxCode(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.wxCode = value;
	}
	
     @WhereSQL(sql="wxCode=:CityCircle_wxCode")
	public java.lang.String getWxCode() {
		return this.wxCode;
	}
	public void setTradeNo(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.tradeNo = value;
	}
	
     @WhereSQL(sql="tradeNo=:CityCircle_tradeNo")
	public java.lang.String getTradeNo() {
		return this.tradeNo;
	}
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
     @WhereSQL(sql="status=:CityCircle_status")
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
	
     @WhereSQL(sql="createTime=:CityCircle_createTime")
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setCode(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.code = value;
	}
	
     @WhereSQL(sql="code=:CityCircle_code")
	public java.lang.String getCode() {
		return this.code;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("同城圈id[").append(getItemId()).append("],")
			.append("打赏金额[").append(getMoney()).append("],")
			.append("打赏留言[").append(getContent()).append("],")
			.append("支付方式：1支付宝 2微信 3余额支付[").append(getPayType()).append("],")
			.append("打赏用户id[").append(getUserId()).append("],")
			.append("支付时间[").append(getPayTime()).append("],")
			.append("支付金额[").append(getPayMoney()).append("],")
			.append("wxCode[").append(getWxCode()).append("],")
			.append("tradeNo[").append(getTradeNo()).append("],")
			.append("0 待支付 1已支付[").append(getStatus()).append("],")
			.append("createTime[").append(getCreateTime()).append("],")
			.append("订单编号[").append(getCode()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CityCircle == false) return false;
		if(this == obj) return true;
		CityCircle other = (CityCircle)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
