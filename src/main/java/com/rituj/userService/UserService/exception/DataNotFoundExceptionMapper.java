package com.rituj.userService.UserService.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DataNotFoundExceptionMapper  implements ExceptionMapper<UserNotEnableException>{

	@Override
	public Response toResponse(UserNotEnableException exception) {
		return Response.status(403).entity(exception.getMessage()).build();
	}

}
