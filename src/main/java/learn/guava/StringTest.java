package learn.guava;

import com.google.common.base.Strings;

public class StringTest {

    public static void main(String[] args) {

        System.out.println(Strings.isNullOrEmpty("  "));//false
        System.out.println(Strings.isNullOrEmpty("  ".trim()));//true
        System.out.println("   ".replace(' ','1'));//111
        System.out.println(Strings.isNullOrEmpty(null)); //true
        System.out.println(Strings.isNullOrEmpty(""));      //true

        System.out.println("a b c d e f".replace(' ',';'));  //a;b;c;d;e;f

        System.out.println(Strings.commonSuffix("xabxw1w1","abx22121"));
        System.out.println(Strings.commonPrefix("1abxw1w1","abx22121").length());

        System.out.println(Strings.repeat("abc",3));    // abcabcabc

    }
}
