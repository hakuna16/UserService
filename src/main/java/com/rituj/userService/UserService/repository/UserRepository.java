package com.rituj.userService.UserService.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.couchbase.client.java.Bucket;
import com.rituj.userService.UserService.common.CouchbaseHelper;
import com.rituj.userService.UserService.domain.Login;
import com.rituj.userService.UserService.domain.SecurityQuestion;
import com.rituj.userService.UserService.domain.User;
import com.rituj.userService.UserService.transformer.CBJsonObjectTransformer;

@Repository
public class UserRepository {

	@Autowired
	private Bucket bucket;

	@Autowired
	private CouchbaseHelper couchbaseHelper;

	@Autowired
	private CBJsonObjectTransformer cbJsonObjectTransformer;

	public User upsertUser(final User user) {
		Assert.notNull(user, "User can not be null");

		return cbJsonObjectTransformer.toObject(couchbaseHelper.upsertDocument(bucket, user), User.class);
	}

	public SecurityQuestion upsertQuestion(final SecurityQuestion securityQuestion) {
		Assert.notNull(securityQuestion, "Secuirity Question can not be null");

		return cbJsonObjectTransformer.toObject(couchbaseHelper.upsertDocument(bucket, securityQuestion),
				SecurityQuestion.class);
	}

	public SecurityQuestion getSecurityQuestion(final String userId) {
		Assert.hasText(userId, "User id can not be null or empty");

		return cbJsonObjectTransformer.toObject(couchbaseHelper.getJsonDocument(bucket, userId),
				SecurityQuestion.class);
	}

	public Login upsertLogin(final Login login) {
		Assert.notNull(login, "Login can not be null");

		return cbJsonObjectTransformer.toObject(couchbaseHelper.upsertDocument(bucket, login), Login.class);
	}

	public Login getLogin(final String id) {
		Assert.hasText(id, "Id can not be null or empty");

		return cbJsonObjectTransformer.toObject(couchbaseHelper.getJsonDocument(bucket, id), Login.class);
	}

	public User getUser(String userId) {
		Assert.hasText(userId, "user Id can not be null or empty");
		
		return cbJsonObjectTransformer.toObject(couchbaseHelper.getJsonDocument(bucket, userId), User.class);
	}
}
