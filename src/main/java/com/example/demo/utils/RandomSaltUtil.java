package com.example.demo.utils;

//MD5码加密

import java.util.LinkedHashSet;
import java.util.Random;

public class RandomSaltUtil {

    public static String getSalt(Integer size) {
        return getSalt("$1$", size);
    }

    public static String getSalt(String head, Integer size) {
        Random random = new Random();
        String chs = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        int len = chs.length();
        LinkedHashSet<Character> set = new LinkedHashSet<>();

        //每一位字符不重复
        while(set.size() < size) {
            set.add(chs.charAt(random.nextInt(len)));
        }

        for(Character c : set) {
            head += c;
        }
        return head;
    }
}
