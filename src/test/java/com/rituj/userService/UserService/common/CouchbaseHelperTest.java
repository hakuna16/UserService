package com.rituj.userService.UserService.common;

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
import com.rituj.userService.UserService.domain.User;
import com.rituj.userService.UserService.exception.InvalidDataException;
import com.rituj.userService.UserService.transformer.CBJsonObjectTransformer;

@RunWith(MockitoJUnitRunner.class)
public class CouchbaseHelperTest {

	@InjectMocks
	private CouchbaseHelper couchbaseHelper;

	@Mock
	private Bucket mockedBucket;

	@Mock
	private CBJsonObjectTransformer cbJsonObjectTransformer;

	@Test(expected = IllegalArgumentException.class)
	public void testUpsertDocumentWhenBucketIsNull() throws InvalidDataException {
		couchbaseHelper.upsertDocument(null, new User());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpsertDocumentWhenDocumentIsNull() throws InvalidDataException {
		couchbaseHelper.upsertDocument(mockedBucket, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpsertDocumentWhenDocumentIdIsNull() throws InvalidDataException {
		User user = new User();
		user.setId(null);
		;
		couchbaseHelper.upsertDocument(null, user);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpsertDocumentWhenDocumentIdIsEmpty() throws InvalidDataException {
		User user = new User();
		user.setId("");
		;
		couchbaseHelper.upsertDocument(null, user);
	}

	@Test
	public void testUpsertDocument() throws InvalidDataException {
		User user = new User();
		user.setId("User_001");

		JsonObject jsonObject = JsonObject.create();
		JsonDocument jsonDocument = JsonDocument.create("User_001", jsonObject);

		when(cbJsonObjectTransformer.toJsonObject(user)).thenReturn(jsonObject);
		when(mockedBucket.upsert(jsonDocument)).thenReturn(jsonDocument);

		JsonDocument jsonDocumentExpected = couchbaseHelper.upsertDocument(mockedBucket, user);
		assertNotNull(jsonDocumentExpected);
	}
}
