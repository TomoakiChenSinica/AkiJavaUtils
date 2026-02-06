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
package tw.dev.tomoaki.lab;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 *
 * @author tomoaki
 */
public class MockHttpServletRequestTestMain {
    
    public static void main(String[] args) {
        testCreate();
    }
    
    private static void testCreate() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setScheme("https");
        request.setServerName("as.iis.sinica.edu.tw");
        request.setServerPort(443);
        request.setContextPath("SummerInterns-2.0");
        request.setRequestURI("/Admin/InternListPage.jsf");
        // System.out.println(request.getRequestURI());
    }
}
