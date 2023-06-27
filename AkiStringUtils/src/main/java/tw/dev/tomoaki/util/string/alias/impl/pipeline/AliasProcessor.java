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

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import tw.dev.tomoaki.util.string.alias.Pipeline;
import tw.dev.tomoaki.util.string.alias.PipelineNode;
import tw.dev.tomoaki.util.string.alias.entity.NodeResult;

/**
 *
 * @author tomoaki
 */
public class AliasProcessor implements Pipeline {

    private Deque<NodeResult> resultStack;
    private Queue<PipelineNode> nodeList;
    private String document;

    protected void doSetupContainer() {
        Stack test = new Stack();
        this.resultStack = new LinkedList();
        this.nodeList = new LinkedList();
    }

    public static class Factory {

        public static AliasProcessor create(PipelineNode... nodes) {
            AliasProcessor processor = new AliasProcessor();
            processor.doSetupContainer();
            processor.nodeList.addAll(Arrays.asList(nodes));
            return processor;
        }
    }

    public String doProcess(String inputDocument) {
        document = inputDocument;
        nodeList.forEach(node -> {
            NodeResult nodeResult = node.doProcess(document);
            this.resultStack.add(nodeResult);
            document = nodeResult.getOutputDocument(); //document 如果不使用整個 Class 的 Global變數，會卡在 lamda 和 local variable
        });
//        return this.getOutputDocument();
        return document;
    }

    public String getInputDocument() {
        NodeResult firstResult = resultStack.getFirst();
        return firstResult.getInputDocument();
    }

    public String getOutputDocument() {
        NodeResult lastResult = resultStack.getLast();
        return lastResult.getOutputDocument();
    }

}
