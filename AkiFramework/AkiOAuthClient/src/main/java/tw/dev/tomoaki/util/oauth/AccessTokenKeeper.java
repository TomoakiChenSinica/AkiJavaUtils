/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.util.oauth;

import tw.dev.tomoaki.util.oauth.entity.OAuthResponse;

/**
 *
 * @author tomoaki
 */
public interface AccessTokenKeeper extends AccessTokenFinder {
    
    default public void saveAccessToken(String clientId, String clientSecret, OAuthResponse response, Object... args) {
        this.saveAccessToken(clientId, clientSecret, response.getAccessToken());
    }
    
    public void saveAccessToken(String clientId, String clientSecret, String accessToken, Object... args);
}
