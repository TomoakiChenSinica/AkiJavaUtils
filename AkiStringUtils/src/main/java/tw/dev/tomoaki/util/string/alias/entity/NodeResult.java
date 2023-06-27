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
package tw.dev.tomoaki.util.string.alias.entity;

import tw.dev.tomoaki.util.string.alias.PipelineNode;

/**
 *
 * @author tomoaki
 */
public class NodeResult {

    private String inputDocument;
    private PipelineNode node;
    private String outputDocument;

    protected NodeResult() {
    }

    public static class Factory {

        public static NodeResult create(String inputDocument, PipelineNode node, String outputDocument) {
            NodeResult result = new NodeResult();
            result.inputDocument = inputDocument;
            result.node = node;
            result.outputDocument = outputDocument;
            return result;
        }
    }

    public String getInputDocument() {
        return inputDocument;
    }

    public void setInputDocument(String inputDocument) {
        this.inputDocument = inputDocument;
    }

    public PipelineNode getNode() {
        return node;
    }

    public void setNode(PipelineNode node) {
        this.node = node;
    }

    public String getOutputDocument() {
        return outputDocument;
    }

    public void setOutputDocument(String outputDocument) {
        this.outputDocument = outputDocument;
    }
    
    

}
