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

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.stream.Stream;
import tw.dev.tomoaki.main.annotation.SpecialMethod;
import tw.dev.tomoaki.main.entity.Animal;
import tw.dev.tomoaki.main.entity.Params;
import tw.dev.tomoaki.reflection.JavaMethodHelper;

/**
 *
 * @author tomoaki
 */
public class ReflectionTestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        testMethod1();
    }

    protected static void test1() {
//        Animal animal = new Animal();
//        Field[] fields = animal.getClass().getFields();        
        Field[] fields = Animal.class.getFields();
        Stream.of(fields).forEach(System.out::println);
    }

    protected static void test2() {
        testField1();
        testField2();
    }

    protected static void testField1() {
        //拿到 public 的
        Field[] fields = Params.class.getFields();
        Stream.of(fields).map(Field::getName).forEach(System.out::println);

        //拿到全部有定義的
        Field[] declaredFields = Params.class.getDeclaredFields();
        Stream.of(declaredFields).map(Field::getName).forEach(System.out::println);
    }

    protected static void testField2() {
        //拿到 public 的
        Field[] fields = Params.class.getFields();

        Stream.of(fields).map(Field::getAnnotations).flatMap(Stream::of).forEach(System.out::println);
        Stream.of(fields).map(Field::getAnnotations).flatMap(Stream::of).map(Annotation::annotationType).forEach(System.out::println);

        Stream.of(fields).map(Field::getDeclaredAnnotations).flatMap(Stream::of).forEach(System.out::println);
        Stream.of(fields).map(Field::getDeclaredAnnotations).flatMap(Stream::of).map(Annotation::annotationType).forEach(System.out::println);

        System.out.println("==============================================================");
        //拿到全部有定義的
        Field[] declaredFields = Params.class.getDeclaredFields();
        Stream.of(declaredFields).map(Field::getAnnotations).flatMap(Stream::of).forEach(System.out::println);
        Stream.of(declaredFields).map(Field::getDeclaredAnnotations).flatMap(Stream::of).forEach(System.out::println);

        Stream.of(declaredFields).map(Field::getDeclaredAnnotations).flatMap(Stream::of).forEach(System.out::println);
        Stream.of(declaredFields).map(Field::getDeclaredAnnotations).flatMap(Stream::of).map(Annotation::annotationType).forEach(System.out::println);

    }

    protected static void test3() {
        Annotation[] annotations = Params.class.getAnnotations();
        Stream.of(annotations).forEach(System.out::println);

        Annotation[] declaredAnnotations = Params.class.getDeclaredAnnotations();
        Stream.of(declaredAnnotations).forEach(System.out::println);
    }

    protected static void testMethod1() {
        Animal dog = new Animal();
        dog.setName("dog");
        JavaMethodHelper.obtainMethodListByAnnotation(Animal.class, SpecialMethod.class).forEach(method -> {
            try {
                method.invoke(dog);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
    
//<editor-fold defaultstate="collapsed" desc="Old Code">
    /*
        protected static void testField2() {
        //拿到 public 的
        Field[] fields = Params.class.getFields();
//        Stream.of(fields).map(Field::getAnnotations).flatMap(annotations -> {
//            Stream.of(annotations)
//        })
//      Stream.of(fields).map(Field::getAnnotations).map(Stream::of).forEach(System.out::println);
        Stream.of(fields).map(Field::getAnnotations).flatMap(Stream::of).forEach(System.out::println);
        Stream.of(fields).map(Field::getAnnotations).flatMap(Stream::of).map(Annotation::annotationType).forEach(System.out::println);
        Stream.of(fields).map(Field::getDeclaredAnnotations).flatMap(Stream::of).forEach(System.out::println);
        Stream.of(fields).map(Field::getDeclaredAnnotations).flatMap(Stream::of).map(Annotation::annotationType).forEach(System.out::println);

        //拿到全部有定義的
        Field[] declaredFields = Params.class.getDeclaredFields();
//        Stream.of(declaredFields).map(Field::getAnnotations).map(Stream::of).forEach(System.out::println);
        Stream.of(declaredFields).map(Field::getAnnotations).flatMap(Stream::of).forEach(System.out::println);
        Stream.of(declaredFields).map(Field::getDeclaredAnnotations).flatMap(Stream::of).forEach(System.out::println);
    }
     */
//</editor-fold>
}
