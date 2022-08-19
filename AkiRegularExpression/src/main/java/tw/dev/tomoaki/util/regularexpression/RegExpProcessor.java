/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.regularexpression;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author arche
 *
 * https://www.w3schools.com/java/java_regex.asp
 */
public class RegExpProcessor {

    private Pattern thePattern;

    protected RegExpProcessor() {
    }

    public static class Factory {

        public static RegExpProcessor create(String regExpCondition) {
            RegExpProcessor regExp = new RegExpProcessor();
            regExp.thePattern = Pattern.compile(regExpCondition);
            return regExp;
        }
        
//        public static RegExpProcessor.Factory create() {
//            throw new UnsupportedOperationException("Method Not Supported Yet");
//        }
    }
    
    public Boolean match(String input) {
        Matcher matcher = thePattern.matcher(input);
        return matcher.find();
    }
    
    public RegExpResult processMatch(String input) {
        Matcher matcher = this.thePattern.matcher(input);
        return RegExpResult.Factory.create(matcher); 
    }
    
    
    /**
     * 
     * 將尋找到的字，用指定的 pattern (可被包含進去)更換調 input
     * 
     * @param input 要進行尋找並更換的文字
     * @param r
     */
    public String processFormatReplace(String input, String formatPattern) {
        Matcher matcher = this.thePattern.matcher(input);
        RegExpResult result = RegExpResult.Factory.create(matcher); 
        String replaceResult = input;
        if(result.isFind()) {
            List<String> groupResultList = result.getGroupResults();
            for(String groupResult : groupResultList) {
                String partResult = String.format(formatPattern, groupResult);
                replaceResult = replaceResult.replaceAll(thePattern.pattern(), partResult);
            }
        }
        return replaceResult;
    }
}
