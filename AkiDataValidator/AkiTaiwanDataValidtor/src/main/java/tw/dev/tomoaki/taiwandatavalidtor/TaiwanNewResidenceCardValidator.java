/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.taiwandatavalidtor;

import java.util.HashMap;
import java.util.Map;
import tw.dev.tomoaki.util.string.AkiStringUtil;
// import tw.dev.tomoaki.util.stringutils.AkiStringUtil;

/**
 *
 * @author tomoaki
 *
 * http://ryanny-gogogo.blogspot.com/2017/05/blog-post.html
 *
 * 一. 字母轉換表 : 字母	A	B	C	D	E	F	G	H	J	K	L	M	N 數字	10	11	12	13	14	15	16	17	18	19	20
 * 21	22
 *
 * 字母	P	Q	R	S	T	U	V	X	Y	W	Z	I	O 數字	23	24	25	26	27	28	29	30	31	32	33	34	35
 *
 *
 * 二. 第一碼英文字母轉換為二位數字碼(轉換之數字與國民身分證同)，分別乘以特定數；第二碼英文字母轉換成二位數字後，只取尾數乘以特定數 三.
 * 轉換後的身分證字號(共11位數字)每一位數均有固定的權重(Weight)，由左往右依序為 『1 9 8 7 6 5 4 3 2 1 1』。 四.
 * 判斷身分證字號是否正確的方法為： 各位數字與其相對應的權重相乘後再加總，加總後的結果若為10的倍數則身分證字號即屬正確。
 *
 */
public class TaiwanNewResidenceCardValidator {

    private static final Integer startNumCode = 10;

    private static Map<String, Integer> residenceNoNumCodeMappedByEnBitMap = doResidenceNoNumCodeMappedByEnBitMapSetup();
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

    private static Map<String, Integer> doResidenceNoNumCodeMappedByEnBitMapSetup() {
        Map<String, Integer> theMap = new HashMap();
        Integer numCode = startNumCode;
        for (Integer asciiCode = 65; asciiCode <= 90; asciiCode++) {
            String asciiText = String.valueOf(((char) asciiCode.intValue()));
            if ("I".equals(asciiText) == false && "O".equals(asciiText) == false) {
                theMap.put(asciiText, numCode);
                numCode++;
            }
        }
//        theMap.put("I", 34);
//        theMap.put("O", 35);

        //以下之前身分證的bug這邊應該也有，搬移過來
        theMap.put("X", 30);
        theMap.put("Y", 31);
        theMap.put("W", 32);
        theMap.put("Z", 33);
        theMap.put("I", 34);
        theMap.put("O", 35);
        //以上之前身分證的bug這邊應該也有，搬移過來

        return theMap;
    }

    public static Boolean doValidate(String residenceNo) {
        if (residenceNo.length() != 10) {
            throw new IllegalArgumentException("居留證長度不符合規則");
        }
        String parsedIdNo = replaceEnBit(residenceNo);

        Integer result = 0;
        try {

            for (Integer index = 0; index <= (11 - 1); index++) {
                Integer numCode = Integer.parseInt(String.valueOf(parsedIdNo.charAt(index)));
                result += numCode * weightMap.get(index);
            }
        } catch (IllegalArgumentException ex) {
            IllegalArgumentException finalEx;
            if (ex.getMessage().contains("For input string")) {
                finalEx = new IllegalArgumentException(ex.getMessage());
                finalEx.setStackTrace(ex.getStackTrace());
            } else {
                finalEx = ex;
            }
            throw finalEx;
        }
        Boolean pass = (result % 10 == 0);
        return pass;
    }

    protected static String replaceEnBit(String residenceNo) {
        String parsedResidenceNo = residenceNo;
        char firstEnBit = parsedResidenceNo.charAt(0);
        char secondEnBit = parsedResidenceNo.charAt(1);

        String firstNumCode = parseFirstEnBit2NumCode(firstEnBit);
        String secondNumCode = parseSecondEnBit2NumCode(secondEnBit);

        parsedResidenceNo = AkiStringUtil.replaceCharAt(parsedResidenceNo, 1, secondNumCode);
        parsedResidenceNo = AkiStringUtil.replaceCharAt(parsedResidenceNo, 0, firstNumCode);

        return parsedResidenceNo;
    }

    protected static String parseFirstEnBit2NumCode(char firstEnBit) {
        Integer numCode = residenceNoNumCodeMappedByEnBitMap.get(String.valueOf(firstEnBit));
        if (numCode == null) {
            throw new IllegalArgumentException("居留證第一個字元[" + firstEnBit + "]，不符規則");
        }
        return numCode.toString();
    }

    /**
     * 新的居留證第二碼變為數字，只有可能是數字8 or 9
     */
    protected static String parseSecondEnBit2NumCode(char secondEnBit) {
        Integer numCode = null;
        try {
            numCode = Integer.parseInt(String.valueOf(secondEnBit));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("居留證第二個字元[" + secondEnBit + "]，不符規則，須為 8 或 9");
        }
        if (numCode != null && (numCode != 8 && numCode != 9)) {
            throw new IllegalArgumentException("居留證第二個字元[" + secondEnBit + "]，不符規則，須為 8 或 9");
        }
        return numCode.toString();

    }
}
