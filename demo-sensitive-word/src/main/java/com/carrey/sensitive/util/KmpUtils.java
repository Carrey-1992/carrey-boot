package com.carrey.sensitive.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Conway
 * @className KmpUtils
 * @description
 * @date 2020/7/24 7:22 下午
 */
public class KmpUtils {

    /**
     * 根据 pattern 字符串 创造出对应的部分匹配表
     *
     * @param pattern
     * @return
     */
    public static int[] createPartialMatchTable(String pattern) {
        if (StringUtils.isBlank(pattern)) {
            return new int[0];
        }

        char[] chars = pattern.toCharArray();
        String[] strs = new String[chars.length];
        StringBuilder sb = new StringBuilder();
        String str0 = String.valueOf(chars[0]);
        sb.append(str0);
        strs[0] = sb.toString();
        for (int i = 1; i < chars.length ; i++) {
            sb.append(chars[i]);
            strs[i] = sb.toString();
        }

        int[] matchTable = new int[chars.length];

        for (int i = 0; i<strs.length; i++) {
            String str = strs[i];
            int length = str.length();
            String preFixStr = str.substring(0, length - 1);
            String suffFixStr = str.substring(1);


            while (preFixStr.length() > 0 && suffFixStr.length() > 0) {
                if (preFixStr.equals(suffFixStr)) {
                    matchTable[i] = preFixStr.length();
                    break;
                }

                preFixStr = preFixStr.substring(0, preFixStr.length() - 1);
                suffFixStr = suffFixStr.substring(1);
            }

        }
        return matchTable;
    }

}
