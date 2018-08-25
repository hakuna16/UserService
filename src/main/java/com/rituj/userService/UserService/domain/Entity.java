package com.rituj.userService.UserService.domain;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;

public abstract class Entity {
	
	@ApiModelProperty(notes = "Id of created doucument", required = true, example = "document Id")
	private String id;
	@ApiModelProperty(notes = "Date anad time of created doucument", required = true, example = "2018-01-01")
	private LocalDateTime createdDate;
	@ApiModelProperty(notes = "Types of document", required = true, example = "User")
	private String docType;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
}
