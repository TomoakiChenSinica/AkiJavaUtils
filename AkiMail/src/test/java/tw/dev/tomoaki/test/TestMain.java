/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.test;

import java.util.Arrays;
import java.util.List;
import tw.dev.tomoaki.aki.mail.helper.MailReceiptHelper;

/**
 *
 * @author Tomoaki Chen
 */
public class TestMain {
    
    public static void main(String[] args) {
        String strReceipts = "tomoaki@iis.sinica.edu.tw ; tomoaki.sinica@gmail.com , tomoaki.nccu@gmail.com";
//        List<String> receipts = Arrays.asList(strReceipts.split(";|,"));
//        System.out.println(receipts);
        System.out.println(MailReceiptHelper.analyzeReceiptsByCommaSplit(strReceipts));
    }
}
