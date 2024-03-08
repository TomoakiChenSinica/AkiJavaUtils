/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.aki.mail.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Tomoaki Chen
 * 
 * @deprecated 1. 用這個拆反而會錯。 2. java mail 本身可以吃逗號分隔
 */
public class MailReceiptHelper {

    public static List<String> analyzeReceiptsByStandardSplit(String strReceipt) {
        List<String> splitKeyWordList= Arrays.asList(",", ";");
        return analyzeReceipts(strReceipt, splitKeyWordList);
    }
    
    public static List<String> analyzeReceiptsBySemicolonSplit(String strReceipt) {
        return analyzeReceipts(strReceipt, ";");
    }    
    
    public static List<String> analyzeReceiptsByCommaSplit(String strReceipt) {
        return analyzeReceipts(strReceipt, ",");
    }
        
    public static List<String> analyzeReceipts(String strReceipt, String splitKeyWord) {
        List<String> splitKeyWordList = Arrays.asList(splitKeyWord);
        return analyzeReceipts(strReceipt, splitKeyWordList);
    }    

    public static List<String> analyzeReceipts(String strReceipt, List<String> splitKeyWordList) {
        if (strReceipt == null) {
            return null;
        }
        List<String> receiptList;
        String[] receiptArr = strReceipt.split(obtainSplitKeyWordRegExp(splitKeyWordList));
        if (receiptArr.length > 0) {
            receiptList = new ArrayList();
            for (String receipt : receiptArr) {
                if (receipt != null && receipt.isEmpty() == false) {
                    receiptList.add(receipt.trim());
                }
            }
        } else {
            receiptList = null;
        }
        return receiptList;
    }
    
    private static String obtainSplitKeyWordRegExp(List<String> splitKeyWordList) {
        String splitKeyWordRegExp = "";
        for(Integer index = 0 ; index < splitKeyWordList.size() ; index++) {
            if(index >= 1) {
                splitKeyWordRegExp += "|";
            }
            splitKeyWordRegExp += splitKeyWordList.get(index);
        }
        return splitKeyWordRegExp;
    }
}
