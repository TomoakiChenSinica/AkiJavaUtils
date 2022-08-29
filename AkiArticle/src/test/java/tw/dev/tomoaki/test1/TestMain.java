/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.test1;

import java.util.Arrays;
import java.util.List;
import tw.dev.tomoaki.test1.entity.Player;
import tw.dev.tomoaki.test1.repoty.PlayerReportCreator;

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
    }

    private static void test1() {
        Player player = Player.obtain("Dustin", "Ackley", 34);
        String article = "${Player.LastName}, ${Player.FirstName} who is ${Player.Age} olds";
        PlayerReportCreator creator = new PlayerReportCreator();
        System.out.println(creator.getArticle(article));
    }

    private static List<Player> obtainTestingList() {
        List<Player> playerList = Arrays.asList(
                Player.obtain("Dustin", "Ackley", 34),
                Player.obtain("Justom", "Smoak", 35)
        );
        return playerList;
    }
}
