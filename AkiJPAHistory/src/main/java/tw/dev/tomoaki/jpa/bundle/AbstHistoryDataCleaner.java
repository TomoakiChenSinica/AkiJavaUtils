/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jpa.bundle;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.naming.NamingException;
import tw.dev.tomoaki.jpa.AbstFacadeProvider;
import tw.dev.tomoaki.jpa.AbstractCriteriaFacade;
import tw.dev.tomoaki.jpa.entity.HistoryEntity;
import tw.dev.tomoaki.util.datetime.DateTimeUtil;

/**
 *
 * @author tomoaki
 */
public abstract class AbstHistoryDataCleaner {
    
    private AbstFacadeProvider facadeProvider;
    
    public AbstHistoryDataCleaner() {
        this.facadeProvider = this.obtainFacadeProvider();
    }
    
    protected abstract AbstFacadeProvider obtainFacadeProvider();
    
    
    /**
     * 刪除今天之前幾天(含那天)的 History 資料 。 <br>
     * 
     * @param historyDataFacadeClazz 要刪除的資料格式其Facade類別(Class)
     * @param days 天數，假設是 1，則代表會刪除前一天(含)之前的資料，
     * @throws NamingException 找不到相關 Facade 時吐出
     * @return 刪除的筆數
     */
    public int removeBeforeToday(Class historyDataFacadeClazz, Integer days) throws NamingException {
        return this.removeBeforeOrEqual(historyDataFacadeClazz, DateTimeUtil.Provider.obtainToday().minusDays(days));
    }
    
    /**
     * 刪除某個時間點(精度為日)之前(不包含)的 History 類資料。 <br>
     * 
     * @param historyDataFacadeClazz 要刪除的資料格式其Facade類別(Class)
     * @param desigDate 指定的時間點，精度到日，即 yyyy-MM-dd 這種
     * @throws NamingException 找不到相關 Facade 時吐出
     * @return 刪除的筆數
     * 
     */    
    public int removeBeforeOrEqual(Class historyDataFacadeClazz, LocalDate desigDate) throws NamingException {
        return this.removeBeforeOrEqual(historyDataFacadeClazz, DateTimeUtil.Provider.obtainLastSecondOfDate(desigDate));
    }
    
    /**
     * 刪除某個時間點(精度為日)之前(包含)的 History 類資料。 <br>
     * 
     * @param historyDataFacadeClazz 要刪除的資料格式其Facade類別(Class)
     * @param desigDate 指定的時間點，精度到日，即 yyyy-MM-dd 這種
     * @throws NamingException 找不到相關 Facade 時吐出
     * @return 刪除的筆數
     * 
     */      
    public int removeBefore(Class historyDataFacadeClazz, LocalDate desigDate) throws NamingException {
        return this.removeBeforeOrEqual(historyDataFacadeClazz, DateTimeUtil.Provider.obtainLastSecondOfDate(desigDate));
    }    
    
    
    
    /**
     * 刪除某個時間點(精度為秒)之前(不包含)的 History 類資料。 <br>
     * 
     * @param historyDataFacadeClazz 要刪除的資料格式其Facade類別(Class)
     * @param desigDateTime 指定的時間點，精度到秒，即 yyyy-MM-dd HH:mm:ss 這種
     * @throws NamingException 找不到相關 Facade 時吐出
     * @return 刪除的筆數
     * 
     */
    public <T extends HistoryEntity> int removeBefore(Class historyDataFacadeClazz, LocalDateTime desigDateTime) throws NamingException {
        //      * @param <T> 繼承於 HistoryEntity 的資料格式
        AbstractCriteriaFacade facade = (AbstractCriteriaFacade) this.facadeProvider.getFacade(historyDataFacadeClazz);
        int removeNums = facade.removeByLessThan("operationDateTime", desigDateTime);
//        facade.commit();
        return removeNums;
    }
  
    /**
     * 刪除某個時間點(精度為秒)之前(包含)的 History 類資料。 <br>
     * 
     * @param historyDataFacadeClazz 要刪除的資料格式其Facade類別(Class)
     * @param desigDateTime 指定的時間點，精度到秒，即 yyyy-MM-dd HH:mm:ss 這種
     * @return 刪除的筆數
     * @throws NamingException 找不到相關 Facade 時吐出
     * 
     */  
    public int removeBeforeOrEqual(Class historyDataFacadeClazz, LocalDateTime desigDateTime) throws NamingException {
        AbstractCriteriaFacade facade = (AbstractCriteriaFacade) this.facadeProvider.getFacade(historyDataFacadeClazz);
        int removeNums = facade.removeByLessThanOrEqual("operationDateTime", desigDateTime);
//        facade.commit();
        return removeNums;
    }
    
}
