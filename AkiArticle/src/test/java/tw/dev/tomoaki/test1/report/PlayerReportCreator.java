/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.test1.report;


import tw.dev.tomoaki.article.ArticleCreator;
import tw.dev.tomoaki.test1.entity.Player;

/**
 *
 * @author arche
 */
public class PlayerReportCreator extends ArticleCreator {

    private PlayerTokenModule playerTokenModule;
//    private Player singlePlayer;
//    private List<Player> playerList;//    private Player singlePlayer;
//    private List<Player> playerList;
    
    

    public PlayerReportCreator() {
        super();
        this.doCustomRulesSetup();
    }
    
    @Override
    protected void doCustomRulesSetup() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.playerTokenModule = new PlayerTokenModule();
        this.moduleList.add(playerTokenModule);
    }
    
    public PlayerReportCreator setupPlayerInfo(Player player) {       
        this.playerTokenModule.doSetupRule(player);
//        this.playerTokenModule.addRule(this, Arrays.asList(player));
        return this;
    }
    
}
