package learn.encrypt.base64;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class Base64Utils {
    private static final Base64 BASE64 = new Base64();
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    //private static final String PREFIX = "XXXXX";

    public static String  encode(byte[] source) {
        byte[] bytes = BASE64.encode(source);
        return new String(bytes);
            //return new String(bytes, DEFAULT_CHARSET);
    }

    public static byte[] decode(String source) {
        byte[] bytes = BASE64.decode(source.getBytes(DEFAULT_CHARSET));
        return bytes;


    }





}
