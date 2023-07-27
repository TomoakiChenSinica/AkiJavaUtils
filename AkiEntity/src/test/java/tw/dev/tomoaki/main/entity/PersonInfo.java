/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.main.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author tomoaki
 */
public class PersonInfo {

    private Long personId;
    private String lastName;
    private String firstName;
    private List<String> teamAbbrevs;
    private List<Animal> pets;

    public PersonInfo(Long personId, String lastName, String firstName) {
        this.personId = personId;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
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

    public List<String> getTeamAbbrevs() {
        return teamAbbrevs;
    }

    public void setTeamAbbrevs(List<String> teamAbbrevs) {
        this.teamAbbrevs = teamAbbrevs;
    }

    public void addTeamAbbrev(String teamAbbrev) {
        if (this.teamAbbrevs == null) {
            this.teamAbbrevs = new ArrayList();
        }
        this.teamAbbrevs.add(teamAbbrev);
    }

    public List<Animal> getPets() {
        return pets;
    }

    public void setPets(List<Animal> pets) {
        this.pets = pets;
    }
    
    public void addPet(Animal pet) {
        if(this.pets == null) {
            this.pets = new ArrayList();
        }
        this.pets.add(pet);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.personId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PersonInfo other = (PersonInfo) obj;
        return Objects.equals(this.personId, other.personId);
    }

    @Override
    public String toString() {
        return "PersonInfo{" + "personId=" + personId + '}';
    }

}
