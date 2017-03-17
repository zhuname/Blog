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
 * @version  2017-02-24 15:17:29
 * @see com.cz.mts.system.entity.Withdraw
 */
@Table(name="t_withdraw")
public class Withdraw  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "提现表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USERID = "用户id";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_OPERTIME = "审核时间";
	public static final String ALIAS_STATUS = "状态：1申请中   2通过   3失败";
	public static final String ALIAS_REASON = "失败原因";
	public static final String ALIAS_MONEY = "提现金额";
	public static final String ALIAS_FACTORAGE = "手续费";
	public static final String ALIAS_REALMONEY = "实际到账金额";
	public static final String ALIAS_BRANCHBANK = "开户行";
	public static final String ALIAS_OWNERNAME = "户主名称/真实姓名";
	public static final String ALIAS_OWNERPHONE = "户主手机号";
	public static final String ALIAS_CARDNUM = "卡号/支付宝账号";
	public static final String ALIAS_WITHDRAWTYPE = "1银行 2支付宝";
    */
	//date formats
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_OPERTIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 用户id
	 */
	private java.lang.Integer userId;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 审核时间
	 */
	private java.util.Date operTime;
	/**
	 * 状态：1申请中   2通过   3失败
	 */
	private java.lang.Integer status;
	/**
	 * 失败原因
	 */
	private java.lang.String reason;
	/**
	 * 提现金额
	 */
	private java.lang.Double money;
	/**
	 * 手续费
	 */
	private java.lang.Double factorage;
	/**
	 * 实际到账金额
	 */
	private java.lang.Double realMoney;
	/**
	 * 开户行
	 */
	private java.lang.String branchBank;
	/**
	 * 户主名称/真实姓名
	 */
	private java.lang.String ownerName;
	/**
	 * 户主手机号
	 */
	private java.lang.String ownerPhone;
	/**
	 * 卡号/支付宝账号
	 */
	private java.lang.String cardNum;
	/**
	 * 1银行 2支付宝
	 */
	private java.lang.Integer withdrawType;
	
	private String bankName;
	
	private java.lang.String osType;
	
	private String userName;
	
	
	@Transient
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@WhereSQL(sql="osType=:MediaPackage_osType")
	public java.lang.String getOsType() {
		return osType;
	}

	public void setOsType(java.lang.String osType) {
		this.osType = osType;
	}
	
	
	//columns END 数据库字段结束
	
	//concstructor

	public Withdraw(){
	}

	public Withdraw(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@WhereSQL(sql="bankName=:Withdraw_bankName")
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Id
     @WhereSQL(sql="id=:Withdraw_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
     @WhereSQL(sql="userId=:Withdraw_userId")
	public java.lang.Integer getUserId() {
		return this.userId;
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
     @WhereSQL(sql="createTime=:Withdraw_createTime")
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
		/*
	public String getoperTimeString() {
		return DateUtils.convertDate2String(FORMAT_OPERTIME, getoperTime());
	}
	public void setoperTimeString(String value) throws ParseException{
		setoperTime(DateUtils.convertString2Date(FORMAT_OPERTIME,value));
	}*/
	
	public void setOperTime(java.util.Date value) {
		this.operTime = value;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
     @WhereSQL(sql="operTime=:Withdraw_operTime")
	public java.util.Date getOperTime() {
		return this.operTime;
	}
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
     @WhereSQL(sql="status=:Withdraw_status")
	public java.lang.Integer getStatus() {
		return this.status;
	}
	public void setReason(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.reason = value;
	}
	
     @WhereSQL(sql="reason=:Withdraw_reason")
	public java.lang.String getReason() {
		return this.reason;
	}
	public void setMoney(java.lang.Double value) {
		this.money = value;
	}
	
     @WhereSQL(sql="money=:Withdraw_money")
	public java.lang.Double getMoney() {
		return this.money;
	}
	public void setFactorage(java.lang.Double value) {
		this.factorage = value;
	}
	
     @WhereSQL(sql="factorage=:Withdraw_factorage")
	public java.lang.Double getFactorage() {
		return this.factorage;
	}
	public void setRealMoney(java.lang.Double value) {
		this.realMoney = value;
	}
	
     @WhereSQL(sql="realMoney=:Withdraw_realMoney")
	public java.lang.Double getRealMoney() {
		return this.realMoney;
	}
	public void setBranchBank(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.branchBank = value;
	}
	
     @WhereSQL(sql="branchBank=:Withdraw_branchBank")
	public java.lang.String getBranchBank() {
		return this.branchBank;
	}
	public void setOwnerName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.ownerName = value;
	}
	
     @WhereSQL(sql="ownerName=:Withdraw_ownerName")
	public java.lang.String getOwnerName() {
		return this.ownerName;
	}
	public void setOwnerPhone(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.ownerPhone = value;
	}
	
     @WhereSQL(sql="ownerPhone=:Withdraw_ownerPhone")
	public java.lang.String getOwnerPhone() {
		return this.ownerPhone;
	}
	public void setCardNum(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.cardNum = value;
	}
	
     @WhereSQL(sql="cardNum=:Withdraw_cardNum")
	public java.lang.String getCardNum() {
		return this.cardNum;
	}
	public void setWithdrawType(java.lang.Integer value) {
		this.withdrawType = value;
	}
	
     @WhereSQL(sql="withdrawType=:Withdraw_withdrawType")
	public java.lang.Integer getWithdrawType() {
		return this.withdrawType;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("用户id[").append(getUserId()).append("],")
			.append("创建时间[").append(getCreateTime()).append("],")
			.append("审核时间[").append(getOperTime()).append("],")
			.append("状态：1申请中   2通过   3失败[").append(getStatus()).append("],")
			.append("失败原因[").append(getReason()).append("],")
			.append("提现金额[").append(getMoney()).append("],")
			.append("手续费[").append(getFactorage()).append("],")
			.append("实际到账金额[").append(getRealMoney()).append("],")
			.append("开户行[").append(getBranchBank()).append("],")
			.append("户主名称/真实姓名[").append(getOwnerName()).append("],")
			.append("户主手机号[").append(getOwnerPhone()).append("],")
			.append("卡号/支付宝账号[").append(getCardNum()).append("],")
			.append("1银行 2支付宝[").append(getWithdrawType()).append("],")
			.append("操作系统").append(getOsType()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Withdraw == false) return false;
		if(this == obj) return true;
		Withdraw other = (Withdraw)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
