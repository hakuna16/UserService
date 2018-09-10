package com.rituj.userService.UserService.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.rituj.userService.UserService.domain.Login;
import com.rituj.userService.UserService.domain.SecurityQuestion;
import com.rituj.userService.UserService.domain.User;
import com.rituj.userService.UserService.exception.InvalidDataException;
import com.rituj.userService.UserService.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@InjectMocks
	private UserController userController;

	@Mock
	private UserService mockedUserService;

	@Test(expected = IllegalArgumentException.class)
	public void testCreateUserProfileWhenUserIsEmpty() throws InvalidDataException {
		userController.createUserProfile(null);
	}

	@Test
	public void testCreateUserProfile() throws InvalidDataException {
		User user = buildUser();

		when(mockedUserService.createUserProfile(user)).thenReturn(user);
		User userExpected = userController.createUserProfile(user);
		validateUser(user,userExpected);
	}
	

	@Test(expected = IllegalArgumentException.class)
	public void testGetUserProfileWhenUserIdIsNull(){
		userController.getUserProfile(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetUserProfileWhenUserIdIsEmpty(){
		userController.getUserProfile("");
	}
	
	@Test
	public void testGetUserProfile(){
		User user = buildUser();
		
		when(mockedUserService.getUserProfile(user.getId())).thenReturn(user);
		User userExpected = userController.getUserProfile(user.getId());
		validateUser(user,userExpected);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateLoginForUserWhenLoginIsNull(){
		userController.createLoginForUser(null);
	}
	
	@Test
	public void testCreateLoginForUser(){
		Login login = builLogin();
		when(mockedUserService.createLoginForUser(login)).thenReturn(login);
		
		userController.createLoginForUser(login);
		assertNotNull(login);
	}
	

	@Test(expected = IllegalArgumentException.class)
	public void testGetLoginWhenIdIaEmpty(){
		userController.getLogin("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetLoginWhenIdIsNull(){
		userController.getLogin(null);
	}

	@Test
	public void testGetLogin(){
		User user = buildUser();
		
		when(mockedUserService.getLogin(user.getId())).thenReturn(builLogin());
		Login login = userController.getLogin(user.getId());
		assertNotNull(login);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetSecurityQuestionsWhenIdIsNull(){
		userController.getSecurityQuestions(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetSecurityQuestionsWhenIdIsEmpty(){
		userController.getSecurityQuestions("");
	}
	
	@Test
	public void testGetSecurityQuestions(){
		User user = buildUser();
		SecurityQuestion question = buildSecurityQuestion();
		
		when(mockedUserService.getSecurityQuestion(user.getId())).thenReturn(question);
		
		SecurityQuestion questionExpected = userController.getSecurityQuestions(user.getId());
		assertNotNull(questionExpected);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetSecurityQuestionsWhenSecurityQuestionIsNull(){
		userController.setSecurityQuestions(null, "user-id");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetSecurityQuestionsWhenUserIdIsNull(){
		userController.setSecurityQuestions(buildSecurityQuestion(), null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetSecurityQuestionsWhenUserIdIsEmpty(){
		userController.setSecurityQuestions(buildSecurityQuestion(), "");
	}
	
	@Test
	public void testSetSecurityQuestions(){
		
		SecurityQuestion question = buildSecurityQuestion();
		
		when(mockedUserService.setSecurityQuestion(question, "userId")).thenReturn(question);
		SecurityQuestion questionExpected = userController.setSecurityQuestions(question, "userId");
		assertNotNull(questionExpected);
		
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testAuthorizeUserWhenUserIdIsEmpty(){
		userController.authorizeUser("", "password");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAuthorizeUserWhenUserIdIsNull(){
		userController.authorizeUser(null, "password");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAuthorizeUserWhenPasswordIsEmpty(){
		userController.authorizeUser("userId", "");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAuthorizeUserWhenPasswordIsNull(){
		userController.authorizeUser("userId", null);
	}
	
	@Test
	public void testAuthorizeUserWhenUserIsEnable(){
		
		when(mockedUserService.authoriseUser("userId", "password")).thenReturn(true);
		
		boolean result = userController.authorizeUser("userId", "password");
		assertTrue(result);
	}
	
	@Test
	public void testAuthorizeUserWhenUserIsNotEnable(){
		
		when(mockedUserService.authoriseUser("userId", "password")).thenReturn(false);
		
		boolean result = userController.authorizeUser("userId", "password");
		assertFalse(result);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIsEnabledWhenUserIsIsNull(){
		userController.isEnabled(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIsEnabledWhenUserIdIsEmpty(){
		userController.isEnabled("");
	}
	
	@Test
	public void testIsEnabledWhenUserIsEnable(){
		
		when(mockedUserService.validateUser("user_id")).thenReturn(true);
		
		boolean result = userController.isEnabled("user_id");
		assertTrue(result);
	}
	
	@Test
	public void testIsEnabledWhenUserIsNotEnable(){
		
		when(mockedUserService.validateUser("user_id")).thenReturn(false);
		
		boolean result = userController.isEnabled("user_id");
		assertFalse(result);
	}
	
	
	private User buildUser(){
		User user = new User();
		user.setFirstName("Rituj");
		user.setLastName("Kumar");
		user.setDocType("user");
		user.setId("user-001");
		return user;
	}
	
	private Login builLogin() {
		Login login = new Login();
		login.setUserName("rituj");
		login.setDocType("user");
		login.setId("USER_001");
		login.setEnabled(true);
		return login;
	}
	
	private void validateUser(User user, User userExpected) {
		assertNotNull(userExpected);
		assertEquals(user.getFirstName(), userExpected.getFirstName());
		assertEquals(user.getLastName(), userExpected.getLastName());
		assertEquals(user.getDocType(), userExpected.getDocType());
		
	}
	
	private SecurityQuestion buildSecurityQuestion() {
		SecurityQuestion securityQuestion = new SecurityQuestion();
		securityQuestion.setDocType("SecurityQuestion");
		securityQuestion.setId("SecurityQuestion_001");
		return securityQuestion;
	}
}
