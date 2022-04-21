/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.regularexpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
        
        public static RegExpProcessor.Factory create() {
            throw new UnsupportedOperationException("Method Not Supported Yet");
        }
    }
    
//    public Boolean match(String input) {
//        Matcher matcher = thePattern.matcher(input);
//        return matcher.find();
//    }
    
//    public RegExpResult processMatch(String input) {
//        Matcher matcher = this.thePattern.matcher(input);
//        return RegExpResult.Factory.create(matcher.results().collect(Collectors.toList())); //不先轉乘List直接丟stream 會有exception
//    }
}
