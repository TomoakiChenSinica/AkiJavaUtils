/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.aki.mail.entity;

import java.util.Properties;
import javax.mail.Session;

/**
 *
 * @author arche
 */
public class MailSessionFactory {

    private static final String PROP_HOST = "mail.smtp.host";
    
    public static Session create(String host) {
        Properties properties = new Properties();//System.getProperties();
        System.out.println("properties : " + properties);
        properties.setProperty(PROP_HOST, host);
        properties.put("mail.smtp.auth", "false");
        properties.put("mail.smtp.starttls.enable", "true");        
        properties.put("mail.smtp.port", "25");
        Session session = Session.getDefaultInstance(properties);
        return session;
    }
}
