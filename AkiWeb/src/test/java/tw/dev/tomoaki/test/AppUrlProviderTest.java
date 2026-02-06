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
package tw.dev.tomoaki.test;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import tw.dev.tomoaki.util.web.AppUrlProvider;

/**
 *
 * @author tomoaki
 */
@DisplayName("DataRelatedFileManager 測試")
public class AppUrlProviderTest {
    
    private MockHttpServletRequest request; /*= new MockHttpServletRequest(); {{
        request.setScheme("https");
        request.setServerName("as.iis.sinica.edu.tw");
        request.setServerPort(443);
        request.setContextPath("SummerInterns-2.0");
        request.setRequestURI("/Admin/InternListPage.jsf");}}*/
    
    @BeforeEach
    public void setup() {
        request = new MockHttpServletRequest();
        request.setScheme("https");
        request.setServerName("as.iis.sinica.edu.tw");
        request.setServerPort(443);
        request.setContextPath("SummerInterns-2.0");
        request.setRequestURI("/Admin/InternListPage.jsf");
    }
    
    
    @Test
    @DisplayName("測試最基礎的替換")    
    public void testAppUrl_Normal() {

        AppUrlProvider urlProvider = AppUrlProvider.create(request);
        String finalUrl = urlProvider.appendUrl("/Admin/PiResearch.jsf");
        
        assertThat(finalUrl).isEqualTo("https://as.iis.sinica.edu.tw/SummerInterns-2.0/Admin/PiResearch.jsf");
    }
    
    @Test
    @DisplayName("測試有重複 slash")    
    public void testAppUrl_Ugly() {

        AppUrlProvider urlProvider = AppUrlProvider.create(request);
        String finalUrl = urlProvider.appendUrl("////Admin/PiResearch.jsf");
        
        assertThat(finalUrl).isEqualTo("https://as.iis.sinica.edu.tw////SummerInterns-2.0/Admin/PiResearch.jsf");
    }
    
    @Test
    @DisplayName("測試有重複 slash, 有處理")    
    public void testAppUrl_UglyUrlWithAppender() {
        AppUrlProvider urlProvider = AppUrlProvider.create(request, true);
        String finalUrl = urlProvider.newUrlAppender()
                .append("////Admin")
                .append("/PiResearch.jsf")
                .buildUrl();
        
        assertThat(finalUrl).isEqualTo("https://as.iis.sinica.edu.tw/SummerInterns-2.0/Admin/PiResearch.jsf");
    }        
}
