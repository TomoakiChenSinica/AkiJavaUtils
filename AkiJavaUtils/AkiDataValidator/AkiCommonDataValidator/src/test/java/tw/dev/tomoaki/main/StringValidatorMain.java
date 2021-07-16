/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.main;

import tw.dev.tomoaki.util.commondatavalidator.StringValidator;

/**
 *
 * @author Tomoaki Chen
 */
public class StringValidatorMain {

    public static void main(String[] args) {
        String test1 = null;
        String test2 = "";
        String test3 = "123123";
        System.out.println(StringValidator.isValueExist(test1));
        System.out.println(StringValidator.isValueExist(test2));
        System.out.println(StringValidator.isValueExist(test3));
    }

}
