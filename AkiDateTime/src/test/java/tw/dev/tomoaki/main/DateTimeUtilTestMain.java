/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.main;

import tw.dev.tomoaki.util.datetime.DateTimeUtil;

/**
 *
 * @author arche
 */
public class DateTimeUtilTestMain {
    
    public static void main(String []args) {
        System.out.println(DateTimeUtil.Provider.obtainToday());
    }
}
