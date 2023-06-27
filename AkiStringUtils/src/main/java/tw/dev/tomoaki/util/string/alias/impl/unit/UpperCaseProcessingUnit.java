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
package tw.dev.tomoaki.util.string.alias.impl.unit;

import tw.dev.tomoaki.util.string.alias.PipelineNode;
import tw.dev.tomoaki.util.string.alias.entity.NodeResult;

/**
 *
 * @author tomoaki
 */
public class UpperCaseProcessingUnit implements PipelineNode {

    public static final String code = "UpperCase";
    
    @Override
    public NodeResult doProcess(String document) {
        return NodeResult.Factory.create(document, this, document.toUpperCase());
    }

    @Override
    public String getDescription() {
        return "Upper Case The Document";
    }
    
}
