package learn.BasicType;

import java.util.Objects;

/**
 * Created by hukai on 2018/9/1.
 */
public class ObjectTest {

    public static void main(String[] args) {
        Integer a = new Integer(500);
        Integer b = new Integer(500);
        System.out.println(a == b);
        //equals底层是先拆箱,然后再比较值
        System.out.println(a.equals(b));//如果a为null会有异常
        Objects.equals(a,b);//a ==b 或者 a.equals(b)

        Integer x = 5;
        x = null;
        System.out.println(x.equals(5));

    }
}
