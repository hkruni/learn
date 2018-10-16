package learn.String;

public class StringTest {

    public static void main(String[] args) {

        String s = "123456";
        s = s.substring(0,7);//substring必须先判断字符串长度
        System.out.println(s);
    }
}
