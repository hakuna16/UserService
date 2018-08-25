package com.rituj.userService.UserService.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.rituj.userService.UserService.domain.SecurityQuestion;
import com.rituj.userService.UserService.domain.User;
import com.rituj.userService.UserService.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User createUserProfile(final User user) {
		Assert.notNull(user, "User can not be null");

		setUserProperty(user);
		return userRepository.upsertUser(user);
	}

	public SecurityQuestion setSecurityQuestion(final SecurityQuestion securityQuestion, final String userId) {
		Assert.notNull(securityQuestion, "user can not be empty or null");
		Assert.hasText(userId, "User Id cant be null");

		setSecurityQuestionProperty(securityQuestion, userId);

		return userRepository.upsertQuestion(securityQuestion);
	}

	public SecurityQuestion getSecurityQuestion(final String userId) {
		Assert.hasText(userId, "User Id cant be null");
		String questionUserId = "SecurityQuestion_" + userId;
		return userRepository.getSecurityQuestion(questionUserId);
	}

	private void setUserProperty(User user) {
		Assert.notNull(user, "User cant be null");

		user.setDocType("user");
		user.setCreatedDate(LocalDateTime.now());
		user.setId(user.getFirstName() + "_" + user.getLastName() + "_" + user.getPhones().get(0).getNumber());
	}

	private void setSecurityQuestionProperty(final SecurityQuestion securityQuestion, final String userId) {
		Assert.notNull(securityQuestion, "user can not be empty or null");
		Assert.hasText(userId, "User Id cant be null");

		securityQuestion.setUserName(userId);
		securityQuestion.setDocType("SecurityQuestion");
		securityQuestion.setCreatedDate(LocalDateTime.now());
		securityQuestion.setId("SecurityQuestion_" + userId);
	}
}
