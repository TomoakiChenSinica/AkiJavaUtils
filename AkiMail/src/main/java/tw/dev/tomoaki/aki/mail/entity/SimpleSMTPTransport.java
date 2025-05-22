/*
 * Copyright 2025 tomoaki.
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
package tw.dev.tomoaki.aki.mail.entity;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

/**
 *
 * @author tomoaki
 */
public class SimpleSMTPTransport implements SMTPTransport {

    protected SimpleSMTPTransport() {
    }

    public static class Factory {

        public static SimpleSMTPTransport create() {
            return new SimpleSMTPTransport();
        }
    }

    @Override
    public void send(Message message) throws MessagingException {
        Transport.send(message);
    }

    @Override
    public void close() throws MessagingException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        
        //Transport Keep 住不是問題，問題在甚麼時候關閉?
    }
}
