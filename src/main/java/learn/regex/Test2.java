package learn.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hukai on 2018/9/12.
 */
public class Test2 {

    public static String REGEX_QQ = "[1-9][0-9]{4,14}";
    public static  String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    public static  String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    public static  String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";


    public static void main(String[] args) {

        System.out.println(Pattern.matches(REGEX_QQ,"377217153"));;
        System.out.println(Pattern.matches(REGEX_MOBILE, "18728192010"));
        System.out.println(Pattern.matches(REGEX_CHINESE, "打开手机打"));

        String a="love23next234csdn3423javaeye";
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(a);
        if (m.find()) {
            System.out.println("找到了");
            System.out.println(m.group());
        }
        System.out.println( m.replaceAll("").trim());

        String regEx1 = "count(\\d+)(df)";
        String s = "count000dfdfsdffaaaa1";
        Pattern pat = Pattern.compile(regEx1);
        Matcher mat = pat.matcher(s);
        if(mat.find()){
            System.out.println(mat.group(2));
        }

    }
}
