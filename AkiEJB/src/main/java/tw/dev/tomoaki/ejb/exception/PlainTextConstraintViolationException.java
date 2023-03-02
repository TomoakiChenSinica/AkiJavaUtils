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
package tw.dev.tomoaki.ejb.exception;

import java.util.List;

/**
 *
 * @author tomoaki
 */
public class PlainTextConstraintViolationException extends RuntimeException {
  
    protected PlainTextConstraintViolationException() {
        super();
    }
    
    protected PlainTextConstraintViolationException(String msg) {
        super(msg);
    }
//    
//    public PlainTextConstraintViolationException(Exception ex) {
//        super(ex);
//    }
    
    public static class Factory {
        public static PlainTextConstraintViolationException create(List<String> violationMsgList) {
            PlainTextConstraintViolationException theEx = new PlainTextConstraintViolationException(violationMsgList.toString());
            return theEx;
        }
    }

    
}
