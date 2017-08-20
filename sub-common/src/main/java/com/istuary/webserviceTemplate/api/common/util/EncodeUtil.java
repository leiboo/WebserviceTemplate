package com.istuary.webserviceTemplate.api.common.util;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by lenovo on 2016/11/29.
 */
public class EncodeUtil {

    private static final Logger logger = LoggerFactory.getLogger(EncodeUtil.class);

    public static String encodeBase64(byte[] bytes){
        String encodedString = "";
        try {
            encodedString = new String(Base64.encodeBase64(bytes), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }
        return encodedString;
    }

    public static byte[] decodeBase64(String decodedString){
        byte[] decodedBytes =Base64.decodeBase64(decodedString);
        return decodedBytes;
    }

    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            String raw = new BigInteger(1, md.digest()).toString(16);
            return String.format("%32s", raw).replaceAll(" ", "0");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

}
