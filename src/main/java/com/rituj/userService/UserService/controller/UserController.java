package com.rituj.userService.UserService.controller;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rituj.userService.UserService.domain.SecurityQuestion;
import com.rituj.userService.UserService.domain.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
@Api(value = "UserService", description = "Opertions for all User Related Service")
public class UserController {

	@ApiOperation(value = "View user for that userId", response = User.class)
	@RequestMapping(value = "/{userId}", method = GET, produces = APPLICATION_JSON)
	public User getUserProfile(@PathVariable("userId") final String userId) {
		return null;
	}

	@ApiOperation(value = "View Security question for user for the given user Id", response = SecurityQuestion.class)
	@RequestMapping(value = "/{userId}/questions", method = GET, produces = APPLICATION_JSON)
	public @ResponseBody SecurityQuestion getSecurityQuestions(@PathVariable("userId") final String userId) {
		return null;
	}

	@ApiOperation(value = "Add Security question for the give user Id", response = SecurityQuestion.class)
	@RequestMapping(value = "/{userId}/questions", method = POST, consumes = APPLICATION_JSON)
	public SecurityQuestion setSecurityQuestions(@PathVariable("userId") final String userId) {
		return null;
	}

	@ApiOperation(value = "check if user is authorised for the given user Id", response = Boolean.class)
	@RequestMapping(value = "/{userId}/isAuthorized", method = POST, consumes = APPLICATION_JSON)
	public boolean authorizeUser(@RequestParam("userId") final String userId,
			@RequestParam("password") final String password) {
		return false;
	}

	@ApiOperation(value = "Checks if the user login is enabled or not", response = Boolean.class)
	@RequestMapping(value = "/{userId}/enable", method = GET, produces = APPLICATION_JSON)
	public boolean isEnabled(@PathVariable("userId") final String userId) {
		return false;
	}

	@ApiOperation(value = "Updates user Profiles for the given user Id", response = User.class)
	@RequestMapping(value = "/{userId}", method = PUT, produces = APPLICATION_JSON)
	public User updateUserProfile(@PathVariable("userId") final String userId) {
		return null;
	}

	@ApiOperation(value = "Delete user Profiles for the given user Id", response = User.class)
	@RequestMapping(value = "/{userId}", method = DELETE, produces = APPLICATION_JSON)
	public User deleteUserProfile(@PathVariable("userId") final String userId) {
		return null;
	}
	
	@ApiOperation(value = "Creates user Profiles", response = User.class)
	@RequestMapping(value = "/user", method = POST, produces = APPLICATION_JSON)
	public User cerateUserProfile(@RequestParam("user") final User user) {
		return null;
	}
}
