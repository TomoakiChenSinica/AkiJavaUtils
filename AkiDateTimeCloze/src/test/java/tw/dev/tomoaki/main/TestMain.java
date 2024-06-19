/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.main;

import java.time.LocalDate;
import java.util.stream.Stream;
import tw.dev.tomoaki.datetimecloze.bundle.LocalDateCloze;

import tw.dev.tomoaki.util.datetime.DateTimeUtil;
import tw.dev.tomoaki.util.datetime.entity.DateRange;

//import tw.dev.tomoaki.datetimecloze.bundle.LocalDateCloze;
import tw.dev.tomoaki.datetimecloze.util.ClozeFormatRangeAnalyzer;
import tw.dev.tomoaki.datetimecloze.util.ClozeFormatRangeHelper;
import tw.dev.tomoaki.datetimecloze.entity.ClozeFormatRange;

/**
 *
 * @author Tomoaki Chen
 */
public class TestMain {

    public static void main(String[] args) {
        test4();
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
        Integer annual = ClozeFormatRangeAnalyzer.analyzeSimpleAnnual("[YYYY]-08-01", "[YYYY+1]-07-31", desigDate2);
        System.out.println(annual);
    }

    protected static void test3() {
        LocalDate today = LocalDate.now();
        ClozeFormatRange formatRange = ClozeFormatRange.Factory.create("[YYYY]-[MM]-07", "[YYYY]-[MM+1]-06");
        DateRange dateRange = ClozeFormatRangeHelper.doCovert(formatRange, today.getYear(), today.getMonthValue(), today.getDayOfMonth());
        System.out.println(dateRange);
    }

    protected static void test4() {
        ClozeFormatRange formatRange = ClozeFormatRange.Factory.create("[YYYY]-[MM]-07", "[YYYY]-[MM+1]-06");
        /*
        2024-05-07 ~ 2024-06-06
        2024-06-07 ~ 2024-07-06
        2024-07-07 ~ 2024-08-07
        */        
        // -----------------------------------------------------------------------------------------------------------
        // String endClozeFormat = formatRange.getStartClozeFormat();        
        // DateCloze cloze = DateCloze.Factory.create(endClozeFormat);
        Stream.of("2024-06-01", "2024-06-07", "2024-07-01").forEach(strDate -> {
            LocalDate date = DateTimeUtil.Provider.parse2Date(strDate);
            DateRange range = ClozeFormatRangeHelper.calculateInRange(formatRange, date);
            System.out.println(range);
        });
        
                
        // -----------------------------------------------------------------------------------------------------------
        /*LocalDate desigDate = DateTimeUtil.Provider.parse2Date("2024-06-07");
        LocalDate resultDate = desigDate.plusMonths(1);
        System.out.println(String.format("desigDate= %s, resultDate= %s", desigDate, resultDate));*/
    }
}
