package com.rituj.userService.UserService.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.rituj.userService.UserService.domain.Entity;
import com.rituj.userService.UserService.exception.InvalidDataException;
import com.rituj.userService.UserService.transformer.CBJsonObjectTransformer;

@Component
public class CouchbaseHelper {

	@Autowired
	private CBJsonObjectTransformer cbJsonObjectTransformer;

	public <T extends Entity> JsonDocument upsertDocument(final Bucket bucket, final T document)
			throws InvalidDataException {
		Assert.notNull(bucket, "Cannot upsert if bucket is null!");
		Assert.notNull(document, "Cannot upsert documents is null!");
		Assert.hasText(document.getId(), "Cannot upsert documentId is Empty!");

		JsonDocument jsonDocument = JsonDocument.create(document.getId(),
				cbJsonObjectTransformer.toJsonObject(document));
		return bucket.upsert(jsonDocument);
	}

}
