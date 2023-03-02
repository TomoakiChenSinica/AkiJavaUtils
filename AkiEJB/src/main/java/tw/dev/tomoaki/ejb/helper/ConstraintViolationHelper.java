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
package tw.dev.tomoaki.ejb.helper;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import tw.dev.tomoaki.ejb.exception.PlainTextConstraintViolationException;

/**
 *
 * @author tomoaki
 */
public class ConstraintViolationHelper {

    private static final String MSG_FORMAT_VAIOLATION = "From Root Class[%s], Constraint Violation Occur For Entity[%s], Property[%s] %s, Detail Is %s";
//    public static void handleException(ConstraintViolationException ex) {
//        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
//        violations.stream().forEach(violation -> {
//            System.out.format("root= %s, leaf= %s, propertyPath= %s, message= %s \n",
//                    violation.getRootBean().getClass().getSimpleName(), violation.getLeafBean().getClass().getSimpleName(), violation.getPropertyPath().toString(),  violation.getMessage());
//        });
//    }

    public static void handleException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        List<String> violationMsgList = violations.stream().map(violation -> convert2Msg(violation)).collect(Collectors.toList());
        throw PlainTextConstraintViolationException.Factory.create(violationMsgList);
    }

    public static String convert2Msg(ConstraintViolation<?> violation) {
        String rootClassName = violation.getRootBeanClass().getSimpleName();
        String leftClassName = violation.getLeafBean().getClass().getSimpleName();
        String propertyName = violation.getPropertyPath().toString();
        String violationMsg = violation.getMessage();
        String detail = Arrays.asList(violation.getExecutableParameters()).toString();
        String msg = String.format(MSG_FORMAT_VAIOLATION, rootClassName, leftClassName, propertyName, violationMsg, detail);
        return msg;
    }
}
