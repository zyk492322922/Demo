package com.zyk.demo.common;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * DES 对称加解密   密码必须是8的倍数位但是不能是8，如 16,24
 * [加密后是byte字节数组，加密后的字节数组无法用new String(byte)转化成String,所以一般加密后,用base64编码]
 */
public class DESUtils {


    private static final String DES_TYPE = "DES";
    /**
     *  加密算法： 加密后, 用base64加密
     *
     * @param source
     * @param passKey
     * @return
     */
    public static String encrypt(String source , String passKey){
        try {
            //DES算法需要有一个可信任的随机数源
            SecureRandom random = new SecureRandom();
            //创建一个DESKeySpec对象
            DESKeySpec desKey = new DESKeySpec(passKey.getBytes("UTF-8"));
            //创建DES密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_TYPE);
            SecretKey secretKey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance(DES_TYPE);
            // 用密匙处初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE,secretKey,random);
            // 真实的加密操作
            byte [] bytes = cipher.doFinal(source.getBytes());
            return new BASE64Encoder().encode(bytes);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密算法
     * @param src
     * @param passKey
     * @return
     */
    public static String decrypt(String src, String passKey){
        try {
            //DES算法需要有一个可信任的随机数源
            SecureRandom secureRandom = new SecureRandom();
            //创建一个DESKeySpec对象
            DESKeySpec desKey = new DESKeySpec(passKey.getBytes());
            // 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_TYPE);
            // 通过 DESKeySpec 转化成 SecretKey
            SecretKey secretKey = keyFactory.generateSecret(desKey);
            // 创建一个Cipher对象
            Cipher cipher = Cipher.getInstance(DES_TYPE);
            // 初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE,secretKey,secureRandom);
            // 真实的解密操作
            byte [] bytes = cipher.doFinal(new BASE64Decoder().decodeBuffer(src));
            return new String(bytes);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String content = "this is 测试";
        String desKey = "afhyujikolkmbgty";

        System.out.println("加密前："+content);
        String result = encrypt(content,desKey);

        System.out.println("加密后：" + result);
        String originStr = decrypt(result,desKey);
        System.out.println("重新解密后："+ originStr);
    }
}
