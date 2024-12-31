/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.util.entity;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        return super.get(langTag);
    }

    public VALUE get(Locale locale) {
        return this.get(locale.toLanguageTag());
    }
    
    public Boolean containssLang(String langTag) {
        return containsKey(langTag);
    }
    
    public Boolean containsLang(Locale locale) {
        return containsKey(locale.toLanguageTag());
    }

    public List<String> getLanguageTagList() {
        return new ArrayList(this.keySet());
    }
}
