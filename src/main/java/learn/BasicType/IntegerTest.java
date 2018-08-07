package learn.BasicType;

/**
 * Created by hukai on 2018/7/27.
 */
public class IntegerTest {

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;

        System.out.println(c == d);//true
        System.out.println(e == f);//false
        System.out.println(c == (a+b));//true   包装类的==在遇到运算符会自动拆箱
        System.out.println(c.equals(a+b));//true
        System.out.println(g == (a+b));//true
        System.out.println(g.equals(a+b));//false  a+b会生成Integer对象，无法比较

        long x1 = 3L;
        int x2 = 3;
        System.out.println(x1 == x2);//true

    }
}
