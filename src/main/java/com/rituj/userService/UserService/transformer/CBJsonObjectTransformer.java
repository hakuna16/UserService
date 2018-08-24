package com.rituj.userService.UserService.transformer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rituj.userService.UserService.domain.Entity;
import com.rituj.userService.UserService.exception.InvalidDataException;

@Component
public class CBJsonObjectTransformer {

	@Autowired
	private ObjectMapper objectMapper;

	public <T extends Entity> JsonObject toJsonObject(final T object) throws InvalidDataException {
		Assert.notNull(object, "Cannot transform null object!");
		Assert.hasText(object.getId(), "Entity id cannot be null/empty!");

		try {
			return JsonObject.fromJson(objectMapper.writeValueAsString(object));
		} catch (JsonProcessingException e) {
			throw new InvalidDataException("Failed to serialize json document for id " + object.getId(), e);
		}
	}

	public <T> T toObject(final JsonDocument jsonDocument, final Class<T> type) throws InvalidDataException {
		Assert.notNull(jsonDocument, "Cannot transform to object for null jsonDocument object!");
		Assert.notNull(type, "Cannot transform to null type!");
		try {
			return objectMapper.readValue(jsonDocument.content().toString(), type);
		} catch (IOException e) {
			throw new InvalidDataException("Failed to de-serialize json document for id " + jsonDocument.id(), e);
		}
	}
}
