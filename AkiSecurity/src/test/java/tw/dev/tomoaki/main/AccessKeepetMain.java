/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.main;

import java.security.NoSuchAlgorithmException;
import tw.dev.tomoaki.util.security.AccessKeeper;

/**
 *
 * @author Tomoaki Chen
 */
public class AccessKeepetMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // TODO code application logic here
        AccessKeeper keeper = AccessKeeper.Factory.create();
        String password = keeper.createPassword("TomoakiTesing");
        System.out.println(keeper.checkPassword(password, "TomoakiTesing"));
        System.out.println(keeper.checkPassword(password, "TomoakiTesin"));

    }

}
