package com.rituj.userService.UserService.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.rituj.userService.UserService.domain.Login;
import com.rituj.userService.UserService.domain.Phone;
import com.rituj.userService.UserService.domain.SecurityQuestion;
import com.rituj.userService.UserService.domain.User;
import com.rituj.userService.UserService.exception.InvalidDataException;
import com.rituj.userService.UserService.exception.UserNotEnableException;
import com.rituj.userService.UserService.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	private UserService userService;

	@Mock
	private UserRepository userRepository;

	@Test(expected = IllegalArgumentException.class)
	public void testCreateUserProfileWhenUserIsEmpty() throws InvalidDataException {
		userService.createUserProfile(null);
	}

	@Test
	public void testCreateUserProfile() throws InvalidDataException {

		User user = buildUser();

		List<Phone> phones = new ArrayList<>();
		phones.add(buildPhone());
		user.setPhones(phones);

		when(userRepository.upsertUser(user)).thenReturn(user);

		User userExpected = userService.createUserProfile(user);

		assertNotNull(userExpected);
		assertEquals(user.getFirstName(), userExpected.getFirstName());
		assertEquals(user.getLastName(), userExpected.getLastName());
		assertEquals(user.getDocType(), userExpected.getDocType());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetSecurityQuestionWhenSecurityQuestionIsNull() {
		userService.setSecurityQuestion(null, "User_001");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetSecurityQuestionWhenUserIdIsNull() {
		SecurityQuestion securityQuestion = buildSecurityQuestion();
		userService.setSecurityQuestion(securityQuestion, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetSecurityQuestionWhenUserIdIsEmpty() {
		SecurityQuestion securityQuestion = buildSecurityQuestion();
		userService.setSecurityQuestion(securityQuestion, "");
	}

	@Test
	public void testSetSecurityQuestion() {
		SecurityQuestion securityQuestion = buildSecurityQuestion();

		when(userRepository.upsertQuestion(securityQuestion)).thenReturn(securityQuestion);
		SecurityQuestion question = userService.setSecurityQuestion(securityQuestion, "User_001");
		assertNotNull(question);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateLoginForUserWhenLoginIsNull() {
		userService.createLoginForUser(null);
	}

	@Test
	public void testCreateLoginForUser() {
		Login login = buildLogin();
		when(userRepository.upsertLogin(login)).thenReturn(login);
		Login loginExpected = userService.createLoginForUser(login);
		assertNotNull(loginExpected);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetLoginWhenUserIdIsNull() {
		userService.getLogin("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetLoginWhenUserIdIsEmpty() {
		userService.getLogin("");
	}

	@Test
	public void testGetLogin() {
		String id = "User_001";
		when(userRepository.getLogin(Mockito.anyString())).thenReturn(buildLogin());
		Login login = userService.getLogin(id);
		assertNotNull(login);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetSecurityQuestionWhenUserIdIsNull() {
		userService.getSecurityQuestion(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetSecurityQuestionWhenUserIdIsEmpty() {
		userService.getSecurityQuestion("");
	}

	@Test(expected = UserNotEnableException.class)
	public void testGetSecurityQuestionWhenUserIsNotEnabled() {
		String id = "User_001";
		Login login = buildLogin();

		when(userRepository.getLogin("Login_" + id)).thenReturn(login);

		SecurityQuestion securityQuestion = userService.getSecurityQuestion(id);
		assertNull(securityQuestion);
	}

	@Test
	public void testGetSecurityQuestionWhenUserIsEnabled() {
		String id = "User_001";
		SecurityQuestion question = buildSecurityQuestion();
		Login login = buildLogin();
		login.setEnabled(true);

		when(userRepository.getLogin("Login_" + id)).thenReturn(login);
		when(userRepository.getSecurityQuestion("SecurityQuestion_User_001")).thenReturn(question);

		SecurityQuestion securityQuestion = userService.getSecurityQuestion(id);
		assertNotNull(securityQuestion);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetUserProfileWhenIdIsNull() {
		userService.getUserProfile(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetUserProfileWhenIdIsEmpty() {
		userService.getUserProfile("");
	}

	@Test
	public void testGetUserProfile() {
		String userId = "USER_001";
		User user = new User();
		user.setId(userId);
		when(userRepository.getUser(userId)).thenReturn(user);
		User expectedUser = userService.getUserProfile(userId);
		assertNotNull(expectedUser);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAuthoriseUserWheUserIdIsNull() {
		String password = "password";
		userService.authoriseUser(null, password);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAuthoriseUserWheUserIdIsEmpty() {
		String password = "password";
		userService.authoriseUser("", password);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAuthoriseUserWhePasswordIsEmpty() {
		String userId = "USER_001";
		userService.authoriseUser(userId, "");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAuthoriseUserWhenPasswordIsNull() {
		String userId = "USER_001";
		userService.authoriseUser(userId, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testauthoriseUserWhenBothIsEmpty() {
		userService.authoriseUser("", "");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testauthoriseUserWhenBothIsNull() {
		userService.authoriseUser(null, null);
	}

	@Test
	public void testAuthoriseUserWhenUserIsNotEnabld() {
		String userId = "USER_001";
		String password = "password";

		Login login = buildLogin();

		when(userRepository.getLogin("Login_" + userId)).thenReturn(login);

		boolean result = userService.authoriseUser(userId, password);
		assertFalse(result);
	}

	@Test
	public void testAuthoriseUserWhenUserIsEnabled() {
		String userId = "USER_001";
		String password = "password";

		Login login = buildLogin();
		login.setEnabled(true);
		login.setPassword(password);

		when(userRepository.getLogin("Login_" + userId)).thenReturn(login);

		boolean result = userService.authoriseUser(userId, password);
		assertTrue(result);
	}

	@Test
	public void testAuthoriseUserWhenUserIsEnabledAndPasswordIsFalse() {
		String userId = "USER_001";
		String password = "password";

		Login login = buildLogin();
		login.setEnabled(true);
		login.setPassword("");

		when(userRepository.getLogin("Login_" + userId)).thenReturn(login);

		boolean result = userService.authoriseUser(userId, password);
		assertFalse(result);
	}

	private Login buildLogin() {
		Login login = new Login();
		login.setEnabled(false);
		login.setId("Login_001");
		return login;
	}

	private Phone buildPhone() {
		Phone phone = new Phone();
		phone.setNumber("8951165691");
		phone.setType("Home");
		return phone;
	}

	private SecurityQuestion buildSecurityQuestion() {
		SecurityQuestion securityQuestion = new SecurityQuestion();
		securityQuestion.setDocType("SecurityQuestion");
		securityQuestion.setId("SecurityQuestion_001");
		return securityQuestion;
	}
	
	private User buildUser(){
		User user = new User();
		user.setFirstName("Rituj");
		user.setLastName("Kumar");
		user.setDocType("user");
		return user;
	}
}
