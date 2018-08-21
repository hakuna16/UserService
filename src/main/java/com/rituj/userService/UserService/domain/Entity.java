package com.rituj.userService.UserService.domain;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;

public abstract class Entity {

	@ApiModelProperty(notes = "Date anad time of created doucument", required = true, example = "2018-01-01")
	private LocalDateTime createdDate;
	@ApiModelProperty(notes = "Types of document", required = true, example = "User")
	private String docType;
}
