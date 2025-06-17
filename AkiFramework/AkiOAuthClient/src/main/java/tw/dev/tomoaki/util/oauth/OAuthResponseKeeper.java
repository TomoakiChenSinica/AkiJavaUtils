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
public interface OAuthResponseKeeper<T extends OAuthResponse> extends OAuthResponseFinder<T> {
    
    public void saveOAuthResponse(String clientId, String clientSecret, T response, Object... args);
}
