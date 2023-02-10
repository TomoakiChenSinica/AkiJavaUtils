/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.util.cast;

import com.ctc.wstx.stax.WstxInputFactory;
import com.ctc.wstx.stax.WstxOutputFactory;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import java.io.IOException;
import javax.xml.stream.XMLInputFactory;

/**
 *
 * @author Tomoaki Chen
 */
public class XmlToJava {

    public static Boolean NEED_WRITE_XML_DECLARATION = Boolean.FALSE;
    
    public static XmlMapper createXmlMapper() {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper;
    }

    public static <T> T getJavaObject(String xmlData, Class<T> objectType) throws IOException {
        if (xmlData != null) {
            T javaObject;
//            XmlMapper xmlMapper = createXmlMapper();
            XMLInputFactory input = new WstxInputFactory();
            input.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE); //目前 Jackson XML 對於另外定義namespace的支援似乎不太好(有看到issue，但還是open，實際測試也很多問題)            
            //用configure會不會比較好? 這兩種set相通? 相等?
            XmlMapper xmlMapper = new XmlMapper(new XmlFactory(input, new WstxOutputFactory()));
            xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, NEED_WRITE_XML_DECLARATION);
            javaObject = xmlMapper.readValue(xmlData, objectType);
            return (T) javaObject;
        } else {
            return null;
        }
    }
}
