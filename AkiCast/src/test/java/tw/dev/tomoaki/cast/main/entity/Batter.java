/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.cast.main.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 *
 * @author arche
 */
public class Batter {

    //https://github.com/FasterXML/jackson-dataformat-xml#additional-annotations
    //https://stackoverflow.com/questions/19847094/jackson-xml-annotations-string-element-with-attribute
    @JacksonXmlProperty(localName="FirstName") 
    private String firstName;
        
    @JacksonXmlProperty(localName="LastName")
    private String lastName;
    
    @JacksonXmlProperty(localName="AVG")
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
