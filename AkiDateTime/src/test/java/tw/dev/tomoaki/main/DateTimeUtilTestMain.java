/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import tw.dev.tomoaki.util.datetime.DateTimeUtil;

/**
 *
 * @author arche
 */
public class DateTimeUtilTestMain {

    public static void main(String[] args) {
        test2();
    }

    private static void test1() {
        System.out.println(DateTimeUtil.Provider.obtainToday());
        LocalDate desigDate = DateTimeUtil.Provider.parse2Date("2022-01-01");
        LocalDateTime firstMinOfDate = desigDate.atStartOfDay();
        LocalDateTime lastMinOfDate = desigDate.atTime(23, 59, 59);
        System.out.format("desigDate= %s, firstMinOfDate= %s, lastMinOfDate= %s", desigDate, firstMinOfDate, lastMinOfDate);               
    }
    
    private static void test2() {
        Date utilDateTime = DateTimeUtil.Converter.convert(DateTimeUtil.Provider.parse2DateTime("2023-01-30 17:02:00"));
        System.out.format("utilDateTime= %s \n", utilDateTime);
        
        Date utilDate = DateTimeUtil.Converter.convert(DateTimeUtil.Provider.parse2Date("2023-01-30"));
        System.out.format("utilDate= %s \n", utilDate);        
        
        
        LocalDateTime dateTime = DateTimeUtil.Converter.convert2DateTime(utilDateTime);
        System.out.format("dateTime= %s \n", dateTime);
        
        LocalDate date = DateTimeUtil.Converter.convert2Date(utilDate);
        System.out.format("date= %s \n", date);
    }
}
