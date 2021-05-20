/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sumingjie.service;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlRootElement(name="BasicResponce")
public class BasicResponse {

    public String internalStatus;

    public String message;

    public BasicResponse() {}

    public BasicResponse(String message){
        this.internalStatus = "Error";
        this.message = message;
    }
}
