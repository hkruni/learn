package learn.encrypt;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.logging.SocketHandler;

public class RSAUtil {

    public static void getKeys() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        String publicKeyStr = getPublicKeyStr(publicKey);
        String privateKeyStr = getPrivateKeyStr(privateKey);

        System.out.println(publicKeyStr);
        System.out.println("-------------------");
        System.out.println(privateKeyStr);

    }

    public static String getPrivateKeyStr(PrivateKey privateKey)
            throws Exception {
        return Base64Utils.getBASE64(privateKey.getEncoded());
    }

    public static String getPublicKeyStr(PublicKey publicKey) throws Exception {
        return Base64Utils.getBASE64(publicKey.getEncoded());
    }

    public static void main(String[] args) throws Exception {
        getKeys();
    }

}
