package com.rituj.userService.UserService.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class Phone {
	@ApiModelProperty(notes = "Type of phone", required = true, example="Work")
	private String type;
	@ApiModelProperty(notes = "phone number", required = true, example="8951165691")
	private String number;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
