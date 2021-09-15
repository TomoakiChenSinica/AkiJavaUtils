/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.taiwandatavalidtor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Tomoaki Chen
 */
public class TaiwanResidenceCardValidator {

    public static Boolean doValidate(String residenceNo) {
        Pattern pattern = Pattern.compile("^[A-Z][A-Z]"); //舊式前兩碼為英文
        Matcher matcher = pattern.matcher(residenceNo);
        if(matcher.find()) {
//            System.out.println("舊式");
            return TaiwanOldResidenceCardValidator.doValidate(residenceNo);
        } else {
//            System.out.println("新式");
            return TaiwanNewResidenceCardValidator.doValidate(residenceNo);
        }
    }
}
