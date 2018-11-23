package learn.encrypt.rsa;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

@SuppressWarnings({ "rawtypes", "unused" })
public class UtilTools {

    private static final String PKCS12 = "PKCS12";
    private static final String CHARSET = "utf-8";
    private final static String CertType = "X.509";
    public final static String TrustStoreType = "JKS";
    private static final String SHA1WithRSA = "SHA1WithRSA";
    private final static String MD5withRSA = "MD5withRSA";
    private static final String SHA224WithRSA = "SHA224WithRSA";
    private static final String SHA256WithRSA = "SHA256WithRSA";
    private static final String SHA384WithRSA = "SHA384WithRSA";
    private static final String SHA512WithRSA = "SHA512WithRSA";
    private static final String RSA = "RSA";
    private static final String ECB = "ECB";
    private static final String PCKCS1PADDING = "PCKCS1Padding";

    /**
     * generate the signature
     *
     * @param source
     * @param pfxPath
     * @param password
     * @return
     * @throws Exception
     */
    public static String generateSignature(String source, String pfxPath, String password) throws Exception {
        byte[] signature = null;
        PrivateKey privateKey = getPrivateKeyInstance(pfxPath, password);
        Signature sig = Signature.getInstance(SHA1WithRSA);
        sig.initSign(privateKey);
        sig.update(source.getBytes(CHARSET));
        signature = sig.sign();
        return Base64Utils.encode(signature);
    }

