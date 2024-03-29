/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.aki.mail.factory;

import java.util.List;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tw.dev.tomoaki.aki.mail.helper.MessageHelper;

/**
 *
 * @author arche
 */
public class TextMessageFactory {

    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String DEFAULT_CONTENT_TYPE = "text/html;charset=UTF-8";

    public static MimeMessage createEmptyMsg(String smtpHost) {
        Session session = MailSessionFactory.create(smtpHost);
        MimeMessage message = new MimeMessage(session);
        return message;
    }

    public static MimeMessage createEmptyMsg(String smtpHost, Integer port) {
        Session session = MailSessionFactory.create(smtpHost, port);
        MimeMessage message = new MimeMessage(session);
        return message;
    }

    public static MimeMessage createPlainTextMsg(String smtpHost, String fromAddr, String subject, String plainText, String... toAddrs) throws MessagingException {
        //MimeMessage 其實是 Message型態的樣子
        MimeMessage message = TextMessageFactory.createEmptyMsg(smtpHost);
        message = MessageHelper.setupSender(message, fromAddr);
        message = MessageHelper.setupSubject(message, subject, DEFAULT_CHARSET);
        message = MessageHelper.setupPlainTextContent(message, plainText, DEFAULT_CHARSET);
        message = MessageHelper.setupReceivers(message, toAddrs);
        return message;
    }

    public static MimeMessage createPlainTextMsg(String smtpHost, Integer port, String fromAddr, String subject, String plainText, String... toAddrs) throws MessagingException {
        //MimeMessage 其實是 Message型態的樣子
        MimeMessage message = TextMessageFactory.createEmptyMsg(smtpHost, port);
        message = MessageHelper.setupSender(message, fromAddr);
        message = MessageHelper.setupSubject(message, subject, DEFAULT_CHARSET);
        message = MessageHelper.setupPlainTextContent(message, plainText, DEFAULT_CHARSET);
        message = MessageHelper.setupReceivers(message, toAddrs);
        return message;
    }

    public static MimeMessage createPlainTextMsg(String smtpHost, String fromAddr, String subject, String plainText, List<String> toAddrs) throws MessagingException {
        //MimeMessage 其實是 Message型態的樣子
        MimeMessage message = TextMessageFactory.createEmptyMsg(smtpHost);
        message = MessageHelper.setupSender(message, fromAddr);
        message = MessageHelper.setupSubject(message, subject, DEFAULT_CHARSET);
        message = MessageHelper.setupPlainTextContent(message, plainText, DEFAULT_CHARSET);
        message = MessageHelper.setupReceivers(message, toAddrs);
        return message;
    }

    public static MimeMessage createPlainTextMsg(String smtpHost, Integer port, String fromAddr, String subject, String plainText, List<String> toAddrs) throws MessagingException {
        //MimeMessage 其實是 Message型態的樣子
        MimeMessage message = TextMessageFactory.createEmptyMsg(smtpHost, port);
        message = MessageHelper.setupSender(message, fromAddr);
        message = MessageHelper.setupSubject(message, subject, DEFAULT_CHARSET);
        message = MessageHelper.setupPlainTextContent(message, plainText, DEFAULT_CHARSET);
        message = MessageHelper.setupReceivers(message, toAddrs);
        return message;
    }

    public static MimeMessage createHtmlTextMsg(String smtpHost, String fromAddr, String subject, String htmlText, String... toAddrs) throws MessagingException {
        //MimeMessage 其實是 Message型態的樣子
        MimeMessage message = TextMessageFactory.createEmptyMsg(smtpHost);
        message = MessageHelper.setupSender(message, fromAddr);
        message = MessageHelper.setupSubject(message, subject, DEFAULT_CHARSET);
        message = MessageHelper.setupHtmlContent(message, htmlText, DEFAULT_CONTENT_TYPE);
        message = MessageHelper.setupReceivers(message, toAddrs);
        return message;
    }

    public static MimeMessage createHtmlTextMsg(String smtpHost, Integer port, String fromAddr, String subject, String htmlText, String... toAddrs) throws MessagingException {
        //MimeMessage 其實是 Message型態的樣子
        MimeMessage message = TextMessageFactory.createEmptyMsg(smtpHost, port);
        message = MessageHelper.setupSender(message, fromAddr);
        message = MessageHelper.setupSubject(message, subject, DEFAULT_CHARSET);
        message = MessageHelper.setupHtmlContent(message, htmlText, DEFAULT_CONTENT_TYPE);
        message = MessageHelper.setupReceivers(message, toAddrs);
        return message;
    }

    public static MimeMessage createHtmlTextMsg(String smtpHost, String fromAddr, String subject, String htmlText, List<String> toAddrs) throws MessagingException {
        //MimeMessage 其實是 Message型態的樣子
        MimeMessage message = TextMessageFactory.createEmptyMsg(smtpHost);
        message = MessageHelper.setupSender(message, fromAddr);
        message = MessageHelper.setupSubject(message, subject, DEFAULT_CHARSET);
        message = MessageHelper.setupHtmlContent(message, htmlText, DEFAULT_CONTENT_TYPE);
        message = MessageHelper.setupReceivers(message, toAddrs);
        return message;
    }

    public static MimeMessage createHtmlTextMsg(String smtpHost, Integer port, String fromAddr, String subject, String htmlText, List<String> toAddrs) throws MessagingException {
        //MimeMessage 其實是 Message型態的樣子
        MimeMessage message = TextMessageFactory.createEmptyMsg(smtpHost, port);
        message = MessageHelper.setupSender(message, fromAddr);
        message = MessageHelper.setupSubject(message, subject, DEFAULT_CHARSET);
        message = MessageHelper.setupHtmlContent(message, htmlText, DEFAULT_CONTENT_TYPE);
        message = MessageHelper.setupReceivers(message, toAddrs);
        return message;
    }

}
