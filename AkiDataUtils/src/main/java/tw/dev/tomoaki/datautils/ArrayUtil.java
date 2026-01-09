/*
 * Copyright 2026 tomoaki.
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
package tw.dev.tomoaki.datautils;

import java.util.function.IntFunction;
import java.util.stream.Stream;

/**
 *
 * @author tomoaki
 */
public class ArrayUtil {
    
    
    /**
     * 參考: https://chatgpt.com/share/696077c7-b280-800d-bc23-2f2dacbd7a04 
     */
    public static <T> T[] concat(IntFunction<T[]> generator, T data, T... otherDatas) {
        return Stream.concat(Stream.of(data), Stream.of(otherDatas)).toArray(generator);
    }
    
    
}
