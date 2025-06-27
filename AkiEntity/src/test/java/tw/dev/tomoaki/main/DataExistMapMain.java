/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import tw.dev.tomoaki.main.entity.Animal;
import tw.dev.tomoaki.main.entity.PersonInfo;
import tw.dev.tomoaki.main.entity.Phylum;
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
//        test1();
//        test2();
//        test4();
//        test5();
        test6();
    }

    public static void test1() {
        Animal dog = new Animal("Dog", 4);
        Animal cat = new Animal("Cat", 4);
        Animal bird = new Animal("Bird", 6);
        Animal bird2 = new Animal("Bird", 2);

        DataExistMap<Animal> animalExistMap = DataExistMap.createOrdered();//new DataExistMap();
        animalExistMap.add(dog);
        animalExistMap.add(cat);
        animalExistMap.add(bird);
        animalExistMap.add(bird);
        animalExistMap.add(bird2);
        System.out.println(animalExistMap.getDataList());

        DataExistMap<Animal> animalExistMap2 = animalExistMap.getCopy();
        System.out.println(animalExistMap2.getDataList());
    }

    public static void test2() {
        DataExistMap<Phylum> phylumMap = new DataExistMap<Phylum>();
        phylumMap.add(Phylum.Bryozoa);
        phylumMap.add(Phylum.Arthropoda);
        phylumMap.add(Phylum.Chaetognatha);

        System.out.println(phylumMap.contains(Phylum.Bryozoa));
        System.out.println(phylumMap.contains(Phylum.codeOf("Bryozoa")));
    }

    protected static void test3() {
        DataExistMap<PersonInfo> existMap = DataExistMap.create();
        PersonInfo ackley = new PersonInfo(1l, "Ackley", "Dustin");
        ackley.addTeamAbbrev("SEA");
        PersonInfo smoak = new PersonInfo(2l, "Smoak", "Justin");
        smoak.addTeamAbbrev("TOR");
        existMap.add(ackley);
        existMap.add(smoak);

        existMap.existList().forEach(person -> {
            System.out.println("Person.Name= " + person.getFirstName() + " " + person.getLastName() + ", Person.Teams= " + person.getTeamAbbrevs());
        });       
        
        ackley.addTeamAbbrev("NYY");
        existMap.add(ackley);
        existMap.existList().forEach(person -> {
            System.out.println("Person.Name= " + person.getFirstName() + " " + person.getLastName() + ", Person.Teams= " + person.getTeamAbbrevs());
        });        
    }
    
    protected static void test4() {
        PersonInfo ackley = new PersonInfo(1l, "Ackley", "Dustin");
        ackley.addTeamAbbrev("SEA");
        PersonInfo smoak = new PersonInfo(2l, "Smoak", "Justin");
        smoak.addTeamAbbrev("TOR");
        
        DataExistMap<PersonInfo> existMap = DataExistMap.create(Arrays.asList(ackley, smoak));        

        existMap.existList().forEach(person -> {
            System.out.println("Person.Name= " + person.getFirstName() + " " + person.getLastName() + ", Person.Teams= " + person.getTeamAbbrevs());
        });       
        
        ackley.addTeamAbbrev("NYY");
        existMap.add(ackley);
        existMap.existList().forEach(person -> {
            System.out.println("Person.Name= " + person.getFirstName() + " " + person.getLastName() + ", Person.Teams= " + person.getTeamAbbrevs());
        });        
    }    

    protected static void test5() {
        Animal milky = new Animal("Milky", 4);
        Animal tan = new Animal("Tan", 4);
        Animal boky = new Animal("Boky", 4);
        
        PersonInfo ackley = new PersonInfo(1l, "Ackley", "Dustin");
        ackley.addTeamAbbrev("SEA");
        ackley.addPet(milky);
        
        PersonInfo smoak = new PersonInfo(2l, "Smoak", "Justin");
        smoak.addTeamAbbrev("TOR");
        
        DataExistMap<PersonInfo> existMap = DataExistMap.create(Arrays.asList(ackley, smoak));        

        existMap.existList().forEach(person -> {
            System.out.println("Person.Name= " + person.getFirstName() + " " + person.getLastName() + ", Person.Pets= " + person.getPets());
        });       
        
        ackley.addTeamAbbrev("NYY");
        ackley.addPet(tan);
        existMap.add(ackley);
        existMap.existList().forEach(person -> {
            System.out.println("Person.Name= " + person.getFirstName() + " " + person.getLastName() + ", Person.Teams= " + person.getPets());
        });        
    }  
    
    protected static void test6() {
        Animal milky = new Animal("Milky", 4);
        Animal tan = new Animal("Tan", 4);
        Animal boky = new Animal("Boky", 4);
        
        PersonInfo ackley = new PersonInfo(1l, "Ackley", "Dustin");
        ackley.addTeamAbbrev("SEA");
        List<Animal> petList = new ArrayList();
        petList.add(milky);
        ackley.setPets(petList);
        
        PersonInfo smoak = new PersonInfo(2l, "Smoak", "Justin");
        smoak.addTeamAbbrev("TOR");
        
        DataExistMap<PersonInfo> existMap = DataExistMap.create(Arrays.asList(ackley, smoak));        
        
        existMap.existList().forEach(person -> {
            System.out.println("Person.Name= " + person.getFirstName() + " " + person.getLastName() + ", Person.Pets= " + person.getPets());
        });       
        
        ackley.addTeamAbbrev("NYY");
        List<Animal> petList2 = new ArrayList();
        petList2.add(milky);
        petList2.add(tan);
        ackley.setPets(petList2);
        
        existMap.add(ackley);
        existMap.existList().forEach(person -> {
            System.out.println("Person.Name= " + person.getFirstName() + " " + person.getLastName() + ", Person.Teams= " + person.getPets());
        });        
    }      
}
