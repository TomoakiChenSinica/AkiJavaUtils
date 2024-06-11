/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.main;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

/**
 *
 * @author Tomoaki Chen
 */
public class PeriodAndDuration {
 
    
    public static void main(String []args) {
        LocalDate startDate = LocalDate.of(2022, Month.JULY, 1);
        LocalDate endDate = LocalDate.of(2022, Month.AUGUST, 1);
        Period period = Period.between(startDate, endDate);

        Period plusWeekPeriod = period.plusDays(14);
        System.out.println(plusWeekPeriod);
    }
}
