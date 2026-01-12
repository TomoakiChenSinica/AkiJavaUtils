/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.datetimeexpression.util;

import java.time.LocalDate;
import java.util.Date;
import tw.dev.tomoaki.datetimeexpression.bundle.LocalDateExpression;
import tw.dev.tomoaki.datetimeexpression.entity.ExpressionFormatRange;
import tw.dev.tomoaki.util.datetime.DateTimeUtil;

/**
 *
 * @author Tomoaki Chen
 */
public class ExpressionRangeAnalyzer {

    public static Integer analyzeSimpleAnnual(ExpressionFormatRange range, Date utilDate) {
        return analyzeSimpleAnnual(range.getStartClozeFormat(), range.getEndClozeFormat(), DateTimeUtil.Converter.convert2Date(utilDate));
    }

    public static Integer analyzeSimpleAnnual(ExpressionFormatRange range, LocalDate date) {
        return analyzeSimpleAnnual(range.getStartClozeFormat(), range.getEndClozeFormat(), date);
    }

    public static Integer analyzeSimpleAnnual(String startClozeFormat, String endClozeFormat, Date utilDate) {
        return analyzeSimpleAnnual(startClozeFormat, endClozeFormat, DateTimeUtil.Converter.convert2Date(utilDate));
    }

    public static Integer analyzeSimpleAnnual(String startClozeFormat, String endClozeFormat, LocalDate date) {
        Integer desigYear = date.getYear();
        LocalDateExpression expression = LocalDateExpression.of().ofYear(desigYear);

        LocalDate startDate = expression.resolve(startClozeFormat); // LocalDateExpression.fillWith(startClozeFormat, desigYear);
        LocalDate endDate = expression.resolve(endClozeFormat); // LocalDateExpression.fillWith(endClozeFormat, desigYear);
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
