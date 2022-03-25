/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.cast;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author Tomoaki Chen
 */
public class ExceptionPrinter {

    public static String obtainStackTrace(Exception ex) {
        StringWriter errors = new StringWriter();
        ex.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }
}
