package com.fans.utils;

import java.nio.charset.Charset;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/**
 * @ClassName HashUtils
 * @Description: 加密工具
 * @Author fan
 * @Date 2019-04-04 14:30
 * @Version 1.0
 **/
public class HashUtils {

    private static final HashFunction FUNCTION = Hashing.sha256();

    private static final String SALT = "kapok";

    public static String encryPassword(String password) {
        HashCode hashCode = FUNCTION.hashString(password + SALT, Charset.forName("UTF-8"));
        return hashCode.toString();
    }

}
