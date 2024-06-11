/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.datetimecloze;

import java.time.LocalDate;
import java.util.Date;
import tw.dev.tomoaki.datetimecloze.entity.ClozeFormatRange;
import tw.dev.tomoaki.util.datetime.DateTimeUtil;

/**
 *
 * @author Tomoaki Chen
 */
public class ClozeFormatRangeAnalyzer {

    public static Integer analyzeSimpleAnnual(ClozeFormatRange range, Date utilDate) {
        return analyzeSimpleAnnual(range.getStartClozeFormat(), range.getEndClozeFormat(), DateTimeUtil.Converter.convert2Date(utilDate));
    }

    public static Integer analyzeSimpleAnnual(ClozeFormatRange range, LocalDate date) {
        return analyzeSimpleAnnual(range.getStartClozeFormat(), range.getEndClozeFormat(), date);
    }

    public static Integer analyzeSimpleAnnual(String startClozeFormat, String endClozeFormat, Date utilDate) {
        return analyzeSimpleAnnual(startClozeFormat, endClozeFormat, DateTimeUtil.Converter.convert2Date(utilDate));
    }

    public static Integer analyzeSimpleAnnual(String startClozeFormat, String endClozeFormat, LocalDate date) {
        Integer desigYear = date.getYear();
        LocalDate startDate = LocalDateCloze.fillWith(startClozeFormat, desigYear);
        LocalDate endDate = LocalDateCloze.fillWith(endClozeFormat, desigYear);
        if (date.isBefore(startDate)) {
            //比起使時間還早，代表事是前一年度
            return desigYear - 1;
        } else if (date.isAfter(endDate)) {
            return desigYear + 1;
        } else {
            return desigYear;
        }
    }
}
