/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author tomoaki
 */
public class AccessKeeper {

    public enum Method {
        NoHead(1, "去頭", "編碼後去頭"),
        NoTail(2, "去尾", "編碼後去尾"),
        NoHeadAndNoTail(3, "去頭去尾", "編碼後去頭去尾");

        private Integer code;
        private String summary;
        private String detail;

        private Method(Integer code, String summary, String detail) {
            this.code = code;
            this.summary = summary;
            this.detail = detail;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public static Method codeOf(Integer designatedCode) {
            Method designatedMethod = null;
            for (Method method : Method.values()) {
                if (method.getCode().equals(designatedCode)) {
                    designatedMethod = method;
                    break;
                }
            }
            if (designatedMethod == null) {
                throw new IllegalArgumentException("Cannot found[" + designatedCode + "]");
            }
            return designatedMethod;
        }

    }

    private final String prefixPk = "toMoaki";
    private final String suffixPk = "AccESsKeEPer";
    private final String middlePk = "KuROsAki";
    private String theAlgorithm;
    private MessageDigest md;

    protected AccessKeeper() {
    }

    public static class Factory {

        public static AccessKeeper create() throws NoSuchAlgorithmException {
            AccessKeeper keeper = new AccessKeeper();
            keeper.theAlgorithm = "MD5";
            keeper.md = MessageDigest.getInstance(keeper.theAlgorithm);
            return keeper;
        }

        public static AccessKeeper create(String theAlgorithm) throws NoSuchAlgorithmException {
            AccessKeeper keeper = new AccessKeeper();
            keeper.theAlgorithm = theAlgorithm;
            keeper.md = MessageDigest.getInstance(keeper.theAlgorithm);
            return keeper;
        }
    }

    protected static String byte2Hex(byte b) {
        String[] hex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        int i = b;
        //System.out.println("byte and int : " + b + " " + i);
        if (i < 0) {
            i += 256;
        }
        return hex[i / 16] + hex[i % 16];
    }

    protected String obtainPasswordCutHead(String oriPassword) {
        String strongPassword = null;
        int cutLength = oriPassword.length() / 3;
        int startIndex = 0 + cutLength;
        strongPassword = oriPassword.substring(startIndex, oriPassword.length());
        return strongPassword;
    }

    protected String obtainPasswordCutTail(String oriPassword) {
        String strongPassword = null;
        int cutLength = oriPassword.length() / 3;
        int endIndex = oriPassword.length() - cutLength;
        strongPassword = oriPassword.substring(0, endIndex);
        return strongPassword;
    }

    protected String createEncodedPassowrd(String... inputs) {
        String thePassword = null;
        String str = "";
        for (String input : inputs) {
            str += input + middlePk;
        }
        str = prefixPk + str + suffixPk;
        byte[] encodeByte = md.digest(str.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < encodeByte.length; i++) {
            sb.append(byte2Hex(encodeByte[i]));
        }

        thePassword = sb.toString();
        //System.out.println("buffer and string : " + sb + "  " + thePassword);
        return thePassword;
    }

    protected Boolean isPasswordMatch(String userAccessPassword, String correctPassword) {
        return userAccessPassword != null && userAccessPassword.equals(correctPassword);
    }

    /**
     * 根據使用者輸入產生 password
     *
     * @param inputs 使用者輸入，可以是無數個字串(String)
     * @return 密碼，編碼之後再裁切而成
     *
     */
    public String createPassword(String... inputs) {
        return this.createPassword(Method.NoHeadAndNoTail.getCode(), inputs);
    }

    /**
     * 根據使用者輸入產生 password
     *
     * @param methodCode 編碼後，形成密碼前的裁切方式代碼
     * @param inputs 使用者輸入，可以是無數個字串(String)
     * @return 密碼，編碼之後再裁切而成
     *
     */
    public String createPassword(Integer methodCode, String... inputs) {
        Method designatedMethod = Method.codeOf(methodCode);
        return this.createPassword(designatedMethod, inputs);
    }

    /**
     * 根據使用者輸入產生 password
     *
     * @param designatedMethod 編碼後，形成密碼前的裁切方式
     * @param inputs 使用者輸入，可以是無數個字串(String)
     * @return 密碼，編碼之後再裁切而成
     *
     */
    public String createPassword(Method designatedMethod, String... inputs) {
        String strongPassword = null;
        String oriPassword = createEncodedPassowrd(inputs);
        switch (designatedMethod) {
            case NoHead: {
                strongPassword = this.obtainPasswordCutHead(oriPassword);
                break;
            }
            case NoTail: {
                strongPassword = this.obtainPasswordCutTail(oriPassword);
                break;
            }
            case NoHeadAndNoTail: {
                strongPassword = this.obtainPasswordCutTail(this.obtainPasswordCutHead(oriPassword));
                break;
            }
        }
        return strongPassword;

    }

    /**
     * 使用者的密碼是由使用者的輸入資料產生。<br>
     * 確認使用者使用的密碼，是否與輸入的資料相符。 <br>
     *
     * @param userAccessPassword 使用者的密碼
     * @param inputs 使用者的輸入資料
     * @return 使用者的密碼是否與輸入資料相符
     */
    public Boolean checkPassword(String userAccessPassword, String... inputs) {
        Method defaultMethod = Method.NoHeadAndNoTail;
        return this.checkPassword(userAccessPassword, defaultMethod, inputs);
    }

    /**
     * 使用者的密碼是由使用者的輸入資料產生。<br>
     * 確認使用者使用的密碼，是否與輸入的資料相符。 <br>
     *
     * @param userAccessPassword 使用者的密碼
     * @param methodCode 編碼後，成為密碼前的裁切方式
     * @param inputs 使用者的輸入
     * @return 使用者的密碼是否與輸入資料相符
     */
    public Boolean checkPassword(String userAccessPassword, Integer methodCode, String... inputs) {
        Method method = Method.codeOf(methodCode);
        return this.checkPassword(userAccessPassword, method, inputs);
    }

    /**
     * 使用者的密碼是由使用者的輸入資料產生。<br>
     * 確認使用者使用的密碼，是否與輸入的資料相符。 <br>
     *
     * @param userAccessPassword 使用者的密碼
     * @param designatedMethod 編碼後，成為密碼前的裁切方式
     * @param inputs 使用者的輸入
     * @return 使用者的密碼是否與輸入資料相符
     */
    public Boolean checkPassword(String userAccessPassword, Method designatedMethod, String... inputs) {
        String correctassword = this.createPassword(designatedMethod, inputs);
        return this.isPasswordMatch(userAccessPassword, correctassword);
    }

    // Conflict With checkPassword(String userAccessPassword, String... inputs)
//    public Boolean checkPassword(String userAccessPassword, String correctPassword) {
//        return userAccessPassword != null && userAccessPassword.equals(correctPassword); 
//    }
}
