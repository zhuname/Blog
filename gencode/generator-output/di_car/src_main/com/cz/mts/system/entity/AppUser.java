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
 * @version  2017-02-24 15:17:23
 * @see com.cz.mts.system.entity.AppUser
 */
@Table(name="t_app_user")
public class AppUser  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "用户表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "昵称";
	public static final String ALIAS_PASSWORD = "密码";
	public static final String ALIAS_PHONE = "电话";
	public static final String ALIAS_QQNUM = "qq号";
	public static final String ALIAS_WXNUM = "微信号";
	public static final String ALIAS_SEX = "性别";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_HEADER = "头像";
	public static final String ALIAS_CITYNAME = "城市名称 ";
	public static final String ALIAS_SIGN = "个性签名";
	public static final String ALIAS_SINANUM = "新浪账号";
	public static final String ALIAS_BALANCE = "余额";
	public static final String ALIAS_FROZEBANLANCE = "冻结余额";
	public static final String ALIAS_OSTYPE = "操作系统";
	public static final String ALIAS_BANKNAME = "银行名称";
	public static final String ALIAS_BRANCHBANK = "开户行";
	public static final String ALIAS_OWNERNAME = "户主名称/真实姓名";
	public static final String ALIAS_OWNERPHONE = "户主手机号";
	public static final String ALIAS_CARDNUM = "卡号/支付宝账号";
	public static final String ALIAS_WITHDRAWTYPE = "1银行 2支付宝";
	public static final String ALIAS_CURRENTGETNUM = "当前可领取次数";
	public static final String ALIAS_CURRENTSHARENUM = "当前可分享次数";
	public static final String ALIAS_GETNUM = "可领取次数";
	public static final String ALIAS_SHARENUM = "可分享次数";
	public static final String ALIAS_ISCLOSEFEE = "是否关闭卡券手续费 0否 1是";
	public static final String ALIAS_ISUPDATE = "是否有信息更新 0否 1是";
	public static final String ALIAS_ISBLACK = "是否是黑名单 0否 1是";
    */
	//date formats
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 昵称
	 */
	private java.lang.String name;
	/**
	 * 密码
	 */
	private java.lang.String password;
	/**
	 * 电话
	 */
	private java.lang.String phone;
	/**
	 * qq号
	 */
	private java.lang.String qqNum;
	/**
	 * 微信号
	 */
	private java.lang.String wxNum;
	/**
	 * 性别
	 */
	private java.lang.String sex;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 头像
	 */
	private java.lang.String header;
	/**
	 * 城市名称 
	 */
	private java.lang.String cityName;
	/**
	 * 个性签名
	 */
	private java.lang.String sign;
	/**
	 * 新浪账号
	 */
	private java.lang.String sinaNum;
	/**
	 * 余额
	 */
	private java.lang.Double balance;
	/**
	 * 冻结余额
	 */
	private java.lang.Double frozeBanlance;
	/**
	 * 操作系统
	 */
	private java.lang.String osType;
	/**
	 * 银行名称
	 */
	private java.lang.String bankName;
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
	/**
	 * 当前可领取次数
	 */
	private java.lang.Integer currentGetNum;
	/**
	 * 当前可分享次数
	 */
	private java.lang.Integer currentShareNum;
	/**
	 * 可领取次数
	 */
	private java.lang.Integer getNum;
	/**
	 * 可分享次数
	 */
	private java.lang.Integer shareNum;
	/**
	 * 是否关闭卡券手续费 0否 1是
	 */
	private java.lang.Integer isCloseFee;
	/**
	 * 是否有信息更新 0否 1是
	 */
	private java.lang.Integer isUpdate;
	/**
	 * 是否是黑名单 0否 1是
	 */
	private java.lang.Integer isBlack;
	//columns END 数据库字段结束
	
	//concstructor

	public AppUser(){
	}

	public AppUser(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:AppUser_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
     @WhereSQL(sql="name=:AppUser_name")
	public java.lang.String getName() {
		return this.name;
	}
	public void setPassword(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.password = value;
	}
	
     @WhereSQL(sql="password=:AppUser_password")
	public java.lang.String getPassword() {
		return this.password;
	}
	public void setPhone(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.phone = value;
	}
	
     @WhereSQL(sql="phone=:AppUser_phone")
	public java.lang.String getPhone() {
		return this.phone;
	}
	public void setQqNum(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.qqNum = value;
	}
	
     @WhereSQL(sql="qqNum=:AppUser_qqNum")
	public java.lang.String getQqNum() {
		return this.qqNum;
	}
	public void setWxNum(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.wxNum = value;
	}
	
     @WhereSQL(sql="wxNum=:AppUser_wxNum")
	public java.lang.String getWxNum() {
		return this.wxNum;
	}
	public void setSex(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.sex = value;
	}
	
     @WhereSQL(sql="sex=:AppUser_sex")
	public java.lang.String getSex() {
		return this.sex;
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
	
     @WhereSQL(sql="createTime=:AppUser_createTime")
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setHeader(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.header = value;
	}
	
     @WhereSQL(sql="header=:AppUser_header")
	public java.lang.String getHeader() {
		return this.header;
	}
	public void setCityName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.cityName = value;
	}
	
     @WhereSQL(sql="cityName=:AppUser_cityName")
	public java.lang.String getCityName() {
		return this.cityName;
	}
	public void setSign(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.sign = value;
	}
	
     @WhereSQL(sql="sign=:AppUser_sign")
	public java.lang.String getSign() {
		return this.sign;
	}
	public void setSinaNum(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.sinaNum = value;
	}
	
     @WhereSQL(sql="sinaNum=:AppUser_sinaNum")
	public java.lang.String getSinaNum() {
		return this.sinaNum;
	}
	public void setBalance(java.lang.Double value) {
		this.balance = value;
	}
	
     @WhereSQL(sql="balance=:AppUser_balance")
	public java.lang.Double getBalance() {
		return this.balance;
	}
	public void setFrozeBanlance(java.lang.Double value) {
		this.frozeBanlance = value;
	}
	
     @WhereSQL(sql="frozeBanlance=:AppUser_frozeBanlance")
	public java.lang.Double getFrozeBanlance() {
		return this.frozeBanlance;
	}
	public void setOsType(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.osType = value;
	}
	
     @WhereSQL(sql="osType=:AppUser_osType")
	public java.lang.String getOsType() {
		return this.osType;
	}
	public void setBankName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bankName = value;
	}
	
     @WhereSQL(sql="bankName=:AppUser_bankName")
	public java.lang.String getBankName() {
		return this.bankName;
	}
	public void setBranchBank(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.branchBank = value;
	}
	
     @WhereSQL(sql="branchBank=:AppUser_branchBank")
	public java.lang.String getBranchBank() {
		return this.branchBank;
	}
	public void setOwnerName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.ownerName = value;
	}
	
     @WhereSQL(sql="ownerName=:AppUser_ownerName")
	public java.lang.String getOwnerName() {
		return this.ownerName;
	}
	public void setOwnerPhone(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.ownerPhone = value;
	}
	
     @WhereSQL(sql="ownerPhone=:AppUser_ownerPhone")
	public java.lang.String getOwnerPhone() {
		return this.ownerPhone;
	}
	public void setCardNum(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.cardNum = value;
	}
	
     @WhereSQL(sql="cardNum=:AppUser_cardNum")
	public java.lang.String getCardNum() {
		return this.cardNum;
	}
	public void setWithdrawType(java.lang.Integer value) {
		this.withdrawType = value;
	}
	
     @WhereSQL(sql="withdrawType=:AppUser_withdrawType")
	public java.lang.Integer getWithdrawType() {
		return this.withdrawType;
	}
	public void setCurrentGetNum(java.lang.Integer value) {
		this.currentGetNum = value;
	}
	
     @WhereSQL(sql="currentGetNum=:AppUser_currentGetNum")
	public java.lang.Integer getCurrentGetNum() {
		return this.currentGetNum;
	}
	public void setCurrentShareNum(java.lang.Integer value) {
		this.currentShareNum = value;
	}
	
     @WhereSQL(sql="currentShareNum=:AppUser_currentShareNum")
	public java.lang.Integer getCurrentShareNum() {
		return this.currentShareNum;
	}
	public void setGetNum(java.lang.Integer value) {
		this.getNum = value;
	}
	
     @WhereSQL(sql="getNum=:AppUser_getNum")
	public java.lang.Integer getGetNum() {
		return this.getNum;
	}
	public void setShareNum(java.lang.Integer value) {
		this.shareNum = value;
	}
	
     @WhereSQL(sql="shareNum=:AppUser_shareNum")
	public java.lang.Integer getShareNum() {
		return this.shareNum;
	}
	public void setIsCloseFee(java.lang.Integer value) {
		this.isCloseFee = value;
	}
	
     @WhereSQL(sql="isCloseFee=:AppUser_isCloseFee")
	public java.lang.Integer getIsCloseFee() {
		return this.isCloseFee;
	}
	public void setIsUpdate(java.lang.Integer value) {
		this.isUpdate = value;
	}
	
     @WhereSQL(sql="isUpdate=:AppUser_isUpdate")
	public java.lang.Integer getIsUpdate() {
		return this.isUpdate;
	}
	public void setIsBlack(java.lang.Integer value) {
		this.isBlack = value;
	}
	
     @WhereSQL(sql="isBlack=:AppUser_isBlack")
	public java.lang.Integer getIsBlack() {
		return this.isBlack;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("昵称[").append(getName()).append("],")
			.append("密码[").append(getPassword()).append("],")
			.append("电话[").append(getPhone()).append("],")
			.append("qq号[").append(getQqNum()).append("],")
			.append("微信号[").append(getWxNum()).append("],")
			.append("性别[").append(getSex()).append("],")
			.append("创建时间[").append(getCreateTime()).append("],")
			.append("头像[").append(getHeader()).append("],")
			.append("城市名称 [").append(getCityName()).append("],")
			.append("个性签名[").append(getSign()).append("],")
			.append("新浪账号[").append(getSinaNum()).append("],")
			.append("余额[").append(getBalance()).append("],")
			.append("冻结余额[").append(getFrozeBanlance()).append("],")
			.append("操作系统[").append(getOsType()).append("],")
			.append("银行名称[").append(getBankName()).append("],")
			.append("开户行[").append(getBranchBank()).append("],")
			.append("户主名称/真实姓名[").append(getOwnerName()).append("],")
			.append("户主手机号[").append(getOwnerPhone()).append("],")
			.append("卡号/支付宝账号[").append(getCardNum()).append("],")
			.append("1银行 2支付宝[").append(getWithdrawType()).append("],")
			.append("当前可领取次数[").append(getCurrentGetNum()).append("],")
			.append("当前可分享次数[").append(getCurrentShareNum()).append("],")
			.append("可领取次数[").append(getGetNum()).append("],")
			.append("可分享次数[").append(getShareNum()).append("],")
			.append("是否关闭卡券手续费 0否 1是[").append(getIsCloseFee()).append("],")
			.append("是否有信息更新 0否 1是[").append(getIsUpdate()).append("],")
			.append("是否是黑名单 0否 1是[").append(getIsBlack()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof AppUser == false) return false;
		if(this == obj) return true;
		AppUser other = (AppUser)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
