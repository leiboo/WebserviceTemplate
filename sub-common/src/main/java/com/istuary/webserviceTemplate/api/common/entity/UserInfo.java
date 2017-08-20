package com.istuary.webserviceTemplate.api.common.entity;

public class UserInfo {

	public String name;
	
	public String password;

	public String uid;

	public String roles;

	public String opers;
	
	public UserInfo(){}
	
	public UserInfo(String name, String password){
		this.name = name;
		this.password = password;
	}

	public UserInfo(String name, String password, String uid, String roles, String opers) {
		this.name = name;
		this.password = password;
		this.uid = uid;
		this.roles = roles;
		this.opers = opers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUid() { return uid; }

	public void setUid(String uid) { this.uid = uid; }

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getOpers() {
		return opers;
	}

	public void setOpers(String opers) {
		this.opers = opers;
	}
}
