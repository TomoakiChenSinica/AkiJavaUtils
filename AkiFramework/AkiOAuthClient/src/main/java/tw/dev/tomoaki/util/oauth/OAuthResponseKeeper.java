/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.util.oauth;

import tw.dev.tomoaki.util.oauth.entity.OAuthResponse;

/**
 * Defines the contract for storing and retrieving an OAuth response.
 *
 * <p>
 * Extends {@link OAuthResponseProvider} with the ability to persist an OAuth
 * response so that it can be looked up later using the same client
 * credentials.</p>
 *
 * @param <T> the type of the OAuth response, must implements/extends {@link OAuthResponse}
 * @author tomoaki
 */
public interface OAuthResponseKeeper<T extends OAuthResponse> extends OAuthResponseProvider<T> {
    
    /**
     * Saves the OAuth response associated with a specific client.
     *
     * @param clientId the unique identifier for the client
     * @param clientSecret the secret key for the client
     * @param response the OAuth response object to be saved
     * @param args additional optional arguments for the saving process
     */    
    public void saveOAuthResponse(String clientId, String clientSecret, T response, Object... args);
}
