/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.cast.main.entity.player.garbage;

import tw.dev.tomoaki.cast.main.entity.player.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 *
 * @author tomoaki
 */
//@JacksonXmlRootElement(localName = "aki:player", namespace = "http://schemas.datacontract.org/2004/07/Vtex.Commerce.WebApps.AdminWcfService.Contracts")
@JacksonXmlRootElement(localName = "player")
public class Player {

    @JacksonXmlProperty(localName = "aki:name", namespace = "http://schemas.datacontract.org/2004/07/Vtex.Commerce.WebApps.AdminWcfService.Contracts")
    private String name;

    @JacksonXmlProperty(localName = "hp", namespace = "http://schemas.datacontract.org/2004/07/Vtex.Commerce.WebApps.AdminWcfService.Contracts")
    private Integer healthPoint;

    @JacksonXmlProperty(localName = "sp", namespace = "http://schemas.datacontract.org/2004/07/Vtex.Commerce.WebApps.AdminWcfService.Contracts")
    private Integer specialPoint;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(Integer healthPoint) {
        this.healthPoint = healthPoint;
    }

    public Integer getSpecialPoint() {
        return specialPoint;
    }

    public void setSpecialPoint(Integer specialPoint) {
        this.specialPoint = specialPoint;
    }

}
