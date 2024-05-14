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
package tw.dev.tomoaki.main;

import java.util.List;
import tw.dev.tomoaki.util.entity.core.KeyPairMap;
import tw.dev.tomoaki.util.entity.NestedMap;

/**
 *
 * @author tomoaki
 */
public class KeyPairMapTestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        test4();
    }

    protected static void test1() {
        KeyPairMap<String, String, String> teamAllLevelsPlayerMap = NestedMap.Factory.create();
        teamAllLevelsPlayerMap.put("Seattle Mariners", "MLB", "G.Kirby");
        teamAllLevelsPlayerMap.put("Seattle Mariners", "2A", "E.Hancock"); //今天升上了了
        teamAllLevelsPlayerMap.put("L.Angels", "MLB", "S.Ohtani");

        System.out.println(teamAllLevelsPlayerMap.getAllValueList());
        System.out.println(teamAllLevelsPlayerMap.get("Seattle Mariners", "2A"));

    }

    protected static void test2() {
        KeyPairMap<String, String, String> teamAllLevelsPlayerMap = NestedMap.Factory.create(Boolean.TRUE);
        teamAllLevelsPlayerMap.put("Seattle Mariners", "MLB", "G.Kirby");
        teamAllLevelsPlayerMap.put("Seattle Mariners", "2A", "E.Hancock"); //今天升上了了
        teamAllLevelsPlayerMap.put("L.Angels", "MLB", "S.Ohtani");

        System.out.println(teamAllLevelsPlayerMap.getAllValueList());
        System.out.println(teamAllLevelsPlayerMap.get("Seattle Mariners", "2A"));

    }

    protected static void test3() {
        NestedMap<String, String, String> teamAllLevelsPlayerMap = NestedMap.Factory.create(Boolean.TRUE);
        teamAllLevelsPlayerMap.put("Seattle Mariners", "MLB", "G.Kirby");
        teamAllLevelsPlayerMap.put("Seattle Mariners", "2A", "E.Hancock"); //今天升上了了
        teamAllLevelsPlayerMap.put("L.Angels", "MLB", "S.Ohtani");
        System.out.println(teamAllLevelsPlayerMap.getInnerMap("Seattle Mariners").values());
    }

    protected static void test4() {
        NestedMap<String, String, String> teamAllLevelsPlayerMap = NestedMap.Factory.create(Boolean.TRUE);
        teamAllLevelsPlayerMap.put("Seattle Mariners", "MLB", "G.Kirby");
        teamAllLevelsPlayerMap.put("Seattle Mariners", "MLB", "E.Hancock"); //今天升上了了
        teamAllLevelsPlayerMap.put("L.Angels", "MLB", "S.Ohtani");
        System.out.println(teamAllLevelsPlayerMap.getInnerMap("Seattle Mariners").values());
        System.out.println(teamAllLevelsPlayerMap.get("Seattle Mariners", "MLB"));        
    }

    protected static void test5() {
        NestedMap<String, String, List<String>> teamAllLevelsPlayerMap = NestedMap.Factory.create(Boolean.TRUE);

    }
}
