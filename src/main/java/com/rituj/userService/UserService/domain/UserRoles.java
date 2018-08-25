package com.rituj.userService.UserService.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class UserRoles extends Entity {

	@ApiModelProperty(notes = "Array of Security Roles", required = true, example = "[1001,1002]")
	private Integer[] secRoles;
	@ApiModelProperty(notes = "User Name for that security Question", required = true, example = "thomas123")
	private String userName;

	public Integer[] getSecRoles() {
		return secRoles;
	}

	public void setSecRoles(Integer[] secRoles) {
		this.secRoles = secRoles;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
