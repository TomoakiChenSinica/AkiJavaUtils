/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.test1.repoty;

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
    private String TOKEN_PLAYER_FIRST_NAME = "${Player.FirstName}";
            
    @ArticleToken(summary="球員姓")
    private String TOKEN_PLAYER_LAST_NAME = "${Player.LastName}";
    

    @ArticleToken(summary="球員年型")
    private String TOKEN_PLAYER_AGE = "${Player.Age}";    
    
    @Override
    public void addRule(ArticleCreator creator, Player player) {
        creator.addTokenReplaceRule(TOKEN_PLAYER_FIRST_NAME, player.getFirstName());
        creator.addTokenReplaceRule(TOKEN_PLAYER_LAST_NAME, player.getLastName());
        creator.addTokenReplaceRule(TOKEN_PLAYER_AGE, player.getAge());
    }
    
}
