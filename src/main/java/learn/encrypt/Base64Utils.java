package learn.encrypt;

import sun.misc.BASE64Decoder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Base64Utils {


    public static String encodeBase64(String s){
        Object retObj= null;
        try {
            byte[] input = s.getBytes();
            Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
            Method mainMethod= clazz.getMethod("encode", byte[].class);
            mainMethod.setAccessible(true);
            retObj = mainMethod.invoke(null, new Object[]{input});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (String)retObj;
    }
    /***
     * decode by Base64
     */
    public static String decodeBase64(String input) {
        Object retObj= null;
        try {
            Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
            Method mainMethod= clazz.getMethod("decode", String.class);
            mainMethod.setAccessible(true);
            retObj = mainMethod.invoke(null, input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String((byte[])retObj);
    }

    public static String getBASE64(byte[] b) {
        String s = null;
        if (b != null) {
            s = new sun.misc.BASE64Encoder().encode(b);
        }
        return s;
    }

    public static byte[] getFromBASE64(String s) {
        byte[] b = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                return b;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return b;
    }

    public static void main(String[] args) {
        System.out.println(encodeBase64("hello,world"));//aGVsbG8sd29ybGQ=

        System.out.println(decodeBase64("aGVsbG8sd29ybGQ="));
    }
}
