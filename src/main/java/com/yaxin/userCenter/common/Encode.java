package com.yaxin.userCenter.common;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;

public class Encode {
    public static String base64(String str) throws Exception {
        byte[] encodeBase64 = Base64.encodeBase64(str.getBytes("UTF-8"));
        return encodeBase64.toString();
    }

    public static String stringToMD5(String str) {
        byte[] input = str.getBytes();
        String md5str = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buff = md.digest(input);
            md5str = bytesToHex(buff);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5str;
    }


    //把数组每一字节换成16进制连成md5字符串
    private static String bytesToHex(byte[] bytes) {
        StringBuffer md5str = new StringBuffer();
        int digital;
        for (int i = 0; i < bytes.length; i++) {
            digital = bytes[i];
            if (digital < 0) {
                digital += 256;
            }
            if (digital < 16) {
                md5str.append("0");
            }
            md5str.append(Integer.toHexString(digital));
        }
        return md5str.toString();
    }
}
