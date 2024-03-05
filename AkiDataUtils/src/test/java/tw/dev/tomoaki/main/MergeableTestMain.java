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
package tw.dev.tomoaki.main;

import tw.dev.tomoaki.main.entity.POJOAnimal;

/**
 *
 * @author tomoaki
 */
public class MergeableTestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        POJOAnimal cat = new POJOAnimal("cat", 4l, 0.1f);
        POJOAnimal bird = new POJOAnimal("bird", 2l, 2l, 0.3f);
        POJOAnimal fantasy = new POJOAnimal();
        System.out.println(fantasy.merge(bird).merge(cat));
        System.out.println(fantasy);
        
        POJOAnimal fantasy2 = new POJOAnimal();
        fantasy2.rightJoin(cat).rightJoin(bird);
        System.out.println(fantasy2);
    }

}
