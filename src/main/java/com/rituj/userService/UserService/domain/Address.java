package com.rituj.userService.UserService.domain;

import io.swagger.annotations.ApiModelProperty;

public class Address {

	@ApiModelProperty(notes = "Type of address", required = true, example = "Home")
	private String type;
	@ApiModelProperty(notes = "Adderss Line 1", required = true, example = "House Number")
	private String address1;
	@ApiModelProperty(notes = "Adderss Line 2", required = true, example = "House Name, Street Name")
	private String address2;
	@ApiModelProperty(notes = "Adderss Line 3", required = true, example = "Place Name")
	private String address3;
	@ApiModelProperty(notes = "City name for the Address ", required = true, example = "New York")
	private String city;
	@ApiModelProperty(notes = "State name for the Address", required = true, example = "Any State Name")
	private String state;
	@ApiModelProperty(notes = "Country name for the Address", required = true, example = "USA")
	private String country;
	@ApiModelProperty(notes = "Pin code for the Address", required = true, example = "732-008")
	private String pinCode;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
}
