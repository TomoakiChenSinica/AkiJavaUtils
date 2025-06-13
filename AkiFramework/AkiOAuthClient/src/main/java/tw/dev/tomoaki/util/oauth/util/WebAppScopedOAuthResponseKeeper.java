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
import tw.dev.tomoaki.util.oauth.OAuthResponseKeeper;
import tw.dev.tomoaki.util.oauth.entity.OAuthResponse;

/**
 *
 * @author tomoaki
 */
public class WebAppScopedOAuthResponseKeeper<T extends OAuthResponse> implements OAuthResponseKeeper<T> {

    private ServletContext context;
    
    public static class Factory {
        
        public static WebAppScopedOAuthResponseKeeper create(HttpServletRequest request) {
            WebAppScopedOAuthResponseKeeper keeper = new WebAppScopedOAuthResponseKeeper();
            keeper.context = request.getServletContext();
            return keeper;
        }
        
    }

    protected String obtainAttributeKey(String clientId, String clientSecret, Object... args) {
        return Stream.concat(Stream.of(clientId, clientSecret), Arrays.stream(args)).map(arg -> arg.toString()).collect(Collectors.joining("-"));
    }

    @Override
    public void saveAccessToken(String clientId, String clientSecret, T oauthResponse) {
        String appAttrKey = obtainAttributeKey(clientId, clientSecret);
        this.context.setAttribute(appAttrKey, oauthResponse);
    }

    @Override
    public T getAccessToken(String clientId, String clientSecret) {
        String appAttrKey = obtainAttributeKey(clientId, clientSecret);
        Object objOAuthResponse = context.getAttribute(appAttrKey);
        return (objOAuthResponse != null) ? (T)objOAuthResponse : null;
    }
}
