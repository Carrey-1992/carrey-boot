package com.carrey.sensitiveworddemo.kmp;

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

        int patternLen = pattern.length();
        int[] matchTable = new int[patternLen];

        int i = 0;
        int matchValue = 0;
        while (i < patternLen) {
            if (i == 0) {
                matchValue = 0;
            } else {
                matchValue = calcMatchValue(pattern.substring(0, i + 1));
            }

            matchTable[i] = matchValue;
            i++;
        }

        return matchTable;
    }
    /**
     * 根据 subStr 字符串 来计算出对应的部分匹配值
     *
     * @param subStr
     * @return
     */
    private static int calcMatchValue(String subStr) {

        int length = subStr.length();
        String preFixStr = subStr.substring(0, length - 1);
        String suffFixStr = subStr.substring(1);

        while (preFixStr.length() > 0 && suffFixStr.length() > 0) {
            if (preFixStr.equals(suffFixStr)) {
                return preFixStr.length();
            }

            if (preFixStr.length() == 1 && suffFixStr.length() == 1) {
                break;
            }
            preFixStr = preFixStr.substring(0, preFixStr.length() - 1);
            suffFixStr = suffFixStr.substring(1, suffFixStr.length());
        }

        return 0;
    }
}
