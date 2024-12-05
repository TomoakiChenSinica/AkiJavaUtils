/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.main;

import java.util.Objects;
import tw.dev.tomoaki.util.commondatavalidator.StringValidator;

/**
 *
 * @author Tomoaki Chen
 */
public class StringValidatorMain {

    public static void main(String[] args) {
        test4();
    }

    private static void test1() {
        String test1 = null;
        String test2 = "";
        String test3 = "123123";
        System.out.println(StringValidator.isValueExist(test1));
        System.out.println(StringValidator.isValueExist(test2));
        System.out.println(StringValidator.isValueExist(test3));
    }

    private static void test2() {
        String test1 = null;
        String test2 = "";
        String test3 = "123123";
        String test4 = "";
        System.out.println(Objects.equals(test1, test2));
        System.out.println(Objects.equals(test1, test3));
        System.out.println(Objects.equals(test2, test3));
        System.out.println(Objects.equals(test2, test4));
    }

    private static void test3() {
        String test1 = null;
        String test2 = "";
        String test3 = "123123";
        String test4 = "";
        System.out.println(StringValidator.equalsSafely(test1, test2));
        System.out.println(StringValidator.equalsSafely(test1, test3));
        System.out.println(StringValidator.equalsSafely(test2, test3));
        System.out.println(StringValidator.equalsSafely(test2, test4));
    }

    private static void test4() {
        String test1 = null;
        String test2 = "";
        String test3 = "123123";
        String test4 = " 123123  ";
        System.out.println(StringValidator.equalsTrimly(test1, test2));
        System.out.println(StringValidator.equalsTrimly(test1, test3));
        System.out.println(StringValidator.equalsTrimly(test2, test3));
        System.out.println(StringValidator.equalsTrimly(test2, test4));
        System.out.println(StringValidator.equalsTrimly(test3, test4));
    }

    private static void test5() {
        String test1 = null;
        String test2 = "";
        String test3 = "123123";
        String test4 = " 123123  ";
        System.out.println(StringValidator.equalsTrimlyAndSafely(test1, test2));
        System.out.println(StringValidator.equalsTrimlyAndSafely(test1, test3));
        System.out.println(StringValidator.equalsTrimlyAndSafely(test2, test3));
        System.out.println(StringValidator.equalsTrimlyAndSafely(test2, test4));
        System.out.println(StringValidator.equalsTrimlyAndSafely(test3, test4));
    }

}
