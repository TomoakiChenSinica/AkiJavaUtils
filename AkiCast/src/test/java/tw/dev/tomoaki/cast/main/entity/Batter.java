/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.cast.main.entity;

/**
 *
 * @author arche
 */
public class Batter {

    private String firstName;
    private String lastName;
    private Double avg;

    public Batter() {
    }
    
    public Batter(String firstName, String lastName, Double avg) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.avg = avg;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

}
