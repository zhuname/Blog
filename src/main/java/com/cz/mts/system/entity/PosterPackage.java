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
 * @version  2017-02-24 15:17:27
 * @see com.cz.mts.system.entity.PosterPackage
 */
@Table(name="t_poster_package")
public class PosterPackage  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "海报红包表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USERID = "用户id";
	public static final String ALIAS_IMAGE = "海报图片";
	public static final String ALIAS_CATEGORYID = "分类id";
	public static final String ALIAS_TITLE = "主题";
	public static final String ALIAS_DESCR = "简介";
	public static final String ALIAS_ENCRYPT = "是否加密0否1是";
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
	 * 海报图片
	 */
	private java.lang.String image;
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
	 * 是否加密0否1是
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
	 * 是否删除: 0否 1是
	 */
	private java.lang.Integer isDel;
	//columns END 数据库字段结束
	
	private AppUser appUser;
	
	
	private Integer isLook;
	//concstructor
	
	private String userHeader;
	
	private Integer lookNum;
	
	
	private Integer isAtt;
	
	private String appUserName;
	
	private String categoryName;
	
	private String payName;
	
	private java.lang.String osType;
	
	private List<RedCity> redCities;
	
	private String userName;
	
	
	private String startTime;
	private String enddTime;
	
	private Integer cityId;
	
	private List<UserMedal> userMedals;
	
	private Double sumOverMoney;
	
	
	private Integer shareNum;
	
	private String cityIds;
	
	private Integer isAppoint;
	private Integer isRelevance;
	private String appointExplain;
	private Integer cardId;
	private Integer topCount;
	private Integer commentCount;
	private Integer appointCount;
	private Integer cardLqNum;
	private Integer cardCategoryId;
	
	private String cardCategoryName;
	private String cardCategoryImage;
	
	private Integer isTop;
	
	private Integer isValid;
	
	private String cardTitle;
	
	
	
	
	@Transient
	public String getCardTitle() {
		return cardTitle;
	}

	public void setCardTitle(String cardTitle) {
		this.cardTitle = cardTitle;
	}
	
	@WhereSQL(sql="isValid=:PosterPackage_isValid")
	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	@Transient
	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	@Transient
	public String getCardCategoryName() {
		return cardCategoryName;
	}

	public void setCardCategoryName(String cardCategoryName) {
		this.cardCategoryName = cardCategoryName;
	}

	@Transient
	public String getCardCategoryImage() {
		return cardCategoryImage;
	}

	public void setCardCategoryImage(String cardCategoryImage) {
		this.cardCategoryImage = cardCategoryImage;
	}

	@WhereSQL(sql="isAppoint=:PosterPackage_shareNum")
	public Integer getIsAppoint() {
		return isAppoint;
	}

	public void setIsAppoint(Integer isAppoint) {
		this.isAppoint = isAppoint;
	}

	@WhereSQL(sql="isRelevance=:PosterPackage_isRelevance")
	public Integer getIsRelevance() {
		return isRelevance;
	}

	public void setIsRelevance(Integer isRelevance) {
		this.isRelevance = isRelevance;
	}

	@WhereSQL(sql="appointExplain=:PosterPackage_appointExplain")
	public String getAppointExplain() {
		return appointExplain;
	}

	public void setAppointExplain(String appointExplain) {
		this.appointExplain = appointExplain;
	}

	@WhereSQL(sql="cardId=:PosterPackage_cardId")
	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	@WhereSQL(sql="topCount=:PosterPackage_topCount")
	public Integer getTopCount() {
		return topCount;
	}

	public void setTopCount(Integer topCount) {
		this.topCount = topCount;
	}

	@WhereSQL(sql="commentCount=:PosterPackage_commentCount")
	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	@WhereSQL(sql="appointCount=:PosterPackage_appointCount")
	public Integer getAppointCount() {
		return appointCount;
	}

	public void setAppointCount(Integer appointCount) {
		this.appointCount = appointCount;
	}

	@WhereSQL(sql="cardLqNum=:PosterPackage_cardLqNum")
	public Integer getCardLqNum() {
		return cardLqNum;
	}

	public void setCardLqNum(Integer cardLqNum) {
		this.cardLqNum = cardLqNum;
	}

	@WhereSQL(sql="cardCategoryId=:PosterPackage_cardCategoryId")
	public Integer getCardCategoryId() {
		return cardCategoryId;
	}

	public void setCardCategoryId(Integer cardCategoryId) {
		this.cardCategoryId = cardCategoryId;
	}

	@Transient
	 public String getCityIds() {
		return cityIds;
	}

	public void setCityIds(String cityIds) {
		this.cityIds = cityIds;
	}

	@WhereSQL(sql="shareNum=:PosterPackage_shareNum")
	public Integer getShareNum() {
		return shareNum;
	}

	public void setShareNum(Integer shareNum) {
		this.shareNum = shareNum;
	}
	
	
	@Transient
	public Double getSumOverMoney() {
		return sumOverMoney;
	}

	public void setSumOverMoney(Double sumOverMoney) {
		this.sumOverMoney = sumOverMoney;
	}

	@Transient
	public List<UserMedal> getUserMedals() {
		return userMedals;
	}

	public void setUserMedals(List<UserMedal> userMedals) {
		this.userMedals = userMedals;
	}

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

	@WhereSQL(sql="osType=:PosterPackage_osType")
	public java.lang.String getOsType() {
		return osType;
	}

	public void setOsType(java.lang.String osType) {
		this.osType = osType;
	}
	
	@Transient
	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}

	@Transient
	public String getAppUserName() {
		return appUserName;
	}

	public void setAppUserName(String appUserName) {
		this.appUserName = appUserName;
	}

	@Transient
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Transient
	public Integer getIsAtt() {
		return isAtt;
	}

	public void setIsAtt(Integer isAtt) {
		this.isAtt = isAtt;
	}

	 @WhereSQL(sql="lookNum=:PosterPackage_lookNum")
	public Integer getLookNum() {
		return lookNum;
	}

	public void setLookNum(Integer lookNum) {
		this.lookNum = lookNum;
	}

	@Transient
	public String getUserHeader() {
		return userHeader;
	}

	public void setUserHeader(String userHeader) {
		this.userHeader = userHeader;
	}

	@Transient
	public AppUser getAppUser() {
		return appUser;
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

	public PosterPackage(){
	}

	public PosterPackage(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:PosterPackage_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
     @WhereSQL(sql="userId=:PosterPackage_userId")
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	public void setImage(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.image = value;
	}
	
     @WhereSQL(sql="image=:PosterPackage_image")
	public java.lang.String getImage() {
		return this.image;
	}
	public void setCategoryId(java.lang.Integer value) {
		this.categoryId = value;
	}
	
     @WhereSQL(sql="categoryId=:PosterPackage_categoryId")
	public java.lang.Integer getCategoryId() {
		return this.categoryId;
	}
	public void setTitle(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.title = value;
	}
	
     @WhereSQL(sql="title=:PosterPackage_title")
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setDescr(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.descr = value;
	}
	
     @WhereSQL(sql="descr=:PosterPackage_descr")
	public java.lang.String getDescr() {
		return this.descr;
	}
	public void setEncrypt(java.lang.Integer value) {
		this.encrypt = value;
	}
	
     @WhereSQL(sql="encrypt=:PosterPackage_encrypt")
	public java.lang.Integer getEncrypt() {
		return this.encrypt;
	}
	public void setCommand(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.command = value;
	}
	
     @WhereSQL(sql="command=:PosterPackage_command")
	public java.lang.String getCommand() {
		return this.command;
	}
	public void setSumMoney(java.lang.Double value) {
		this.sumMoney = value;
	}
	
     @WhereSQL(sql="sumMoney=:PosterPackage_sumMoney")
	public java.lang.Double getSumMoney() {
		return this.sumMoney;
	}
	public void setLqNum(java.lang.Integer value) {
		this.lqNum = value;
	}
	
     @WhereSQL(sql="lqNum=:PosterPackage_lqNum")
	public java.lang.Integer getLqNum() {
		return this.lqNum;
	}
	public void setTopPrice(java.lang.Double value) {
		this.topPrice = value;
	}
	
     @WhereSQL(sql="topPrice=:PosterPackage_topPrice")
	public java.lang.Double getTopPrice() {
		return this.topPrice;
	}
	public void setPayMoney(java.lang.Double value) {
		this.payMoney = value;
	}
	
     @WhereSQL(sql="payMoney=:PosterPackage_payMoney")
	public java.lang.Double getPayMoney() {
		return this.payMoney;
	}
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
     @WhereSQL(sql="status=:PosterPackage_status")
	public java.lang.Integer getStatus() {
		return this.status;
	}
	public void setPayType(java.lang.Integer value) {
		this.payType = value;
	}
	
     @WhereSQL(sql="payType=:PosterPackage_payType")
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
     @WhereSQL(sql="expTime=:PosterPackage_expTime")
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
     @WhereSQL(sql="createTime=:PosterPackage_createTime")
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
     @WhereSQL(sql="payTime=:PosterPackage_payTime")
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
     @WhereSQL(sql="failTime=:PosterPackage_failTime")
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
     @WhereSQL(sql="succTime=:PosterPackage_succTime")
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
     @WhereSQL(sql="endTime=:PosterPackage_endTime")
	public java.util.Date getEndTime() {
		return this.endTime;
	}
	public void setFailReason(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.failReason = value;
	}
	
     @WhereSQL(sql="failReason=:PosterPackage_failReason")
	public java.lang.String getFailReason() {
		return this.failReason;
	}
	public void setBalance(java.lang.Double value) {
		this.balance = value;
	}
	
     @WhereSQL(sql="balance=:PosterPackage_balance")
	public java.lang.Double getBalance() {
		return this.balance;
	}
	public void setNum(java.lang.Integer value) {
		this.num = value;
	}
	
     @WhereSQL(sql="num=:PosterPackage_num")
	public java.lang.Integer getNum() {
		return this.num;
	}
	public void setCode(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.code = value;
	}
	
     @WhereSQL(sql="code=:PosterPackage_code")
	public java.lang.String getCode() {
		return this.code;
	}
	public void setWxCode(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.wxCode = value;
	}
	
     @WhereSQL(sql="wxCode=:PosterPackage_wxCode")
	public java.lang.String getWxCode() {
		return this.wxCode;
	}
	public void setTradeNo(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.tradeNo = value;
	}
	
     @WhereSQL(sql="tradeNo=:PosterPackage_tradeNo")
	public java.lang.String getTradeNo() {
		return this.tradeNo;
	}
	public void setIsDel(java.lang.Integer value) {
		this.isDel = value;
	}
	
     @WhereSQL(sql="isDel=:PosterPackage_isDel")
	public java.lang.Integer getIsDel() {
		return this.isDel;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("用户id[").append(getUserId()).append("],")
			.append("海报图片[").append(getImage()).append("],")
			.append("分类id[").append(getCategoryId()).append("],")
			.append("主题[").append(getTitle()).append("],")
			.append("简介[").append(getDescr()).append("],")
			.append("是否加密0否1是[").append(getEncrypt()).append("],")
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
			.append("是否删除: 0否 1是[").append(getIsDel()).append("],")
			.append("操作系统").append(getOsType()).append("],")
			.append("投放城市").append(getCityIds()).append("],")
			.append("是否开启预约").append(getIsAppoint()).append("],")
			.append("是否关联卡券").append(getIsRelevance()).append("],")
			.append("预约说明").append(getAppointExplain()).append("],")
			.append("卡券id").append(getCardId()).append("],")
			.append("点赞次数").append(getTopCount()).append("],")
			.append("评论次数").append(getCommentCount()).append("],")
			.append("预约次数").append(getAppointCount()).append("],")
			.append("预约说明").append(getAppointExplain()).append("],")
			.append("卡券已领取次数").append(getCardLqNum()).append("],")
			.append("卡券分类id").append(getCardCategoryId()).append("],")
			.append("红包领完是否在三天内").append(getIsValid()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof PosterPackage == false) return false;
		if(this == obj) return true;
		PosterPackage other = (PosterPackage)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
