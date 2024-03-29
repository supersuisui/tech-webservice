/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sumingjie.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.core.MediaType;


/**
 *
 * @author Administrator
 */
public class App {
        private static final String URL = "http://localhost:8080/rest/pictures";
        
        public static void main(String[] args){
            Client client = Client.create();
            System.out.println("Query:/pictures");
            printList(findPictures(client,URL,""));
            System.out.println();
            
            System.out.println("query:/pictures?author=Леонардо да Винчи");
            printList(findPictures(client,URL,"author=Леонардо да Винчи"));
            System.out.println();
            
            System.out.println("Query: /pictures?author=Леонардо да Винчи&year=1495");
            printList(findPictures(client, URL, "author=Леонардо да Винчи&year=1495"));
            System.out.println();

            System.out.println("Query: /pictures?id=7");
            printList(findPictures(client, URL, "id=7"));
            System.out.println();

            System.out.println("Query: /pictures/7");
            printList(findPictures(client, URL + "/7", ""));
            System.out.println();
        }
    private static List<Picture> findPictures(Client client,String url,String query){
        WebResource webResource  = client.resource(url);
        if(!query.isEmpty()){
            Map<String,String> map = getQueryMap(query);
            
            Set<String> keys = map.keySet();
            for(String key : keys){
                webResource = webResource.queryParam(key,map.get(key));
            }
        }
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if(response.getStatus() != ClientResponse.Status.OK.getStatusCode()){
            throw new IllegalStateException("Requset failed");
        }
        GenericType<List<Picture>> type = new GenericType<List<Picture>>(){
        };
        return response.getEntity(type);
    }
    
    private static Map<String,String> getQueryMap(String query){
        String [] params = query.split("&");
        Map<String,String> map = new HashMap<String,String>();
        for(String param:params){
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name,value);
        }
        return map;
    }
        
    private static void printList(List<Picture> pictures){
        for(Picture picture : pictures){
            System.out.println(picture);
        }
    }
}
