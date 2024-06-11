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
package tw.dev.tomoaki.file.entity;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import tw.dev.tomoaki.util.regularexpression.impl.FileNameRegExpUtil;
import tw.dev.tomoaki.util.regularexpression.impl.result.FileNameRegExpResult;

/**
 *
 * @author tomoaki
 * @deprecated 設計起來怪怪的，先砍掉
 */
@Deprecated
public class FileNameDetail {

    private String prefix;
    private String suffix;

    protected FileNameDetail() {
    }

    public static class Factory {

        public static FileNameDetail create(String fileDisplayName) {
            FileNameRegExpResult fileNameResult = FileNameRegExpUtil.process(fileDisplayName);
            return Factory.create(fileNameResult.getPrefix(), fileNameResult.getSuffix());
        }

        public static FileNameDetail create(String prefix, String suffix) {
            if (LocalStringValidator.isNotExist(prefix) && LocalStringValidator.isNotExist(suffix)) {
                throw new IllegalArgumentException("File Prefix(Name) And Suffix(Name) Are All Null");
            }
            FileNameDetail nameDetail = new FileNameDetail();
            nameDetail.prefix = prefix;
            nameDetail.suffix = suffix;
            return nameDetail;
        }
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public String toDisplayName() {
        return Stream.of(prefix, suffix).filter(Objects::nonNull).collect(Collectors.joining("."));
    }

    @Override
    public String toString() {
        return toDisplayName();
    }

    /* inner class，如果寫成 "private class LocalStringValidator" 會報錯 */
    private static class LocalStringValidator {

        public static Boolean isExist(String str) {
            return (str != null) && !str.isEmpty();
        }

        public static Boolean isNotExist(String str) {
            return (str == null) || str.isEmpty();
        }

        public static Boolean isTrimExist(String str) {
            return (str != null) && !str.trim().isEmpty();
        }

        public static Boolean isTrimNotExist(String str) {
            return (str == null) || str.trim().isEmpty();
        }

    }

}
