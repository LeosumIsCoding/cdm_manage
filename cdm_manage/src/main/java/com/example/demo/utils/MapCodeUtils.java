package com.example.demo.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapCodeUtils {

    private static Map<String, List> codeMap = new HashMap();

    //    设置验证码
    public static void putCode(String key, String code) {
//        将验证码包装成list集合
        List codeList = new ArrayList();

        codeList.add(code);
//        当前时间的毫秒值
        codeList.add(System.currentTimeMillis());

//        设置完成验证码
        codeMap.put(key, codeList);
    }

    //    获取验证码
    public static String getCode(String key) {
//        判断key值是否存在
        if (codeMap.containsKey(key)) {
            List valueCode = codeMap.get(key);
            String code = (String) valueCode.get(0);  //获取验证码
//          当初设置的时间
            long setTime = (long) valueCode.get(1);

//  《验证码，时间》

//          判断时效性
            long nowTime = System.currentTimeMillis();

//            判断是否过期  大于五分钟
            if (nowTime - setTime > 5 * 60 * 1000) {
//                验证码过期
                codeMap.remove(key);
                return null;
            }
            return code;
        }

//        当前的key对应的验证码不存在
        return null;
    }

    //    移除验证码
    public static void removeCode(String key) {
        codeMap.remove(key);
    }

}
