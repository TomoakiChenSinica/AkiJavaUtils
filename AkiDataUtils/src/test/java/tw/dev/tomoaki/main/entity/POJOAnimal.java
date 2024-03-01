/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.main.entity;

import tw.dev.tomoaki.datautils.datareflection.Mergeable;

/**
 *
 * @author tomoaki
 */
public class POJOAnimal implements Mergeable<POJOAnimal> {

    private String name;
    private Long numsOfFit;
    private Long numsOfWing;
    private Float runningSpeed;
    private Float flyingSpeed;

    public POJOAnimal() {
    }

    public POJOAnimal(String name, Long numsOfFit) {
        this.name = name;
        this.numsOfFit = numsOfFit;
    }

    public POJOAnimal(String name, Long numsOfFit, Float runningSpeed) {
        this.name = name;
        this.numsOfFit = numsOfFit;
        this.runningSpeed = runningSpeed;
    }

    public POJOAnimal(String name, Long numsOfFit, Long numsOfWing, Float flyingSpeed) {
        this.name = name;
        this.numsOfFit = numsOfFit;
        this.numsOfWing = numsOfWing;
        this.flyingSpeed = flyingSpeed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumsOfFit() {
        return numsOfFit;
    }

    public void setNumsOfFit(Long numsOfFit) {
        this.numsOfFit = numsOfFit;
    }

    public Long getNumsOfWing() {
        return numsOfWing;
    }

    public void setNumsOfWing(Long numsOfWing) {
        this.numsOfWing = numsOfWing;
    }

    public Float getRunningSpeed() {
        return runningSpeed;
    }

    public void setRunningSpeed(Float runningSpeed) {
        this.runningSpeed = runningSpeed;
    }

    public Float getFlyingSpeed() {
        return flyingSpeed;
    }

    public void setFlyingSpeed(Float flyingSpeed) {
        this.flyingSpeed = flyingSpeed;
    }

    @Override
    public String toString() {
        return String.format("name= %s, numsOfFit= %s, numsOfWing= %s, runningSpeed= %s, flyingSpeed= %s", name, numsOfFit, numsOfWing, runningSpeed, flyingSpeed);
    }
}
