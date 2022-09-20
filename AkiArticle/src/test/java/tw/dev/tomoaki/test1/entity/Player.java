/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.test1.entity;

/**
 *
 * @author arche
 */
public class Player {

    private String lastName;
    private String firstName;
    private Integer age;
    
    public static Player obtain(String firstName, String lastName, Integer age) {
        Player player = new Player();
        player.age = age;
        player.firstName = firstName;
        player.lastName = lastName;
        return player;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
