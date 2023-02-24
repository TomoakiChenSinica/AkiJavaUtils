/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.ejb.auth;

import java.util.Date;

/**
 *
 * @author tomoaki
 */
public interface AuthInfo {
    
    
    public Date getLastModifiedDateTime();
    
    public String getIdentifier();
}
