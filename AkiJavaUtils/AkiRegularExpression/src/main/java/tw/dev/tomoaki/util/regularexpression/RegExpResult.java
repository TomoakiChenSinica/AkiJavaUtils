/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.regularexpression;

import java.util.ArrayList;
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
    }

    public static class Factory {
        public static RegExpResult create(Matcher matcher) {
            return create(matcher.results());
        }
        
        
        public static RegExpResult create(List<MatchResult> matchResultList) {
            RegExpResult result = new RegExpResult();
            result.find = matchResultList.size() > 0;
            if (result.find) {
                result.groupResults = new ArrayList();
                matchResultList.forEach(matchResult -> {
                    result.groupResults.add(matchResult.group());
                });
            }
            return result;
        }

        public static RegExpResult create(Stream<MatchResult> matchResultStream) {
            RegExpResult result = new RegExpResult();
            result.find = matchResultStream.count() > 0;
            if (result.find) {
                result.groupResults = new ArrayList();
                matchResultStream.forEach(matchResult -> {
                    result.groupResults.add(matchResult.group());
                });
            }
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
