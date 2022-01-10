/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.aki.mail.client;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import tw.dev.tomoaki.aki.mail.entity.MessageFactory;

/**
 *
 * @author arche
 */
public class SMTPClient {

    private String hostName = "localhost";
    private Integer port;

    private static final String DEFAULT_HOST = "localhost";

    protected SMTPClient() {
    }

    public static class Factory {

        public static SMTPClient create() {
            SMTPClient client = new SMTPClient();
            client.hostName = DEFAULT_HOST;
            return client;
        }
        
        public static SMTPClient create(String hostName) {
            SMTPClient client = new SMTPClient();
            client.hostName = hostName;
            return client;
        }        
       
        public static SMTPClient create(String hostName, Integer port) {
            SMTPClient client = new SMTPClient();
            client.hostName = hostName;
            client.port = port;
            return client;
        }                
    }

    public void sendPlainTextMessage(String fromAddr, String toAddr, String subject, String plainText) throws MessagingException {
        MimeMessage msg = MessageFactory.createPlainTextMsg(hostName, fromAddr, toAddr, subject, plainText);
        Transport.send(msg);
    }

}
