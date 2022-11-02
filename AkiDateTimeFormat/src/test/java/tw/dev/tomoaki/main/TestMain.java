/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.main;

import java.time.LocalDate;
import tw.dev.tomoaki.datetimeformat.DateFormatParser;
import tw.dev.tomoaki.datetimeformat.DateFormatRangeParser;
import tw.dev.tomoaki.util.datetime.DateTimeUtil;

/**
 *
 * @author Tomoaki Chen
 */
public class TestMain {
    
    public static void main(String[] args) {
//        DateTimeFormatParser.parseFormat2Date(2022, "[YYYY-1]-01-01、[YYYY+1]-01-01");
        LocalDate desigDate = DateFormatParser.parseFormat2Date("[YYYY-1]-[MM+1]-01", 2022, 12);
        System.out.println(desigDate);
        
//        LocalDate standardDate = DateTimeUtil.Provider.parse2Date("2022-12-01");
//        System.out.println(standardDate.plusYears(-1));


        LocalDate desigDate2 = DateTimeUtil.Provider.parse2Date("2022-08-01");
        Integer annual = DateFormatRangeParser.analyzeSimpleAnnual("[YYYY]-08-01", "[YYYY+1]-07-31", desigDate2);
        System.out.println(annual);
    }
}
