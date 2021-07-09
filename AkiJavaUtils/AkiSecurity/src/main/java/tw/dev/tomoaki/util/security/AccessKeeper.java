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

    private String prefixPk = "toMoaki";
    private String suffixPk = "AccESsKeEPer";
    private String theAlgorithm;
    private MessageDigest md;

//    final public static int NoHead = 1;
//    final public static int NoTail = 2;
//    final public static int NoHeadAndNoTail = 3;
//    public PasswordUtil() {
//        try {
//            this.theAlgorithm = "MD5";
//            md = MessageDigest.getInstance(theAlgorithm);
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(PasswordUtil.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public PasswordUtil(String algorithm) {
//        try {
//            setAlgorithm(algorithm);
//            md = MessageDigest.getInstance(theAlgorithm);
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(PasswordUtil.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
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

    public String createPassowrd(String str) {
        String thePassword = null;
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

    public String createStrongPassword(String str) {
        return this.createStrongPassword(str, Method.NoHeadAndNoTail.getCode());
    }

    public String createStrongPassword(String str, int way) {
        String strongPassword = null;
        int newLength;
        int startIndex;
        int endIndex;
        String oriPassword = createPassowrd(str);

//        if (way == Method.NoHead) {
//            strongPassword = this.CutHead(oriPassword);
//        } else if (way == this.NoTail) {
//            strongPassword = this.CutTail(oriPassword);
//        } else if (way == this.NoHeadAndNoTail) {
//            strongPassword = this.CutTail(this.CutHead(oriPassword));
//        }
        Method designatedMethod = Method.codeOf(way);
        switch (designatedMethod) {
            case NoHead: {
                strongPassword = this.CutHead(oriPassword);
                break;
            }
            case NoTail: {
                strongPassword = this.CutTail(oriPassword);
                break;
            }
            case NoHeadAndNoTail: {
                strongPassword = this.CutTail(this.CutHead(oriPassword));
            }
        }
        return strongPassword;
    }

    public String createStrongPassword(int intStr, int way) {
        String str = Integer.toString(intStr);
        return this.createStrongPassword(str, way);
    }

    public static String byte2Hex(byte b) {
        String[] hex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        int i = b;
        //System.out.println("byte and int : " + b + " " + i);
        if (i < 0) {
            i += 256;
        }
        return hex[i / 16] + hex[i % 16];
    }

    protected String CutHead(String oriPassword) {
        String strongPassword = null;
        int cutLength = oriPassword.length() / 3;
        int startIndex = 0 + cutLength;
        strongPassword = oriPassword.substring(startIndex, oriPassword.length());
        //System.out.println("theStrongPassword " + strongPassword);
        return strongPassword;
    }

    protected String CutTail(String oriPassword) {
        String strongPassword = null;
        int cutLength = oriPassword.length() / 3;
        int endIndex = oriPassword.length() - cutLength;
        strongPassword = oriPassword.substring(0, endIndex);
        return strongPassword;
    }

    public boolean checkPassword(String id, String password) {
        int method = Method.NoHeadAndNoTail.getCode();
        return this.checkPassword(id, password, method);
    }

    public boolean checkPassword(String id, String password, int method) {
        String rightPassword = this.createStrongPassword(id, method);
        if (password != null && password.equals(rightPassword)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPassword(String id, String password, String strMethod) {
        if (strMethod != null && !"".equals(strMethod)) {
            Integer method = Integer.parseInt(strMethod);
            return this.checkPassword(id, password, method);
        } else {
            return false;
        }
    }

}
