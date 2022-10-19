/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.util.regularexpression;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tomoaki Chen
 */
public class RegExpCaptureMap {
    
    private Map<Integer, List<String>> captureResultKeyByOrderMap;
    
    public RegExpCaptureMap() {
        this.captureResultKeyByOrderMap = new LinkedHashMap();
    }
    
    public void put(Integer order, String captureResult) {
        List<String> resultList = this.getResultList(order);
        if(resultList == null) {
            resultList = new ArrayList();
        }
        resultList.add(captureResult);
        this.captureResultKeyByOrderMap.put(order, resultList);
    }
    
    public List<String> getResultList(Integer desigOrder) {
        return this.captureResultKeyByOrderMap.get(desigOrder);
    }
    
}
