/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sumingjie.service;

import com.sumingjie.service.exceptions.AuthException;
import com.sumingjie.service.exceptions.IllegalIdException;
import com.sumingjie.service.exceptions.InsertingException;
import com.sumingjie.service.exceptions.InvalidCreatingParametersException;
import com.sumingjie.service.exceptions.InvalidEntityException;
import com.sumingjie.service.exceptions.InvalidUpdatingParametersException;
import java.util.List;
import java.util.StringTokenizer;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.postgresql.util.Base64;

/**
 *
 * @author Administrator
 */
@Path("/pictures")
@Produces({MediaType.APPLICATION_JSON})
public class PictureResource {

    private static final String AUTH_HEADER_KEY = "Authorization";
    private static final String AUTH_HEADER_PREFIX = "Basic ";

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    @GET
    public List<Picture> find(@Context UriInfo info) {
        String id = info.getQueryParameters().getFirst("id");
        String name = info.getQueryParameters().getFirst("name");
        String author = info.getQueryParameters().getFirst("author");
        String year = info.getQueryParameters().getFirst("year");
        String material = info.getQueryParameters().getFirst("material");
        String height = info.getQueryParameters().getFirst("height");
        String width = info.getQueryParameters().getFirst("width");
        return new PostgreSQLDAO().findPictures(id, name, author, year, material, height, width);
    }

    @GET
    @Path("/{id}")
    public List<Picture> getOne(@PathParam("id") int id) throws IllegalIdException {
        if (id <= 0) {
            throw IllegalIdException.DEFAULT_INSTANCE;
        }

        return new PostgreSQLDAO().findOne(id);
    }

    @POST
    @Consumes("application/json")
    public String create(@Context HttpHeaders headers, Picture picture) throws InvalidCreatingParametersException,
            InsertingException, AuthException {

        if (!isAuthenticated(headers)) {
            throw AuthException.DEFAULT_INSTANCE;
        }

        if (picture.getName() == null || picture.getName().trim().isEmpty()) {
            throw new InvalidCreatingParametersException("Invalid creating parameter: name");
        }

        if (picture.getAuthor() == null || picture.getAuthor().trim().isEmpty()) {
            throw new InvalidCreatingParametersException("Invalid creating parameter: author");
        }

        if (picture.getYear() <= 0) {
            throw new InvalidCreatingParametersException("Invalid creating parameter: year");
        }

        if (picture.getMaterial() == null || picture.getMaterial().trim().isEmpty()) {
            throw new InvalidCreatingParametersException("Invalid creating parameter: material");
        }

        if (picture.getHeight() <= 0) {
            throw new InvalidCreatingParametersException("Invalid creating parameter: height");
        }

        if (picture.getWidth() <= 0) {
            throw new InvalidCreatingParametersException("Invalid creating parameter: width");
        }

        PostgreSQLDAO dao = new PostgreSQLDAO();
        return "{\"result\":" + dao.createPicture(picture) + "}";
    }

    @PUT
    @Consumes("application/json")
    @Path("/{id}")
    public String update(@Context HttpHeaders headers, @PathParam("id") int id, Picture picture) throws IllegalIdException, InvalidEntityException,
            InvalidUpdatingParametersException, AuthException {
        if (!isAuthenticated(headers)) {
            throw AuthException.DEFAULT_INSTANCE;
        }

        if (id <= 0) {
            throw IllegalIdException.DEFAULT_INSTANCE;
        }

        if ((picture.getName() == null || picture.getName().trim().isEmpty()) &&
                (picture.getAuthor() == null || picture.getAuthor().trim().isEmpty()) &&
                (picture.getYear() <= 0) &&
                (picture.getMaterial() == null || picture.getMaterial().trim().isEmpty()) &&
                (picture.getHeight() <= 0) &&
                (picture.getWidth() <= 0)) {
            throw InvalidUpdatingParametersException.DEFAULT_INSTANCE;
        }

        PostgreSQLDAO dao = new PostgreSQLDAO();
        return "{\"result\":" + dao.updatePicture(id, picture) + "}";
    }

    @DELETE
    @Path("/{id}")
    public String delete(@Context HttpHeaders headers, @PathParam("id") int id) throws IllegalIdException,
            InvalidEntityException, AuthException {
        if (!isAuthenticated(headers)) {
            throw AuthException.DEFAULT_INSTANCE;
        }

        if (id <= 0) {
            throw IllegalIdException.DEFAULT_INSTANCE;
        }

        PostgreSQLDAO dao = new PostgreSQLDAO();
        return "{\"result\":" + dao.deletePicture(id) + "}";
    }

    private boolean isAuthenticated(HttpHeaders headers) {
        List<String> authHeader = headers.getRequestHeaders().get(AUTH_HEADER_KEY);
        if (authHeader == null) {
            return false;
        }

        String authToken = authHeader.get(0);
        if (authToken.isEmpty()) {
            return false;
        }

        authToken = authToken.replaceFirst(AUTH_HEADER_PREFIX, "");
        String decodedString = new String(Base64.decode(authToken));
        StringTokenizer stringTokenizer = new StringTokenizer(decodedString, ":");
        String username = stringTokenizer.nextToken();
        String password = stringTokenizer.nextToken();

        return username.equals(USERNAME) && password.equals(PASSWORD);
    }
}
