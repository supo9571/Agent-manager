package com.manager.common.utils.google;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

/**
 * 描述:谷歌验证器工具类
 *
 */

public class GoogleAuth {
    public static void main(String[] args) {
        String code = "930573";
        boolean isPattern = isPattern("FYAAK2VSJSDJKCAH",code);
        getKeyStr();
        System.out.println(isPattern);
    }
    /**
     * 判断输入的验证码是否符合
     * @param key     密钥
     * @param code   验证码
     * @return
     */
    public static boolean isPattern(String key,String code) {
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        boolean isPattern = gAuth.authorize(key,Integer.valueOf(code));
        return isPattern;
    }

    /**
     * 获得为用户随机生成的密钥
     * @return
     */
    public static String getKeyStr() {
        GoogleAuthenticator gAuth  = new GoogleAuthenticator();
        final GoogleAuthenticatorKey key = gAuth.createCredentials();
        String keyStr = key.getKey();
        System.out.println(keyStr);
        return keyStr;
    }
}
