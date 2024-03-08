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
package tw.dev.tomoaki.aki.mail.helper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.mail.Address;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author tomoaki
 */
public class MailAddressHelper {

    public static Address tryObtainMailAddress(String address) {
        try {
            return new InternetAddress(address);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static List<Address> tryObtainMailAddressList(String... addresses) {
        return Stream.of(addresses).map(MailAddressHelper::tryObtainMailAddress).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static List<Address> tryObtainMailAddressList(List<String> addressList) {
        return addressList.stream().map(MailAddressHelper::tryObtainMailAddress).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static Address[] tryObtainMailAddresses(String... addresses) {
        List<Address> mailAddressList = tryObtainMailAddressList(addresses);
        return mailAddressList.toArray(new Address[0]);
    }    
    
    public static Address[] tryObtainMailAddresses(List<String> addressList) {
        List<Address> mailAddressList = tryObtainMailAddressList(addressList);
        return mailAddressList.toArray(new Address[0]);
    }
}
