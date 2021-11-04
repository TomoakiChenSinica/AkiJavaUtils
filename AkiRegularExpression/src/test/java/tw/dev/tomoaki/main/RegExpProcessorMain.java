/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.main;
import tw.dev.tomoaki.util.regularexpression.RegExpProcessor;
import tw.dev.tomoaki.util.regularexpression.RegExpResult;
/**
 *
 * @author arche
 */
public class RegExpProcessorMain {

    public static void main(String[] args) {
        String pattern = "[0-9]";
        RegExpProcessor processor = RegExpProcessor.Factory.create(pattern);
        RegExpResult result = processor.processMatch("ABC");
        System.out.println(result.isFind());
        System.out.println(result.getGroupResults());        
    }
}
