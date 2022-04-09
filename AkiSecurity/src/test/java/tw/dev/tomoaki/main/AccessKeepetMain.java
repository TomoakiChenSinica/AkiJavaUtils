/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.main;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
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
//        AccessKeeper keeper = AccessKeeper.Factory.create();
<<<<<<< Updated upstream
//        String password = keeper.createPassword("tomoaki.outside@gmail.com", "Netflix");
//        System.out.println(password);
//        System.out.println(keeper.checkPassword(password, "TomoakiTesing"));
//        System.out.println(keeper.checkPassword(password, "TomoakiTesin"));
        
        for(Integer index = 1; index  <= 10 ; index++){
            LocalDateTime nowDateTime = LocalDateTime.now();
            System.out.println(nowDateTime);
        }
=======
//        String password = keeper.createPassword("TomoakiTesing");
//        System.out.println(keeper.checkPassword(password, "TomoakiTesing"));
//        System.out.println(keeper.checkPassword(password, "TomoakiTesin"));
        Netflix();
        DisneyPlus();
    }
    
    public static void Netflix() throws NoSuchAlgorithmException {
        AccessKeeper keeper = AccessKeeper.Factory.create();
        String password = keeper.createPassword("tomoaki.outside@gmail.com", "Netflix");
        System.out.println(password);
>>>>>>> Stashed changes
    }
    
    public static void DisneyPlus() throws NoSuchAlgorithmException {
        AccessKeeper keeper = AccessKeeper.Factory.create();
        String password = keeper.createPassword("tomoaki.outside@gmail.com", "DisneyPlus");
        System.out.println(password);
    }    
    
}
