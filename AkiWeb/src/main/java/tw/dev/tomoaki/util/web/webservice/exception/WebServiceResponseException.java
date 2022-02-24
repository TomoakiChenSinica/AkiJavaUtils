/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.web.webservice.exception;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 *
 * @author tomoaki
 * 從bpm copy 過來 一直以來在想如何解決 Response 時,
 * 對方回傳有錯之問題.因此之前建了這居
 * 
 * 目前在改名 WebServiceResponseException
 * 
 * 其實之前有類似的（難怪想說為啥以前明明有傳進Response卻找不到......之前的是叫JerseyResponseException 在 CardLogManagement底下
 * 
 */
public class WebServiceResponseException extends RuntimeException{
    
//    public Response response;
//    public Integer statusCode;
//    
//    public WebServiceResponseException(){
//        super();
//    }
//    
//    public WebServiceResponseException(String message){
//        super(message);
//    }
//    
//    public WebServiceResponseException(java.lang.Throwable throwable) {
//        super(throwable);
//    }    
//    
//    public WebServiceResponseException(WebTarget webTarget, Response response){
//        super("Error occur when connect to " + webTarget.getUri() + " error Code is " + response.getStatus() + " message = " + response.readEntity(String.class));
//        this.response = response;
//    }
//
//    public Integer getStatusCode() {
//        return statusCode;
//    }
//
//    public void setStatusCode(Integer statusCode) {
//        this.statusCode = statusCode;
//    }
     
    public Response response;
    public Integer statusCode;
    
    public static class Factory {      
        public static WebServiceResponseException create(){
            return new WebServiceResponseException();
        }        
        
        public static WebServiceResponseException create(String msg){
            return new WebServiceResponseException(msg);
        }        
        
        public static WebServiceResponseException create(java.lang.Throwable throwable){
            return new WebServiceResponseException(throwable);
        }                
        
        public static WebServiceResponseException create(WebTarget webTarget) {
            String msg = String.format("Error Occur When Create connection To ", webTarget.getUri());
            return WebServiceResponseException.Factory.create(msg);
        }
        
        public static WebServiceResponseException create(WebTarget webTarget, Response response){
            String msg = String.format("Error Occur When Connect To %s, Error Code= %s, message=%s ", webTarget.getUri(),  response.getStatus(),  response.readEntity(String.class));
            return new WebServiceResponseException(msg);
        }
    }
    
    protected WebServiceResponseException(){
        super();
    }
    
    protected WebServiceResponseException(String message){
        super(message);
    }
    
    protected WebServiceResponseException(java.lang.Throwable throwable) {
        super(throwable);
    }    

    
//    protected WebServiceResponseException(WebTarget webTarget, Response response){
//        super("Error occur when connect to " + webTarget.getUri() + " error Code is " + response.getStatus() + " message = " + response.readEntity(String.class));
//        this.response = response;
//    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
       
    

}