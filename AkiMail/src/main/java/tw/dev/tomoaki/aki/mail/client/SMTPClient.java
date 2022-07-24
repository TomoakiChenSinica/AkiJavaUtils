/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.aki.mail.client;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import tw.dev.tomoaki.aki.mail.entity.MessageFactory;
import tw.dev.tomoaki.aki.mail.entity.MultiPartMessageFactory;

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
        MimeMessage msg = MessageFactory.createPlainTextMsg(hostName, fromAddr, subject, plainText, toAddr);
        Transport.send(msg);
    }

    public void sendPlainTextMessage(String fromAddr, List<String> toAddr, String subject, String plainText) throws MessagingException {
        MimeMessage msg = MessageFactory.createPlainTextMsg(hostName, fromAddr, subject, plainText, toAddr);
        Transport.send(msg);
    }

    public void sendPlainTextMessage(String fromAddr, String toAddr, String subject, String plainText, File file) throws MessagingException, IOException {
        MultiPartMessageFactory multiPartMsgFactory = MultiPartMessageFactory.obtain(hostName, fromAddr);
        MimeMessage msg = multiPartMsgFactory.addReceiver(toAddr).setupSubject(subject).appendContent(plainText).addAttachment(file).produceMessage();
        Transport.send(msg);        
    }

    public void sendPlainTextMessage(String fromAddr, String toAddr, String subject, String plainText, List<File> fileList) throws MessagingException, IOException {
        MultiPartMessageFactory multiPartMsgFactory = MultiPartMessageFactory.obtain(hostName, fromAddr);
        MimeMessage msg = multiPartMsgFactory.addReceiver(toAddr).setupSubject(subject).appendContent(plainText).addAllAttachment(fileList).produceMessage();
        Transport.send(msg); 
    }

    public void sendPlainTextMessage(String fromAddr, List<String> toAddr, String subject, String plainText, File file) throws MessagingException, IOException {
        MultiPartMessageFactory multiPartMsgFactory = MultiPartMessageFactory.obtain(hostName, fromAddr);
        MimeMessage msg = multiPartMsgFactory.addAllReceiver(toAddr).setupSubject(subject).appendContent(plainText).addAttachment(file).produceMessage();
        Transport.send(msg); 
    }

    public void sendPlainTextMessage(String fromAddr, List<String> toAddr, String subject, String plainText, List<File> fileList) throws MessagingException, IOException {
        MultiPartMessageFactory multiPartMsgFactory = MultiPartMessageFactory.obtain(hostName, fromAddr);
        MimeMessage msg = multiPartMsgFactory.addAllReceiver(toAddr).setupSubject(subject).appendContent(plainText).addAllAttachment(fileList).produceMessage();
        Transport.send(msg); 
    }

    
    
    
    public void sendHtmlMessage(String fromAddr, String toAddr, String subject, String htmlText) throws MessagingException {
        MimeMessage msg = MessageFactory.createHtmlTextMsg(hostName, fromAddr, subject, processHtmlMessage(htmlText), toAddr);
        Transport.send(msg);
    }

    public void sendHtmlMessage(String fromAddr, List<String> toAddr, String subject, String htmlText) throws MessagingException {
        MimeMessage msg = MessageFactory.createHtmlTextMsg(hostName, fromAddr, subject, processHtmlMessage(htmlText), toAddr);
        Transport.send(msg);
    }

    public void sendHtmlMessage(String fromAddr, String toAddr, String subject, String htmlText, File file) {
        throw new UnsupportedOperationException("Method Not Supported Yet");
    }

    public void sendHtmlMessage(String fromAddr, String toAddr, String subject, String htmlText, List<File> fileList) {
        throw new UnsupportedOperationException("Method Not Supported Yet");
    }

    public void sendHtmlMessage(String fromAddr, List<String> toAddr, String subject, String htmlText, File file) {
        throw new UnsupportedOperationException("Method Not Supported Yet");
    }

    public void sendHtmlMessage(String fromAddr, List<String> toAddr, String subject, String htmlText, List<File> fileList) {
        throw new UnsupportedOperationException("Method Not Supported Yet");
    }

    protected String processHtmlMessage(String htmlText) {
        htmlText = htmlText.replace("\r\n", "<br/>");
        htmlText = htmlText.replace("\n", "<br/>");
        return htmlText;
    }

}
