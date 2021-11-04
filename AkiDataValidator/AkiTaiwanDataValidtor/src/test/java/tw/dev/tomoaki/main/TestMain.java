/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.main;

import tw.dev.tomoaki.taiwandatavalidtor.TaiwanNewResidenceCardValidator;
import tw.dev.tomoaki.taiwandatavalidtor.TaiwanOldResidenceCardValidator;
import tw.dev.tomoaki.taiwandatavalidtor.TaiwanResidenceCardValidator;

/**
 *
 * @author Tomoaki Chen
 */
public class TestMain {

    public static void main(String[] args) {
        String residenceNo1 = "A800091748";
//        System.out.println(TaiwanNewResidenceCardValidator.doValidate(residenceNo1));
        
        String residenceNo2 = "AD00091748";
//        System.out.println(TaiwanOldResidenceCardValidator.doValidate(residenceNo2));
        
//        TaiwanResidenceCardValidator.doValidate(residenceNo1);
//        TaiwanResidenceCardValidator.doValidate(residenceNo2);
        String residenceNo3 = "AC00091748";
        String residenceNo4 = "A900091748";


        System.out.println(TaiwanResidenceCardValidator.doValidate(residenceNo1));
        System.out.println(TaiwanResidenceCardValidator.doValidate(residenceNo2));
        System.out.println(TaiwanResidenceCardValidator.doValidate(residenceNo3));
        System.out.println(TaiwanResidenceCardValidator.doValidate(residenceNo4));
        
    }     
}
