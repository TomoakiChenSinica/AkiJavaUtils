/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.taiwandatavalidtor;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tomoaki
 *
 * 一. 字母轉換表 : 
 * 字母	A	B	C	D	E	F	G	H	J	K	L	M	N 
 * 數字	10	11	12	13	14	15	16	17	18	19	20               21	22 
 * 
 * 字母	P	Q	R	S	T	U	V	X	Y	W	Z	I	O 
 * 數字	23	24	25	26	27	28	29	30	31	32	33	34	35
 *
 * 二. 轉換後的身分證字號(共11位數字)每一位數均有固定的權重(Weight)，由左往右依序為 『1 9 8 7 6 5 4 3 2 1 1』。 三.
 * 判斷身分證字號是否正確的方法為： 各位數字與其相對應的權重相乘後再加總，加總後的結果若為10的倍數則身分證字號即屬正確。
 *
 */
public class TaiwanIdCardValidator {

    private static final Integer startNumCode = 10;

    private static Map<String, Integer> idNoNumCodeMappedByFirstEnBitMap = doIdNoNumCodeMappedByFirstEnBitMapSetup();
    private static Map<Integer, Integer> weightMap = doWeightMapSetup();

    private static Map<Integer, Integer> doWeightMapSetup() {
        Map<Integer, Integer> theMap = new HashMap();
        theMap.put(0, 1);
        theMap.put(1, 9);
        theMap.put(2, 8);
        theMap.put(3, 7);
        theMap.put(4, 6);
        theMap.put(5, 5);
        theMap.put(6, 4);
        theMap.put(7, 3);
        theMap.put(8, 2);
        theMap.put(9, 1);
        theMap.put(10, 1);
        return theMap;
    }

    private static Map<String, Integer> doIdNoNumCodeMappedByFirstEnBitMapSetup() {
        Map<String, Integer> theMap = new HashMap();
        Integer numCode = startNumCode;
        for (Integer asciiCode = 65; asciiCode <= 90; asciiCode++) {
            String asciiText = String.valueOf(((char) asciiCode.intValue()));
            //I、O 和 W ~ Z 不是照排序的
            if ("I".equals(asciiText) == false && "O".equals(asciiText) == false && "W".equals(asciiText) == false &&
                "X".equals(asciiText) == false && "Y".equals(asciiText) == false && "Z".equals(asciiText) == false) {
                theMap.put(asciiText, numCode);
                numCode++;
            }
        }
        theMap.put("X", 30);
        theMap.put("Y", 31);
        theMap.put("W", 32);
        theMap.put("Z", 33);
        theMap.put("I", 34);
        theMap.put("O", 35);
//        System.out.println("theMap : " + theMap);
        return theMap;
    }

    public static Boolean doValidate(String idNo) {
        if (idNo.length() != 10) {
            throw new IllegalArgumentException("身分證長度不符合規則");
        }
        String parsedIdNo = replaceFirstEnBit(idNo);
        Integer result = 0;
        for (Integer index = 0; index <= (11 - 1); index++) {
            String strNumCode = String.valueOf(parsedIdNo.charAt(index)); //這個方法不是很好，先暫時這樣，
            Integer numCode = obtainNumCode(index, strNumCode);
            result += numCode * weightMap.get(index);
        }

        Boolean pass = (result % 10 == 0);

        return pass;
    }

    protected static Integer obtainNumCode(Integer index, String strNumCode) {
        Integer numCode = null;
        try {
            numCode = Integer.parseInt(strNumCode);
        } catch (IllegalArgumentException ex) {
            IllegalArgumentException finalEx;
            if (ex.getMessage().contains("For input string")) {
                finalEx = new IllegalArgumentException("身分證第" + (index + 1 - 1) + "個字" + strNumCode + "不是數字"); //index 有點討厭，原本是index+1(因為陣列)，但又因為身分證的英文馬會變
                finalEx.setStackTrace(ex.getStackTrace());
            } else {
                finalEx = ex;
            }
            throw finalEx;
        }
        return numCode;
    }

    protected static String replaceFirstEnBit(String idNo) {
        String parsedIdNo = idNo;
        char firstEnBit = parsedIdNo.charAt(0);
        String numCode = parseFirstEnBit2NumCode(firstEnBit);
        parsedIdNo = parsedIdNo.replace(String.valueOf(firstEnBit), numCode);
        return parsedIdNo;
    }

    protected static String parseFirstEnBit2NumCode(char firstEnBit) {
        Integer numCode = idNoNumCodeMappedByFirstEnBitMap.get(String.valueOf(firstEnBit));
        if (numCode == null) {
            throw new IllegalArgumentException("身分證第一個字元[" + firstEnBit + "]，不符規則");
        }
        return numCode.toString();
    }
}
