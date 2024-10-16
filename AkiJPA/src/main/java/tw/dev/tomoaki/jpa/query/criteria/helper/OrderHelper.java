package tw.dev.tomoaki.jpa.query.criteria.helper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

/**
 *
 * @author tomoaki
 */
public class OrderHelper {

    public static List<Order> createAscOrderList(Root root, CriteriaBuilder cb, String... entityPropName) {
        return createAscOrderList(root, cb, Arrays.asList(entityPropName));
    }
   

    public static List<Order> createAscOrderList(Root root, CriteriaBuilder cb, List<String> entityPropNameList) {
        return entityPropNameList.stream()
                .map(propName -> cb.asc(root.get(propName)))
                .collect(Collectors.toList());
    }

    public static Order createAscOrder(Root root, CriteriaBuilder cb, String entityPropName) {
        return cb.asc(root.get(entityPropName));
    }
    
    // --------------------------------------------------------------------------------------------------------------

    public static List<Order> createDescOrderList(Root root, CriteriaBuilder cb, String... entityPropName) {
        return createDescOrderList(root, cb, Arrays.asList(entityPropName));
    }
   

    public static List<Order> createDescOrderList(Root root, CriteriaBuilder cb, List<String> entityPropNameList) {
        return entityPropNameList.stream()
                .map(propName -> cb.desc(root.get(propName)))
                .collect(Collectors.toList());
    }

    public static Order createDescOrder(Root root, CriteriaBuilder cb, String entityPropName) {
        return cb.desc(root.get(entityPropName));
    }
}
