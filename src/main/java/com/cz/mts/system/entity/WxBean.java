package com.cz.mts.system.entity;

import java.io.Serializable;
import java.util.List;

public class WxBean implements Serializable{

	public WxBean() {
		super();
	}
	
	private String access_token;
	private String FromUserName;
	private String openid;
	private String unionid;
	private String sex;
	private String nickname;
	private String headimgurl;
	private String ticket;
	private List<Integer> tagid_list;
	private String city;
	
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public List<Integer> getTagid_list() {
		return tagid_list;
	}
	public void setTagid_list(List<Integer> tagid_list) {
		this.tagid_list = tagid_list;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public WxBean(String access_token, String fromUserName, String openid,
			String unionid, String sex, String nickname, String headimgurl,
			List<Integer> tagid_list, String city) {
		super();
		this.access_token = access_token;
		FromUserName = fromUserName;
		this.openid = openid;
		this.unionid = unionid;
		this.sex = sex;
		this.nickname = nickname;
		this.headimgurl = headimgurl;
		this.tagid_list = tagid_list;
		this.city = city;
	}
	
	
}
