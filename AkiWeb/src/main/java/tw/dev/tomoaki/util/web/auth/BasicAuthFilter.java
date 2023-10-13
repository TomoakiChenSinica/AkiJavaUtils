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
public abstract class BasicAuthFilter<SESSION_CONTEXT extends BasicSessionContext, SESSION_CONTEXT_FACTORY extends SessionContextFactory<SESSION_CONTEXT>> implements Filter {

    private Boolean printLog = false;
    private FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (printLog) {
                System.out.format("[%s] Initializing filter", this.getClass().getSimpleName());
            }
        }
        try {
            this.doBeforeInit();
            this.doSetupGlobalVariable();
            this.doAfterInit();
        } catch(ServletException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    protected abstract void doBeforeInit() throws ServletException;

    protected abstract void doAfterInit() throws ServletException;

    protected void doSetupGlobalVariable() {
        this.printLog = this.getPrintLog();
    }

    @Override
    public void destroy() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            if (printLog) {
                System.out.format("[%s] doFilter()", this.getClass().getSimpleName());
            }

            doBeforeProcessing(request, response);

            Throwable problem = null;
            try {
                if(this.isAuthenticated(request, response) && this.isSystemPermitted(request, response) ) {
                    chain.doFilter(request, response);
                }
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


    private void doBeforeProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException, InstantiationException, IllegalAccessException {
        if (printLog) {
            System.out.format("[%s] doBeforeProcessing()", this.getClass().getSimpleName());
        }

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

       /*
        if(!this.isAuthenticated(req, resp, session)) {
            this.redirect2LoginPage(req, resp, session);
            return;
        } else if(!this.isSystemPermitted(req, resp, session)) {
            resp.sendRedirect(this.getSystemPermissionDeniedPageUrl());
            return;
        } else if (this.isAuthenticated(req, resp, session) && this.isSystemPermitted(req, resp, session)) {
            if(printLog) System.out.format("[%s] doBeforeProcessing(): isAuthenticated", this.getClass().getSimpleName());
        }
        */
       
        if (!this.isAuthenticated(req, resp, session)) {
            this.redirect2LoginPage(req, resp, session);
            return;
        } else if (!this.isSystemPermitted(req, resp, session)) {
            resp.sendRedirect(this.getSystemPermissionDeniedPageUrl());
            return;
        } else {
            if (printLog) System.out.format("[%s] doBeforeProcessing(): isAuthenticated", this.getClass().getSimpleName());           
        }
       
    }

    protected void doAfterProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        if (printLog) {
            System.out.format("[%s] doAfterProcessing()", this.getClass().getSimpleName());            
        }
    }

    protected void redirect2LoginPage(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws IOException, ServletException {
        this.saveOriUrl(req, resp, session);
        if (printLog) {
            System.out.format("[%s] redirect2LoginPage(HttpServletRequest, HttpServletResponse, HttpSession): loginPageUrl= %s", this.getClass().getSimpleName(), this.getLoginPageUrl());
        }
        resp.sendRedirect(this.getLoginPageUrl());
    }
  
    private void saveOriUrl(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws ServletException {
        try {
            SESSION_CONTEXT_FACTORY factory = this.obtainSessionContextFactory();
            SESSION_CONTEXT sessionContext = factory.obtainSessionContext(session, this.getSessionContextClazz(), Boolean.TRUE);
            sessionContext.init(req);
            if(this.printLog) System.out.format("[%s] saveOriUrl(): sessionContext= %s", this.getClass().getSimpleName(), sessionContext);            
//            if(this.printLog) System.out.format("[%s] saveOriUrl(): T.sessionAttrKey= %s", this.getClass().getSimpleName(), T.sessionAttrKey);
//            session.setAttribute(T.sessionAttrKey, sessionContext);
            factory.saveSessionContext(session, sessionContext);
//            if(this.printLog) System.out.format("[%s] saveOriUrl(): After Set Attribute To session= %s, attrKey= %s, attrValue= %s", this.getClass().getSimpleName(), session, T.sessionAttrKey, sessionContext);
//            if(this.printLog) System.out.format("[%s] saveOriUrl(): Try Get Attribute From session= %s, attrKey= %s, attrValue= %s", this.getClass().getSimpleName(), session, T.sessionAttrKey, session.getAttribute(T.sessionAttrKey));
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


    protected Boolean isAuthenticated(ServletRequest req, ServletResponse resp) throws InstantiationException, IllegalAccessException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        HttpSession session = request.getSession();
        return this.isAuthenticated(request, response, session);        
    }    
    
    protected Boolean isAuthenticated(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws InstantiationException, IllegalAccessException {
        if(printLog) System.out.format("[%s] isAuthenticated(): session= %s", this.getClass().getSimpleName(), session);
        SESSION_CONTEXT_FACTORY factory = this.obtainSessionContextFactory();
        SESSION_CONTEXT sessionContext = factory.obtainSessionContext(session, this.getSessionContextClazz(), Boolean.FALSE);
        if (printLog) {
            System.out.format("[%s] isAuthenticated(): sessionContext= %s", this.getClass().getSimpleName(), sessionContext);
        }
        if (printLog && sessionContext != null) {
            System.out.format("[%s] isAuthenticated(): sessionContext Is Existed, sessionContext.getIsAuthorized= %s", this.getClass().getSimpleName(), sessionContext.getIsAuthorized());
        }        
        return sessionContext != null && sessionContext.getIsAuthorized();
    }    
    
    protected Boolean isSystemPermitted(ServletRequest req, ServletResponse resp) throws InstantiationException, IllegalAccessException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        HttpSession session = request.getSession();
        return this.isSystemPermitted(request, response, session);        
    }        
    
    protected Boolean isSystemPermitted(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws InstantiationException, IllegalAccessException {
        SESSION_CONTEXT_FACTORY factory = this.obtainSessionContextFactory();
        SESSION_CONTEXT sessionContext = factory.obtainSessionContext(session, this.getSessionContextClazz(), Boolean.FALSE);
        return sessionContext != null && sessionContext.getIsSystemPermitted();
    }

    protected abstract Class<SESSION_CONTEXT> getSessionContextClazz();

    protected abstract String getLoginPageUrl();
    
    protected abstract String getSystemPermissionDeniedPageUrl();

    protected abstract String getDefaultPageUrl();

    protected abstract Boolean getPrintLog();

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
    
    protected abstract SESSION_CONTEXT_FACTORY obtainSessionContextFactory();
}
