package tw.dev.tomoaki.jpa;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tomoaki
 * https://blogs.oracle.com/MaheshKannan/entry/portable_global_jndi_names
 */
public class FacadeProvider {
    protected String applicationName; //如果Project 是在 ear 下的形式，才需要此項
    protected String ejbModuleName;
    protected String facadePackagePath;
    
    protected final String facadeSuffix = "Facade";
    
   public String generateFacadeName(String tableName){
        return tableName + this.facadeSuffix;
    } 
    
    public String generateFacadePath(String tableName){
        String facadeName = this.generateFacadeName(tableName);
        return facadePackagePath + "." + facadeName;
    }
    
    public Object getFacade(String tableName) throws NamingException{
        try {
            String facadeName = tableName + "Facade";
            String facadePath = facadePackagePath + "." + facadeName;
//            Class subClass = Class.forName(facadePath);
            InitialContext c = new InitialContext();
            applicationName = (String)c.lookup("java:app/AppName");
            Object theFacade = c.lookup("java:global/" + applicationName + "/" + ejbModuleName + "/" + facadeName + "!" + facadePath);//.getClass().asSubclass(subClass);            
   
            return theFacade;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    

   public Class getDesignatedFacadeClass(String tableName){
        Class theFacadeClass = null;        
        try {
            String facadeName = this.generateFacadeName(tableName);
            String facadePath = this.generateFacadePath(tableName);
            Class classType = Class.forName(facadePath);
            InitialContext c = new InitialContext();
            theFacadeClass = c.lookup("java:global/" + applicationName + "/" + ejbModuleName + "/" + facadeName + "!" + facadePath).getClass().asSubclass(classType);                                              
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return theFacadeClass;
    }        
}
