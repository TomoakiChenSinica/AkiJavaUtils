/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jpa.query.nativesql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import tw.dev.tomoaki.util.commondatavalidator.StringValidator;

/**
 *
 * @author tomoaki
 */
public class PreparedNativeQueryFactory<T> {

    private EntityManager em;

    private String nativeSql;
    private Class<T> entityClazz;
    private Boolean autoFixList = true;

    private Query persistanceNativeQuery;
    private Integer index;

    protected PreparedNativeQueryFactory() {
    }

    public static class Factory {

        public static PreparedNativeQueryFactory obtain(EntityManager entityManager, String nativeSql, Class entityClazz) {
            PreparedNativeQueryFactory nativeQueryFactory = new PreparedNativeQueryFactory();
            nativeQueryFactory.em = entityManager;
            nativeQueryFactory.init(entityClazz, nativeSql);
            nativeQueryFactory.doSetupPersistanceQuery();
            return nativeQueryFactory;
        }

        public static PreparedNativeQueryFactory obtain(EntityManager entityManager) {
            PreparedNativeQueryFactory nativeQueryFactory = new PreparedNativeQueryFactory();
            nativeQueryFactory.em = entityManager;
            return nativeQueryFactory;
        }
    }

    protected void doSetupPersistanceQuery() {
        this.persistanceNativeQuery = this.em.createNativeQuery(nativeSql, entityClazz);
    }

    protected void doInitIndex() {
        this.index = 1;
    }

    protected void doCleanUp() {
        this.nativeSql = null;
        this.entityClazz = null;
//        this.persistanceNativeQuery = null;
        this.index = null;
    }

    public PreparedNativeQueryFactory init(Class entityClazz, String nativeSql) {
        if (this.isFactoryReady()) {
            String msgFmt = "PreparedNativeQueryFactory Has Already Init, Please Run produce";
            throw new IllegalArgumentException(String.format(msgFmt));
        }

        this.entityClazz = entityClazz;
        this.nativeSql = nativeSql;
        this.doInitIndex();
        this.doSetupPersistanceQuery();
        return this;
    }
    
    public PreparedNativeQueryFactory turnOnAutoFixList() {
        this.autoFixList = true;
        return this;
    }
    
    public PreparedNativeQueryFactory turnOffAutoFixList() {
        this.autoFixList = false;
        return this;
    }    

    public PreparedNativeQueryFactory setParam(String str) {
        this.doValidateCanSetParam();
        this.persistanceNativeQuery = this.persistanceNativeQuery.setParameter(index++, str);
        return this;

    }

    public PreparedNativeQueryFactory setParam(Long num) {
        this.doValidateCanSetParam();
        this.persistanceNativeQuery = this.persistanceNativeQuery.setParameter(index++, num);
        return this;
    }

    public PreparedNativeQueryFactory setParam(Object obj) {
        this.doValidateCanSetParam();
        this.persistanceNativeQuery = this.persistanceNativeQuery.setParameter(index++, obj);
        return this;
    }

    public PreparedNativeQueryFactory setParam(Date utilDate) {
        this.doValidateCanSetParam();
        this.persistanceNativeQuery = PreparedNativeQueryParamHelper.setParam(persistanceNativeQuery, index++, utilDate);
        return this;
    }

    public PreparedNativeQueryFactory setParam(List<?> dataList) {
        this.doValidateCanSetParam();
        try {
            if(this.autoFixList) dataList = (dataList == null) ? new ArrayList() : dataList;
            this.persistanceNativeQuery = PreparedNativeQueryParamHelper.setParam(persistanceNativeQuery, index++, em, dataList);
            return this;
        } catch (Exception ex) {
            throw new PreparedNativeQueryException(ex);
        }
    }
    
    public PreparedNativeQueryFactory setParam(Object[] dataArr) {
        this.doValidateCanSetParam();
        try {
            if(this.autoFixList) dataArr = (dataArr == null) ? Arrays.asList().toArray() : dataArr;
            this.persistanceNativeQuery = PreparedNativeQueryParamHelper.setParam(persistanceNativeQuery, index++, em, dataArr);
            return this;
        } catch (Exception ex) {
            throw new PreparedNativeQueryException(ex);
        }        
    }

    public Query produce() {
        doCleanUp();
        return this.persistanceNativeQuery;
    }
    
//    public List

    
//<editor-fold defaultstate="collapsed" desc="輔助 Methods">
    protected Boolean isFactoryReady() {
        return StringValidator.isValueExist(nativeSql) && entityClazz != null;
    }

    protected void doValidateCanSetParam() {
        if (!isFactoryReady()) {
            String msgFmt = "PreparedNativeQueryFactory Is Not Init Yet, Please Run init(Class, String) First";
            throw new IllegalArgumentException(String.format(msgFmt));
        }
    }
    
//</editor-fold>    
}
