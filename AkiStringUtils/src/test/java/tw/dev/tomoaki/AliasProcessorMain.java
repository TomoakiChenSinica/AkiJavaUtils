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
package tw.dev.tomoaki;

import tw.dev.tomoaki.util.string.alias.impl.exception.ProcessingUnitSelectorException;
import tw.dev.tomoaki.util.string.alias.impl.pipeline.AliasProcessor;
import tw.dev.tomoaki.util.string.alias.impl.unit.DistinctSpaceProcessingUnit;
import tw.dev.tomoaki.util.string.alias.impl.unit.TrimProcessingUnit;
import tw.dev.tomoaki.util.string.alias.impl.unit.UpperCaseProcessingUnit;

/**
 *
 * @author tomoaki
 */
public class AliasProcessorMain {

    public static void main(String[] args) throws ProcessingUnitSelectorException {
//        AliasProcessor processor = AliasProcessor.Factory.create(new TrimProcessingUnit(),
//                new DistinctSpaceProcessingUnit(),
//                new UpperCaseProcessingUnit()
//        );
        AliasProcessor processor = AliasProcessor.Factory.create("Trim", "DistinctSpace", "UpperCase");
        String result = processor.doProcess("   This  is      a book ");
        System.out.println(result);
    }
}
