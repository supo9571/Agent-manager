package com.manager.common.utils.file;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author marvin 2021/9/1
 * 获取解密map
 */
public class DecodeMap {

    private static Map<String,String> decodeMap;


    private static String decodeStr = "{\"N2ZjOTdj\":\"src\",\"NmNiOTJm\":\"game\",\"NDQyMjY1\":\"lobby\",\"YzMzN2Qx\":\"update\"," +
            "\"OGJlMjU5\":\"bbd\",\"M2IxNTFh\":\"bjl\",\"NjI2Nzgw\":\"brnn\",\"MmNjMTcy\":\"bxjg\",\"YTg0Y2Iw\":\"ddz\",\"NTc1M2U5\":\"dzpk\"," +
            "\"ODI1Mzky\":\"ebg\",\"ZTAxN2Nh\":\"ermj\",\"NjIyNmQz\":\"ghz\",\"YmExZTlh\":\"hbsl\",\"NGE3Zjg4\":\"hhdz\",\"NDU5MDQ5\":\"hjk\"," +
            "\"Y2QyZWMx\":\"hlxd\",\"YTE2NTc4\":\"jdqs\",\"NjFjMGRh\":\"jxqy\",\"NzE0ZWQz\":\"jzhw\",\"MjI2MTRh\":\"kpqz\",\"YjdjZWNm\":\"lhdz\"," +
            "\"MmVmYmY1\":\"mssj\",\"NWM1MDBk\":\"pdsgj\",\"MTAyZGFh\":\"psz\",\"NDQ1MTkw\":\"rummy\",\"ZjhiODA2\":\"sgj\",\"YzhkOGIz\":\"shjx\"," +
            "\"YTE1YWQ1\":\"teen_patti\",\"ZDliMGM3\":\"wrzjh\",\"MDhhODQ1\":\"wzry\",\"M2ZhMWIz\":\"res\",\"Nzk1ZTc1\":\"animation\",\"NjVhM2Rk\":" +
            "\"labelatlas\",\"MTI3MjY3\":\"sound\",\"Mjk1YjM5\":\"version.manifest\",\"ZWIwZGU2\":\"game_versions\"}";
    /**
     * 获取下载路径
     */
    public static Map<String,String> decodeMap() {
        if(decodeMap==null){
            JSONObject jsonObject = JSONObject.parseObject(decodeStr);
            decodeMap = jsonObject.toJavaObject(Map.class);
        }
        return decodeMap;
    }
}
