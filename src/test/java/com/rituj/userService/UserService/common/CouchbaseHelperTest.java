package com.rituj.userService.UserService.common;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveDocumentWhenBucketIsNull(){
		couchbaseHelper.removeDocument(null, "user_001");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveDocumentWhenIdIsNull(){
		couchbaseHelper.removeDocument(mockedBucket, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveDocumentWhenIdIsEmpty(){
		couchbaseHelper.removeDocument(mockedBucket, "");
	}
	
	@Test
	public void testRemoveDocument(){
		JsonObject jsonObject = JsonObject.create();
		JsonDocument jsonDocument = JsonDocument.create("User_001", jsonObject);
		
		when(mockedBucket.remove("user_001")).thenReturn(jsonDocument);
		JsonDocument jsonDocumentExpected = couchbaseHelper.removeDocument(mockedBucket, "user_001");
		assertNotNull(jsonDocumentExpected);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWhenBucketIsNull(){
		List<String> ids = new ArrayList<>();
		ids.add("user_001");
		couchbaseHelper.remove(null, ids);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveWhenIdIsNull(){
		couchbaseHelper.remove(mockedBucket, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveWhenIdIsEmpty(){
		List<String> ids = new ArrayList<>();
		couchbaseHelper.remove(mockedBucket, ids);
	}
	
	@Test
	public void testRemove(){
		List<String> documentIds = getDocumentIds();
		
		JsonObject jsonObject = JsonObject.create();
		JsonDocument jsonDocument = JsonDocument.create("User_001", jsonObject);
		
		when(mockedBucket.remove("user_001")).thenReturn(jsonDocument);
		List<JsonDocument> jsonDocuments = couchbaseHelper.remove(mockedBucket, documentIds);
		assertNotNull(jsonDocuments);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetJsonDocumentWhenIdIsEmpty(){
		couchbaseHelper.getJsonDocument(mockedBucket, "");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetJsonDocumentWhenIdIsNull(){
		couchbaseHelper.getJsonDocument(mockedBucket, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetJsonDocumentWhenBucketIsNull(){
		couchbaseHelper.getJsonDocument(null, "user_001");
	}
	
	@Test
	public void testGetJsonDocument(){
		JsonObject jsonObject = JsonObject.create();
		JsonDocument jsonDocument = JsonDocument.create("User_001", jsonObject);
		
		when(mockedBucket.get("user_001")).thenReturn(jsonDocument);
		JsonDocument jsonDocumentExpected = couchbaseHelper.getJsonDocument(mockedBucket, "user_001");
		assertNotNull(jsonDocumentExpected);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetWhenIdIsEmpty(){
		List<String> ids = new ArrayList<>(); 
		couchbaseHelper.get(mockedBucket, ids);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetWhenIdIsNull(){
		couchbaseHelper.get(mockedBucket, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetWhenBucketIsNull(){
		couchbaseHelper.get(null, getDocumentIds());
	}
	
	@Test
	public void testGet(){
		List<String> documentIds = getDocumentIds();
		
		JsonObject jsonObject = JsonObject.create();
		JsonDocument jsonDocument = JsonDocument.create("User_001", jsonObject);
		
		List<JsonDocument>  jsonDocuments = couchbaseHelper.get(mockedBucket, documentIds);
		assertNotNull(jsonDocuments);
	}
	
	private List<String> getDocumentIds(){
		String id1 = "user_001";
		List<String> ids = new ArrayList<>(2);
		ids.add(id1);
		return ids;
	}
}