    /**
     * check the signature
     *
     * @param datasource
     * @param sign
     * @param certificatePath
     * @return
     * @throws Exception
     */
    public static boolean checkSignature(String datasource, String sign, String certificatePath) throws Exception {
        try {
            X509Certificate x509Certificate = (X509Certificate) getInstance(certificatePath);
            Signature signature = Signature.getInstance(SHA1WithRSA);
            signature.initVerify(x509Certificate);
            signature.update(datasource.getBytes(CHARSET));
            return signature.verify(Base64Utils.decode(sign));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * 加载私钥
     *
     * @param strPfx
     * @param strPassword
     * @return
     */
    private static PrivateKey getPrivateKeyInstance(String strPfx, String strPassword) throws Exception {
        FileInputStream fis = null;
        try {
            KeyStore ks = KeyStore.getInstance(PKCS12);
            fis = new FileInputStream(strPfx);
            char[] chars = null;
            if ((strPassword == null) || strPassword.trim().equals("")) {
                chars = null;
            } else {
                chars = strPassword.toCharArray();
            }
            ks.load(fis, chars);
            fis.close();
            Enumeration enumas = ks.aliases();
            String keyAlias = null;
            if (enumas.hasMoreElements()) {
                keyAlias = (String) enumas.nextElement();
            }
            return (PrivateKey) ks.getKey(keyAlias, chars);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * 获得证书
     *
     * @param certificatePath
     * @return
     */
    private static Certificate getInstance(String certificatePath) throws Exception {
        InputStream is = null;
        try {
            is = new FileInputStream(certificatePath);
            CertificateFactory certificateFactory = CertificateFactory.getInstance(CertType);
            return certificateFactory.generateCertificate(is);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * 将pfx或p12的文件转为keystore
     *
     * @param pfxFile 原文件路径及名称
     * @param pfxPsw 密码
     * @param keyStoreFile 生成的文件名和路径
     */
    public static void coverTokeyStore(String pfxFile, String pfxPsw, String keyStoreFile) throws Exception {
        KeyStore inputKeyStore = null;
        FileInputStream input = null;
        FileOutputStream output = null;
        String keyAlias = "";
        try {
            inputKeyStore = KeyStore.getInstance(PKCS12);
            input = new FileInputStream(pfxFile);
            char[] password = null;

            if ((pfxPsw == null) || pfxPsw.trim().equals("")) {
                password = null;
            } else {
                password = pfxPsw.toCharArray();
            }
            inputKeyStore.load(input, password);
            KeyStore outputKeyStore = KeyStore.getInstance(TrustStoreType);
            outputKeyStore.load(null, pfxPsw.toCharArray());
            Enumeration enums = inputKeyStore.aliases();
            while (enums.hasMoreElements()) {
                keyAlias = (String) enums.nextElement();

                System.out.println("alias=[" + keyAlias + "]");

                if (inputKeyStore.isKeyEntry(keyAlias)) {
                    Key key = inputKeyStore.getKey(keyAlias, password);
                    Certificate[] certChain = inputKeyStore.getCertificateChain(keyAlias);
                    outputKeyStore.setKeyEntry(keyAlias, key, pfxPsw.toCharArray(), certChain);
                }
            }
            output = new FileOutputStream(keyStoreFile);
            outputKeyStore.store(output, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * 将keystore转为pfx
     *
     * @param keyStoreFile 生成的文件名和路径
     * @param pfxPsw 密码
     * @param pfxFile 原文件路径及名称
     */
    public static void coverToPfx(String keyStoreFile, String pfxPsw, String pfxFile) throws Exception {
        KeyStore inputKeyStore = null;
        FileInputStream input = null;
        FileOutputStream output = null;
        String keyAlias = "";
        try {
            inputKeyStore = KeyStore.getInstance(TrustStoreType);
            input = new FileInputStream(keyStoreFile);
            char[] password = null;
            if ((pfxPsw == null) || pfxPsw.trim().equals("")) {
                password = null;
            } else {
                password = pfxPsw.toCharArray();
            }
            inputKeyStore.load(input, password);
            KeyStore outputKeyStore = KeyStore.getInstance(PKCS12);
            outputKeyStore.load(null, pfxPsw.toCharArray());
            Enumeration enums = inputKeyStore.aliases();
            while (enums.hasMoreElements()) {
                keyAlias = (String) enums.nextElement();
                System.out.println("alias=[" + keyAlias + "]");
                if (inputKeyStore.isKeyEntry(keyAlias)) {
                    Key key = inputKeyStore.getKey(keyAlias, password);
                    Certificate[] certChain = inputKeyStore.getCertificateChain(keyAlias);
                    outputKeyStore.setKeyEntry(keyAlias, key, pfxPsw.toCharArray(), certChain);
                }
            }
            output = new FileOutputStream(pfxFile);
            outputKeyStore.store(output, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * 使用公钥 进行 非对称加密数据
     * @param certPath
     * @param dataSource
     * @return
     * @throws Exception
     */
    public static String certEncode(String certPath, String dataSource) throws Exception {
        InputStream input = null;
        try {
            byte[] plainText = dataSource.getBytes(CHARSET);
            // 证书格式为x509
            CertificateFactory certificateFactory = CertificateFactory.getInstance(CertType);
            // 读取证书文件的输入流
            input = new FileInputStream(certPath);
            Certificate certificate = certificateFactory.generateCertificate(input);
            // 支持：RSA/ECB/PCKCS1Padding
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.ENCRYPT_MODE, certificate.getPublicKey());
            cipher.update(plainText);
            byte[] signByte = cipher.doFinal();
            String sign = Base64Utils.encode(signByte);
            return sign;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * 使用私钥 进行非对称解密数据
     * @param keyStorePath
     * @param keyStorePass
     * @param alias
     * @param certPass
     * @param decodeData
     * @return
     * @throws Exception
     */
    public static String certDecode(String keyStorePath, String keyStorePass, String alias, String certPass, String decodeData) throws Exception {
        InputStream input = null;
        try {
            // 密文数据Base64转换
            byte[] cipherText = Base64Utils.decode(decodeData);
            // 提供密钥库类型
            KeyStore keyStore = KeyStore.getInstance(TrustStoreType);
            // 读取keystore文件的输入流
            input = new FileInputStream(keyStorePath);
            keyStore.load(input, keyStorePass.toCharArray());
            // 加载证书
            PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, certPass.toCharArray());
            // 支持：RSA/ECB/PCKCS1Padding
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            cipher.update(cipherText);
            byte[] sourceByte = cipher.doFinal();
            String source = new String(sourceByte, CHARSET);
            return source;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return null;

    }

}
