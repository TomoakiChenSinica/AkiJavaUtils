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
package tw.dev.tomoaki.util.string.alias.impl.pipeline;

import java.lang.reflect.InvocationTargetException;
import tw.dev.tomoaki.util.string.alias.PipelineNode;
import tw.dev.tomoaki.util.string.alias.enumoption.ProcessingOption;
import tw.dev.tomoaki.util.string.alias.impl.exception.ProcessingUnitSelectorException;

/**
 *
 * @author tomoaki
 */
public class ProcessingUnitSelector {

    public static PipelineNode get(String code) throws ProcessingUnitSelectorException {
        try {
            Class pipelineNodeClazz = ProcessingOption.codeOf(code).getNode();
            return (PipelineNode) pipelineNodeClazz.getConstructor().newInstance();
        } catch (Exception ex) {
            throw new ProcessingUnitSelectorException(ex);
        }
    }

    public static PipelineNode get(ProcessingOption option) throws ProcessingUnitSelectorException {
        try {
            Class pipelineNodeClazz = option.getNode();
            return (PipelineNode) pipelineNodeClazz.getConstructor().newInstance();
        } catch (Exception ex) {
            throw new ProcessingUnitSelectorException(ex);
        }
    }
}
