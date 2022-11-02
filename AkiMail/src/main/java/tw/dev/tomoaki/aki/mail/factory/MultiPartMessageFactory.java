/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author arche
 */
public class MultiPartMessageFactory {

    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String DEFAULT_CONTENT_TYPE = "text/html;charset=UTF-8";

    private String smtpHost;
    private String sender;
    private List<String> receiverList;
    private String subject;
    private String content;
//    private List<File> attachmentList;
    private List attachmentList;

    protected MultiPartMessageFactory() {
    }

    public static MultiPartMessageFactory obtain(String hostName, String sender) {
        MultiPartMessageFactory factory = new MultiPartMessageFactory();
        factory.smtpHost = hostName;
        factory.sender = sender;
        factory.doInit();
        return factory;
    }

    protected void doInit() {
        this.receiverList = new ArrayList();
        this.subject = "";
        this.content = "";
        this.attachmentList = new ArrayList();
    }

    public MultiPartMessageFactory addReceiver(String receiver) {
        this.receiverList.add(receiver);
        return this;
    }

    public MultiPartMessageFactory addAllReceiver(List<String> receiverList) {
        this.receiverList.addAll(receiverList);
        return this;
    }

    public MultiPartMessageFactory setupSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public MultiPartMessageFactory setupContent(String content) {
        this.content = content;
        return this;
    }

    public MultiPartMessageFactory appendContent(String partOfContent) {
        this.content += partOfContent;
        return this;
    }

    public MultiPartMessageFactory setupAttachmentList(List attachmentList) {
        this.attachmentList = attachmentList;
        return this;
    }

    public MultiPartMessageFactory addAttachment(File attachment) {
        this.attachmentList.add(attachment);
        return this;
    }

    public MultiPartMessageFactory addAllAttachment(List attachmentList) {
        this.attachmentList.addAll(attachmentList);
        return this;
    }

    public MimeMessage produceMessage() throws MessagingException, IOException {
        MimeMessage msg = TextMessageFactory.createEmptyMsg(smtpHost);
        msg = MessageHelper.setupSender(msg, sender);
        msg = MessageHelper.setupReceivers(msg, receiverList);
        msg = MessageHelper.setupSubject(msg, subject, DEFAULT_CHARSET);
        if (this.attachmentList == null || this.attachmentList.isEmpty()) {
            msg = MessageHelper.setupHtmlContent(msg, content, DEFAULT_CONTENT_TYPE);
        } else {
            Object objAttachment = this.attachmentList.get(0);
            if (objAttachment instanceof File) {
                msg = MessageHelper.setupHtmlContentWithFile(msg, content, DEFAULT_CONTENT_TYPE, attachmentList);
            } else if (objAttachment instanceof MimeBodyPart) {
                msg = MessageHelper.setupBodyParts(msg, attachmentList);
            }
        }
        this.doInit(); //這個是清空暫存在這裏面的 subject、content。而不是 MimeMessage裡面的
        return msg;
    }
}