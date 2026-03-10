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
 * @param <T>          the type of the OAuth response
 * @see 取消 Factory 原因 <a href="https://chatgpt.com/share/684bdcc9-d114-800d-b769-5ebe6321b3af">與 GPT 討論</a>
 * @author tomoaki
 */
public class WebAppScopedOAuthResponseKeeper<T extends OAuthResponse> implements OAuthResponseKeeper<T> {

    private ServletContext context;
    
    public WebAppScopedOAuthResponseKeeper(HttpServletRequest request) {
        this.context = request.getServletContext();
    }    
    
    /**
     * Obtains a cache or storage key derived from the client credentials.
     *
     * <p>Uses the provided {@code clientId} and {@code clientSecret} as the 
     * basis for generating a unique attribute key for temporary storage.</p>
     *
     * @param clientId     the unique identifier for the client
     * @param clientSecret the secret key associated with the client
     * @param args         additional context or parameters used to refine the key
     * @return             a formatted string to be used as a lookup or storage key
     */    
    protected String obtainAttributeKey(String clientId, String clientSecret, Object... args) {
        return Stream.concat(Stream.of(clientId, clientSecret), Arrays.stream(args)).map(arg -> arg.toString()).collect(Collectors.joining("-"));
    }

    @Override
    public void saveOAuthResponse(String clientId, String clientSecret, T oauthResponse, Object... args) {
        String appAttrKey = obtainAttributeKey(clientId, clientSecret, args);
        this.context.setAttribute(appAttrKey, oauthResponse);
    }

    @Override
    public T getOAuthResponse(String clientId, String clientSecret, Object... args) {
        String appAttrKey = obtainAttributeKey(clientId, clientSecret, args);
        Object objOAuthResponse = context.getAttribute(appAttrKey);
        return (objOAuthResponse != null) ? (T)objOAuthResponse : null;
    }
}
