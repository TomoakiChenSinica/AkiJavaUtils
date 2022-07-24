/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.aki.mail.helper;

import java.io.File;
import java.util.List;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author arche
 */
public class MessageHelper {

    public static MimeMessage setupSender(MimeMessage message, String fromAddr) throws AddressException, MessagingException {
        message.setFrom(new InternetAddress(fromAddr));
        return message;
    }

    public static MimeMessage setupReceivers(MimeMessage message, String... toAddrs) throws MessagingException {
        for (String toAddr : toAddrs) {
            message.addRecipients(Message.RecipientType.TO, toAddr);
        }
        return message;
    }    
    
    public static MimeMessage setupReceivers(MimeMessage message, List<String> toAddrs) throws MessagingException {
        for (String toAddr : toAddrs) {
            message.addRecipients(Message.RecipientType.TO, toAddr);
        }
        return message;
    }

    public static MimeMessage setupSubject(MimeMessage message, String subject, String charSet) throws MessagingException {
        message.setSubject(subject, charSet);
        return message;
    }

    public static MimeMessage setupPlainTextContent(MimeMessage message, String plainTextContent, String charSet) throws MessagingException {
        message.setText(plainTextContent, charSet);
        return message;
    }
    
    public static MimeMessage setupHtmlContent(MimeMessage message, String htmlTextContent, String contentType) throws MessagingException {
        message.setContent(htmlTextContent, contentType);
        return message;
    }
//  https://www.javatpoint.com/example-of-sending-attachment-with-email-using-java-mail-api  
//    public static MimeMessage setupMulitPartPlainTextContent(MimeMessage message, String plainTextContent, List<File> file) {
//    }
    
}
