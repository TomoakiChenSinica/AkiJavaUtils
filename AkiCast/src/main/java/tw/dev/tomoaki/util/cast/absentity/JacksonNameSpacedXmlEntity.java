/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.util.cast.absentity;

/**
 *
 * @author tomoaki
 */
public abstract class JacksonNameSpacedXmlEntity {
    
    protected String xmlNameSpace;

    public String getXmlNameSpace() {
        return xmlNameSpace;
    }

    public void setXmlNameSpace(String xmlNameSpace) {
        this.xmlNameSpace = xmlNameSpace;
    }          
}
