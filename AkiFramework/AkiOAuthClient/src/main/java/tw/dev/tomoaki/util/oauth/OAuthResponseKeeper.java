/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.util.oauth;

import tw.dev.tomoaki.util.oauth.entity.OAuthResponse;

/**
 *
 * @author tomoaki
 * @param <T>
 */
public interface OAuthResponseKeeper<T extends OAuthResponse> extends OAuthResponseFinder<T> {
    
    /**
     * Saves the OAuth response associated with a specific client.
     *
     * @param clientId the unique identifier for the client
     * @param clientSecret the secret key for the client
     * @param response the type of the OAuth response     
     * @param args additional optional arguments for the saving process
     */    
    public void saveOAuthResponse(String clientId, String clientSecret, T response, Object... args);
}
