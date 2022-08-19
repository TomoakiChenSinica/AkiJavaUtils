/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.regularexpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.stream.Stream;

/**
 *
 * @author arche
 */
public class RegExpResult {

    private Boolean find;
    private List<String> groupResults;

    protected RegExpResult() {
        groupResults = new ArrayList();
    }

    public static class Factory {

        public static RegExpResult create(Matcher matcher) {
            // formatPattern
            RegExpResult result = new RegExpResult();
            while (matcher.find()) {
                result.groupResults.add(matcher.group());
            }
            result.find = !result.groupResults.isEmpty();
            return result;
        }
    }

    public Boolean isFind() {
        return find;
    }

    public List<String> getGroupResults() {
        return groupResults;
    }

}
