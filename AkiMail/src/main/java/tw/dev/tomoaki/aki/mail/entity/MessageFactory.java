/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.aki.mail.entity;

import java.util.ArrayList;
import java.util.List;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author arche
 */
public class MessageFactory {

    private static final String DEFAULT_CHARSET = "UTF-8";

    protected static MimeMessage createBaseMsg(String smtpHost, String fromAddr, List<String> toAddrs, String subject) throws AddressException, MessagingException {
        //MimeMessage 其實是 Message型態的樣子
        Session session = MailSessionFactory.create(smtpHost);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromAddr));
        message.setSubject(subject, DEFAULT_CHARSET);
        for (String toAddr : toAddrs) {
            message.setRecipients(Message.RecipientType.TO, toAddr);
        }
        return message;

    }
    
    public static MimeMessage createPlainTextMsg(String smtpHost, String fromAddr, String toAddr, String subject, String plainText) throws MessagingException {
        List<String> toAddrs = new ArrayList();
        toAddrs.add(toAddr);
        return MessageFactory.createPlainTextMsg(smtpHost, fromAddr, toAddrs, subject, plainText);
    }

    public static MimeMessage createPlainTextMsg(String smtpHost, String fromAddr, List<String> toAddrs, String subject, String plainText) throws MessagingException {
        MimeMessage message = MessageFactory.createBaseMsg(smtpHost, fromAddr, toAddrs, subject);
        message.setText(plainText, DEFAULT_CHARSET);
        return message;
    }

    public static MimeMessage createAttacheAblePlainTextMag(String smtpHost, String fromAddr, String toAddr, String subject, String plainText, String path2File) throws MessagingException {
        List<String> toAddrs = new ArrayList();
        return MessageFactory.createAttacheAblePlainTextMag(smtpHost, fromAddr, toAddrs, subject, plainText, path2File);
    }   
    
    public static MimeMessage createAttacheAblePlainTextMag(String smtpHost, String fromAddr, List<String> toAddrs, String subject, String plainText, String path2File) throws MessagingException {
        MimeMessage message =  MessageFactory.createBaseMsg(smtpHost, fromAddr, toAddrs, subject);
         // Create a multipar message
         Multipart multipart = new MimeMultipart();
        
         // Create the message part
         BodyPart messageBodyPart = new MimeBodyPart();
         // Now set the actual message
         messageBodyPart.setText(plainText);
         multipart.addBodyPart(messageBodyPart);

         // Part two is attachment
         BodyPart attachBodyPart = BodyPartFactory.createFileBodyPart(path2File);         
         multipart.addBodyPart(attachBodyPart);

         // Send the complete message parts
         message.setContent(multipart);    
         return message;
    }

}
