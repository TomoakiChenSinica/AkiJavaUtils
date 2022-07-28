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
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import tw.dev.tomoaki.aki.mail.factory.MimeBodyMessageFactory;
import tw.dev.tomoaki.aki.mail.factory.TextMessageFactory;
import tw.dev.tomoaki.aki.mail.factory.MultiPartMessageFactory;

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
            client.doSetupMailProperty();            
            return client;
        }

        public static SMTPClient create(String hostName) {
            SMTPClient client = new SMTPClient();
            client.hostName = hostName;
            client.doSetupMailProperty();            
            return client;
        }

        public static SMTPClient create(String hostName, Integer port) {
            SMTPClient client = new SMTPClient();
            client.hostName = hostName;
            client.port = port;
            client.doSetupMailProperty();
            return client;
        }
    }
    
    private void doSetupMailProperty() {
        System.setProperty("mail.mime.splitlongparameters", "false");
    }

    public void sendPlainTextMessage(String fromAddr, String toAddr, String subject, String plainText) throws MessagingException {
        MimeMessage msg = TextMessageFactory.createPlainTextMsg(hostName, fromAddr, subject, plainText, toAddr);
        Transport.send(msg);
    }

    public void sendPlainTextMessage(String fromAddr, List<String> toAddr, String subject, String plainText) throws MessagingException {
        MimeMessage msg = TextMessageFactory.createPlainTextMsg(hostName, fromAddr, subject, plainText, toAddr);
        Transport.send(msg);
    }

    public void sendPlainTextMessage(String fromAddr, String toAddr, String subject, String plainText, File file) throws MessagingException, IOException {
        MultiPartMessageFactory multiPartMsgFactory = MultiPartMessageFactory.obtain(hostName, fromAddr);
        MimeMessage msg = multiPartMsgFactory.addReceiver(toAddr).setupSubject(subject).appendContent(plainText).addAttachment(file).produceMessage();
        Transport.send(msg);        
    }

//    public void sendPlainTextMessage(String fromAddr, String toAddr, String subject, String plainText, List<File> fileList) throws MessagingException, IOException {
//        MultiPartMessageFactory multiPartMsgFactory = MultiPartMessageFactory.obtain(hostName, fromAddr);
//        MimeMessage msg = multiPartMsgFactory.addReceiver(toAddr).setupSubject(subject).appendContent(plainText).addAllAttachment(fileList).produceMessage();
//        Transport.send(msg); 
//    }
    
    public void sendPlainTextMessage(String fromAddr, String toAddr, String subject, String plainText, MimeBodyPart fileBodyPart) throws MessagingException, IOException {
        MimeBodyMessageFactory mimeBodyMessageFactory = MimeBodyMessageFactory.obtain(hostName, fromAddr);
        MimeMessage msg = mimeBodyMessageFactory.addReceiver(toAddr).setupSubject(subject).appendMimeBody(fileBodyPart).produceMessage();
        Transport.send(msg);        
    }    
    
    public void sendPlainTextMessage(String fromAddr, String toAddr, String subject, String plainText, List attachmentList) throws MessagingException, IOException {
        if(attachmentList == null || attachmentList.isEmpty()) {
            this.sendPlainTextMessage(fromAddr, toAddr, subject, plainText);
        } else {
            MimeMessage msg = null;
            Object attachmentData = attachmentList.get(0);
            if(attachmentData instanceof File) {
                MultiPartMessageFactory multiPartMsgFactory = MultiPartMessageFactory.obtain(hostName, fromAddr);
                msg = multiPartMsgFactory.addReceiver(toAddr).setupSubject(subject).appendContent(plainText).addAllAttachment(attachmentList).produceMessage();
            } else if(attachmentData  instanceof MimeBodyPart) {
                MimeBodyMessageFactory mimeBodyMessageFactory = MimeBodyMessageFactory.obtain(hostName, fromAddr);
                mimeBodyMessageFactory = mimeBodyMessageFactory.addReceiver(toAddr).setupSubject(subject).appendPlainTextBody(plainText);
                for(Object objBodyPart : attachmentList){
                    MimeBodyPart bodyPart = (MimeBodyPart)objBodyPart;
                    mimeBodyMessageFactory.appendMimeBody(bodyPart);
                    msg = mimeBodyMessageFactory.produceMessage();
                }
            }
            Transport.send(msg);             
        }
        
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
        MimeMessage msg = TextMessageFactory.createHtmlTextMsg(hostName, fromAddr, subject, processHtmlMessage(htmlText), toAddr);
        Transport.send(msg);
    }

    public void sendHtmlMessage(String fromAddr, List<String> toAddr, String subject, String htmlText) throws MessagingException {
        MimeMessage msg = TextMessageFactory.createHtmlTextMsg(hostName, fromAddr, subject, processHtmlMessage(htmlText), toAddr);
        Transport.send(msg);
    }

    public void sendHtmlMessage(String fromAddr, String toAddr, String subject, String htmlText, File file) throws MessagingException, IOException {
        MultiPartMessageFactory multiPartMsgFactory = MultiPartMessageFactory.obtain(hostName, fromAddr);
        MimeMessage msg = multiPartMsgFactory.addReceiver(toAddr).setupSubject(subject).appendContent(htmlText).addAttachment(file).produceMessage();
        Transport.send(msg); 
    }

    public void sendHtmlMessage(String fromAddr, String toAddr, String subject, String htmlText, List<File> fileList) throws MessagingException, IOException {
       MultiPartMessageFactory multiPartMsgFactory = MultiPartMessageFactory.obtain(hostName, fromAddr);
        MimeMessage msg = multiPartMsgFactory.addReceiver(toAddr).setupSubject(subject).appendContent(htmlText).addAllAttachment(fileList).produceMessage();
        Transport.send(msg); 
    }

    public void sendHtmlMessage(String fromAddr, List<String> toAddr, String subject, String htmlText, File file) throws MessagingException, IOException {
        MultiPartMessageFactory multiPartMsgFactory = MultiPartMessageFactory.obtain(hostName, fromAddr);
        MimeMessage msg = multiPartMsgFactory.addAllReceiver(toAddr).setupSubject(subject).appendContent(htmlText).addAttachment(file).produceMessage();
        Transport.send(msg);
    }

    public void sendHtmlMessage(String fromAddr, List<String> toAddr, String subject, String htmlText, List<File> fileList) throws MessagingException, IOException {
        MultiPartMessageFactory multiPartMsgFactory = MultiPartMessageFactory.obtain(hostName, fromAddr);
        MimeMessage msg = multiPartMsgFactory.addAllReceiver(toAddr).setupSubject(subject).appendContent(htmlText).addAllAttachment(fileList).produceMessage();
        Transport.send(msg);
    }

    protected String processHtmlMessage(String htmlText) {
        htmlText = htmlText.replace("\r\n", "<br/>");
        htmlText = htmlText.replace("\n", "<br/>");
        return htmlText;
    }

}
