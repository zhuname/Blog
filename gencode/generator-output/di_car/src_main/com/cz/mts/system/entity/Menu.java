package com.cz.mts.system.entity;

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
 * @version  2017-02-24 11:23:52
 * @see com.cz.mts.system.entity.Menu
 */
@Table(name="t_menu")
public class Menu  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "菜单";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "菜单名称";
	public static final String ALIAS_PID = "pid";
	public static final String ALIAS_DESCRIPTION = "描述";
	public static final String ALIAS_PAGEURL = "pageurl";
	public static final String ALIAS_TYPE = "0.功能按钮,1.导航菜单";
	public static final String ALIAS_STATE = "是否有效";
	public static final String ALIAS_SORTNO = "sortno";
	public static final String ALIAS_MENUICON = "menuIcon";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 菜单名称
	 */
	private java.lang.String name;
	/**
	 * pid
	 */
	private java.lang.String pid;
	/**
	 * 描述
	 */
	private java.lang.String description;
	/**
	 * pageurl
	 */
	private java.lang.String pageurl;
	/**
	 * 0.功能按钮,1.导航菜单
	 */
	private java.lang.Integer type;
	/**
	 * 是否有效
	 */
	private java.lang.String state;
	/**
	 * sortno
	 */
	private java.lang.Integer sortno;
	/**
	 * menuIcon
	 */
	private java.lang.String menuIcon;
	//columns END 数据库字段结束
	
	//concstructor

	public Menu(){
	}

	public Menu(
		java.lang.String id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:Menu_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
     @WhereSQL(sql="name=:Menu_name")
	public java.lang.String getName() {
		return this.name;
	}
	public void setPid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.pid = value;
	}
	
     @WhereSQL(sql="pid=:Menu_pid")
	public java.lang.String getPid() {
		return this.pid;
	}
	public void setDescription(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.description = value;
	}
	
     @WhereSQL(sql="description=:Menu_description")
	public java.lang.String getDescription() {
		return this.description;
	}
	public void setPageurl(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.pageurl = value;
	}
	
     @WhereSQL(sql="pageurl=:Menu_pageurl")
	public java.lang.String getPageurl() {
		return this.pageurl;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
     @WhereSQL(sql="type=:Menu_type")
	public java.lang.Integer getType() {
		return this.type;
	}
	public void setState(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.state = value;
	}
	
     @WhereSQL(sql="state=:Menu_state")
	public java.lang.String getState() {
		return this.state;
	}
	public void setSortno(java.lang.Integer value) {
		this.sortno = value;
	}
	
     @WhereSQL(sql="sortno=:Menu_sortno")
	public java.lang.Integer getSortno() {
		return this.sortno;
	}
	public void setMenuIcon(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.menuIcon = value;
	}
	
     @WhereSQL(sql="menuIcon=:Menu_menuIcon")
	public java.lang.String getMenuIcon() {
		return this.menuIcon;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("菜单名称[").append(getName()).append("],")
			.append("pid[").append(getPid()).append("],")
			.append("描述[").append(getDescription()).append("],")
			.append("pageurl[").append(getPageurl()).append("],")
			.append("0.功能按钮,1.导航菜单[").append(getType()).append("],")
			.append("是否有效[").append(getState()).append("],")
			.append("sortno[").append(getSortno()).append("],")
			.append("menuIcon[").append(getMenuIcon()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Menu == false) return false;
		if(this == obj) return true;
		Menu other = (Menu)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
