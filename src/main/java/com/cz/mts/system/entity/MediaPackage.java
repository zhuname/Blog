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
 * @version  2017-02-24 15:17:26
 * @see com.cz.mts.system.entity.MediaPackage
 */
@Table(name="t_media_package")
public class MediaPackage  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "视频红包表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USERID = "用户id";
	public static final String ALIAS_CATEGORYID = "分类id";
	public static final String ALIAS_TITLE = "主题";
	public static final String ALIAS_DESCR = "简介";
	public static final String ALIAS_ENCRYPT = "是否加密:0否1是";
	public static final String ALIAS_COMMAND = "加密口令";
	public static final String ALIAS_SUMMONEY = "红包金额";
	public static final String ALIAS_GETNUM = "领取次数";
	public static final String ALIAS_TOPPRICE = "最高金额";
	public static final String ALIAS_PAYMONEY = "支付金额";
	public static final String ALIAS_STATUS = "状态：0未支付 1支付 2失败 3通过 4抢完5过期";
	public static final String ALIAS_PAYTYPE = "支付类型：1支付宝 2微信 3余额支付";
	public static final String ALIAS_EXPTIME = "到期时间";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_PAYTIME = "支付时间";
	public static final String ALIAS_FAILTIME = "失败时间";
	public static final String ALIAS_SUCCTIME = "审核通过时间";
	public static final String ALIAS_ENDTIME = "抢完时间";
	public static final String ALIAS_FAILREASON = "失败原因";
	public static final String ALIAS_BALANCE = "剩余金额";
	public static final String ALIAS_NUM = "剩余待抢次数";
	public static final String ALIAS_CODE = "订单编号";
	public static final String ALIAS_WXCODE = "微信交易流水号";
	public static final String ALIAS_TRADENO = "交易流水号";
	public static final String ALIAS_MEDIAURL = "视频url";
	public static final String ALIAS_MEDIAIMAGE = "视频图片";
	public static final String ALIAS_SCANNUM = "浏览次数";
	public static final String ALIAS_ISDEL = "是否删除: 0否 1是";
    */
	//date formats
	//public static final String FORMAT_EXPTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_PAYTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_FAILTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_SUCCTIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_ENDTIME = DateUtils.DATETIME_FORMAT;
	
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
	 * 分类id
	 */
	private java.lang.Integer categoryId;
	/**
	 * 主题
	 */
	private java.lang.String title;
	/**
	 * 简介
	 */
	private java.lang.String descr;
	/**
	 * 是否加密:0否1是
	 */
	private java.lang.Integer encrypt;
	/**
	 * 加密口令
	 */
	private java.lang.String command;
	/**
	 * 红包金额
	 */
	private java.lang.Double sumMoney;
	/**
	 * 领取次数
	 */
	private java.lang.Integer lqNum;
	/**
	 * 最高金额
	 */
	private java.lang.Double topPrice;
	/**
	 * 支付金额
	 */
	private java.lang.Double payMoney;
	/**
	 * 状态：0未支付 1支付 2失败 3通过 4抢完5过期
	 */
	private java.lang.Integer status;
	/**
	 * 支付类型：1支付宝 2微信 3余额支付
	 */
	private java.lang.Integer payType;
	/**
	 * 到期时间
	 */
	private java.util.Date expTime;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 支付时间
	 */
	private java.util.Date payTime;
	/**
	 * 失败时间
	 */
	private java.util.Date failTime;
	/**
	 * 审核通过时间
	 */
	private java.util.Date succTime;
	/**
	 * 抢完时间
	 */
	private java.util.Date endTime;
	/**
	 * 失败原因
	 */
	private java.lang.String failReason;
	/**
	 * 剩余金额
	 */
	private java.lang.Double balance;
	/**
	 * 剩余待抢次数
	 */
	private java.lang.Integer num;
	/**
	 * 订单编号
	 */
	private java.lang.String code;
	/**
	 * 微信交易流水号
	 */
	private java.lang.String wxCode;
	/**
	 * 交易流水号
	 */
	private java.lang.String tradeNo;
	/**
	 * 视频url
	 */
	private java.lang.String mediaUrl;
	/**
	 * 视频图片
	 */
	private java.lang.String mediaImage;
	/**
	 * 浏览次数
	 */
	private java.lang.Integer scanNum;
	/**
	 * 是否删除: 0否 1是
	 */
	private java.lang.Integer isDel;
	//columns END 数据库字段结束
	
	private AppUser appUser;
	
	
	private Integer isLook;
	
	private Integer isAttention;
	
	private List<AppUser> appUsers;
	
	private Integer isCollect;
	
	private List<UserMedal> userMedals;
	
	private Integer appUserId;
	
	private List<MoneyDetail> moneyDetails;
	
	private String name;
	private String phone;
	private String sex;
	private String header;
	private String sign;
	
	private String categoryName;
	private String payName;
	
	private java.lang.String osType;
	
	private List<RedCity> redCities;
	
	private String userName;
	
	private String startTime;
	private String enddTime;
	
	private Integer cityId;
	
	
	@Transient
	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@Transient
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Transient
	public String getEnddTime() {
		return enddTime;
	}

	public void setEnddTime(String enddTime) {
		this.enddTime = enddTime;
	}
	
	@Transient
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Transient
	 public List<RedCity> getRedCities() {
		return redCities;
	}

	public void setRedCities(List<RedCity> redCities) {
		this.redCities = redCities;
	}
	
	 @WhereSQL(sql="osType=:MediaPackage_osType")
	public java.lang.String getOsType() {
		return osType;
	}

	public void setOsType(java.lang.String osType) {
		this.osType = osType;
	}

	@Transient
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Transient
	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}

	@Transient
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Transient
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Transient
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	@Transient
	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
	@Transient
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	//concstructor
	@Transient
	public List<MoneyDetail> getMoneyDetails() {
		return moneyDetails;
	}

	public void setMoneyDetails(List<MoneyDetail> moneyDetails) {
		this.moneyDetails = moneyDetails;
	}

	@Transient
	public Integer getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(Integer appUserId) {
		this.appUserId = appUserId;
	}

	@Transient
	public AppUser getAppUser() {
		return appUser;
	}
	
	@Transient
	public List<UserMedal> getUserMedals() {
		return userMedals;
	}
	public void setUserMedals(List<UserMedal> userMedals) {
		this.userMedals = userMedals;
	}
	@Transient
	public Integer getIsCollect() {
		return isCollect;
	}
	public void setIsCollect(Integer isCollect) {
		this.isCollect = isCollect;
	}
	@Transient
	public Integer getIsAttention() {
		return isAttention;
	}

	public void setIsAttention(Integer isAttention) {
		this.isAttention = isAttention;
	}
	@Transient
	public List<AppUser> getAppUsers() {
		return appUsers;
	}

	public void setAppUsers(List<AppUser> appUsers) {
		this.appUsers = appUsers;
	}

	@Transient
	public Integer getIsLook() {
		return isLook;
	}

	public void setIsLook(Integer isLook) {
		this.isLook = isLook;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	
	//concstructor

	public MediaPackage(){
	}

	public MediaPackage(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:MediaPackage_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
     @WhereSQL(sql="userId=:MediaPackage_userId")
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	public void setCategoryId(java.lang.Integer value) {
		this.categoryId = value;
	}
	
     @WhereSQL(sql="categoryId=:MediaPackage_categoryId")
	public java.lang.Integer getCategoryId() {
		return this.categoryId;
	}
	public void setTitle(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.title = value;
	}
	
     @WhereSQL(sql="title=:MediaPackage_title")
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setDescr(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.descr = value;
	}
	
     @WhereSQL(sql="descr=:MediaPackage_descr")
	public java.lang.String getDescr() {
		return this.descr;
	}
	public void setEncrypt(java.lang.Integer value) {
		this.encrypt = value;
	}
	
     @WhereSQL(sql="encrypt=:MediaPackage_encrypt")
	public java.lang.Integer getEncrypt() {
		return this.encrypt;
	}
	public void setCommand(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.command = value;
	}
	
     @WhereSQL(sql="command=:MediaPackage_command")
	public java.lang.String getCommand() {
		return this.command;
	}
	public void setSumMoney(java.lang.Double value) {
		this.sumMoney = value;
	}
	
     @WhereSQL(sql="sumMoney=:MediaPackage_sumMoney")
	public java.lang.Double getSumMoney() {
		return this.sumMoney;
	}
	public void setLqNum(java.lang.Integer value) {
		this.lqNum = value;
	}
	
     @WhereSQL(sql="lqNum=:MediaPackage_lqNum")
	public java.lang.Integer getLqNum() {
		return this.lqNum;
	}
	public void setTopPrice(java.lang.Double value) {
		this.topPrice = value;
	}
	
     @WhereSQL(sql="topPrice=:MediaPackage_topPrice")
	public java.lang.Double getTopPrice() {
		return this.topPrice;
	}
	public void setPayMoney(java.lang.Double value) {
		this.payMoney = value;
	}
	
     @WhereSQL(sql="payMoney=:MediaPackage_payMoney")
	public java.lang.Double getPayMoney() {
		return this.payMoney;
	}
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
     @WhereSQL(sql="status=:MediaPackage_status")
	public java.lang.Integer getStatus() {
		return this.status;
	}
	public void setPayType(java.lang.Integer value) {
		this.payType = value;
	}
	
     @WhereSQL(sql="payType=:MediaPackage_payType")
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
     @WhereSQL(sql="expTime=:MediaPackage_expTime")
	public java.util.Date getExpTime() {
		return this.expTime;
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
     @WhereSQL(sql="createTime=:MediaPackage_createTime")
	public java.util.Date getCreateTime() {
		return this.createTime;
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
     @WhereSQL(sql="payTime=:MediaPackage_payTime")
	public java.util.Date getPayTime() {
		return this.payTime;
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
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
     @WhereSQL(sql="failTime=:MediaPackage_failTime")
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
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
     @WhereSQL(sql="succTime=:MediaPackage_succTime")
	public java.util.Date getSuccTime() {
		return this.succTime;
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
     @WhereSQL(sql="endTime=:MediaPackage_endTime")
	public java.util.Date getEndTime() {
		return this.endTime;
	}
	public void setFailReason(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.failReason = value;
	}
	
     @WhereSQL(sql="failReason=:MediaPackage_failReason")
	public java.lang.String getFailReason() {
		return this.failReason;
	}
	public void setBalance(java.lang.Double value) {
		this.balance = value;
	}
	
     @WhereSQL(sql="balance=:MediaPackage_balance")
	public java.lang.Double getBalance() {
		return this.balance;
	}
	public void setNum(java.lang.Integer value) {
		this.num = value;
	}
	
     @WhereSQL(sql="num=:MediaPackage_num")
	public java.lang.Integer getNum() {
		return this.num;
	}
	public void setCode(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.code = value;
	}
	
     @WhereSQL(sql="code=:MediaPackage_code")
	public java.lang.String getCode() {
		return this.code;
	}
	public void setWxCode(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.wxCode = value;
	}
	
     @WhereSQL(sql="wxCode=:MediaPackage_wxCode")
	public java.lang.String getWxCode() {
		return this.wxCode;
	}
	public void setTradeNo(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.tradeNo = value;
	}
	
     @WhereSQL(sql="tradeNo=:MediaPackage_tradeNo")
	public java.lang.String getTradeNo() {
		return this.tradeNo;
	}
	public void setMediaUrl(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.mediaUrl = value;
	}
	
     @WhereSQL(sql="mediaUrl=:MediaPackage_mediaUrl")
	public java.lang.String getMediaUrl() {
		return this.mediaUrl;
	}
	public void setMediaImage(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.mediaImage = value;
	}
	
     @WhereSQL(sql="mediaImage=:MediaPackage_mediaImage")
	public java.lang.String getMediaImage() {
		return this.mediaImage;
	}
	public void setScanNum(java.lang.Integer value) {
		this.scanNum = value;
	}
	
     @WhereSQL(sql="scanNum=:MediaPackage_scanNum")
	public java.lang.Integer getScanNum() {
		return this.scanNum;
	}
	public void setIsDel(java.lang.Integer value) {
		this.isDel = value;
	}
	
     @WhereSQL(sql="isDel=:MediaPackage_isDel")
	public java.lang.Integer getIsDel() {
		return this.isDel;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("用户id[").append(getUserId()).append("],")
			.append("分类id[").append(getCategoryId()).append("],")
			.append("主题[").append(getTitle()).append("],")
			.append("简介[").append(getDescr()).append("],")
			.append("是否加密:0否1是[").append(getEncrypt()).append("],")
			.append("加密口令[").append(getCommand()).append("],")
			.append("红包金额[").append(getSumMoney()).append("],")
			.append("领取次数[").append(getLqNum()).append("],")
			.append("最高金额[").append(getTopPrice()).append("],")
			.append("支付金额[").append(getPayMoney()).append("],")
			.append("状态：0未支付 1支付 2失败 3通过 4抢完5过期[").append(getStatus()).append("],")
			.append("支付类型：1支付宝 2微信 3余额支付[").append(getPayType()).append("],")
			.append("到期时间[").append(getExpTime()).append("],")
			.append("创建时间[").append(getCreateTime()).append("],")
			.append("支付时间[").append(getPayTime()).append("],")
			.append("失败时间[").append(getFailTime()).append("],")
			.append("审核通过时间[").append(getSuccTime()).append("],")
			.append("抢完时间[").append(getEndTime()).append("],")
			.append("失败原因[").append(getFailReason()).append("],")
			.append("剩余金额[").append(getBalance()).append("],")
			.append("剩余待抢次数[").append(getNum()).append("],")
			.append("订单编号[").append(getCode()).append("],")
			.append("微信交易流水号[").append(getWxCode()).append("],")
			.append("交易流水号[").append(getTradeNo()).append("],")
			.append("视频url[").append(getMediaUrl()).append("],")
			.append("视频图片[").append(getMediaImage()).append("],")
			.append("浏览次数[").append(getScanNum()).append("],")
			.append("是否删除: 0否 1是[").append(getIsDel()).append("],")
			.append("操作系统").append(getOsType()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MediaPackage == false) return false;
		if(this == obj) return true;
		MediaPackage other = (MediaPackage)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
