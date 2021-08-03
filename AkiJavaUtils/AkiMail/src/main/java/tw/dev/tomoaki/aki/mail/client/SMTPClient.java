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
    
    public void sendPlainTextMessage(String fromAddr, String toAddr, String subject, String plainText) throws MessagingException {
        MimeMessage msg = MessageFactory.createPlainTextMsg(hostName, fromAddr, subject, plainText, toAddr);
        Transport.send(msg);
    }

}
