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
public interface AccessTokenFinder {
        
    public String getAccessToken(String clientId, String clientSecret, Object... args);
}
