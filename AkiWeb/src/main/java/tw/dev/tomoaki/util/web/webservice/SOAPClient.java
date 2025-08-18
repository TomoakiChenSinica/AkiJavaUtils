/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.util.web.webservice;

import java.io.IOException;
import java.util.Arrays;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import tw.dev.tomoaki.util.web.webservice.helper.SOAPClientHelper;
import tw.dev.tomoaki.util.web.webservice.helper.SOAPMessageHelper;

/**
 *
 * @author tomoaki
 */
public class SOAPClient extends BasicHttpClient { // 之後可以嘗試依賴反轉

    public Response call(WebTarget target, SOAPMessage requestMessage) throws SOAPException, IOException {
        String strSOAPRequest = SOAPMessageHelper.stringfy(requestMessage);
        return this.call(target, strSOAPRequest);
    }

    public Response call(WebTarget target, String strSOAPRequest) {
        Response resp = this.doPost(target, MediaType.APPLICATION_XML_TYPE, MediaType.APPLICATION_XML_TYPE, strSOAPRequest);
        return resp;
    }

    public Response call(WebTarget target, SOAPMessage requestMessage, String cookie) throws SOAPException, IOException {
        String strSOAPRequest = SOAPMessageHelper.stringfy(requestMessage);
        return this.call(target, strSOAPRequest, cookie);
    }

    public Response call(WebTarget target, String strSOAPRequest, String cookie) {
        Response resp = this.doPost(target, MediaType.APPLICATION_XML_TYPE, MediaType.APPLICATION_XML_TYPE, null, Arrays.asList(cookie), strSOAPRequest);
        return resp;
    }

    // -------------------------------------------------------------------------------------------------
    
    public SOAPMessage responseMessage(WebTarget target, SOAPMessage requestMessage) throws SOAPException, IOException {
        return SOAPClientHelper.parseMessage(this.call(target, requestMessage));
    }

    public SOAPMessage responseMessage(WebTarget target, String strSOAPRequest) throws SOAPException, IOException {
        return SOAPClientHelper.parseMessage(this.call(target, strSOAPRequest));
    }

    public SOAPMessage responseMessage(WebTarget target, SOAPMessage requestMessage, String cookie) throws SOAPException, IOException {
        return SOAPClientHelper.parseMessage(this.call(target, requestMessage, cookie));
    }

    public SOAPMessage responseMessage(WebTarget target, String strSOAPRequest, String cookie) throws SOAPException, IOException {
        return SOAPClientHelper.parseMessage(this.call(target, strSOAPRequest, cookie));
    }
}
