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
package tw.dev.tomoaki.util.web.request;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import tw.dev.tomoaki.util.commondatavalidator.StringValidator;

/**
 *
 * @author tomoaki
 *
 * 雖然 GPT 說 X-Forwarded-For 等非標準，但似乎大部分還是會用此
 * https://docs.aws.amazon.com/zh_tw/elasticloadbalancing/latest/classic/x-forwarded-headers.html
 * https://zh.wikipedia.org/zh-tw/X-Forwarded-For
 * https://blog.csdn.net/weiyuefei/article/details/78687545
 */
public class ProxyRequestHelper {

    // private final static String[] DEFAULT_PROXY_HEADERS = {"X-Forward-For", "X-Forward-Proto", "X-Forward-Host"};
    private final static String[] COMMON_HEADERS_REMOTE_ADDRESS = {"X-Forwarded-For"};
    private final static String[] COMMON_HEADERS_HOST = {"X-Forwarded-Host"};
    private final static String[] COMMON_HEADERS_PORT = {"X-Forwarded-Port"};

    /**
     * 取得 使用者送出請求時，使用者自身的 Address(IP)， <br>
     * HttpServletRequest.getLocalPort。<br>
     * 此 Methods 會使用 X-Forwarded-For 等常見的 Header Name 當關鍵字
     *
     * @param request 使用者的 HTTP 請求，格式為 HttpServletRequest
     * @return 使用者送出請求時，使用者自身的 Address(IP)
     */    
    public static String obtainRemoteAddr(HttpServletRequest request) {
        return obtainRemoteAddr(request, Arrays.asList(COMMON_HEADERS_REMOTE_ADDRESS));
    }

    /**
     * 取得 使用者送出請求時，使用者自身的 Address(IP)
     *
     * @param request 使用者的 HTTP 請求，格式為 HttpServletRequest
     * @param headerName Request 的 Header Name，用來取得使用者送出請求時，使用者自身的 Address(IP)
     * @param otherHeaderNames Request 的 其他Header Names，用來取得使用者送出請求時，使用者自身的
     * Address(IP)
     * @return 使用者送出請求時，使用者自身的 Address(IP)
     */
    public static String obtainRemoteAddr(HttpServletRequest request, String headerName, String... otherHeaderNames) {
        List<String> headerNameList = Stream.concat(Stream.of(headerName), Stream.of(otherHeaderNames)).collect(Collectors.toList());
        return obtainRemoteAddr(request, headerNameList);
    }

    /**
     * 取得 使用者送出請求時，使用者自身的 Address(IP)
     *
     * @param request 使用者的 HTTP 請求，格式為 HttpServletRequest
     * @param headerNameList Request 的 Header Name 清單，用來取得使用者呼叫時的 Address (IP)
     * @return 使用者送出請求時，使用者自身的 Address(IP)
     */
    public static String obtainRemoteAddr(HttpServletRequest request, List<String> headerNameList) {
        String remoteAddress = headerNameList.stream() // Stream.of(headerNameList).filter(Objects::nonNull)
                .filter(StringValidator::isValueTrimExist)
                .map(request::getHeader)
                .filter(StringValidator::isValueTrimExist) // findFirst 要避免 Empty 或 Null
                .findFirst()
                .orElse(null);
        return (remoteAddress != null && !remoteAddress.trim().isEmpty()) ? remoteAddress : request.getRemoteAddr();
    }

    // ------------------------------------------------------------------------------------------
    
    /**
     * 取得 使用者發送請求時，使用的(Server端)Host Name， <br>
     * 此 Methods 會使用 X-Forwarded-Host 等常見的 Header Name 當關鍵字
     *
     * @param request 使用者的 HTTP 請求，格式為 HttpServletRequest
     * @return 使用者發送請求時，使用的(Server端)Host Name
     */
    public static String obtainRemoteHost(HttpServletRequest request) {
        return obtainRemoteHost(request, Arrays.asList(COMMON_HEADERS_HOST));
    }

    /**
     * 取得 使用者發送請求時，使用的(Server端)Host Name， <br>
     *
     * @param request 使用者的 HTTP 請求，格式為 HttpServletRequest
     * @param headerName Request 的 Header Name，用來取得使用者呼叫時的 Host Name
     * @param otherHeaderNames Request 的 其他Header Names，用來取得使用者呼叫時的 Host Name
     * @return 使用者發送請求時，使用的(Server端)Host Name
     */
    public static String obtainRemoteHost(HttpServletRequest request, String headerName, String... otherHeaderNames) {
        List<String> headerNameList = Stream.concat(Stream.of(headerName), Stream.of(otherHeaderNames)).collect(Collectors.toList());
        return obtainRemoteHost(request, headerNameList);
    }

    /**
     * 取得 使用者發送請求時，使用的(Server端)Host Name
     *
     * @param request 使用者的 HTTP 請求，格式為 HttpServletRequest
     * @param headerNameList Request 的 Header Name 清單，用來取得使用者呼叫時的 Nost Name
     * @return 使用者發送請求時，使用的(Server端)Host Name
     */
    public static String obtainRemoteHost(HttpServletRequest request, List<String> headerNameList) {
        String remoteAddress = headerNameList.stream()
                .filter(StringValidator::isValueTrimExist)
                .map(request::getHeader)
                .filter(StringValidator::isValueTrimExist) // findFirst 要避免 Empty 或 Null
                .findFirst()
                .orElse(null);
        return (remoteAddress != null && !remoteAddress.trim().isEmpty()) ? remoteAddress : request.getRemoteHost();
    }

