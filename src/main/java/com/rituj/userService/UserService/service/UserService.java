package com.rituj.userService.UserService.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.rituj.userService.UserService.domain.User;
import com.rituj.userService.UserService.exception.InvalidDataException;
import com.rituj.userService.UserService.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User createUserProfile(final User user) throws InvalidDataException {
		Assert.notNull(user, "User can not be null");
		
		setUserId(user);
		return userRepository.upsert(user);
	}

	private void setUserId(User user) {
		Assert.notNull(user, "User cant be null");
		
		user.setDocType("user");
		user.setCreatedDate(LocalDateTime.now());
		user.setId(user.getFirstName() + "_" + user.getLastName() + "_" + user.getPhones().get(0).getNumber());
	}
}
