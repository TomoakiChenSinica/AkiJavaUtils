/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.main;

import java.time.LocalDate;
import tw.dev.tomoaki.datetimecloze.LocalDateCloze;
import tw.dev.tomoaki.datetimecloze.DateFormatRangeParser;
import tw.dev.tomoaki.util.datetime.DateTimeUtil;

/**
 *
 * @author Tomoaki Chen
 */
public class TestMain {

    public static void main(String[] args) {
        test1_2();
    }

    protected static void test1() {
        // DateTimeFormatParser.parseFormat2Date(2022, "[YYYY-1]-01-01、[YYYY+1]-01-01");
        // LocalDate desigDate = LocalDateCloze.fillWith("[YYYY-1]-[MM+1]-01", 2022, 12);
        LocalDate desigDate = LocalDateCloze.fillWith("[YYYY+1]-[MM]-[DD]", 2020, 2, 29);
        System.out.println(desigDate);
    }
    
    protected static void test1_2() {
        // LocalDate desigDate = LocalDateCloze.fillWith("[YYYY+1]-12-31", 2020, 2, 29);
        LocalDate desigDate = LocalDateCloze.fillWith("[YYYY+1]-12-31", 2020);
        System.out.println(desigDate);
    }    

    protected static void test2() {
        // LocalDate standardDate = DateTimeUtil.Provider.parse2Date("2022-12-01");
        // System.out.println(standardDate.plusYears(-1));
        LocalDate desigDate2 = DateTimeUtil.Provider.parse2Date("2022-08-01");
        Integer annual = DateFormatRangeParser.analyzeSimpleAnnual("[YYYY]-08-01", "[YYYY+1]-07-31", desigDate2);
        System.out.println(annual);
    }
}
