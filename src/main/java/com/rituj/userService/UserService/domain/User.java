package com.rituj.userService.UserService.domain;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class User extends Entity {
	@ApiModelProperty(notes = "user first name", required = true, example = "Thomas")
	private String firstName;
	@ApiModelProperty(notes = "user middle name", required = true, example = "Thomas")
	private String middleName;
	@ApiModelProperty(notes = "user last name", required = true, example = "Luke")
	private String lastName;
	@ApiModelProperty(notes = "user list of address", required = true, example = "[]")
	private List<Address> addresses;
	@ApiModelProperty(notes = "user list of phone numbers", required = true, example = "[]")
	private List<Phone> phones;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

}
