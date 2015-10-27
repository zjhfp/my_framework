package com.pan.bbf.user.entities;

import java.util.Date;

import com.pan.bbf.common.entities.BaseAuditableEntity;

/**
 * 用户信息
 */
public class User extends BaseAuditableEntity{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 真实姓名
	 */
	private String realName;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 电话号码
	 */
	private String phone;
	
	/**
	 * 登录名
	 */
	private String loginName;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 最后登录时间
	 */
	private Date lastLoginDate;
	
	/**
	 * 用户是否已锁定
	 */
	private Boolean isLocked;
	
	/**
	 * 是否为超级用户
	 */
	private Boolean isAdmin;

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
