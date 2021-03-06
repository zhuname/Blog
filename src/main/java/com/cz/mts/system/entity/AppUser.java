package com.cz.mts.system.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import com.cz.mts.frame.annotation.WhereSQL;
import com.cz.mts.frame.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
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
//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
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
	private java.lang.Integer currentLqNum;
	/**
	 * 当前可分享次数
	 */
	private java.lang.Integer currentShareNum;
	/**
	 * 可领取次数
	 */
	private java.lang.Integer lqNum;
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
	
	private Integer fansNum;
	
	
	private Integer cityId;
	
	
	/**
	 * 是否是黑名单 0否 1是
	 */
	private java.lang.Integer isBlack;
	
	private java.lang.String newPwd;
	
	private java.lang.String wxName;
	private java.lang.String wxAccount;
	private java.lang.String wxPhone;	

	
	private List<UserMedal> userMedals;
	
	private Integer posterCount;
	private Integer mediaCount;
	private Integer cardCount;
	
	private List<Medal> medals;
	
	private String endTime;
	private String startTime;
	
	private java.lang.Integer isPush;
	
	private Integer cardStatus;
	
	private Integer sumPerson;
	
	private Double sumMoney;
	
	private Integer provinceId;
	private String provinceName;
	private Date posterScanTime; 
	private Date mediaScanTime;
	private Date activityScanTime;
	private Date circleScanTime;
	private Double sumAllMoney;
	private Integer viewedCount;
	private Integer commentedCount;
	private Integer topedCount;
	private Integer attenedCount;
	
	private Integer isAppointFee;
	
	private Integer activityCount;
	private Integer cityCircleCount;
	
	private Double posterSumMoney;
	private Double posterRemainMoney;
	private Double mediaSumMoney;
	private Double mediaRemainMoney;
	private String payWxOpenId;
	
	
	@Transient
	public String getPayWxOpenId() {
		return payWxOpenId;
	}

	public void setPayWxOpenId(String payWxOpenId) {
		this.payWxOpenId = payWxOpenId;
	}

	@Transient
	public Double getPosterSumMoney() {
		return posterSumMoney;
	}

	public void setPosterSumMoney(Double posterSumMoney) {
		this.posterSumMoney = posterSumMoney;
	}

	@Transient
	public Double getPosterRemainMoney() {
		return posterRemainMoney;
	}

	public void setPosterRemainMoney(Double posterRemainMoney) {
		this.posterRemainMoney = posterRemainMoney;
	}

	@Transient
	public Double getMediaSumMoney() {
		return mediaSumMoney;
	}

	public void setMediaSumMoney(Double mediaSumMoney) {
		this.mediaSumMoney = mediaSumMoney;
	}

	@Transient
	public Double getMediaRemainMoney() {
		return mediaRemainMoney;
	}

	public void setMediaRemainMoney(Double mediaRemainMoney) {
		this.mediaRemainMoney = mediaRemainMoney;
	}

	@Transient
	public Integer getActivityCount() {
		return activityCount;
	}

	public void setActivityCount(Integer activityCount) {
		this.activityCount = activityCount;
	}

	@Transient
	public Integer getCityCircleCount() {
		return cityCircleCount;
	}

	public void setCityCircleCount(Integer cityCircleCount) {
		this.cityCircleCount = cityCircleCount;
	}

	@WhereSQL(sql="isAppointFee=:AppUser_isAppointFee")
	public Integer getIsAppointFee() {
		return isAppointFee;
	}

	public void setIsAppointFee(Integer isAppointFee) {
		this.isAppointFee = isAppointFee;
	}

	@Transient
	public Double getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(Double sumMoney) {
		this.sumMoney = sumMoney;
	}

	@Transient
	public Integer getSumPerson() {
		return sumPerson;
	}

	public void setSumPerson(Integer sumPerson) {
		this.sumPerson = sumPerson;
	}

	@WhereSQL(sql="isPush=:AppUser_isPush")
	public java.lang.Integer getIsPush() {
		return isPush;
	}

	@Transient
	public Integer getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(Integer cardStatus) {
		this.cardStatus = cardStatus;
	}

	public void setIsPush(java.lang.Integer isPush) {
		this.isPush = isPush;
	}

	//columns END 数据库字段结束
	@Transient
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	@Transient
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	//concstructor
	@Transient
	public List<Medal> getMedals() {
		return medals;
	}

	public void setMedals(List<Medal> medals) {
		this.medals = medals;
	}

	@Transient
	public Integer getPosterCount() {
		return posterCount;
	}

	public void setPosterCount(Integer posterCount) {
		this.posterCount = posterCount;
	}

	@Transient
	public Integer getMediaCount() {
		return mediaCount;
	}

	public void setMediaCount(Integer mediaCount) {
		this.mediaCount = mediaCount;
	}

	@Transient
	public Integer getCardCount() {
		return cardCount;
	}

	public void setCardCount(Integer cardCount) {
		this.cardCount = cardCount;
	}

	@Transient
	public List<UserMedal> getUserMedals() {
		return userMedals;
	}

	public void setUserMedals(List<UserMedal> userMedals) {
		this.userMedals = userMedals;
	}

	@WhereSQL(sql="wxName=:AppUser_wxName")
	public java.lang.String getWxName() {
		return wxName;
	}

	public void setWxName(java.lang.String wxName) {
		this.wxName = wxName;
	}
	
	@WhereSQL(sql="wxAccount=:AppUser_wxAccount")
	public java.lang.String getWxAccount() {
		return wxAccount;
	}

	public void setWxAccount(java.lang.String wxAccount) {
		this.wxAccount = wxAccount;
	}
	
	@WhereSQL(sql="wxPhone=:AppUser_wxPhone")
	public java.lang.String getWxPhone() {
		return wxPhone;
	}


	@WhereSQL(sql="cityId=:AppUser_cityId")
	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public void setWxPhone(java.lang.String wxPhone) {
		this.wxPhone = wxPhone;
	}

	@Transient
	public java.lang.String getNewPwd() {
		return newPwd;
	}

	@Transient
	public Integer getFansNum() {
		return fansNum;
	}

	public void setFansNum(Integer fansNum) {
		this.fansNum = fansNum;
	}

	public void setNewPwd(java.lang.String newPwd) {
		this.newPwd = newPwd;
	}

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
	
     @WhereSQL(sql="name =:AppUser_name")
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
	
     @WhereSQL(sql="phone =:AppUser_phone")
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
     @WhereSQL(sql="provinceId=:AppUser_provinceId")
	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	@WhereSQL(sql="provinceName=:AppUser_provinceName")
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @WhereSQL(sql="posterScanTime=:AppUser_posterScanTime")
	public Date getPosterScanTime() {
		return posterScanTime;
	}

	public void setPosterScanTime(Date posterScanTime) {
		this.posterScanTime = posterScanTime;
	}

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @WhereSQL(sql="mediaScanTime=:AppUser_mediaScanTime")
	public Date getMediaScanTime() {
		return mediaScanTime;
	}

	public void setMediaScanTime(Date mediaScanTime) {
		this.mediaScanTime = mediaScanTime;
	}

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @WhereSQL(sql="activityScanTime=:AppUser_activityScanTime")
	public Date getActivityScanTime() {
		return activityScanTime;
	}

	public void setActivityScanTime(Date activityScanTime) {
		this.activityScanTime = activityScanTime;
	}

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @WhereSQL(sql="circleScanTime=:AppUser_circleScanTime")
	public Date getCircleScanTime() {
		return circleScanTime;
	}

	public void setCircleScanTime(Date circleScanTime) {
		this.circleScanTime = circleScanTime;
	}

	@WhereSQL(sql="sumAllMoney=:AppUser_sumAllMoney")
	public Double getSumAllMoney() {
		return sumAllMoney;
	}

	public void setSumAllMoney(Double sumAllMoney) {
		this.sumAllMoney = sumAllMoney;
	}

	@WhereSQL(sql="viewedCount=:AppUser_viewedCount")
	public Integer getViewedCount() {
		return viewedCount;
	}

	public void setViewedCount(Integer viewedCount) {
		this.viewedCount = viewedCount;
	}

	@WhereSQL(sql="commentedCount=:AppUser_commentedCount")
	public Integer getCommentedCount() {
		return commentedCount;
	}

	public void setCommentedCount(Integer commentedCount) {
		this.commentedCount = commentedCount;
	}

	@WhereSQL(sql="topedCount=:AppUser_topedCount")
	public Integer getTopedCount() {
		return topedCount;
	}

	public void setTopedCount(Integer topedCount) {
		this.topedCount = topedCount;
	}

	@WhereSQL(sql="attenedCount=:AppUser_attenedCount")
	public Integer getAttenedCount() {
		return attenedCount;
	}

	public void setAttenedCount(Integer attenedCount) {
		this.attenedCount = attenedCount;
	}

	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
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
	public void setCurrentLqNum(java.lang.Integer value) {
		this.currentLqNum = value;
	}
	
     @WhereSQL(sql="currentLqNum=:AppUser_currentLqNum")
	public java.lang.Integer getCurrentLqNum() {
		return this.currentLqNum;
	}
	public void setCurrentShareNum(java.lang.Integer value) {
		this.currentShareNum = value;
	}
	
     @WhereSQL(sql="currentShareNum=:AppUser_currentShareNum")
	public java.lang.Integer getCurrentShareNum() {
		return this.currentShareNum;
	}
	public void setLqNum(java.lang.Integer value) {
		this.lqNum = value;
	}
	
     @WhereSQL(sql="lqNum=:AppUser_lqNum")
	public java.lang.Integer getLqNum() {
		return this.lqNum;
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
			.append("当前可领取次数[").append(getCurrentLqNum()).append("],")
			.append("当前可分享次数[").append(getCurrentShareNum()).append("],")
			.append("可领取次数[").append(getLqNum()).append("],")
			.append("可分享次数[").append(getShareNum()).append("],")
			.append("是否关闭卡券手续费 0否 1是[").append(getIsCloseFee()).append("],")
			.append("是否有信息更新 0否 1是[").append(getIsUpdate()).append("],")
			.append("是否是黑名单 0否 1是[").append(getIsBlack()).append("],")
			.append("推送开关[").append(getIsPush()).append("],")
			.append("城市id[").append(getCityId()).append("],")
			.append("省份id[").append(getProvinceId()).append("],")
			.append("省名称[").append(getProvinceName()).append("],")
			.append("海报最后浏览时间[").append(getPosterScanTime()).append("],")
			.append("视频最后浏览时间[").append(getMediaScanTime()).append("],")
			.append("同城活动最后浏览时间[").append(getActivityScanTime()).append("],")
			.append("城事圈最后浏览时间[").append(getCircleScanTime()).append("],")
			.append("总收入[").append(getSumAllMoney()).append("],")
			.append("被浏览次数[").append(getViewedCount()).append("],")
			.append("被评论次数[").append(getCommentedCount()).append("],")
			.append("被点赞次数[").append(getTopedCount()).append("],")
			.append("被关注次数[").append(getAttenedCount()).append("],")
			.append("是否有预约佣金[").append(getIsAppointFee()).append("],")
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

	
