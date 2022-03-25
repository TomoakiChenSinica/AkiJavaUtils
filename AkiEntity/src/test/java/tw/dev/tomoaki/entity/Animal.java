/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.entity;

/**
 *
 * @author Tomoaki Chen
 */
public class Animal {
    
    private String name;
    private Integer foots;

    public Animal(String name, Integer foots) {
        this .name = name;
        this.foots = foots;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFoots() {
        return foots;
    }

    public void setFoots(Integer foots) {
        this.foots = foots;
    }

    @Override
    public String toString() {
        return String.format("Animal[name= %s, foots= %s]",  name, foots);
    }
    
    
    
}
