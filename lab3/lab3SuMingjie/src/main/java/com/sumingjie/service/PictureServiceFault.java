/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sumingjie.service;

/**
 *
 * @author Administrator
 */
public class PictureServiceFault {
    private static final String DEFAULT_MESSAGE = "Parameter q cannot be null or empty";
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static PictureServiceFault defaultInstance() {
        PictureServiceFault fault = new PictureServiceFault();
        fault.message = DEFAULT_MESSAGE;
        return fault;
    }
}
