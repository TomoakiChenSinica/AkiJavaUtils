/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import tw.dev.tomoaki.util.datetime.DateTimeUtil;

/**
 *
 * @author arche
 */
public class DateTimeUtilTestMain {
    
    public static void main(String []args) {
        System.out.println(DateTimeUtil.Provider.obtainToday());
        LocalDate desigDate = DateTimeUtil.Provider.parse2Date("2022-01-01");
        LocalDateTime firstMinOfDate = desigDate.atStartOfDay();
        LocalDateTime lastMinOfDate = desigDate.atTime(23, 59, 59);
        System.out.format("desigDate= %s, firstMinOfDate= %s, lastMinOfDate= %s", desigDate, firstMinOfDate, lastMinOfDate);
    }
}
