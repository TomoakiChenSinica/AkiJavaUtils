/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.util.cast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 *
 * @author Tomoaki Chen
 */
public class JavaToXml {
    
    public static XmlMapper createXmlMapper() {
        XmlMapper mapper = new XmlMapper();
        return mapper;
    }
    
    public static <T> String getXmlString(T obj) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(obj);
        return xml;
    }
}
