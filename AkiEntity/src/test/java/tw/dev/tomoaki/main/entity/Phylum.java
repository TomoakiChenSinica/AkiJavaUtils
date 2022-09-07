/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.main.entity;

/**
 *
 * @author Tomoaki Chen
 */
public enum Phylum {
    
    Acanthocephala("Acanthocephala", "棘頭動物門", "身體前端有吻，吻上有鉤刺。營寄生生活"),
    Annelida("Annelida", "環節動物門", "身體柔軟且分節"),
    Arthropoda("Arthropoda", "節肢動物門", "分節的身體及附肢、幾丁質外骨骼"),
    Brachiopoda("Brachiopoda", "腕足動物門", "海生。具有總擔與肉莖。有一對上下開合、左右對稱的外殼"),
    Bryozoa("Bryozoa", "外肛動物門", "水生群體動物。具有總擔，無肉莖，肛門在觸手圈外"),
    Chaetognatha("Chaetognatha", "毛顎動物門", "海生的小型動物。體形似箭，在頭和鰭的兩邊均有幾丁質組成的尖刺	");
    
    
    private String code;
    private String name;
    private String summary;    
    
    private Phylum(String code, String name, String summary) {
        this.code = code;        
        this.name = name;
        this.summary = summary;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public static Phylum codeOf(String desigCode) {
        Phylum desigPhylum = null;
        for(Phylum phylum : Phylum.values()) {
            if(phylum.getCode().equals(desigCode)) {
                desigPhylum = phylum;
                break;
            }
        }    
        if(desigPhylum == null) {
            throw new IllegalArgumentException("Cannot Found");
        }
        return desigPhylum;
    }
}
