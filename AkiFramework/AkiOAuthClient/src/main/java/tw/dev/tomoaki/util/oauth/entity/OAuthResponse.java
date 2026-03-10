/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.util.oauth.entity;

/**
 * Represents the data structure for an OAuth response.
 *
 * <p>
 * Implementing classes should define the specific attributes and behaviors
 * required to process and store response data received from an OAuth
 * provider.</p>
 *
 * @author tomoaki
 */
public interface OAuthResponse {

    /**
     * Returns the access token string returned by the OAuth provider.
     *
     * <p>
     * This token is used for authenticating subsequent requests to access
     * protected resources.</p>
     *
     * @return the access token issued by the OAuth server
     */
    public String getAccessToken();

    /**
     * Sets the access token retrieved from the OAuth provider.
     *
     * <p>
     * Stores the provided {@code accessToken} to be used for subsequent
     * authenticated requests.</p>
     *
     * @param accessToken the access token string to be stored
     */    
    public void setAccessToken(String accessToken);
}
