package com.carrey.sensitive;

/**
 * @author Conway
 * @className KeyWordWarp
 * @description
 * @date 2020/7/24 11:47 下午
 */
public class KeyWordWarp {
    /**
     * 关键词
     */
    private KeyWord keyWord;

    /**
     * KMP算法 移动位数 = 已匹配的字符数 - 对应的部分匹配值
     */
    private int shift;

    public KeyWordWarp(KeyWord keyWord, int shift) {
        this.keyWord = keyWord;
        this.shift = shift;
    }

    public KeyWordWarp(int shift) {
        this.shift = shift;
    }

    public KeyWordWarp(KeyWord keyWord) {
        this.keyWord = keyWord;
    }

    public int getShift() {
        return shift;
    }

    public KeyWord getKeyWord() {
        return keyWord;
    }
}
