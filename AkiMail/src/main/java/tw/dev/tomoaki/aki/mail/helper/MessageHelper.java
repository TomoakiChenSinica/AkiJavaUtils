/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.aki.mail.helper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

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
    public static MimeMessage setupMulitPartPlainTextContent(MimeMessage message, String plainTextContent, String charSet, List<File> fileList) throws MessagingException, IOException {
        Multipart multipart = new MimeMultipart();
        
        MimeBodyPart plainTextContentBodyPart = new MimeBodyPart();
        plainTextContentBodyPart.setText(plainTextContent, charSet);
        multipart.addBodyPart(plainTextContentBodyPart);
        
        for(File file : fileList) {
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.attachFile(file);
            multipart.addBodyPart(attachmentBodyPart);
        }
        message.setContent(multipart);
        return message;
    }
    
    public static MimeMessage setupMulitPartHtmlContent(MimeMessage message, String plainTextContent, String contentType, List<File> fileList) throws MessagingException, IOException {
        Multipart multipart = new MimeMultipart();
        
        MimeBodyPart plainTextContentBodyPart = new MimeBodyPart();
        plainTextContentBodyPart.setContent(plainTextContent, contentType);
        multipart.addBodyPart(plainTextContentBodyPart);
        
        for(File file : fileList) {
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.attachFile(file);
            multipart.addBodyPart(attachmentBodyPart);
        }
        message.setContent(multipart);
        return message;
    }    

}
