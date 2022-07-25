/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.test;

import java.io.File;
import java.io.IOException;
import javax.mail.MessagingException;
import tw.dev.tomoaki.aki.mail.client.SMTPClient;
import tw.dev.tomoaki.aki.mail.helper.MailReceiptHelper;

/**
 *
 * @author Tomoaki Chen
 */
public class TestMain {
    
    public static void main(String[] args) throws MessagingException, IOException {
        String strReceipts = "tomoaki@iis.sinica.edu.tw ; tomoaki.sinica@gmail.com , tomoaki.nccu@gmail.com";
//        List<String> receipts = Arrays.asList(strReceipts.split(";|,"));
//        System.out.println(receipts);
        System.out.println(MailReceiptHelper.analyzeReceiptsByCommaSplit(strReceipts));
        File file = new File("C:\\Users\\Tomoaki Chen\\Pictures\\TEST\\image2.png");
        System.out.println(file.getName());
        SMTPClient smtpClient = SMTPClient.Factory.create();
//        smtpClient.sendHtmlMessage("tomoaki@iis.sinica.edu.tw", "tomoaki.nccu@gmail.com", "Testing", "Hello World");
        smtpClient.sendPlainTextMessage("tomoaki@iis.sinica.edu.tw", "tomoaki.nccu@gmail.com", "這是我的測試", "這張圖片如何。\r\n測試中，不要擋信RRRR。Do Re Mi Fa So LAAAAAAAAAAAAAAAaa", file);

    }
}
