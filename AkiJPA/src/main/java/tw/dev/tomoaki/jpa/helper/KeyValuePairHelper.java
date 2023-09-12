/*
 * Copyright 2023 tomoaki.
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import tw.dev.tomoaki.jpa.entity.KeyValuePair;
import tw.dev.tomoaki.util.commondatavalidator.ListValidator;

/**
 *
 * @author tomoaki
 */
public class KeyValuePairHelper {

    public static List<KeyValuePair> obtainKeyValuePairList(List<String> entityPropNameList, List<Object> valueList) {
        Integer legnth4ColValueList = validatePropList(entityPropNameList, valueList);
        List<KeyValuePair> pairList = new ArrayList();
        for (Integer index = 0; index < legnth4ColValueList; index++) {
            String propName = entityPropNameList.get(index);
            Object colValue = valueList.get(index);
            KeyValuePair pair = new KeyValuePair(propName, colValue);
            pairList.add(pair);
        }
        return pairList;
    }

    private static Integer validatePropList(List<String> entityPropNameList, List<Object> valueList) {
        if (!ListValidator.isListExist(entityPropNameList)) {
            throw new IllegalArgumentException("entityPropNameList= %s Is Null Or Empty");
        }

        if (!ListValidator.isListExist(valueList)) {
            throw new IllegalArgumentException("valueList= %s Is Null Or Empty");
        }

        Integer length4ColNameList = entityPropNameList.size();
        Integer legnth4ColValueList = valueList.size();
        if (!Objects.equals(length4ColNameList, legnth4ColValueList)) {
            throw new IllegalArgumentException("entityPropertyNameList= %s, valueList= %s, There Length Is Not Equal");
        }
        return length4ColNameList;
    }
}
