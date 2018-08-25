package com.rituj.userService.UserService.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class Question {

	@ApiModelProperty(notes = "Security question", required = true, example = "What is your fav color!?")
	private String question;
	@ApiModelProperty(notes = "Answer for the question", required = true, example = "red")
	private String answer;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
