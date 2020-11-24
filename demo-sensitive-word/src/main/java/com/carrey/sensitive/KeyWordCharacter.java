package com.carrey.sensitive;

import java.io.Serializable;

/**
 * @author Conway
 * @className Character
 * @description 关键词字符对象
 * @date 2020/7/24 7:41 下午
 */
public class KeyWordCharacter implements Serializable {

    /**
     * 字符
     */
    private char character;

    /**
     * KMP算法 部分匹配数
     */
    private int partialMatchNum;

    public KeyWordCharacter(char character, int partialMatchNum) {
        this.character = character;
        this.partialMatchNum = partialMatchNum;
    }

    public char getCharacter() {
        return character;
    }

    public int getPartialMatchNum() {
        return partialMatchNum;
    }
}
