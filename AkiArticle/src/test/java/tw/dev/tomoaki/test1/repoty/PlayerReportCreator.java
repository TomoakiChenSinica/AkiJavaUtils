/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.test1.repoty;

import java.util.Arrays;
import tw.dev.tomoaki.article.ArticleCreator;

/**
 *
 * @author arche
 */
public class PlayerReportCreator extends ArticleCreator {

    @Override
    protected void doCustomRulesSetup() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.moduleList = Arrays.asList(new PlayerTokenModule());
    }
    
}
