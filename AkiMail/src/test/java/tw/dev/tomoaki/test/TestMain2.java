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
public class TestMain2 {

    public static void main(String[] args) throws MessagingException, IOException {
        String strReceipts = "tomoaki.sinica@gmail.com";//,tomoaki.nccu@gmail.com";
//        List<String> receipts = Arrays.asList("tomoaki.nccu@gmail.com, tomoaki.sinica@gmail.com");
//        File file = new File("C:\\Users\\Tomoaki Chen\\Pictures\\1110000605o3307n3nr1.gif");
        SMTPClient smtpClient = SMTPClient.Factory.create("localhost", 25);
        
        smtpClient.sendHtmlMessage("tomoaki@iis.sinica.edu.tw", strReceipts, "標題", "內容喔喔喔");
//        smtpClient.sendPlainTextMessage("tomoaki@iis.sinica.edu.tw", receipts, "這是我的測試", "這是一篇關於披薩的文章。\r\n   測試中，不要擋信RRRR。Do Re Mi Fa So LAAAAAAAAAAAAAAAaa", file); //OK
//        smtpClient.sendPlainTextMessage("tomoaki@iis.sinica.edu.tw", strReceipts, "這是我的測試", "這是一篇關於披薩的文章。\r\n   測試中，不要擋信RRRR。Do Re Mi Fa So LAAAAAAAAAAAAAAAaa", file); //OK
//        smtpClient.sendHtmlMessage("tomoaki@iis.sinica.edu.tw", MailReceiptHelper.analyzeReceiptsByCommaSplit(strReceipts), "Testing HTML Content", "Hello World <br/> <span style=\"color:red;\">測試</span>", file);
    }
}
