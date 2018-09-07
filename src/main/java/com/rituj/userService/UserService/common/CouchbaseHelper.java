package com.rituj.userService.UserService.common;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.rituj.userService.UserService.domain.Entity;
import com.rituj.userService.UserService.transformer.CBJsonObjectTransformer;

@Component
public class CouchbaseHelper {

	@Autowired
	private CBJsonObjectTransformer cbJsonObjectTransformer;

	/**
	 * Inserts OR Updates given {@link Entity} object to the given
	 * {@link Bucket}. If the document exists for the document id, the document
	 * will get updated; else throws an {@link IllegalArgumentException}.
	 *
	 * @param bucket
	 *            instance of the couchbase bucket where documents are to be
	 *            upserted
	 * @param document
	 *            {@link Entity} to be inserted or updated
	 * @return updated {@link JsonDocument} object
	 * @throws IllegalArgumentException
	 *             if document id is missing
	 */
	public <T extends Entity> JsonDocument upsertDocument(final Bucket bucket, final T document) {
		Assert.notNull(bucket, "Cannot upsert if bucket is null!");
		Assert.notNull(document, "Cannot upsert documents is null!");
		Assert.hasText(document.getId(), "Cannot upsert documentId is Empty!");

		JsonDocument jsonDocument = JsonDocument.create(document.getId(),
				cbJsonObjectTransformer.toJsonObject(document));
		return bucket.upsert(jsonDocument);
	}

	/**
	 * Fetches a list of {@link JsonDocument} objects for given list of document
	 * ids from couchbase DB from given bucket instance.
	 *
	 * @param bucket
	 *            instance of the couchbase bucket
	 * @param documentIds
	 *            list of document ids which needs to be fetched
	 * @return list of {@link JsonDocument} objects
	 */
	public List<JsonDocument> get(final Bucket bucket, final List<String> documentIds) {
		Assert.notNull(bucket, "Cannot fetch if bucket is null!");
		Assert.notEmpty(documentIds, "Cannot get json document for empty list of document ids!");

		return documentIds.stream().map(documentId -> getJsonDocument(bucket, documentId)).collect(Collectors.toList());
	}

	/**
	 * Fetches a {@link JsonDocument} object for given document id from
	 * couchbase DB from given bucket instance.
	 *
	 * @param bucket
	 *            instance of the couchbase bucket
	 * @param documentId
	 *            document id which needs to be fetched
	 * @return {@link JsonDocument} object
	 */
	public JsonDocument getJsonDocument(final Bucket bucket, final String documentId) {
		Assert.notNull(bucket, "Cannot fetch if bucket is null!");
		Assert.notNull(documentId, "Cannot get json document for null document id!");
		return bucket.get(documentId);
	}

	/**
	 * Delete a list of documents for given list of document ids from couchbase
	 * DB from given bucket instance. Returns list of {@link JsonDocument}
	 * objects that have been removed.
	 *
	 * @param bucket
	 *            instance of the couchbase bucket
	 * @param documentIds
	 *            list of document ids which needs to be removed
	 * @return list of {@link JsonDocument} objects
	 */
	public List<JsonDocument> remove(final Bucket bucket, final List<String> documentIds) {
		Assert.notNull(bucket, "Cannot fetch if bucket is null!");
		Assert.notEmpty(documentIds, "Cannot delete json document for empty list of document ids!");

		return documentIds.stream().map(documentId -> removeDocument(bucket, documentId)).collect(Collectors.toList());
	}

	/**
	 * Delete a document for given document id from couchbase DB from given
	 * bucket instance. Returns {@link JsonDocument} object that has been
	 * removed.
	 *
	 * @param bucket
	 *            instance of the couchbase bucket
	 * @param documentId
	 *            list of document id which needs to be removed
	 * @return {@link JsonDocument} object
	 */
	public JsonDocument removeDocument(final Bucket bucket, final String documentId) {
		Assert.notNull(bucket, "Cannot fetch if bucket is null!");
		Assert.hasText(documentId, "Cannot delete json document for blank document id!");

		return bucket.remove(documentId);
	}
}
