/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sumingjie.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class WebServiceClient {
    public static void main(String[] args)throws MalformedURLException{
        URL url = new URL("http://localhost:8080/PictureService?wsdl");
        PictureService pictureService = new PictureService(url);
        List<Picture> picture = pictureService.getPictureWebServicePort().getAllPictures();
        for(Picture p:picture){
            System.out.println(p.toString());
            System.out.println("ID:"+p.getId()+",author:"+p.getAuthor()+",name:"+p.getName()+",year:"+p.getYear()+",material:"+p.getMaterial()+",height:"+p.getHeight()+",width:"+p.getWidth());
                    
        }
        System.out.println("Total pictures:"+picture.size());
    }
    
}
