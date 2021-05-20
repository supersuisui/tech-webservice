
package com.sumingjie.client;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "InsertingException", targetNamespace = "http://service.sumingjie.com/")
public class InsertingException
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private PictureServiceFault faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public InsertingException(String message, PictureServiceFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public InsertingException(String message, PictureServiceFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.sumingjie.client.PictureServiceFault
     */
    public PictureServiceFault getFaultInfo() {
        return faultInfo;
    }

}
