package com.rituj.userService.UserService.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class SecRoles extends Entity{
	
	@ApiModelProperty(notes = "Security Role", required = true, example="Administrator")
	private String name;
	@ApiModelProperty(notes = "Short description of role", required = true, example="Admin Role")
	private String description;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
