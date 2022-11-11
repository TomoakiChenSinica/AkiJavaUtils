/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.cssinliner.helper;

import com.gargoylesoftware.css.parser.InputSource;
import java.io.StringReader;

/**
 *
 * @author Tomoaki Chen
 */
public class InputSourceHelper {
    
    public static InputSource createSource(String cssCode) {
        return new InputSource(new StringReader(cssCode));
    }
}
