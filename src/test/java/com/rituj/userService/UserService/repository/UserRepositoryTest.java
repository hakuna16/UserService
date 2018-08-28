package com.rituj.userService.UserService.repository;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.rituj.userService.UserService.common.CouchbaseHelper;
import com.rituj.userService.UserService.domain.Login;
import com.rituj.userService.UserService.domain.SecurityQuestion;
import com.rituj.userService.UserService.domain.User;
import com.rituj.userService.UserService.exception.InvalidDataException;
import com.rituj.userService.UserService.transformer.CBJsonObjectTransformer;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {

	@InjectMocks
	private UserRepository userRepository;

	@Mock
	private Bucket mockedBucket;
	@Mock
	private CBJsonObjectTransformer mockedCbJsonObjectTransformer;
	@Mock
	private CouchbaseHelper couchbaseHelper;

	@Test(expected = IllegalArgumentException.class)
	public void testUpsertWheUserIsNull() throws InvalidDataException {
		userRepository.upsertUser(null);
	}

	@Test
	public void testUpsert() throws InvalidDataException {

		User user = new User();
		user.setId("User_001");

		JsonObject jsonObject = JsonObject.create();
		JsonDocument jsonDocument = JsonDocument.create("User_001", jsonObject);

		when(couchbaseHelper.upsertDocument(mockedBucket, user)).thenReturn(jsonDocument);
		when(mockedCbJsonObjectTransformer.toObject(jsonDocument, User.class)).thenReturn(user);

		User userExpected = userRepository.upsertUser(user);
		assertNotNull(userExpected);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpsertQuestionWhenSecurityQuestionIsNull() {
		userRepository.upsertQuestion(null);
	}

	@Test
	public void testUpsertQuestion() {

		SecurityQuestion securityQuestion = new SecurityQuestion();
		securityQuestion.setId("SecurityQuestion_001");
		JsonObject jsonObject = JsonObject.create();
		JsonDocument jsonDocument = JsonDocument.create("SecurityQuestion_001", jsonObject);

		when(couchbaseHelper.upsertDocument(mockedBucket, securityQuestion)).thenReturn(jsonDocument);
		when(mockedCbJsonObjectTransformer.toObject(jsonDocument, SecurityQuestion.class)).thenReturn(securityQuestion);

		SecurityQuestion question = userRepository.upsertQuestion(securityQuestion);

		assertNotNull(question);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetSecurityQuestionWhenUserIdIsEmpty() {
		userRepository.getSecurityQuestion("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetSecurityQuestionWhenUserIdIsNull() {
		userRepository.getSecurityQuestion(null);
	}

	@Test
	public void testGetSecurityQuestion() {
		SecurityQuestion securityQuestion = new SecurityQuestion();
		securityQuestion.setId("SecurityQuestion_001");
		JsonObject jsonObject = JsonObject.create();
		JsonDocument jsonDocument = JsonDocument.create("SecurityQuestion_001", jsonObject);

		when(couchbaseHelper.getJsonDocument(mockedBucket, "SecurityQuestion_001")).thenReturn(jsonDocument);
		when(mockedCbJsonObjectTransformer.toObject(jsonDocument, SecurityQuestion.class)).thenReturn(securityQuestion);

		SecurityQuestion question = userRepository.getSecurityQuestion("SecurityQuestion_001");
		assertNotNull(question);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpsertLoginWhenLodginIsNull() {
		userRepository.upsertLogin(null);
	}

	@Test
	public void testUpsertLogin() {

		Login login = new Login();
		String id = "Login_001";
		login.setId(id);

		JsonObject jsonObject = JsonObject.create();
		JsonDocument jsonDocument = JsonDocument.create(id, jsonObject);

		when(couchbaseHelper.upsertDocument(mockedBucket, login)).thenReturn(jsonDocument);
		when(mockedCbJsonObjectTransformer.toObject(jsonDocument, Login.class)).thenReturn(login);

		Login loginExpected = userRepository.upsertLogin(login);
		assertNotNull(loginExpected);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetLoginWhenIdIsNull() {
		userRepository.getLogin(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetLoginWheIdIsEmpty() {
		userRepository.getLogin("");
	}

	@Test
	public void testGetLogin() {
		Login login = new Login();
		String id = "Login_001";
		login.setId(id);

		JsonObject jsonObject = JsonObject.create();
		JsonDocument jsonDocument = JsonDocument.create(id, jsonObject);

		when(couchbaseHelper.getJsonDocument(mockedBucket, id)).thenReturn(jsonDocument);
		when(mockedCbJsonObjectTransformer.toObject(jsonDocument, Login.class)).thenReturn(login);

		Login loginExpected = userRepository.getLogin(id);
		assertNotNull(loginExpected);
	}
}
