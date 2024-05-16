/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.util.entity;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author tomoaki
 *
 * @param <VALUE>
 */
public class LocaleDiffField<VALUE> extends HashMap<String, VALUE> {

    @Override
    public VALUE put(String langTag, VALUE value) {
        return super.put(langTag, value); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public VALUE get(String langTag) {
        // System.out.println("get(String)");
        return super.get(langTag);
    }

    public VALUE get(Locale locale) {
        // System.out.println("get(Locale)");
        return this.get(locale.toLanguageTag());
    }
}

//public class DiffField<KEY, VALUE> extends HashMap<KEY, VALUE> {    
//}
