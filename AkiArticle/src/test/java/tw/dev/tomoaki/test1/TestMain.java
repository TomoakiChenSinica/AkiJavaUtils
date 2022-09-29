/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.test1;

import java.util.Arrays;
import java.util.List;
import tw.dev.tomoaki.article.helper.ArticleHelper;
import tw.dev.tomoaki.test1.entity.Player;
import tw.dev.tomoaki.test1.report.PlayerReportCreator;
import tw.dev.tomoaki.util.regularexpression.RegExpProcessor;

/**
 *
 * @author arche
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        test1();
        test0();
    }
    
    private static void test0() {
        RegExpProcessor regExpProcessor = RegExpProcessor.Factory.create(ArticleHelper.REGEXP_WORD_CAPTURE_ITERATOR);
        regExpProcessor.processMatch("<div> \r\n"
                + "#{<div>${Participant.Name}</div> <div>${Participant.AffiDepartName}</div>}[]"
                + "</div>").getCaptureResults().forEach(result -> System.out.println(result));
    }

    private static void test1() {
        try {
            Player player = Player.obtain("Dustin", "Ackley", 34);
            String article = "${Common.NowYear} ${Player.LastName}, ${Player.FirstName} who is ${Player.Age} olds";
            PlayerReportCreator creator = new PlayerReportCreator();
            
//            creator.getTokenOptionList().forEach(tokenOption -> System.out.println(tokenOption));            
            
            creator = creator.setupPlayerInfo(player);
            
            System.out.println(creator.getTokenMap());
            System.out.println(creator.getArticle(article));
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static List<Player> obtainTestingList() {
        List<Player> playerList = Arrays.asList(
                Player.obtain("Dustin", "Ackley", 34),
                Player.obtain("Justom", "Smoak", 35)
        );
        return playerList;
    }
    
    private static void test2() {
    }
}
