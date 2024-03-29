package com.sumingjie.service.exceptions;

import com.sumingjie.service.BasicResponse;
import com.sun.jersey.api.client.ClientResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidCreatingParametersMapper implements ExceptionMapper<InvalidCreatingParametersException> {

    @Override
    public Response toResponse(InvalidCreatingParametersException e) {
        return Response.status(ClientResponse.Status.BAD_REQUEST).entity(new BasicResponse(e.getMessage())).type(MediaType.APPLICATION_JSON).build();
    }
}
