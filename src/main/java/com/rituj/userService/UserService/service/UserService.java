package com.rituj.userService.UserService.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.rituj.userService.UserService.domain.Login;
import com.rituj.userService.UserService.domain.SecurityQuestion;
import com.rituj.userService.UserService.domain.User;
import com.rituj.userService.UserService.exception.UserNotEnableException;
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

	public User getUserProfile(final String userId) {
		Assert.hasText(userId, "User Id cant be null or empty");

		return userRepository.getUser(userId);
	}

	public SecurityQuestion setSecurityQuestion(final SecurityQuestion securityQuestion, final String userId) {
		Assert.notNull(securityQuestion, "SecurityQuestion can not be empty or null");
		Assert.hasText(userId, "User Id cant be null or empty");

		setSecurityQuestionProperty(securityQuestion, userId);

		return userRepository.upsertQuestion(securityQuestion);
	}

	public Login createLoginForUser(final Login login) {
		Assert.notNull(login, "Login cant be null");

		setUserLoginProperty(login);
		return userRepository.upsertLogin(login);
	}

	public Login getLogin(final String userId) {
		Assert.hasText(userId, "userId cant be null or empty");

		String id = "Login_" + userId;
		return userRepository.getLogin(id);
	}

	public SecurityQuestion getSecurityQuestion(final String userId) {
		Assert.hasText(userId, "User Id cant be null");

		String questionUserId = "SecurityQuestion_" + userId;
		if (validateUser(userId)) {
			return userRepository.getSecurityQuestion(questionUserId);
		} else {
			throw new UserNotEnableException("User Is Not enbaled");
		}
	}

	public boolean validateUser(final String userId) {
		Assert.hasText(userId, "User Id cant be null");

		Login login = getLogin(userId);
		return login.isEnabled();
	}

	private void setUserProperty(User user) {
		Assert.notNull(user, "User cant be null");

		user.setDocType("User");
		user.setCreatedDate(LocalDateTime.now());
		user.setId(user.getFirstName() + "_" + user.getLastName() + "_" + user.getPhones().get(0).getNumber());
	}

	private void setSecurityQuestionProperty(final SecurityQuestion securityQuestion, final String userId) {
		Assert.notNull(securityQuestion, "SecurityQuestion can not be null");
		Assert.hasText(userId, "User Id cant be empty or null");

		securityQuestion.setUserName(userId);
		securityQuestion.setDocType("SecurityQuestion");
		securityQuestion.setCreatedDate(LocalDateTime.now());
		securityQuestion.setId("SecurityQuestion_" + userId);
	}

	private void setUserLoginProperty(Login login) {
		Assert.notNull(login, "login cant be null");

		login.setCreatedDate(LocalDateTime.now());
		login.setDocType("Login");
		login.setId("Login_" + login.getUserName());

	}

}
