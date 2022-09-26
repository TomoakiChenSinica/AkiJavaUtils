/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import tw.dev.tomoaki.util.regularexpression.helper.RegExpCommonPattern;
import tw.dev.tomoaki.util.regularexpression.RegExpProcessor;
import tw.dev.tomoaki.util.regularexpression.RegExpResult;

/**
 *
 * @author arche
 */
public class RegExpProcessorMain {

    public static void main(String[] args) {
        testCapture();
  }

    public static void testCapture() {
        String strPattern = "#\\{(.*)\\}\\[\\]";
        String strTest = "<div> \r\n"
                + "#{<div>${Person.Name}</div><div>${Person.Age}</div>}[] \r\n"
                + "#{<div>${Person.Name}</div>}[] \r\n"                
                + "</div>";
//        RegExpProcessor processor = RegExpProcessor.Factory.create(strPattern);
//        processor.processMatch(strTest).getGroupResults().forEach(result -> System.out.println(result));
        System.out.println(strPattern);
        System.out.println(strTest);
        Pattern pattern = Pattern.compile(strPattern);
        Matcher matcher = pattern.matcher(strTest);
        while(matcher.find()) {
            System.out.println("group0= " + matcher.group(0));
            System.out.println("group1= " + matcher.group(1));            
            //https://www.fooish.com/regex-regular-expression/groups-lookaround.html
            //capture
            // 可能要確定一下 RegExp 的 Group、Caputre、Match定義，我是蠻習慣說， Match 是指吻合， Capture 是指 () 內的 (不過在 https://regex101.com/ 我的 Capture叫group。而在js自己 是 match[1]
        }
    }
    
    public static void testNums() {
        String pattern = "([0-9]{2})";
        RegExpProcessor processor = RegExpProcessor.Factory.create(pattern);
        RegExpResult result = processor.processMatch("12345");
        System.out.println(result.isFind());
        System.out.println(result.getGroupResults());
    }
    
    public static void testReplace() {
        String article = "This is a book, https://stackoverflow.com";
        String pattern = RegExpCommonPattern.PURE_HTML_URL;
        RegExpProcessor processor = RegExpProcessor.Factory.create(pattern);
        RegExpResult result = processor.processMatch(article);
        System.out.println(result.isFind());
        System.out.println(result.getGroupResults());
        System.out.println(processor.processFormatReplace(article, "<a href=\"" + RegExpProcessor.formatReplaceToken + "\" target=\"_blank\">" + RegExpProcessor.formatReplaceToken + " </a>"));
      
    }
}
