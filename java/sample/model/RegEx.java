package sample.model;
import java.util.regex.*;

/**
 * Created by Андрій on 29.10.2016.
 */
public class RegEx {
    public static boolean match(String content, String regex)
    {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(content);
        return m.matches();
    }
    public static boolean matchFull(String content, String regex) {
        return match(content, "^"+regex+"$");
    }
}
