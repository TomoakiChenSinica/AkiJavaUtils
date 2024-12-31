/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.web.webservice;

import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.MultiPart;
import tw.dev.tomoaki.util.web.webservice.exception.WebServiceResponseException;
//import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

/**
 *
 * @author Tomoaki Chen
 */
/**
 *
 * @author Tomoaki Chen
 */
public abstract class BasicRestClient {

//    protected <RESULT> RESULT obtainResultByGet(WebTarget target, MediaType[] acceptMediaTypes, String bearerToken, List<String> cookies) {
//        Response response = this.doGet(target, acceptMediaTypes, bearerToken, cookies);
//        Integer statusCode = response.getStatus();
//        if (statusCode / 100 == 2) {
//            String result = response.readEntity(String.class);
//            response.close();
//            client.close();
//            return result;
//        } else {
//            WebServiceResponseException ex = WebServiceResponseException.Factory.create(target, response);
//            response.close();
//            client.close();
//            throw ex;
//        }
//    }

//<editor-fold defaultstate="collapsed" desc="共用的底層，doGet() 系列">
    // [FIXME202412181117] doGet(WebTarget target, MediaType acceptMediaType) 這種要不要用 Varargs(doGet(WebTarget target, MediaType acceptMediaType))    
    protected Response doGet(WebTarget target, MediaType acceptMediaType) {
        MediaType[] acceptMediaTypes = {acceptMediaType};
        return this.doGet(target, acceptMediaTypes, null, null);
    }

    protected Response doGet(WebTarget target, MediaType[] acceptMediaTypes) {
        return this.doGet(target, acceptMediaTypes, null, null);
    }

    protected Response doGet(WebTarget target, MediaType acceptMediaType, String bearerToken) {
        MediaType[] acceptMediaTypes = {acceptMediaType};
        return this.doGet(target, acceptMediaTypes, bearerToken, null);
    }

    protected Response doGet(WebTarget target, MediaType acceptMediaType, String bearerToken, List<String> cookies) {
        MediaType[] acceptMediaTypes = {acceptMediaType};
        return this.doGet(target, acceptMediaTypes, bearerToken, cookies);
    }

