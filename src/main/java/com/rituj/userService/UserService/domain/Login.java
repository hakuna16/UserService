package com.rituj.userService.UserService.domain;

import io.swagger.annotations.ApiModelProperty;

public class Login extends Entity{

	@ApiModelProperty(notes = "Time of last login", required = true, example="2018-01-01")
	private String lastLogin;
	@ApiModelProperty(notes = "Password", required = true, example="Hashed password")
	private String password;
	@ApiModelProperty(notes = "contact details", required = true, example="mail Id")
	private String loc;
	@ApiModelProperty(notes = "User enable or not", required = true, example="true")
	private boolean enabled;
	@ApiModelProperty(notes = "User Name", required = true, example="thomas123")
	private String userName;
	
	
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
