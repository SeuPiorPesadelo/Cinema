package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe formatadora. FormatadorUtil
 */
public class FormatadorUtil {

    /**
     * Realiza parse de Date para String
     *
     * @param d
     * @return void
     */
    public static String formataHora(Date d) {
        SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
        String str = fmt.format(d);
        return str;
    }

    /**
     * Valida Hora. String para Date
     *
     * @param h
     * @return void
     */
    public static Date formataDateHora(String h) {
        String regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        if (h.matches(regex) && h.length() == 5) {
            Date d = new Date();
            d.setHours(Integer.parseInt(h.substring(0, 2)));
            d.setMinutes(Integer.parseInt(h.substring(3, 5)));
            return d;
        } else {
            return null;
        }
    }
}
