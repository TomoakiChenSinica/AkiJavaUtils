/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.main;

import tw.dev.tomoaki.entity.Animal;
import tw.dev.tomoaki.util.entity.DataExistMap;

/**
 *
 * @author Tomoaki Chen
 */
public class DataExistMapMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        test1();
    }

    public static void test1() {
        Animal dog = new Animal("Dog", 4);
        Animal cat = new Animal("Cat", 4);
        Animal bird = new Animal("Bird", 6);
        Animal bird2 = new Animal("Bird", 2);
        
        DataExistMap<Animal> animalExistMap = DataExistMap.Factory.createOrdered();//new DataExistMap();
        animalExistMap.add(dog);
        animalExistMap.add(cat);
        animalExistMap.add(bird);
        animalExistMap.add(bird);        
        animalExistMap.add(bird2);        
        System.out.println(animalExistMap.getDataList());
        
        DataExistMap<Animal> animalExistMap2 = animalExistMap.getCopy();
        System.out.println(animalExistMap2.getDataList());        
    }

}
