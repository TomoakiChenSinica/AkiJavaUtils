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
package tw.dev.tomoaki.article.module.data;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author tomoaki
 */
public interface ArticleData {

    public String getHeaderContent();

    public String getMainContent();

    public String getFooterContent();

    default public String obtainBlockedText() {
        return Stream.of(getHeaderContent(), getMainContent(), getFooterContent()).filter(Objects::nonNull).collect(Collectors.joining("\r\n"));
    }

    default public String obtainInlineText() {
        return this.obtainInlineText("");
    }

    default public String obtainInlineText(String splitor) {
        return Stream.of(getHeaderContent(), getMainContent(), getFooterContent()).filter(Objects::nonNull).collect(Collectors.joining(splitor));
    }
}
