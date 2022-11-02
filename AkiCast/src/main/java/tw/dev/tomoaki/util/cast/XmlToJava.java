/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.util.cast;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;

/**
 *
 * @author Tomoaki Chen
 */
public class XmlToJava {
    
    public static XmlMapper createXmlMapper() {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper;
    }
    
    
    public static <T> T getJavaObject(String xmlData, Class<T> objectType) throws IOException {
        if (xmlData != null) {
            T javaObject;
            XmlMapper xmlMapper = createXmlMapper();
            javaObject = xmlMapper.readValue(xmlData, objectType);
            return (T) javaObject;
        } else {
            return null;
        }
    }    
}
