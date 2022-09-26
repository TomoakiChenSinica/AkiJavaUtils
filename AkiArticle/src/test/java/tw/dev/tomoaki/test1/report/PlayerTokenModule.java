/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.test1.report;

import java.util.List;
import java.util.Map;
import java.util.Set;
import tw.dev.tomoaki.article.ArticleCreator;
import tw.dev.tomoaki.article.annotaion.ArticleToken;
import tw.dev.tomoaki.article.module.intf.ArticleEntityDataTokenRuleModule;
import tw.dev.tomoaki.test1.entity.Player;

/**
 *
 * @author arche
 */
public class PlayerTokenModule extends ArticleEntityDataTokenRuleModule<Player>{

    @ArticleToken(summary="球員名")
    public String TOKEN_PLAYER_FIRST_NAME = "${Player.FirstName}";
    @ArticleToken(summary="球員姓")
    public String TOKEN_PLAYER_LAST_NAME = "${Player.LastName}";
    @ArticleToken(summary="球員年型")
    public String TOKEN_PLAYER_AGE = "${Player.Age}";

    @ArticleToken(summary="球員清單", level=2)
    public String TOKEN_PLAYER_INFO_LIST = "${PlayerList,Standard}";
    
    @Override
    public void addRule(ArticleCreator creator, Player player) {
        creator.addTokenReplaceRule(TOKEN_PLAYER_FIRST_NAME, player.getFirstName());
        creator.addTokenReplaceRule(TOKEN_PLAYER_LAST_NAME, player.getLastName());
        creator.addTokenReplaceRule(TOKEN_PLAYER_AGE, player.getAge());
    }

    @Override
    public void addRule(ArticleCreator creator, List<Player> dataList) {

    }
    
    public String obtainStandardReport() {
        return "${Player.FisrtName} ${Player.LastName} is ${Player.Age} years old \n";
    }
    
}
