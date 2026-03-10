/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.util.oauth;

import tw.dev.tomoaki.util.oauth.entity.OAuthResponse;

/**
 * Defines the contract for obtaining an OAuth response for a given client.
 *
 * <p>
 * Implementations may fulfil this contract in different ways — for example,
 * by looking up a previously stored response, or by performing the actual
 * OAuth authorization flow when no stored response is available.</p>
 *
 * @param <T> the expected type of the OAuth response, must implements/extends {@link OAuthResponse}
 * @author tomoaki
 */
public interface OAuthResponseProvider<T extends OAuthResponse> {

    /**
     * Returns the OAuth response associated with the specified client.
     *
     * <p>
     * Implementations may return a cached or stored response, or initiate
     * the actual OAuth call to obtain a new one if none is available.</p>
     *
     * @param clientId the unique identifier for the client
     * @param clientSecret the secret key for the client
     * @param args additional context or parameters to refine the retrieval
     * @return the OAuth response object, or {@code null} if not found and
     *         the implementation does not perform the actual OAuth call
     */
    public T getOAuthResponse(String clientId, String clientSecret, Object... args);
}
