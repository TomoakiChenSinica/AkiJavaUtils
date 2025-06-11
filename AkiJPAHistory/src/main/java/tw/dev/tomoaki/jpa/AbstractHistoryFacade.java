package tw.dev.tomoaki.jpa;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Arrays;
import java.util.List;
import tw.dev.tomoaki.jpa.entity.HistoryEntity;
import tw.dev.tomoaki.util.commondatavalidator.ListValidator;


/**
 *
 * @author tomoaki
 * 
 * 原本為僅限於此專案的 AcadResMgmtHistoryFacade，改名 AbstractHistoryFacade
 */
public abstract class AbstractHistoryFacade<HISTORY_DATA extends HistoryEntity, DATA> extends AbstractCriteriaFacade<HISTORY_DATA> { // extends AbstractQueryFacade<HISTORY_DATA> {

    public AbstractHistoryFacade(Class<HISTORY_DATA> entityClass) {
        super(entityClass);
    }

    protected abstract String getDataIdPropName();
    
    protected abstract Object obtainDataIdPropValue(DATA data);

    public List<HISTORY_DATA> findByPaper(DATA data) {
        return this.findByEquals(getDataIdPropName(), obtainDataIdPropValue(data));
    }
    

    public HISTORY_DATA getCreatedByData(DATA data) {
        // List<String> propNameList = Arrays.asList(getDataIdPropName(), "operationStatus");
        List<String> propNameList = Arrays.asList(getDataIdPropName(), "operationStatusCode");
        List<Object> propValueList = Arrays.asList(obtainDataIdPropValue(data), "INSERT");
        List<HISTORY_DATA> historyDataList = this.findByAndEquals(propNameList, propValueList);
        if(ListValidator.isListExist(historyDataList)) {
            return historyDataList.get(0);
        }
        return null;
    }

    
}
