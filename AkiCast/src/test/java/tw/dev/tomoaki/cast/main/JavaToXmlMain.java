/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tw.dev.tomoaki.cast.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Arrays;
import java.util.List;
import tw.dev.tomoaki.cast.main.entity.Batter;
import tw.dev.tomoaki.util.cast.JavaToXml;

/**
 *
 * @author Tomoaki Chen
 */
public class JavaToXmlMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JsonProcessingException {
        // TODO code application logic here
        Batter ackley = new Batter("Dustin", "Ackley", 0.253);
        Batter smoak = new Batter("Justin", "Smoak", 0.232);
        List<Batter> batterList = Arrays.asList(ackley, smoak);
        
        String xmlData4BatterSmoak = JavaToXml.getXmlString(smoak);
        System.out.println(xmlData4BatterSmoak);
        
        String xmlData4BatterList = JavaToXml.getXmlString(batterList); //出來的會怪怪的
        System.out.println(xmlData4BatterList);
    }
    
}
