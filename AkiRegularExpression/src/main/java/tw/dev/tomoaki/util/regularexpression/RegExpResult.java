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
//    private List<String> groupResults;
    private List<String> matchResults;
//    private List<String> captureResults;
    private RegExpCaptureMap captureResultMap;

    protected RegExpResult() {
        matchResults = new ArrayList();
        captureResultMap = new RegExpCaptureMap();
    }

    public static class Factory {

        public static RegExpResult create(Matcher matcher) {
            // formatPattern
            RegExpResult result = new RegExpResult();
            while (matcher.find()) {
                result.matchResults.add(matcher.group(0));
                Integer groupCount = matcher.groupCount();
                if (groupCount >= 1) {
                    for (Integer order = 1; order <= groupCount; order++) {
                        String captureResult =  matcher.group(order);
                        result.captureResultMap.put(order,captureResult);
                    }
                }
            }
            result.find = !result.matchResults.isEmpty();
            return result;
        }
    }

    public Boolean isFind() {
        return find;
    }

    public List<String> getMatchResults() {
        return matchResults;
    }

    public List<String> getCaptureResults() {
        return captureResultMap.getResultList(1);
    }
    
    public List<String> getCaptureResults(Integer order) {
        return captureResultMap.getResultList(order);
    }

}
