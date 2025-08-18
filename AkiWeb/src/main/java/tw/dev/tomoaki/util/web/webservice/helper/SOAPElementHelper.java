/*
 * Copyright 2025 tomoaki.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tw.dev.tomoaki.util.web.webservice.helper;

import java.io.StringWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Node;

/**
 *
 * @author tomoaki
 */
public class SOAPElementHelper {

    
    public static String stringfy(Node node) throws TransformerException {
        return stringfy(node, false);
    }
    
    
    /**
     *  https://chatgpt.com/share/689ee897-47cc-800d-a5e1-58b9967c596e
     * 
     * @param node 格式是 org.w3c.dom.Node，SOAPNode、SOAPBody 皆繼承此
     * @param includeXmlDeclaration 保留 XML Declaration
     * @return SOAP 的 XML 轉成字串
     * @throws javax.xml.transform.TransformerException
     */
    public static String stringfy(Node node, Boolean includeXmlDeclaration) throws TransformerException { // public static String stringfy(SOAPElement element) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, includeXmlDeclaration ? "no" : "yes");
        
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(node), new StreamResult(writer));
        String xmlElement = writer.toString();
        return xmlElement;
    }
}
