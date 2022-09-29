/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.test1.report;

import java.util.List;
import tw.dev.tomoaki.article.annotaion.ArticleToken;
import tw.dev.tomoaki.article.helper.ArticleHelper;
import tw.dev.tomoaki.article.module.intf.ArticleEntityDataTokenRuleModule;
import tw.dev.tomoaki.test1.entity.Player;
import tw.dev.tomoaki.util.regularexpression.RegExpProcessor;

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
//
//    @ArticleToken(summary="球員清單", level=2)
//    public String TOKEN_PLAYER_INFO_LIST = "${PlayerList,Standard}";
//    
    @Override
    public void doSetupRule(Player player) {
        this.doAddRule(TOKEN_PLAYER_FIRST_NAME, player.getFirstName());
        this.doAddRule(TOKEN_PLAYER_LAST_NAME, player.getLastName());
        this.doAddRule(TOKEN_PLAYER_AGE, player.getAge());
        this.moduleArticleTokenMap
    }

    @Override
    public void doSetupRule(List<Player> dataList) {
//        RegExpProcessor regExpProcessor = RegExpProcessor.Factory.create(ArticleHelper.REGEXP_WORD_CAPTURE_ITERATOR);
        
    }
    
}
