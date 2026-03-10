/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.util.oauth;

import tw.dev.tomoaki.util.oauth.entity.OAuthResponse;

/**
 * @param <T> the expected type of the OAuth response, must implements/extends {@link OAuthResponse}
 * @author tomoaki
 */
public interface OAuthResponseFinder<T extends OAuthResponse> {
        
    /**
     * Retrieves the OAuth response associated with the specified client.
     *
     * <p>
     * Uses the provided {@code clientId} and {@code clientSecret} to look up or
     * obtain the stored OAuth response object.</p>
     *
     * @param clientId the unique identifier for the client
     * @param clientSecret the secret key for the client
     * @param args additional context or parameters to refine the retrieval
     * @return the OAuth response object, or {@code null} if not found
     */    
    public T getOAuthResponse(String clientId, String clientSecret, Object... args);
}
