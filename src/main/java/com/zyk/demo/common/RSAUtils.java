package com.zyk.demo.common;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.IOException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA : 非对称加密算法
 * 可以【公钥加密 私钥解密】 也可以【私钥加密 公钥解密】 , 一般用前者
 * 对于签名: 必须私钥签名, 用公钥验签
 */
public class RSAUtils {

    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";


    /**
     * base64编码
     * @param bytes
     * @return
     */
    public static String encryptBASE64(byte [] bytes){
        return new BASE64Encoder().encode(bytes);
    }

    /**
     * base64解码
     * @param str
     * @return
     */
    public static byte[] decryptBASE64(String str){
        try {
            return new BASE64Decoder().decodeBuffer(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 初始化密钥
     * @return
     * @throws Exception
     */
    public static Map<String,Key> initKey() throws Exception{
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        Map<String,Key> keyMap = new HashMap<String, Key>(2);
        keyMap.put(PUBLIC_KEY,keyPair.getPublic()); // 公钥
        keyMap.put(PRIVATE_KEY,keyPair.getPrivate());// 私钥
        return keyMap;
    }

    /**
     * 获取公钥
     * @param map
     * @return
     */
    public static String getPublicKey(Map<String,Key> map){
        Key publicKey = map.get(PUBLIC_KEY);
        return encryptBASE64(publicKey.getEncoded());
    }

    /**
     * 获取私钥
     * @param map
     * @return
     */
    public static String getPrivateKey(Map<String,Key> map){
        Key privateKey = map.get(PRIVATE_KEY);
        return encryptBASE64(privateKey.getEncoded());
    }

    /**
     * 用公钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(String data, String key) throws Exception {
        // 对公钥解码
        byte [] keyBytes = decryptBASE64(key);
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        // 获取cipher
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        // 初始化cipher
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        return cipher.doFinal(data.getBytes());
    }


    /**
     * 用公钥解密
     *
     * @param data(用私钥加密后的数据)
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublishKey(byte[] data, String key) throws Exception {
        // 对公钥解码
        byte [] keyBytes = decryptBASE64(key);
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        // 获取cipher
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        // 初始化cipher
        cipher.init(Cipher.DECRYPT_MODE,publicKey);
        return cipher.doFinal(data);
    }


    /**
     * 用私钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(String data, String key) throws Exception{
        // 对私钥解码
        byte[] keyByes = decryptBASE64(key);
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec  = new PKCS8EncodedKeySpec(keyByes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 获取cipher
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        // 初始化cipher
        cipher.init(Cipher.ENCRYPT_MODE,privateKey);
        return cipher.doFinal(data.getBytes());
    }

    /**
     * 用私钥解密
     *
     * @param data(用公钥加密后的数据)
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data,String key) {
        try {

            // 对私钥解码
            byte[] keyBytes = decryptBASE64(key);
            // 获得私钥
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

            // 获取cipher
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            // 初始化cipher
            cipher.init(Cipher.DECRYPT_MODE,privateKey);
            return cipher.doFinal(data);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用私钥对信息生成数字签名(sign)
     * 只能用私钥对信息生成数字签名
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data,String privateKey) throws Exception{
        // 解码由base64编码的私钥
        byte[] keyBytes = decryptBASE64(privateKey);

        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        // 使用 KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 获取私钥对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);
        return encryptBASE64(signature.sign());
    }

    /**
     * 用公钥校验数字签名
     *
     * @param data
     * @param publicKey
     * @param sign
     * @return
     * @throws Exception
     */
    public static Boolean verify(byte[] data, String publicKey, String sign) throws  Exception{
        // 对公钥base64解码
        byte[] keyBytes = decryptBASE64(publicKey);
        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        // 使用 KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 获得公钥对象
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);

        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(data);
        return signature.verify(decryptBASE64(sign));
    }

    public static void main(String[] args) {

        try {
            Map<String,Key> keyMap = initKey();
            String publicKey = getPublicKey(keyMap);
            String privateKey = getPrivateKey(keyMap);

           // System.out.println(PUBLIC_KEY +"  = " + publicKey);
           // System.out.println(PRIVATE_KEY + " = " + privateKey);

            String content = "this is 测试！！";
            byte[] encryptByPublicKey = encryptByPublicKey(content,publicKey); //公钥加密
            byte[] encryptByPrivateKey = encryptByPrivateKey(content,privateKey);// 私钥加密

            byte[] decryptByPublicKey = decryptByPublishKey(encryptByPrivateKey,publicKey);//公钥解密(参数是私钥加密的密文)
            byte[] decryptByPrivateKey = decryptByPrivateKey(encryptByPublicKey,privateKey);//私钥解密(参数是公钥加密的密文)


            System.out.println("---------------公钥加密私钥解密过程begin-----------");
            System.out.println("原文：" + content);
            System.out.println("公钥加密base64：" + new String(encryptBASE64(encryptByPublicKey)));
            System.out.println("私钥解密：" + new String(decryptByPrivateKey));
            String priSign = sign(encryptByPrivateKey,privateKey);
            System.out.println("私钥加密数字签名sign：" + priSign);
            System.out.println("公钥校验结果：" + verify(encryptByPrivateKey,publicKey,priSign));
            System.out.println("---------------公钥加密私钥解密过程end-----------");

            System.out.println("---------------私钥加密公钥解密begin-----------");
            System.out.println("原文：" + content);
            System.out.println("私钥加密base64：" + new String(encryptBASE64(encryptByPrivateKey)));
            System.out.println("公钥解密：" + new String(decryptByPublicKey));
            System.out.println("暂时没有公钥加密数字签名,用私钥解密的方法");
            System.out.println("---------------私钥加密公钥解密过程end-----------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
