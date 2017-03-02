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
 * @version  2017-02-24 15:17:27
 * @see com.cz.mts.system.entity.MoneyDetail
 */
@Table(name="t_money_detail")
public class MoneyDetail  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "金额表动记录表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USERID = "用户id";
	public static final String ALIAS_CREATETIME = "创建时间";
	public static final String ALIAS_TYPE = "类型：1海报红包 2视频红包 3购买卡券 4充值 5发布视频 6发布海报 7提现 8发布卡券";
	public static final String ALIAS_MONEY = "变动金额";
	public static final String ALIAS_BALANCE = "剩余金额";
	public static final String ALIAS_ITEMID = "目标id";
	public static final String ALIAS_OSTYPE = "操作系统";
    */
	//date formats
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	
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
	 * 类型：1海报红包 2视频红包 3购买卡券 4充值 5发布视频 6发布海报 7提现 8发布卡券
	 */
	private java.lang.Integer type;
	/**
	 * 变动金额
	 */
	private java.lang.Double money;
	/**
	 * 剩余金额
	 */
	private java.lang.Double balance;
	/**
	 * 目标id
	 */
	private java.lang.Integer itemId;
	/**
	 * 操作系统
	 */
	private java.lang.String osType;
	//columns END 数据库字段结束
	
	private Integer payType;
	
	
	//concstructor

	private AppUser appUser;
	
	@Transient
	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public MoneyDetail(){
	}

	public MoneyDetail(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	
	
	@Id
     @WhereSQL(sql="id=:MoneyDetail_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
     @WhereSQL(sql="userId=:MoneyDetail_userId")
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
	
	@WhereSQL(sql="payType=:MoneyDetail_payType")
     public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	@WhereSQL(sql="createTime=:MoneyDetail_createTime")
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
     @WhereSQL(sql="type=:MoneyDetail_type")
	public java.lang.Integer getType() {
		return this.type;
	}
	public void setMoney(java.lang.Double value) {
		this.money = value;
	}
	
     @WhereSQL(sql="money=:MoneyDetail_money")
	public java.lang.Double getMoney() {
		return this.money;
	}
	public void setBalance(java.lang.Double value) {
		this.balance = value;
	}
	
     @WhereSQL(sql="balance=:MoneyDetail_balance")
	public java.lang.Double getBalance() {
		return this.balance;
	}
	public void setItemId(java.lang.Integer value) {
		this.itemId = value;
	}
	
     @WhereSQL(sql="itemId=:MoneyDetail_itemId")
	public java.lang.Integer getItemId() {
		return this.itemId;
	}
	public void setOsType(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.osType = value;
	}
	
     @WhereSQL(sql="osType=:MoneyDetail_osType")
	public java.lang.String getOsType() {
		return this.osType;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("用户id[").append(getUserId()).append("],")
			.append("创建时间[").append(getCreateTime()).append("],")
			.append("类型：1海报红包 2视频红包 3购买卡券 4充值 5发布视频 6发布海报 7提现 8发布卡券[").append(getType()).append("],")
			.append("变动金额[").append(getMoney()).append("],")
			.append("剩余金额[").append(getBalance()).append("],")
			.append("目标id[").append(getItemId()).append("],")
			.append("操作系统[").append(getOsType()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MoneyDetail == false) return false;
		if(this == obj) return true;
		MoneyDetail other = (MoneyDetail)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
