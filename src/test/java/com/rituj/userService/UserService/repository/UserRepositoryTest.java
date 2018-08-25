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
	public void testUpsertWheUserIsNull() throws InvalidDataException{
		userRepository.upsert(null);
	}
	
	@Test
	public void testUpsert() throws InvalidDataException{
		
		User user = new User();
		user.setId("User_001");

		JsonObject jsonObject = JsonObject.create();
		JsonDocument jsonDocument = JsonDocument.create("User_001", jsonObject);
		
		when(couchbaseHelper.upsertDocument(mockedBucket, user)).thenReturn(jsonDocument);
		when(mockedCbJsonObjectTransformer.toObject(jsonDocument, User.class)).thenReturn(user);

		User userExpected = userRepository.upsert(user);
		assertNotNull(userExpected);
	}
	
	
}
