/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.aki.mail.factory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import tw.dev.tomoaki.aki.mail.helper.MessageHelper;
import tw.dev.tomoaki.aki.mail.helper.MimeBodyPartHelper;

/**
 *
 * @author Tomoaki Chen
 */
public class MimeBodyMessageFactory {

    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String DEFAULT_CONTENT_TYPE = "text/html;charset=UTF-8";

    private String smtpHost;
    private String sender;
    private List<String> receiverList;
    private String subject;
    private List<MimeBodyPart> mimeBodyList;
    
    

    public static MimeBodyMessageFactory obtain(String hostName, String sender) {
        MimeBodyMessageFactory factory = new MimeBodyMessageFactory();
        factory.smtpHost = hostName;
        factory.sender = sender;
        factory.doInit();
        return factory;
    }    
    
    protected void doInit() {
        this.receiverList = new ArrayList();
        this.subject = "";
        this.mimeBodyList = new ArrayList();
    }
    
    public MimeBodyMessageFactory addReceiver(String receiver) {
        this.receiverList.add(receiver);
        return this;
    }

    public MimeBodyMessageFactory addAllReceiver(List<String> receiverList) {
        this.receiverList.addAll(receiverList);
        return this;
    }

    public MimeBodyMessageFactory setupSubject(String subject) {
        this.subject = subject;
        return this;
    }    
    
    public MimeBodyMessageFactory appendPlainTextBody(String plainText) throws MessagingException {
        return this.appendPlainTextBody(plainText, DEFAULT_CHARSET);
    }
    
    public MimeBodyMessageFactory appendPlainTextBody(String plainText, String charSet) throws MessagingException {
        MimeBodyPart bodyPart = MimeBodyPartHelper.obtain4PlainTextContent(plainText, charSet);
        this.mimeBodyList.add(bodyPart);
        return this;
    }
    
    public MimeBodyMessageFactory appendHtmlBody(String htmlContent) throws MessagingException {
        return this.appendHtmlBody(htmlContent, DEFAULT_CONTENT_TYPE);
    }    
    
    public MimeBodyMessageFactory appendHtmlBody(String htmlContent, String contentType) throws MessagingException {
        MimeBodyPart bodyPart = MimeBodyPartHelper.obtain4HtmlContent(htmlContent, contentType);
        this.mimeBodyList.add(bodyPart);
        return this;
    }    
    
    public MimeBodyMessageFactory appendFileBody(File file) throws MessagingException, IOException {
        MimeBodyPart bodyPart = MimeBodyPartHelper.obtain4File(file);
        this.mimeBodyList.add(bodyPart);
        return this;
    }        
    
    public MimeBodyMessageFactory appendMimeBody(MimeBodyPart bodyPart) {
        this.mimeBodyList.add(bodyPart);
        return this;
    }
    
    public MimeMessage produceMessage() throws MessagingException, IOException {
        MimeMessage msg = TextMessageFactory.createEmptyMsg(smtpHost);
        msg = MessageHelper.setupSender(msg, sender);
        msg = MessageHelper.setupReceivers(msg, receiverList);
        msg = MessageHelper.setupSubject(msg, subject, DEFAULT_CHARSET);
        msg = MessageHelper.setupBodyParts(msg, mimeBodyList);
        this.doInit();
        return msg;
    }    
    
}
