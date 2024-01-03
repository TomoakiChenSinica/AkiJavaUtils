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
package tw.dev.tomoaki.util.web.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tomoaki
 * @param <T> 此 Factory 要產生的 SessionContext 實作類別
 */
public abstract class SessionContextFactory<T extends BasicSessionContext> {

    protected abstract String obtainSessionAttrKey();

    public <T extends BasicSessionContext> T obtainSessionContext(HttpServletRequest request, Class<T> clazz, Boolean newInst4Null) throws InstantiationException, IllegalAccessException {       
        return this.obtainSessionContext(request.getSession(), clazz, newInst4Null);
    }

    public <T extends BasicSessionContext> T obtainSessionContext(HttpSession session, Class<T> clazz, Boolean newInst4Null) throws InstantiationException, IllegalAccessException {
        Object objSessionContext = session.getAttribute(this.obtainSessionAttrKey());
//        return (objSessionContext == null) ? newInst4Null ? clazz.newInstance() : null : (T) objSessionContext;
        T sessionContext = (objSessionContext == null) ? newInst4Null ? clazz.newInstance() : null : (T) objSessionContext;
        if(sessionContext != null) {
            sessionContext.setSessionContextFactory(this);
        }
        return sessionContext;
    }

    public void saveSessionContext(HttpSession session, T sessionContext) {
        session.setAttribute(this.obtainSessionAttrKey(), sessionContext);
    }
    
    public void revokeSessionContext(HttpSession session) {
        session.removeAttribute(this.obtainSessionAttrKey());
    }

}
