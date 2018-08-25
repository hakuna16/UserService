package com.rituj.userService.UserService.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.rituj.userService.UserService.domain.Phone;
import com.rituj.userService.UserService.domain.User;
import com.rituj.userService.UserService.exception.InvalidDataException;
import com.rituj.userService.UserService.repository.UserRepository;
import com.rituj.userService.UserService.service.UserService;

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

		User user = new User();
		user.setFirstName("Rituj");
		user.setLastName("Kumar");
		user.setDocType("user");

		java.util.List<Phone> phones = new ArrayList<>();
		phones.add(buildPhone());
		user.setPhones(phones);

		when(userRepository.upsert(user)).thenReturn(user);

		User userExpected = userService.createUserProfile(user);

		assertNotNull(userExpected);
		assertEquals(user.getFirstName(), userExpected.getFirstName());
		assertEquals(user.getLastName(), userExpected.getLastName());
		assertEquals(user.getDocType(), userExpected.getDocType());
	}

	private Phone buildPhone() {
		Phone phone = new Phone();
		phone.setNumber("8951165691");
		phone.setType("Home");
		return phone;
	}
}
