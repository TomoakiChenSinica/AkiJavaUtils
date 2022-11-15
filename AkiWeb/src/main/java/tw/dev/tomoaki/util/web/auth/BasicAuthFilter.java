package tw.dev.tomoaki.util.web.auth;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Tomoaki Chen
 */
public abstract class BasicAuthFilter<T extends BasicSessionContext> implements Filter {

   
    private Boolean printLog = false;
    private FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (printLog) {
                log("AuthFilter:Initializing filter");
            }
        }
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            if (printLog) {
                log("AuthFilter:doFilter()");
            }

            doBeforeProcessing(request, response);

            Throwable problem = null;
            try {
                chain.doFilter(request, response);
            } catch (Throwable t) {
                // If an exception is thrown somewhere down the filter chain,
                // we still want to execute our after processing, and then
                // rethrow the problem after that.
                problem = t;
                t.printStackTrace();
            }

            doAfterProcessing(request, response);

            // If there was a problem, we want to rethrow it if it is
            // a known type, otherwise log it.
            if (problem != null) {
                if (problem instanceof ServletException) {
                    throw (ServletException) problem;
                }
                if (problem instanceof IOException) {
                    throw (IOException) problem;
                }
                sendProcessingError(problem, response);
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

//    private void doBeforeProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException, InstantiationException, IllegalAccessException {
//        if (debug) {
//            log("AuthFilter:DoBeforeProcessing");
//        }
//
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse resp = (HttpServletResponse) response;
//        HttpSession session = req.getSession();
//
//        if (this.isAuthenticated(req, resp, session)) {
//            if (req.getRequestURI().contains(this.getSessionAttrOriUrl())) {
//                resp.sendRedirect(this.getDefaultPageUrl());
//            }
//
//            String oriUrl = this.findOriUrl(req, resp, session);
//            if (oriUrl != null) {
//                session.removeAttribute(this.getSessionAttrOriUrl());
//                if (oriUrl.contains(this.getLoginPageUrl()) && oriUrl.lastIndexOf("/") == (oriUrl.length() - 1)) {
//                    resp.sendRedirect(this.getDefaultPageUrl());
//                } else {
//                    resp.sendRedirect(oriUrl);
//                }
//            } else {
////                resp.sendRedirect("/CardLogManagement/pages/cardLog/cardLogList.jsf");
//            }
//
//        } else {
//            this.redirect2LoginPage(req, resp, session);
//        }
//    }
    private void doBeforeProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException, InstantiationException, IllegalAccessException {
        if (printLog) {
            log("AuthFilter:DoBeforeProcessing");
        }

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        if (this.isAuthenticated(req, resp, session)) {
//            if (req.getRequestURI().contains(this.getSessionAttrOriUrl())) {
//                resp.sendRedirect(this.getDefaultPageUrl());
//            }
//
//            String oriUrl = this.findOriUrl(req, resp, session);
//            if (oriUrl != null) {
//                session.removeAttribute(this.getSessionAttrOriUrl());
//                if (oriUrl.contains(this.getLoginPageUrl()) && oriUrl.lastIndexOf("/") == (oriUrl.length() - 1)) {
//                    resp.sendRedirect(this.getDefaultPageUrl());
//                } else {
//                    resp.sendRedirect(oriUrl);
//                }
//            } else {
////                resp.sendRedirect("/CardLogManagement/pages/cardLog/cardLogList.jsf");
//            }

        } else {
            this.redirect2LoginPage(req, resp, session);
        }
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        if (printLog) {
            log("AuthFilter:DoAfterProcessing");
        }
    }

    protected void redirect2LoginPage(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws IOException, ServletException {
        this.saveOriUrl(req, resp, session);
        if(printLog) System.out.format("[%s]redirect2LoginPage(HttpServletRequest, HttpServletResponse, HttpSession): loginPageUrl= %s", this.getClass().getSimpleName(), this.getLoginPageUrl());
        resp.sendRedirect(this.getLoginPageUrl());
    }

    private void saveOriUrl(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws ServletException {
        try {
//        String oriUrl = req.getRequestURL().toString();
//        Map<String, String[]> paramMap = req.getParameterMap();
//        String oriUrlWithQueryParam = this.obtainUrlWithQueryParam(oriUrl, paramMap);
////        System.out.println("oriUrl = " + oriUrl + ", oriUrlWithQueryParam = " + oriUrlWithQueryParam);
//        session.setAttribute(this.getSessionAttrOriUrl(), oriUrlWithQueryParam);
            T sessionContext = T.obtainSessionContext(session, this.getSessionContextClazz());
//            sessionContext.setLastRequestUrl(oriUrl);
            sessionContext.init(req);
            session.setAttribute(T.sessionAttrKey, sessionContext);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private String obtainUrlWithQueryParam(String url, Map<String, String[]> queryParamMap) {
        List<String> strParamPairList = null;
        if (queryParamMap != null && queryParamMap.isEmpty() == false) {
            strParamPairList = new ArrayList();
            for (Entry<String, String[]> entry : queryParamMap.entrySet()) {
                String key = entry.getKey();
                String[] values = entry.getValue();
                for (String value : values) {
                    String strParamPair = key + "=" + value;
                    strParamPairList.add(strParamPair);
                }
            }
        }
        if (strParamPairList != null && strParamPairList.isEmpty() == false) {
            String queryParamPart = "?";
            Integer listSize = strParamPairList.size();
            for (Integer count = 1; count <= listSize; count++) {
                Integer index = count - 1;
                String strParamPair = strParamPairList.get(index);
                if (count >= 2) {
                    queryParamPart += "&";
                }
                queryParamPart += strParamPair;
            }
            url += queryParamPart;
        }
        return url;
    }

    private String findOriUrl(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
        Object objOriUrl = session.getAttribute(this.getSessionAttrOriUrl());
        return objOriUrl == null ? null : (String) objOriUrl;
    }

    protected Boolean isAuthenticated(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws InstantiationException, IllegalAccessException {
        T sessionContext = T.obtainSessionContext(session, this.getSessionContextClazz());
        if(printLog) System.out.format("[%s]isAuthenticated(): sessionContext= %s", this.getClass().getSimpleName(), sessionContext);
        if(printLog && sessionContext != null) System.out.format("[%s]isAuthenticated(): sessionContext.getIsAuthorized= %s", this.getClass().getSimpleName(), sessionContext.getIsAuthorized());
        return sessionContext != null && sessionContext.getIsAuthorized();
    }

    protected abstract Class<T> getSessionContextClazz();

    protected abstract String getSessionAttrOriUrl();

    protected abstract String getLoginPageUrl();

    protected abstract String getDefaultPageUrl();
    
//    protected abstract Boolean getPrintLog();

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }
}
