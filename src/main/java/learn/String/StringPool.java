package learn.String;

public class StringPool {


    /**
     * 判断字符串是否存在于JVM的字符串常量池中
     * @param str
     * @return
     */
    public static boolean isINStringPool(String str) {

        String s = str.intern();
        if(s == str)
            return true;

        return false;
    }

    public static void main(String[] args) {

        String s1 = new String("456");

        System.out.println(isINStringPool("123"));
        System.out.println(isINStringPool(s1));
    }
}
