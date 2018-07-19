package learn.guava;

import com.google.common.base.Strings;

public class StringTest {

    public static void main(String[] args) {

        System.out.println(Strings.isNullOrEmpty("  ".trim()));
        System.out.println("   ".replace(' ','1'));
        System.out.println(Strings.isNullOrEmpty("  "));
        System.out.println(Strings.isNullOrEmpty(null));
        System.out.println(Strings.isNullOrEmpty(""));

        System.out.println("a b c d e f".replace(' ',';'));

        System.out.println(Strings.commonSuffix("xabxw1w1","abx22121"));
        System.out.println(Strings.commonPrefix("1abxw1w1","abx22121").length());

        System.out.println(Strings.repeat("abc",3));
    }
}
