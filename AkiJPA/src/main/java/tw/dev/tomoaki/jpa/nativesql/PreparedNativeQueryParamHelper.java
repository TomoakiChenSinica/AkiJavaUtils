/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jpa.nativesql;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author tomoaki
 */
public class PreparedNativeQueryParamHelper {

//    public static Query setParam(Query query, Integer index, Date desigDate) {
//        if (desigDate == null) {
//            throw new IllegalArgumentException("desigDate Is Null");
//        }
//        query.setParameter(index, new Timestamp(desigDate.getTime()));
//        return query;
//    }
    public static Query setParam(Query query, Integer index, Date desigDate) {
        query.setParameter(index, (desigDate == null) ? null : new Timestamp(desigDate.getTime()));
        return query;
    }

    public static Query setParam(Query query, Integer index, EntityManager em, List<?> dataList) throws SQLException {
        query.setParameter(index, obtainTextSqlArray(em, dataList));
        return query;
    }

    public static Array obtainTextSqlArray(EntityManager em, List<?> dataList) throws SQLException {
        if (dataList == null) {
            throw new IllegalArgumentException("dataList Is Null");
        }
        return em.unwrap(Connection.class).createArrayOf("text", dataList.toArray());
    }
}
