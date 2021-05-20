/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sumingjie.serviceexception;

import com.sumingjie.service.PictureServiceFault;
import javax.xml.ws.WebFault;

/**
 *
 * @author Administrator
 */
@WebFault(faultBean="com.sumingjie.service.PictureServiceFault")
public class InvalidEntityException extends Exception{
    private static final long serialVersionUID  = -6647544772732631047L;
    private final PictureServiceFault fault;
    public InvalidEntityException(String message,PictureServiceFault fault){
        super(message);
        this.fault = fault;
    }
    public InvalidEntityException(String message,PictureServiceFault fault,Throwable cause){
        super(message,cause);
        this.fault = fault;
    }
    public PictureServiceFault getFaultInfo(){
        return fault;
    }
    
}
