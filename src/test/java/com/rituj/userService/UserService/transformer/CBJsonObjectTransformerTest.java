package com.rituj.userService.UserService.transformer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rituj.userService.UserService.domain.Login;
import com.rituj.userService.UserService.domain.User;

@RunWith(MockitoJUnitRunner.class)
public class CBJsonObjectTransformerTest {

	@Mock
	private ObjectMapper objectMapper;
	
	@InjectMocks
	private CBJsonObjectTransformer cbJsonObjectTransformer;
	
	@Test(expected = IllegalArgumentException.class)
	public void testToJsonObjectWhenObjectIsNull(){
		cbJsonObjectTransformer.toJsonObject(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testToJsonObjectWhenIdIsEmpty(){
		Login login = buildLogin();
		login.setId("");
		cbJsonObjectTransformer.toJsonObject(login);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testToJsonObjectWhenIdIsNull(){
		Login login = buildLogin();
		login.setId(null);
		cbJsonObjectTransformer.toJsonObject(login);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testToJsonObjectWhenJsonIsWrong() throws JsonProcessingException{
		Login login = buildLogin();
		login.setId("ID_001");
		Mockito.when(objectMapper.writeValueAsString(login)).thenReturn("asdasda");
		cbJsonObjectTransformer.toJsonObject(login);
	}
	
	@Test
	public void testToJsonObject() throws JsonProcessingException{
		Login login = buildLogin();
		login.setId("ID_001");
		String json = " {"
				+ " }";
		when(objectMapper.writeValueAsString(login)).thenReturn(json);
		JsonObject object = cbJsonObjectTransformer.toJsonObject(login);
		assertNotNull(object);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testToJsonObjectsWhenIdIsNull() throws JsonProcessingException{
		
		List<Login> logins = buildLoginList();
		cbJsonObjectTransformer.toJsonObjects(logins);
	}
	
	@Test
	public void testToJsonObjects() throws JsonProcessingException{
		
		List<Login> logins = buildLoginListForIDs();
		String json = " {"
				+ " }";
		when(objectMapper.writeValueAsString(logins.get(0))).thenReturn(json);
		List<JsonObject> objects = cbJsonObjectTransformer.toJsonObjects(logins);
		assertNotNull(objects);
	}
	
	@Test
	public void testToObject(){
		User user = new User();
		user.setId("User_001");

		JsonObject jsonObject = JsonObject.create();
		jsonObject.put("userName", true);
		JsonDocument jsonDocument = JsonDocument.create("User_001", jsonObject);
		User userExpected = cbJsonObjectTransformer.toObject(jsonDocument, User.class);
		assertNull(userExpected);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testToObjectWithException(){
		cbJsonObjectTransformer.toObject(null, Login.class);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testToObjectWhenTypeIsNull(){
		User user = new User();
		user.setId("User_001");

		JsonObject jsonObject = JsonObject.create();
		jsonObject.put("userName", true);
		JsonDocument jsonDocument = JsonDocument.create("User_001", jsonObject);
		cbJsonObjectTransformer.toObject(jsonDocument, null);
	}
	
	@Test
	public void testToObjects(){
		User user = new User();
		user.setId("User_001");

		JsonObject jsonObject = JsonObject.create();
		jsonObject.put("userName", true);
		JsonDocument jsonDocument = JsonDocument.create("User_001", jsonObject);
		List<JsonDocument> jsonDocuments = new ArrayList<>();
		jsonDocuments.add(jsonDocument);
		List<User> users = cbJsonObjectTransformer.toObjects(jsonDocuments, User.class);
		assertNull(users.get(0));
	}
	
	private Login buildLogin() {
		Login login = new Login();
		return login;
	}
	
	private List<Login> buildLoginList(){
		List<Login> logins = new ArrayList<>();
		Login login = buildLogin();
		logins.add(login);
		return logins;
	}
	
	private List<Login> buildLoginListForIDs(){
		List<Login> logins = new ArrayList<>();
		Login login = buildLogin();
		login.setId("Login_001");
		logins.add(login);
		return logins;
	}
	
}
