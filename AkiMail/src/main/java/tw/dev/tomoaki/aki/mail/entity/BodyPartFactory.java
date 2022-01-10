/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.aki.mail.entity;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

/**
 *
 * @author arche
 */
public class BodyPartFactory {

    public static BodyPart createFileBodyPart(String path2File) throws MessagingException {
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(path2File);
        String fileName = BodyPartFactory.obtainFileName(source);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fileName);
        return messageBodyPart;
    }
    
    protected static String obtainFileName(DataSource dataSource) {
        return dataSource.getName();
    }
}
