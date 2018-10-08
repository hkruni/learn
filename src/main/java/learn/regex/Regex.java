package learn.regex;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public static boolean checkRule(String url, String rule){
        Pattern pattern = Pattern.compile(rule);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

    public static void  group(String url, String rule){
        Pattern pattern = Pattern.compile(rule);
        Matcher m = pattern.matcher(url);
        while (m.find()) {
            System.out.println(m.group(1));
        }
    }



    public static void main(String[] args) {
//        String url = "http://www.jd.com/item/321xac.html";
//        String rule = "https{0,1}://www.jd.com/.*";
//        System.out.println(checkRule(url, rule));

        String s1 = "jd12alda.com189303jdjaop";
        String s2 = ".*com(\\d+)jd.*";

        group(s1,s2);
    }
}
