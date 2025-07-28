/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.util.web.webservice.helper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

/**
 *
 * @author tomoaki
 */
public class SOAPMessageHelper {

    private final static String DEFAULT_ENCODING = "UTF-8";
    
    private SOAPMessageHelper() {
    }

    public static String stringfy(SOAPMessage responseMessage) throws SOAPException, IOException {
        ByteArrayOutputStream osResponseMessage = new ByteArrayOutputStream();
        responseMessage.writeTo(osResponseMessage);
        return osResponseMessage.toString(DEFAULT_ENCODING);
    }
}
