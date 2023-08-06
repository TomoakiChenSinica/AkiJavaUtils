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
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import tw.dev.tomoaki.util.string.alias.Pipeline;
import tw.dev.tomoaki.util.string.alias.PipelineNode;
import tw.dev.tomoaki.util.string.alias.entity.NodeResult;
import tw.dev.tomoaki.util.string.alias.enumoption.ProcessingOption;
import tw.dev.tomoaki.util.string.alias.impl.exception.ProcessingUnitSelectorException;

/**
 *
 * @author tomoaki
 */
public class AliasStandardizer implements Pipeline {

    private Deque<NodeResult> resultStack;
    private Queue<PipelineNode> nodeList;
    private String document;

    protected void doSetupContainer() {
//        Stack test = new Stack();
        this.resultStack = new LinkedList();
        this.nodeList = new LinkedList();
    }

    public static class Factory {

        public static AliasStandardizer create() {
            return Factory.create(Arrays.asList());
        }

        public static AliasStandardizer create(String... unitCodes) {
            List<PipelineNode> nodeList = Stream.of(unitCodes).map(unitCode -> {
                try {
                    return ProcessingUnitSelector.get(unitCode);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return null;
                }
            }).collect(Collectors.toList());
            return Factory.create(nodeList);
        }

        public static AliasStandardizer create(PipelineNode... nodes) {
            return Factory.create(Arrays.asList(nodes));
        }

        public static AliasStandardizer create(List<PipelineNode> nodeList) {
            AliasStandardizer processor = new AliasStandardizer();
            processor.doSetupContainer();
            if (nodeList != null && !nodeList.isEmpty()) {
                processor.nodeList.addAll(nodeList);
            }
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
        String outputDocument = document;
        document = null;
        return outputDocument;
    }

    public String getInputDocument() {
        NodeResult firstResult = resultStack.getFirst();
        return firstResult.getInputDocument();
    }

    public String getOutputDocument() {
        NodeResult lastResult = resultStack.getLast();
        return lastResult.getOutputDocument();
    }

    public void add(PipelineNode node) {
        this.nodeList.add(node);
    }

    public void add(ProcessingOption option) throws ProcessingUnitSelectorException {
        PipelineNode node = ProcessingUnitSelector.get(option);
        this.nodeList.add(node);
    }

    public void add(String unitCode) throws ProcessingUnitSelectorException {
        PipelineNode node = ProcessingUnitSelector.get(unitCode);
        this.nodeList.add(node);
    }

    public void addAll(PipelineNode... nodes) {
        List<PipelineNode> appendedNodeList = Arrays.asList(nodes);
        this.nodeList.addAll(appendedNodeList);
    }

    public void addAll(ProcessingOption... options) {
        List<PipelineNode> appendedNodeList = Stream.of(options)
                .map(option -> {
                    try {
                        return ProcessingUnitSelector.get(option);
                    } catch(Exception ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(Collectors.toList());
        this.nodeList.addAll(appendedNodeList);
    }
    
    public void addAll(String... codes) {
        List<PipelineNode> appendedNodeList = Stream.of(codes)
                .map(code -> {
                    try {
                        return ProcessingUnitSelector.get(code);
                    } catch(Exception ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(Collectors.toList());
        this.nodeList.addAll(appendedNodeList);
    }    

}
