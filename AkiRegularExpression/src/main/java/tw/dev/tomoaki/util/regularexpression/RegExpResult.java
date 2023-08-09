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
    private List<String> matchResults;
    private List<RegExpMatchInfo> matchInfos;
    private RegExpCaptureMap captureResultMap;

    protected RegExpResult() {
        matchResults = new ArrayList();
        matchInfos = new ArrayList();
        captureResultMap = new RegExpCaptureMap();
    }

    public static class Factory {

        public static RegExpResult create(Matcher matcher) {
            // formatPattern
            RegExpResult result = new RegExpResult();
            while (matcher.find()) {
                String strMatch = matcher.group(0);
                result.matchResults.add(strMatch);

                RegExpMatchInfo matchInfo = new RegExpMatchInfo();
                matchInfo.setMatch(strMatch);

                Integer groupCount = matcher.groupCount();
                if (groupCount >= 1) { //後面的 Matcher.group 是屬於 Capture，一次Match可能會有多個 Capture
                    for (Integer order = 1; order <= groupCount; order++) {
                        String captureResult = matcher.group(order);
                        result.captureResultMap.add(order, captureResult);
                        matchInfo.addCapture(captureResult);
                    }
                }
                result.matchInfos.add(matchInfo);
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

    public List<RegExpMatchInfo> getMatchInfos() {
        return matchInfos;
    }

}
