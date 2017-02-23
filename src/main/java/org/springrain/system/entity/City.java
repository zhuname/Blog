package org.springrain.system.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2017-02-21 09:29:30
 * @see org.springrain.demo.entity.City
 */
@Table(name="t_city")
public class City  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "城市表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "城市名";
	public static final String ALIAS_CAPITAL = "拼音首字母";
	public static final String ALIAS_OPEN = "是否开通  1为开通0否";
	public static final String ALIAS_FATHER_ID = "所属城市id";
	public static final String ALIAS_PINYIN = "拼音";
	public static final String ALIAS_IS_HOT = "是否热门0否1是";
	public static final String ALIAS_PHONE = "客服电话";
	public static final String ALIAS_MONEY = "最低额度";
	public static final String ALIAS_LIMIT_MONEY = "额度预警";
	public static final String ALIAS_OPEN_FIRST_MONEY = "是否打开首次登陆送红包0否1是";
	public static final String ALIAS_RED_MONEY = "首次登录红包金额";
	public static final String ALIAS_BALANCE = "分站余额";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 城市名
	 */
	private java.lang.String name;
	/**
	 * 拼音首字母
	 */
	private java.lang.String capital;
	/**
	 * 是否开通  1为开通0否
	 */
	private java.lang.Integer open;
	/**
	 * 所属城市id
	 */
	private java.lang.Integer father_id;
	/**
	 * 拼音
	 */
	private java.lang.String pinyin;
	/**
	 * 是否热门0否1是
	 */
	private java.lang.Integer is_hot;
	/**
	 * 客服电话
	 */
	private java.lang.String phone;
	/**
	 * 最低额度
	 */
	private java.lang.Double money;
	/**
	 * 额度预警
	 */
	private java.lang.Double limit_money;
	/**
	 * 是否打开首次登陆送红包0否1是
	 */
	private java.lang.Integer open_first_money;
	/**
	 * 首次登录红包金额
	 */
	private java.lang.Double red_money;
	/**
	 * 分站余额
	 */
	private java.lang.Double balance;
	//columns END 数据库字段结束
	
	//concstructor

	public City(){
	}

	public City(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:City_id")
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
     @WhereSQL(sql="name=:City_name")
	public java.lang.String getName() {
		return this.name;
	}
	public void setCapital(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.capital = value;
	}
	
     @WhereSQL(sql="capital=:City_capital")
	public java.lang.String getCapital() {
		return this.capital;
	}
	public void setOpen(java.lang.Integer value) {
		this.open = value;
	}
	
     @WhereSQL(sql="open=:City_open")
	public java.lang.Integer getOpen() {
		return this.open;
	}
	public void setFather_id(java.lang.Integer value) {
		this.father_id = value;
	}
	
     @WhereSQL(sql="father_id=:City_father_id")
	public java.lang.Integer getFather_id() {
		return this.father_id;
	}
	public void setPinyin(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.pinyin = value;
	}
	
     @WhereSQL(sql="pinyin=:City_pinyin")
	public java.lang.String getPinyin() {
		return this.pinyin;
	}
	public void setIs_hot(java.lang.Integer value) {
		this.is_hot = value;
	}
	
     @WhereSQL(sql="is_hot=:City_is_hot")
	public java.lang.Integer getIs_hot() {
		return this.is_hot;
	}
	public void setPhone(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.phone = value;
	}
	
     @WhereSQL(sql="phone=:City_phone")
	public java.lang.String getPhone() {
		return this.phone;
	}
	public void setMoney(java.lang.Double value) {
		this.money = value;
	}
	
     @WhereSQL(sql="money=:City_money")
	public java.lang.Double getMoney() {
		return this.money;
	}
	public void setLimit_money(java.lang.Double value) {
		this.limit_money = value;
	}
	
     @WhereSQL(sql="limit_money=:City_limit_money")
	public java.lang.Double getLimit_money() {
		return this.limit_money;
	}
	public void setOpen_first_money(java.lang.Integer value) {
		this.open_first_money = value;
	}
	
     @WhereSQL(sql="open_first_money=:City_open_first_money")
	public java.lang.Integer getOpen_first_money() {
		return this.open_first_money;
	}
	public void setRed_money(java.lang.Double value) {
		this.red_money = value;
	}
	
     @WhereSQL(sql="red_money=:City_red_money")
	public java.lang.Double getRed_money() {
		return this.red_money;
	}
	public void setBalance(java.lang.Double value) {
		this.balance = value;
	}
	
     @WhereSQL(sql="balance=:City_balance")
	public java.lang.Double getBalance() {
		return this.balance;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("城市名[").append(getName()).append("],")
			.append("拼音首字母[").append(getCapital()).append("],")
			.append("是否开通  1为开通0否[").append(getOpen()).append("],")
			.append("所属城市id[").append(getFather_id()).append("],")
			.append("拼音[").append(getPinyin()).append("],")
			.append("是否热门0否1是[").append(getIs_hot()).append("],")
			.append("客服电话[").append(getPhone()).append("],")
			.append("最低额度[").append(getMoney()).append("],")
			.append("额度预警[").append(getLimit_money()).append("],")
			.append("是否打开首次登陆送红包0否1是[").append(getOpen_first_money()).append("],")
			.append("首次登录红包金额[").append(getRed_money()).append("],")
			.append("分站余额[").append(getBalance()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof City == false) return false;
		if(this == obj) return true;
		City other = (City)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
