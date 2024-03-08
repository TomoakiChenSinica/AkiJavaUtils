/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.mail.MessagingException;
import tw.dev.tomoaki.aki.mail.client.SMTPClient;
import tw.dev.tomoaki.aki.mail.helper.MailReceiptHelper;

/**
 *
 * @author Tomoaki Chen
 */
public class TestMain {

    protected final static SMTPClient SMTP_CLIENT = SMTPClient.Factory.create("127.0.0.1", 25);
    protected final static String EMAIL_SENDER = "tomoaki@iis.sinica.edu.tw";

    public static void main(String[] args) throws MessagingException, IOException {
        String strReceipts = "tomoaki.sinica@gmail.com , tomoaki.nccu@gmail.com";
        List<String> receipts = MailReceiptHelper.analyzeReceiptsByCommaSplit(strReceipts);
        File file = new File("C:\\Users\\tomokai\\Pictures\\素材\\喜多中指.gif");
        trySendPlainTextMessageWithMultipleReceivers(receipts);
    }
    
    protected static void trySendPlainTextMessageWithMultipleReceivers(List<String> receipts) throws MessagingException, IOException {
        SMTP_CLIENT.sendPlainTextMessage(EMAIL_SENDER, receipts, "測試測試", "測試內容");
    }

    protected static void trySendPlainTextMessageWithFile(File file) throws MessagingException, IOException {
        SMTP_CLIENT.sendPlainTextMessage(EMAIL_SENDER, "tomoaki.nccu@gmail.com", "這是我的測試", "這張圖片如何。\r\n   測試中，不要擋信RRRR。Do Re Mi Fa So LAAAAAAAAAAAAAAAaa", file);
    }

    protected static void trySendPlainTextMessageWithMultipleReceiversAndFile(List<String> receipts, File file) throws MessagingException, IOException {
        SMTP_CLIENT.sendPlainTextMessage(EMAIL_SENDER, receipts, "這是我的測試", "這是一篇關於披薩的文章。\r\n   測試中，不要擋信RRRR。Do Re Mi Fa So LAAAAAAAAAAAAAAAaa");
    }

    protected static void trySendHTMLMessageWithMultipleReceiversAndFile(String strReceipts, File file) throws MessagingException, IOException {
        SMTP_CLIENT.sendHtmlMessage(EMAIL_SENDER, MailReceiptHelper.analyzeReceiptsByCommaSplit(strReceipts), "Testing HTML Content", "Hello World <br/> <span style=\"color:red;\">測試</span>", file);
    }

    protected static void trySendHTMLMessageWithFile(File file) throws MessagingException, IOException {
        SMTP_CLIENT.sendHtmlMessage(EMAIL_SENDER, "tomoaki.sinica@gmail.com", "Testing HTML Content", "Hello World <br/> <span style=\"color:red;\">測試</span>", file);
    }
}
