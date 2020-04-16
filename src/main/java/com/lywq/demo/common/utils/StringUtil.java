package com.lywq.demo.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.UUID;

/**
 * @author lywq WED
 * @title: StringUtil
 * @projectName demo
 * @description: 字符串操作工具
 * @date 2019/12/7 11:13
 */
public class StringUtil {
    /**
     * 截取字符串
     *
     * @param str   待截取的字符串
     * @param start 截取起始位置 （ 1 表示第一位 -1表示倒数第1位）
     * @param end   截取结束位置 （如上index）
     * @return
     */
    public static String sub(String str, int start, int end) {
        String result = null;

        if (str == null || "".equals(str)) {
            return "";
        }

        int len = str.length();
        start = start < 0 ? len + start : start - 1;
        end = end < 0 ? len + end + 1 : end;

        return str.substring(start, end);
    }

    /**
     * 将字符串str的格式转为utf-8
     *
     * @param str
     * @return
     */
    public static String toUTF_8(String str) {
        String result = null;
        try {
            if (StringUtils.isEmpty(str)) {
                return str;
            }
            result = new String(str.getBytes("iso-8859-1"), "utf-8");
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 若str字符串以tag结束则剔除tag
     *
     * @param str 待剔除的字符串
     * @param tag 要剔除的标签
     * @return 剔除后的字符串
     * @throws Exception
     */
    public static String trimEnd(String str, String tag) throws Exception {
        String result = str;
        if (str == null || "".equals(str)) {
            return str;
        }
        if (tag == null || "".equals(tag)) {
            throw new Exception("参数tag 不能为null或‘’ ");
        }

        int tagPosition = str.lastIndexOf(tag);
        if (tagPosition + tag.length() == str.length()) {
            result = str.trim().substring(0, tagPosition);
        }
        return result;
    }

    /**
     * 截取指定最后一个字符后面的内容
     *
     * @param str 待截取的字符串
     * @param tag 指定的字符标签
     * @return
     */
    public static String subLastTag(String str, String tag) {
        String result = null;

        if (str == null || "".equals(str)) {
            return "";
        }

        result = str.substring(str.lastIndexOf(tag) + 1);

        return result;
    }

    /**
     * 产生一个36个字符的UUID
     *
     * @return UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 产生一个32个字符的UUID
     *
     * @return UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取多少位随机数
     *
     * @param num
     * @return
     */
    public static String getNumStringRandom(int num) {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        //随机生成数字，并添加到字符串
        for (int i = 0; i < num; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

    /**
     * 获取区间内的随机数
     *
     * @param min
     * @param max
     * @return
     */
    public static int getRandomBetween(int min, int max) {
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }
}
