package com.rituj.userService.UserService.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.rituj.userService.UserService.controller.UserController;
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
		User user = new User();
		user.setFirstName("Rituj");
		user.setLastName("Kumar");
		user.setDocType("user");

		Mockito.when(mockedUserService.createUserProfile(user)).thenReturn(user);
		User userExpected = userController.createUserProfile(user);

		assertNotNull(userExpected);
		assertEquals(user.getFirstName(), userExpected.getFirstName());
		assertEquals(user.getLastName(), userExpected.getLastName());
		assertEquals(user.getDocType(), userExpected.getDocType());

	}
}
