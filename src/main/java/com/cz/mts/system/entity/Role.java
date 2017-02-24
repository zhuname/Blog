package com.cz.mts.system.entity;

import java.util.List;

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
 * @version  2017-02-24 11:23:53
 * @see com.cz.mts.system.entity.Role
 */
@Table(name="t_role")
public class Role  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "角色";
	public static final String ALIAS_ID = "角色ID";
	public static final String ALIAS_NAME = "角色名称";
	public static final String ALIAS_CODE = "权限编码";
	public static final String ALIAS_PID = "上级角色ID";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_STATE = "是否有效(否/是)";
    */
	//date formats
	
	//columns START
	/**
	 * 角色ID
	 */
	private java.lang.String id;
	/**
	 * 角色名称
	 */
	private java.lang.String name;
	/**
	 * 权限编码
	 */
	private java.lang.String code;
	/**
	 * 上级角色ID
	 */
	private java.lang.String pid;
	/**
	 * 备注
	 */
	private java.lang.String remark;
	/**
	 * 是否有效(否/是)
	 */
	private java.lang.String state;
	//columns END 数据库字段结束
	
	
	private List<Menu> menus;
	
	//concstructor

	public Role(){
	}

	public Role(
		java.lang.String id
	){
		this.id = id;
	}

	@Transient
	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	//get and set
	public void setId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.id = value;
	}
	
	@Id
     @WhereSQL(sql="id=:Role_id")
	public java.lang.String getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
     @WhereSQL(sql="name=:Role_name")
	public java.lang.String getName() {
		return this.name;
	}
	public void setCode(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.code = value;
	}
	
     @WhereSQL(sql="code=:Role_code")
	public java.lang.String getCode() {
		return this.code;
	}
	public void setPid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.pid = value;
	}
	
     @WhereSQL(sql="pid=:Role_pid")
	public java.lang.String getPid() {
		return this.pid;
	}
	public void setRemark(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.remark = value;
	}
	
     @WhereSQL(sql="remark=:Role_remark")
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setState(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.state = value;
	}
	
     @WhereSQL(sql="state=:Role_state")
	public java.lang.String getState() {
		return this.state;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("角色ID[").append(getId()).append("],")
			.append("角色名称[").append(getName()).append("],")
			.append("权限编码[").append(getCode()).append("],")
			.append("上级角色ID[").append(getPid()).append("],")
			.append("备注[").append(getRemark()).append("],")
			.append("是否有效(否/是)[").append(getState()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Role == false) return false;
		if(this == obj) return true;
		Role other = (Role)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
