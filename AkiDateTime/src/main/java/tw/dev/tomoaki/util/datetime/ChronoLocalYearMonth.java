/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.datatime;

import java.time.LocalDate;

/**
 *
 * @author tomoaki
 */
public interface ChronoLocalYearMonth {

    public LocalDate atDay(Integer dayOfMonth);

    public boolean isBefore(ChronoLocalYearMonth otherYearMonth);

    public boolean isAfter(ChronoLocalYearMonth otherYearMonth);

    public boolean isEqual(ChronoLocalYearMonth otherYearMonth);
    
    @Override
    public boolean equals(Object obj);

}
