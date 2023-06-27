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
package tw.dev.tomoaki.util.string.alias.enumoption;

import tw.dev.tomoaki.util.string.alias.PipelineNode;
import tw.dev.tomoaki.util.string.alias.impl.unit.UpperCaseProcessingUnit;

/**
 *
 * @author tomoaki
 */
public enum ProcessingOption {
    
    
    UPPER_CASE(UpperCaseProcessingUnit.code, "", UpperCaseProcessingUnit.class);
    
    private String code;
    private String description;
    private Class<?> node;
    
    private ProcessingOption(String code, String description, Class<?> node) {
        this.code = code;
        this.description = description;
        this.node = node;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Class<?> getNode() {
        return node;
    }

    public void setNode(Class<?> node) {
        this.node = node;
    }
    
    
}
