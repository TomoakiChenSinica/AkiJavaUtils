/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tw.dev.tomoaki.cast.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import tw.dev.tomoaki.cast.main.entity.Batter;
import tw.dev.tomoaki.cast.main.entity.player.Player;
import tw.dev.tomoaki.util.cast.JavaToXml;
import tw.dev.tomoaki.util.cast.XmlToJava;

/**
 *
 * @author Tomoaki Chen
 */
public class JavaToXmlMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JsonProcessingException, IOException {
        test2();
    }
    
    private static void test1() throws JsonProcessingException {
        // TODO code application logic here
        Batter ackley = new Batter("Dustin", "Ackley", 0.253);
        Batter smoak = new Batter("Justin", "Smoak", 0.232);
        List<Batter> batterList = Arrays.asList(ackley, smoak);
        
        String xmlData4BatterSmoak = JavaToXml.getXmlString(smoak);
        System.out.println(xmlData4BatterSmoak);
        
        String xmlData4BatterList = JavaToXml.getXmlString(batterList); //出來的會怪怪的
        System.out.println(xmlData4BatterList);    
    }
    
    private static void test2() throws JsonProcessingException {
        Player player = new Player();
        player.setName("Link");
        player.setHealthPoint(1000000000);
        player.setSpecialPoint(99999);
        String xmlData4Player = JavaToXml.getXmlString(player);
        System.out.format("player= %s \n", xmlData4Player);        
    }
    
}
