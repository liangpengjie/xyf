package com.xyf.common;

import org.springframework.util.DigestUtils;

/**
 * @author 木木
 * @date 2018/8/30 09:59
 * @description
 */
public class MD5Utils {

    /**
     * MD5加密
     * @param password
     * @return
     */
    public static String encryptMD5(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
