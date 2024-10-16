/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.util.cast;

import com.ctc.wstx.stax.WstxInputFactory;
import com.ctc.wstx.stax.WstxOutputFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javax.xml.stream.XMLInputFactory;
import static tw.dev.tomoaki.util.cast.XmlToJava.NEED_WRITE_XML_DECLARATION;

/**
 *
 * @author Tomoaki Chen
 */
public class JavaToXml {

    public static Boolean needWriteXMLDeclaration = Boolean.FALSE;
    public static Boolean allowJavaTime = Boolean.FALSE;

    public static XmlMapper createXmlMapper() {
        XmlMapper mapper = new XmlMapper();
        return mapper;
    }

    /*
    public static <T> String getXmlString(T obj) throws JsonProcessingException {
        // XmlMapper xmlMapper = XmlMapper.builder()
        //        .defaultUseWrapper(false)
        //        .build();
        XmlMapper xmlMapper = new XmlMapper();
        if (allowJavaTime) {
            xmlMapper.registerModule(new JavaTimeModule());
        }

//        XMLInputFactory input = new WstxInputFactory();
//        input.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
//        XmlMapper xmlMapper = new XmlMapper(new XmlFactory(input, new WstxOutputFactory()));        
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, needWriteXMLDeclaration); //https://xmlwriter.net/xml_guide/xml_declaration.shtml
        String xml = xmlMapper.writeValueAsString(obj);
        return xml;
    }*/
}
