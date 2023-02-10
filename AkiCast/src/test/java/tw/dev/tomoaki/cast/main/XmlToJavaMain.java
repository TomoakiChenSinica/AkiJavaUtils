/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.cast.main;

import java.io.IOException;
import tw.dev.tomoaki.cast.main.entity.Batter;
import tw.dev.tomoaki.cast.main.entity.player.Player;
import tw.dev.tomoaki.util.cast.XmlToJava;

/**
 *
 * @author Tomoaki Chen
 * https://github.com/FasterXML/jackson-dataformat-xml
 * https://www.baeldung.com/jackson-xml-serialization-and-deserialization
 */
public class XmlToJavaMain {
 
    public static void main(String[] args) throws IOException {
        test2();
    }
    
    private static void test1() throws IOException {
        String xmlData = "<Batter><firstName>Justin</firstName><lastName>Smoak</lastName><avg>0.232</avg></Batter>";
        Batter smoak = XmlToJava.getJavaObject(xmlData, Batter.class);
        System.out.println("smoak avg= " + smoak.getAvg());    
    }
    
    private static void test2() throws IOException {
        String strXMLData = "<aki:player><aki:name>Link</aki:name><aki:hp>1000000000</aki:hp><aki:sp>99999</aki:sp></aki:player>";
        Player player = XmlToJava.getJavaObject(strXMLData, Player.class);
        System.out.println("player= " + player);
    }
        
}
