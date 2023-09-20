package tw.dev.tomoaki.main;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 *
 * @author tomoaki
 */
public class MonthTestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(Month.APRIL.getDisplayName(TextStyle.FULL, Locale.TAIWAN));
        System.out.println(Month.APRIL.getDisplayName(TextStyle.FULL, Locale.US));
        System.out.println(Month.APRIL.getDisplayName(TextStyle.SHORT, Locale.TAIWAN));
        System.out.println(Month.APRIL.getDisplayName(TextStyle.SHORT, Locale.US));
    }
    
}
