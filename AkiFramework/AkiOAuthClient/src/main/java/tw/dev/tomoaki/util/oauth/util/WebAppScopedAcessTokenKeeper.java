/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.util.oauth.util;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import tw.dev.tomoaki.util.oauth.AccessTokenKeeper;

/**
 *
 * @author tomoaki
 */
public class WebAppScopedAcessTokenKeeper implements AccessTokenKeeper {

    private ServletContext context;
    
    public static class Factory {
        
        public WebAppScopedAcessTokenKeeper create(HttpServletRequest request) {
            WebAppScopedAcessTokenKeeper keeper = new WebAppScopedAcessTokenKeeper();
            keeper.context = request.getServletContext();
            return keeper;
        }
        
    }
  
    /*
    public void saveAccessToken(HttpServletRequest request, String clientId, String clientSecret, AccessToken accessToken, Object... args) {
        String appAttrKey = obtainAccessTokenKey(clientId, clientSecret, args);
        request.getServletContext().setAttribute(appAttrKey, accessToken);
    }

    public AccessToken getAccessToken(HttpServletRequest request, String clientId, String clientSecret, Object... args) {
        String appAttrKey = obtainAccessTokenKey(clientId, clientSecret, args);
        Object objAccessToken = request.getServletContext().getAttribute(appAttrKey);
        AccessToken accessToken = (objAccessToken == null) ? null : (AccessToken) objAccessToken;
        return accessToken;
    } */

    protected String obtainAccessTokenKey(String clientId, String clientSecret, Object... args) {
        return Stream.concat(Stream.of(clientId, clientSecret), Arrays.stream(args)).map(arg -> arg.toString()).collect(Collectors.joining("-"));
    }

    @Override
    public void saveAccessToken(String clientId, String clientSecret, String accessToken) {
        String appAttrKey = obtainAccessTokenKey(clientId, clientSecret);
        this.context.setAttribute(appAttrKey, accessToken);
    }

    @Override
    public String getAccessToken(String clientId, String clientSecret) {
        String appAttrKey = obtainAccessTokenKey(clientId, clientSecret);
        Object objAccessToken = context.getAttribute(appAttrKey);
        return (objAccessToken != null) ? (String)objAccessToken : null;
    }
}