    // ------------------------------------------------------------------------------------------
    
    /**
     * 取得 使用者發送請求時，使用的(Server端)Port， <br>
     * 原本取名 obtainPort 改名 obtainLocalPort 以對應 HttpServletRequest.getLocalPort。<br>
     * 此 Methods 會使用 X-Forwarded-Port 等常見的 Header Name 當關鍵字
     *
     * @param request 使用者的 HTTP 請求，格式為 HttpServletRequest
     * @return 使用者發送請求時，使用的(Server端)Port
     */
    public static Integer obtainLocalPort(HttpServletRequest request) {
        return obtainLocalPort(request, Arrays.asList(COMMON_HEADERS_PORT));
    }

    /**
     * 取得 使用者發送請求時，使用的(Server端)Port， <br>
     * 原本取名 obtainPort 改名 obtainLocalPort 以對應 HttpServletRequest.getLocalPort
     *
     * @param request 使用者的 HTTP 請求，格式為 HttpServletRequest
     * @param headerName Request 的 Header Name，用來取得使用者呼叫時的 Port
     * @param otherHeaderNames Request 的 其他Header Names，用來取得使用者呼叫時的 Port
     * @return 使用者發送請求時，使用的(Server端)Port
     */
    public static Integer obtainLocalePort(HttpServletRequest request, String headerName, String... otherHeaderNames) {
        List<String> headerNameList = Stream.concat(Stream.of(headerName), Stream.of(otherHeaderNames)).collect(Collectors.toList());
        return obtainLocalPort(request, headerNameList);
    }

    /**
     * 取得 使用者發送請求時，使用的(Server端)Port， <br>
     * 原本取名 obtainPort 改名 obtainLocalPort 以對應 HttpServletRequest.getLocalPort
     *
     * @param request 使用者的 HTTP 請求，格式為 HttpServletRequest
     * @param headerNameList Request 的 Header Name 清單，用來取得使用者呼叫時的 Port
     * @return 使用者發送請求時，使用的(Server端)Port
     */
    public static Integer obtainLocalPort(HttpServletRequest request, List<String> headerNameList) {
        String port = headerNameList.stream()
                .filter(StringValidator::isValueTrimExist)
                .map(request::getHeader)
                .filter(StringValidator::isValueTrimExist) // findFirst 要避免 Empty 或 Null
                .findFirst()
                .orElse(null);
        return (port != null && !port.trim().isEmpty()) ? Integer.valueOf(port) : request.getLocalPort();
    }
    
    // ------------------------------------------------------------------------------------------
    
    /**
     * 取得 使用者發送請求時，使用的(Server端)Port， <br>
     * 此 Methods 會使用 X-Forwarded-Port 等常見的 Header Name 當關鍵字
     *
     * @param request 使用者的 HTTP 請求，格式為 HttpServletRequest
     * @return 使用者發送請求時，使用的(Server端)Port
     */
    public static Integer obtainServerPort(HttpServletRequest request) {
        return obtainServerPort(request, Arrays.asList(COMMON_HEADERS_PORT));
    }

    /**
     * 取得 使用者發送請求時，使用的(Server端)Port， <br>
     * 原本取名 obtainPort 改名 obtainLocalPort 以對應 HttpServletRequest.getLocalPort
     *
     * @param request 使用者的 HTTP 請求，格式為 HttpServletRequest
     * @param headerName Request 的 Header Name，用來取得使用者呼叫時的 Port
     * @param otherHeaderNames Request 的 其他Header Names，用來取得使用者呼叫時的 Port
     * @return 使用者發送請求時，使用的(Server端)Port
     */
    public static Integer obtainServerPort(HttpServletRequest request, String headerName, String... otherHeaderNames) {
        List<String> headerNameList = Stream.concat(Stream.of(headerName), Stream.of(otherHeaderNames)).collect(Collectors.toList());
        return obtainServerPort(request, headerNameList);
    }

    /**
     * 取得 使用者發送請求時，使用的(Server端)Port， <br>
     * 原本取名 obtainPort 改名 obtainLocalPort 以對應 HttpServletRequest.getLocalPort
     *
     * @param request 使用者的 HTTP 請求，格式為 HttpServletRequest
     * @param headerNameList Request 的 Header Name 清單，用來取得使用者呼叫時的 Port
     * @return 使用者發送請求時，使用的(Server端)Port
     */
    public static Integer obtainServerPort(HttpServletRequest request, List<String> headerNameList) {
        String port = headerNameList.stream()
                .filter(StringValidator::isValueTrimExist)
                .map(request::getHeader)
                .filter(StringValidator::isValueTrimExist) // findFirst 要避免 Empty 或 Null
                .findFirst()
                .orElse(null);
        return (port != null && !port.trim().isEmpty()) ? Integer.valueOf(port) : request.getServerPort();
    }    
}
