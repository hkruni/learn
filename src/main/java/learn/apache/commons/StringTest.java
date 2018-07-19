package learn.apache.commons;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class StringTest {

    public static void main(String[] args) {


        System.out.println("---------判断是否为空或数字--------------");
        System.out.println(StringUtils.isEmpty("\r\n"));//false,判断null或长度为0
        System.out.println(StringUtils.isBlank("\r\n"));//true
        String  s = "\b   aa  a\t";
        System.out.println(s.length());//10
        System.out.println(StringUtils.trim(s).length());//5
        System.out.println(StringUtils.strip(s).length());//9，只去掉了\t
        System.out.println(StringUtils.isNumeric("34 6"));//false
        System.out.println(StringUtils.isNumericSpace(" 34 6    "));//true，只包含数字和空格
        System.out.println(StringUtils.isNumericSpace("\n 34 6    "));//false，只包含数字和空格

        System.out.println("---------子字符串---------");
        String s1 = "ahx(hhu.)|txt|.txt";
        System.out.println(StringUtils.substringAfterLast(s1,"."));//txt，取文件扩展名
        System.out.println(StringUtils.substringBetween(s1,"(",")"));//txt
        System.out.println(s1.substring(s1.lastIndexOf('.') + 1));//txt
        System.out.println(s1.toUpperCase());//HU.TXT
        System.out.println(s1.replace("|",""));


        System.out.println("--------数组，分隔----------");
        String []array = new String []{"aaa","bbb","ccc","ddd","eee","fff"};
        System.out.println(StringUtils.join(array,"::"));//aaa::bbb::ccc::ddd::eee::fff
        System.out.println(String.join("::",array));//同上

        String s3 = "abc|123|def|456|789|123";
        String []s4 = StringUtils.split(s3,'|');//[abc, 123, def, 456, 789, 123]
        String []s5 = s3.split("|");//[a, b, c, |, 1, 2, 3, |, d, e, f, |, 4, 5, 6, |, 7, 8, 9, |, 1, 2, 3]
        System.out.println(Arrays.toString(s4));
        System.out.println(Arrays.toString(s5));


        System.out.println("--------补齐，重复----------");
        String s2 = "abc";
        System.out.println(StringUtils.leftPad(s2,5));//  abc  用空格左补齐,直到字符串长度为5
        System.out.println(StringUtils.leftPad(s2,10,"12"));//1212121abc
        System.out.println(StringUtils.repeat(s2,3));//abcabcabc
        System.out.println(StringUtils.repeat(s2,":",3));//abc:abc:abc
        System.out.println(StringUtils.remove("abc|asa|2121|2121","|"));

    }
}