    protected Response doGet(WebTarget target, MediaType[] acceptMediaTypes, String bearerToken, List<String> cookies) {
        Response resp = null;
        try {
            Invocation.Builder builder = target.request().accept(acceptMediaTypes);
            if (bearerToken != null) {
                builder.header("Authorization", "Bearer " + bearerToken);
            }

            if (cookies != null) {
                for (String cookie : cookies) {
                    builder.header("Cookie", cookie);
                }
            }
            return builder.get();
        } catch (Exception ex) {
            WebServiceResponseException resultEx = (resp == null) ? WebServiceResponseException.Factory.create(target, ex) : WebServiceResponseException.Factory.create(target, resp);
            throw resultEx;
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="共用的底層，doPut() 系列">
    protected <T> Response doPut(WebTarget target, MediaType acceptMediaType, MediaType requestMediaType, T entity) {
        MediaType[] acceptMediaTypes = {acceptMediaType};
        return this.doPut(target, acceptMediaTypes, requestMediaType, null, entity);
    }

    protected <T> Response doPut(WebTarget target, MediaType acceptMediaType, MediaType requestMediaType, String bearerToken, T entity) {
        MediaType[] acceptMediaTypes = {acceptMediaType};
        return this.doPut(target, acceptMediaTypes, requestMediaType, bearerToken, null, entity);
    }

    protected <T> Response doPut(WebTarget target, MediaType acceptMediaType, MediaType requestMediaType, String bearerToken, List<String> cookies, T entity) {
        MediaType[] acceptMediaTypes = {acceptMediaType};
        return this.doPut(target, acceptMediaTypes, requestMediaType, bearerToken, cookies, entity);
    }

    protected <T> Response doPut(WebTarget target, MediaType[] acceptMediaTypes, MediaType requestMediaType, T entity) {
        return this.doPut(target, acceptMediaTypes, requestMediaType, null, null, entity);
    }

    protected <T> Response doPut(WebTarget target, MediaType[] acceptMediaTypes, MediaType requestMediaType, List<String> cookies, T entity) {
        return this.doPut(target, acceptMediaTypes, requestMediaType, null, cookies, entity);
    }

    protected <T> Response doPut(WebTarget target, MediaType[] acceptMediaTypes, MediaType requestMediaType, String bearerToken, List<String> cookies, T entity) {
        Response resp = null;
        try {
            Invocation.Builder builder = target.request().accept(acceptMediaTypes);
            if (bearerToken != null) {
                builder.header("Authorization", "Bearer " + bearerToken);
            }

            if (cookies != null) {
                for (String cookie : cookies) {
                    builder.header("Cookie", cookie);
                }
            }
            return builder.put(Entity.entity(entity, requestMediaType));
        } catch (Exception ex) {
            WebServiceResponseException resultEx = (resp == null) ? WebServiceResponseException.Factory.create(target, ex) : WebServiceResponseException.Factory.create(target, resp);
            throw resultEx;
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="共用的底層，doPost() 系列">
//    protected <T> Response doPostMultiForm(WebTarget target, MediaType[] acceptMediaTypes, List<String> cookies, String bearerToken, MultipartBody  form) {
//        return this.doPost(target, acceptMediaTypes, MediaType.MULTIPART_FORM_DATA, bearerToken, cookies);        
//    }
    protected <T> Response doPostMultiPartForm(WebTarget target, MediaType acceptMediaType, MultiPart multiPart) {
        return this.doPost(target, acceptMediaType, null, null, multiPart);
    }

    protected <T> Response doPostMultiPartForm(WebTarget target, MediaType acceptMediaType, String bearerToken, List<String> cookies, MultiPart multiPart) {
        MediaType[] acceptMediaTypes = {acceptMediaType};
        return this.doPost(target, acceptMediaTypes, acceptMediaType, bearerToken, cookies, multiPart);
    }

    protected <T> Response doPostMultiPartForm(WebTarget target, MediaType[] acceptMediaTypes, String bearerToken, List<String> cookies, MultiPart multiPart) {
        return this.doPost(target, acceptMediaTypes, MediaType.MULTIPART_FORM_DATA_TYPE, bearerToken, cookies, multiPart);
    }

    protected <T> Response doPostForm(WebTarget target, MediaType acceptMediaType, Form form) {
        MediaType[] acceptMediaTypes = {acceptMediaType};
        return this.doPostForm(target, acceptMediaTypes, null, null, form);
    }

    protected <T> Response doPostForm(WebTarget target, MediaType acceptMediaType, String bearerToken, List<String> cookies, Form form) {
        MediaType[] acceptMediaTypes = {acceptMediaType};
        return this.doPostForm(target, acceptMediaTypes, bearerToken, cookies, form);
    }

    protected <T> Response doPostForm(WebTarget target, MediaType[] acceptMediaTypes, String bearerToken, List<String> cookies, Form form) {
        return this.doPost(target, acceptMediaTypes, MediaType.APPLICATION_FORM_URLENCODED_TYPE, bearerToken, cookies, form);
    }

    protected <T> Response doPost(WebTarget target, MediaType acceptMediaType, MediaType requestMediaType, T entity) {
        MediaType[] acceptMediaTypes = {acceptMediaType};
        return this.doPost(target, acceptMediaTypes, requestMediaType, null, entity);
    }

    protected <T> Response doPost(WebTarget target, MediaType acceptMediaType, MediaType requestMediaType, String bearerToken, T entity) {
        MediaType[] acceptMediaTypes = {acceptMediaType};
        return this.doPost(target, acceptMediaTypes, requestMediaType, bearerToken, null, entity);
    }

    protected <T> Response doPost(WebTarget target, MediaType[] acceptMediaTypes, MediaType requestMediaType, T entity) {
        return this.doPost(target, acceptMediaTypes, requestMediaType, null, null, entity);
    }

    protected <T> Response doPost(WebTarget target, MediaType[] acceptMediaTypes, MediaType requestMediaType, List<String> cookies, T entity) {
        return this.doPost(target, acceptMediaTypes, requestMediaType, null, cookies, entity);
    }

    protected <T> Response doPost(WebTarget target, MediaType acceptMediaType, MediaType requestMediaType, String bearerToken, List<String> cookies, T entity) {
        MediaType[] acceptMediaTypes = {acceptMediaType};
        return this.doPost(target, acceptMediaTypes, requestMediaType, bearerToken, cookies, entity);
    }

    protected <T> Response doPost(WebTarget target, MediaType[] acceptMediaTypes, MediaType requestMediaType, String bearerToken, List<String> cookies, T entity) {
        Response resp = null;
        try {
            Invocation.Builder builder = target.request().accept(acceptMediaTypes);
            if (bearerToken != null) {
                builder.header("Authorization", "Bearer " + bearerToken);
            }

            if (cookies != null) {
                for (String cookie : cookies) {
                    builder.header("Cookie", cookie);
                }
            }
            resp = builder.post(Entity.entity(entity, requestMediaType));
            return resp;
        } catch (Exception ex) {
            WebServiceResponseException resultEx = (resp == null) ? WebServiceResponseException.Factory.create(target, ex) : WebServiceResponseException.Factory.create(target, resp);
            throw resultEx;
        }
    }


    /*
    public static class FormFactory {
  
        private MultivaluedMap<String, Object> formParamMap;
        
        public FormFactory() {
            formParamMap = new MultivaluedHashMap();
        }
        
        public FormFactory add(String paramName, Object paramValue) {
            this.formParamMap.add(paramName, paramValue);
            return this;
        }
        
//        public Response post() {
////            Response resp = null;
////            try {
////                Invocation.Builder builder = target.request().accept(acceptMediaTypes);
////                if (bearerToken != null) {
////                    builder.header("Authorization", "Bearer " + bearerToken);
////                }
////
////                if (cookies != null) {
////                    for (String cookie : cookies) {
////                        builder.header("Cookie", cookie);
////                    }
////                }
////
////                resp = builder.post(Entity.form(entity, requestMediaType));
////                return resp;
////            } catch (Exception ex) {
////                WebServiceResponseException resultEx = (resp == null) ? WebServiceResponseException.Factory.create(target, ex) : WebServiceResponseException.Factory.create(target, resp);
////                throw resultEx;
////            }        
//        }
        public MultivaluedMap<String, Object> create() {
            return this.formParamMap;
        }
    }
     */
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="共用的底層，doDelete() 系列">    
    protected <T> Response doDelete(WebTarget target, MediaType acceptMediaType, MediaType requestMediaType/*, T entity*/) {
        MediaType[] acceptMediaTypes = {acceptMediaType};
        return this.doDelete(target, acceptMediaTypes, requestMediaType, null/*, entity*/);
    }

    protected <T> Response doDelete(WebTarget target, MediaType acceptMediaType, MediaType requestMediaType, String bearerToken/*, T entity*/) {
        MediaType[] acceptMediaTypes = {acceptMediaType};
        return this.doDelete(target, acceptMediaTypes, requestMediaType, bearerToken, null/*, entity*/);
    }

    protected <T> Response doDelete(WebTarget target, MediaType[] acceptMediaTypes, MediaType requestMediaType/*, T entity*/) {
        return this.doDelete(target, acceptMediaTypes, requestMediaType, null, null/*, entity*/);
    }

    protected <T> Response doDelete(WebTarget target, MediaType[] acceptMediaTypes, MediaType requestMediaType, List<String> cookies/*, T entity*/) {
        return this.doDelete(target, acceptMediaTypes, requestMediaType, null, cookies/*, entity*/);
    }

    protected <T> Response doDelete(WebTarget target, MediaType acceptMediaType, MediaType requestMediaType, String bearerToken, List<String> cookies/*, T entity*/) {
        MediaType[] acceptMediaTypes = {acceptMediaType};
        return this.doDelete(target, acceptMediaTypes, requestMediaType, bearerToken, cookies/*, entity*/);
    }

    protected <T> Response doDelete(WebTarget target, MediaType[] acceptMediaTypes, MediaType requestMediaType, String bearerToken, List<String> cookies/*, T entity*/) {
        Response resp = null;

        try {
            Invocation.Builder builder = target.request().accept(acceptMediaTypes);
            if (bearerToken != null) {
                builder.header("Authorization", "Bearer " + bearerToken);
            }

            if (cookies != null) {
                for (String cookie : cookies) {
                    builder.header("Cookie", cookie);
                }
            }
            return builder.delete();
        } catch (Exception ex) {
            WebServiceResponseException resultEx = (resp == null) ? WebServiceResponseException.Factory.create(target, ex) : WebServiceResponseException.Factory.create(target, resp);
            throw resultEx;
        }
    }
//</editor-fold>

    public Client obtainNewClientInstance(String url) {
        return RestClientFactory.configureClient(url);
    }
}
