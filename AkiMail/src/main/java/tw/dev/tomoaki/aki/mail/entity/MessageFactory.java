/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.aki.mail.entity;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author arche
 */
public class MessageFactory {

    private static final String DEFAULT_CHARSET = "UTF-8";

    public static MimeMessage createPlainTextMsg(String smtpHost, String fromAddr, String subject, String plainText, String... toAddrs) throws MessagingException {
        //MimeMessage 其實是 Message型態的樣子
        Session session = MailSessionFactory.create(smtpHost);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromAddr));
        message.setSubject(subject, DEFAULT_CHARSET);
        message.setText(plainText, DEFAULT_CHARSET);
        for (String toAddr : toAddrs) {
            message.setRecipients(Message.RecipientType.TO, toAddr);
        }
        return message;
    }
    
    public static MimeMessage createHtmlTextMsg(String smtpHost, String fromAddr, String subject, String htmlText, String... toAddrs) throws MessagingException {
        //MimeMessage 其實是 Message型態的樣子
        Session session = MailSessionFactory.create(smtpHost);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromAddr));
        message.setSubject(subject, DEFAULT_CHARSET);
        message.setContent(htmlText, "text/html;charset=UTF-8");
        for (String toAddr : toAddrs) {
            message.setRecipients(Message.RecipientType.TO, toAddr);
        }
        return message;
    }    
}
