package com.rituj.userService.UserService.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class SecurityQuestion extends Entity {

	@ApiModelProperty(notes = "Security Question 1", required = true, example = "Question1")
	private Question question1;
	@ApiModelProperty(notes = "Security Question 2", required = true, example = "Question2")
	private Question question2;
	@ApiModelProperty(notes = "Security Question 3", required = true, example = "Question3")
	private Question question3;
	@ApiModelProperty(notes = "User Name for that security question", required = true, example = "thomas123")
	private String userName;

	public Question getQuestion1() {
		return question1;
	}

	public void setQuestion1(Question question1) {
		this.question1 = question1;
	}

	public Question getQuestion2() {
		return question2;
	}

	public void setQuestion2(Question question2) {
		this.question2 = question2;
	}

	public Question getQuestion3() {
		return question3;
	}

	public void setQuestion3(Question question3) {
		this.question3 = question3;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
