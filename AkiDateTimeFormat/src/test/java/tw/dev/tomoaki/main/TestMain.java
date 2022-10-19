/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.main;

import tw.dev.tomoaki.datetimeformat.DateTimeFormatParser;

/**
 *
 * @author Tomoaki Chen
 */
public class TestMain {
    
    public static void main(String[] args) {
//        DateTimeFormatParser.parseFormat2Date(2022, "[YYYY-1]-01-01、[YYYY+1]-01-01");
        System.out.println(DateTimeFormatParser.parseFormat2Date("[YYYY-1]-[DD+1]-01", 2022, 1)  );
    }
}
