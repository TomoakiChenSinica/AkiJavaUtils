/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.aki.mail.helper;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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
    public static MimeMessage setupMulitPartPlainTextContent(MimeMessage message, String plainTextContent, String contentType, List<File> fileList) throws MessagingException, IOException {
        Multipart multipart = new MimeMultipart();
        
        MimeBodyPart plainTextContentBodyPart = new MimeBodyPart();
        plainTextContentBodyPart.setContent(plainTextContent, contentType);
        multipart.addBodyPart(plainTextContentBodyPart);
        
        for(File file : fileList) {
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();

//            attachmentBodyPart.attachFile(file);
//            attachmentBodyPart.setFileName(file.getName());

//            attachmentBodyPart.setFileName(URLEncoder.encode(file.getName(), "UTF-8"));
//            DataSource dataSource = new FileDataSource(file);
//            attachmentBodyPart.setDataHandler(new DataHandler(dataSource, URLConnection.guessContentTypeFromName(file.getName())) );
//            attachmentBodyPart.setFileName(file.getName());            

             attachmentBodyPart.attachFile(file);
             attachmentBodyPart.setHeader("Content-Type", "application/octet-stream; name=\"" + file.getName() + "\";charset=UTF-8");
             attachmentBodyPart.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(file.getName(), "UTF-8")  + "\"" /*+ ";name=\"" + file.getName() + "\""*/); //影響瀏覽器下載?
             /*
              
             1. Content-TypeFileName 不URLEncode ，不在在後面 chartset UTF-8，會被gmail deny
             2. Content-Type 後面加UTF-8，不加URL Encode可以正常寄信，不過還是亂碼(Disposition 尚未Encode或加Charset，也未加上 charset=utf-8
             3. Content-Type 後面加UTF-8，URLEncode，會被擋信
             4. (Content-Type不Encode)即使 Disposition 加上 charset，會收到信，但還是亂碼
             5. Content-Type Encode ，並且兩者都有 charset，收的到信，但名稱亂碼
             6. 兩者都Encode，收不到信
             7 .只送 Content-Type，可見到 原始郵件事是有編碼(用貓咪老師.jpg字樣)，但顯示上次錯
             8. 發現上面 image/jpeg 打成 mage/jpeg，再次回復成兩邊都有加charset and urlencode ，結果檔名變成顯示 encode後的結果  --> 但其實下載好像是對的!!!!
             9. 拿掉 Dispotision的 Encode，變成拒收
             10.兩個都不Encode變亂碼
             11. 前者encode還是亂碼
             12.前者不encode ，後者encode，後面也不用刻意加 utf-8，下載框是對的
             */

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
