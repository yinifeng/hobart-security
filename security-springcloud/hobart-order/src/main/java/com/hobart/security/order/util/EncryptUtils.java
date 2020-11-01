package com.hobart.security.order.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptUtils {
    
    public static String encodeBase64(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static byte[] decodeBase64(String str){
        return Base64.getDecoder().decode(str);
    }

    public static String encodeUTF8StringBase64(String str){
        return Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
    }

    public static String decodeUTF8StringBase64(String str){
        byte[] bytes = Base64.getDecoder().decode(str);
        return new String(bytes,StandardCharsets.UTF_8);
    }
}
