package com.rituj.userService.UserService.transformer;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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

	/**
     * Transforms given list of {@link JsonDocument} objects to a list of Objects of given type {@code <T>}.
     *
     * @param jsonDocuments list of {@link JsonDocument} objects to be converted
     * @param type {@link Class} type to which the Json Objects are to be converted to
     * @param <T> type of the object to be converted to
     * @return list of objects of type {@code <T>}
     */
	public <T> List<T> toObjects(final List<JsonDocument> jsonDocuments, final Class<T> type) {
		Assert.notEmpty(jsonDocuments, "Cannot transform to object for null or empty jsonDocuments object!");
		Assert.notNull(type, "Cannot transform null object!");
		return jsonDocuments.stream().map(jsonDocument -> toObject(jsonDocument, type)).collect(Collectors.toList());
	}
    
    /**
     * Transforms given {@link JsonDocument} object to Object of given type {@code <T>}.
     * 
     * @param jsonDocument {@link JsonDocument} object to be converted
     * @param type {@link Class} type to which the Json Objects are to be converted to
     * @param <T> type of the object to be converted to
     * @return converted object of type {@code <T>}
     */
	public <T> T toObject(final JsonDocument jsonDocument, final Class<T> type) {
		Assert.notNull(jsonDocument,"Cannot transform to object for null jsonDocument object!");
		Assert.notNull(type,"Cannot transform to null type!");
		try {
			return objectMapper.readValue(jsonDocument.content().toString(), type);
		} catch (IOException e) {
			throw new InvalidDataException("Failed to de-serialize json document for id " + jsonDocument.id(), e);
		}
	}

    /**
     * Transforms given list of objects to list of {@link JsonObject} objects.
     *
     * @param objects the list of objects to be transformed
     * @param <T> the type of the objects to be transformed
     * @return list of {@link JsonObject} objects created from given object of type T
     * @throws IllegalArgumentException if the given data is invalid
     */
    public <T extends Entity> List<JsonObject> toJsonObjects(final List<T> objects) {
        Assert.notNull(objects,"Cannot transform to null type!");
        return objects.stream().map(object -> toJsonObject(object)).collect(Collectors.toList());
    }

    /**
     * Transforms given object to {@link JsonObject} instance.
     *
     * @param object the object to be transformed
     * @param <T> the type of the object to be transformed
     * @return {@link JsonObject} created from given object of type T
     * @throws IllegalArgumentException if the given data is invalid
     */
    public <T extends Entity> JsonObject toJsonObject(final T object) {
        Assert.notNull(object, "Cannot transform null object!");
        Assert.hasText(object.getId(), "Entity id cannot be null/empty!");

        try {
            return JsonObject.fromJson(objectMapper.writeValueAsString(object));
        } catch (JsonProcessingException e) {
            throw new InvalidDataException("Failed to serialize json document for id " + object.getId(), e);
        }
    }
}
