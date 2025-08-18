/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.util.web.webservice.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import javax.xml.soap.MessageFactory;
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
    
    public static SOAPMessage parseSOAP(String xmlSOAP) throws SOAPException, IOException {        
        return parseSOAP(xmlSOAP, "UTF-8");
    }        
    
    public static SOAPMessage parseSOAP(String xmlSOAP, String charSetName) throws SOAPException, IOException {        
        return parseSOAP(xmlSOAP, Charset.forName(charSetName));
    }    
    
    
    /**
     * 參考自: https://chatgpt.com/share/689ed68a-117c-800d-957b-0f7a9151713f
     * 
     * @param xmlSOAP 
     * @param charSet 編碼
     * @return SOAPMessage，Body 是 bodyContent
     * @throws javax.xml.soap.SOAPException
     * @throws java.io.IOException
     * 
     */
    public static SOAPMessage parseSOAP(String xmlSOAP, Charset charSet) throws SOAPException, IOException {        
        ByteArrayInputStream isBodyContent = new ByteArrayInputStream(xmlSOAP.getBytes(charSet));
        MessageFactory factory = MessageFactory.newInstance();
        return factory.createMessage(null, isBodyContent);
    }
}
