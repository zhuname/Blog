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
 * @version  2017-02-24 15:17:28
 * @see com.cz.mts.system.entity.RoleMenu
 */
@Table(name="t_role_menu")
public class RoleMenu  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "角色菜单中间表";
	public static final String ALIAS_ID = "编号";
	public static final String ALIAS_ROLEID = "角色编号";
	public static final String ALIAS_MENUID = "菜单编号";
    */
	//date formats
	
	//columns START
	/**
	 * 编号
	 */
	private java.lang.String id;
	/**
	 * 角色编号
	 */
	private java.lang.String roleId;
	/**
	 * 菜单编号
	 */
	private java.lang.String menuId;
	//columns END 数据库字段结束
	
	//concstructor

	public RoleMenu(){
	}

	public RoleMenu(
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
     @WhereSQL(sql="id=:RoleMenu_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setRoleId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.roleId = value;
	}
	
     @WhereSQL(sql="roleId=:RoleMenu_roleId")
	public java.lang.String getRoleId() {
		return this.roleId;
	}
	public void setMenuId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.menuId = value;
	}
	
     @WhereSQL(sql="menuId=:RoleMenu_menuId")
	public java.lang.String getMenuId() {
		return this.menuId;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("编号[").append(getId()).append("],")
			.append("角色编号[").append(getRoleId()).append("],")
			.append("菜单编号[").append(getMenuId()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof RoleMenu == false) return false;
		if(this == obj) return true;
		RoleMenu other = (RoleMenu)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
