/*
 * Copyright 2024 tomoaki.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tw.dev.tomoaki.jpa.helper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.sessions.UnitOfWork;
import tw.dev.tomoaki.util.string.AkiStringUtil;

/**
 *
 * @author tomoaki
 */
public class JPAEntityHelper {

    public static Boolean isDataUpdate(EntityManager entityManager, Object entity) {
        UnitOfWork unitOfWork = ((JpaEntityManager) entityManager.getDelegate()).getUnitOfWork();
        return unitOfWork.isObjectRegistered(entity);
    }

//    protected static Boolean tryExecuteWithEclipselink(EntityManager entityManager, Object entity) {
//        try {
//            Class.forName("org.eclipse.persistence.jpa.PersistenceProvider");
//            UnitOfWork unitOfWork = ((JpaEntityManager) entityManager.getDelegate()).getUnitOfWork();
//            return unitOfWork.isObjectRegistered(entity);
//        } catch(ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }    
//    }
    public static Field obtainIdField(Object entity) {
        Class entityClass = entity.getClass();
        Field[] fields = entityClass.getDeclaredFields();
        Field idField = Stream.of(fields)
                .filter(field -> field.getAnnotation(javax.persistence.Id.class) != null)
                .findAny()
                .orElse(null);
        return idField;
    }

    public static Method guessIdGetter(Object entity, Field idField) throws NoSuchMethodException {
        if (idField == null) {
            throw new IllegalArgumentException("idField Is Not Existed");
        }

        Class entityClass = entity.getClass();
        String methodName = "get" + AkiStringUtil.capitalizeHeader(idField.getName(), 1);
        Method method = entityClass.getDeclaredMethod(methodName);
        return method;
    }

    public static Object obtainIdValue(Object entity) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Field idField = obtainIdField(entity);
        Method idGetter = guessIdGetter(entity, idField);
        return idGetter.invoke(entity);
    }
}
