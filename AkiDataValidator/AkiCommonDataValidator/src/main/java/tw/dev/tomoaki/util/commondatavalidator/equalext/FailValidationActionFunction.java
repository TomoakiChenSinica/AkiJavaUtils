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

import java.lang.FunctionalInterface;
import java.util.function.Consumer;

/**
 *
 * @author tomoaki
 */
//@FunctionalInterface
//public interface FailValidationActionFunction<T> {
//    
//    public void onValidateFail(T data);
//}
@FunctionalInterface
public interface FailValidationActionFunction<T> extends Consumer<T> {

//    public void onValidateFail(T data);
    @Override
    default void accept(final T elem) {
        try {
            acceptThrows(elem);
        } catch (final Exception e) {
            // Implement your own exception handling logic here..
            // For example:
            System.out.println("handling an exception...");
            // Or ...
            throw new RuntimeException(e);
        }
    }
    
    void acceptThrows(T elem) throws Exception;    

}
