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
package tw.dev.tomoaki.util.commondatavalidator.equalext;

import java.util.Objects;
import java.util.stream.Stream;

/**
 *
 * @author tomoaki
 */
public class ContainsValidator {
    
//    public static final FailValidationActionFunction DEFAULT_FAIL_ACTION = (data) -> ;
//   
//    public static <T> void doValidate(FailValidationActionFunction failValidation, T standardData, T... comparedDatas) {
////        for(T data : comparedDatas) {
////            if(Objects.equals(standardData, data)) {
////            
////            }
////        }
//        T eqData = Stream.of(comparedDatas).filter(comparedData -> Objects.equals(standardData, comparedData)).findAny().orElse(null);
//        if(eqData == null) {
//            failValidation.onValidateFail(standardData);
//        }
//    }
}
