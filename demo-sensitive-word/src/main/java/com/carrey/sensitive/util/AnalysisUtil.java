package com.carrey.sensitive.util;


import com.carrey.sensitive.KeyWord;
import com.carrey.sensitive.KeyWordCharacter;
import com.carrey.sensitive.KeyWordWarp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 关键词分析工具类。
 * 
 * @author hailin0@yeah.net
 * @createDate 2016年5月22日
 *
 */
public class AnalysisUtil {

    private static final String PARTIAL_MATCH_NUM = "partialMatchNum";

    /**
     * 构建关键词
     * 
     * @param word 词语
     * @param endTag 属性信息
     * @return
     */
    public static KeyWord getKeyWord(String word, Map<String, Object> endTag) {
        KeyWord tmp = new KeyWord(word);
        return tmp;
    }

    /**
     * 将指定的词构造到一棵树中。
     * 
     * @param tree 构造出来的树
     * @param characters 指定的词的字符对象集合
     * @return
     */
    public static Map<String, Object> makeTreeByWord(Map<String, Object> tree, List<KeyWordCharacter> characters) {
        if (CollectionUtils.isEmpty(characters)) {
            tree.putAll(EndTagUtil.buind());
            return tree;
        }
        KeyWordCharacter character = characters.iterator().next();
        String next = String.valueOf(character.getCharacter());
        Map<String, Object> nextTree = (Map<String, Object>)tree.get(next);
        if (nextTree == null) {
            nextTree = new HashMap<>();
        }
        // 递归构造树结构
        characters.remove(character);
        nextTree.put(PARTIAL_MATCH_NUM,character.getPartialMatchNum());
        tree.put(next, makeTreeByWord(nextTree, characters));
        return tree;
    }

    /**
     * 根据精确、模糊等匹配方式返回相应的实际关键词。
     *
     * @param kw 敏感词
     * @param pre 前缀
     * @param sufix 后缀
     * @return
     */
    private static KeyWord checkPattern(KeyWord kw, String pre, String sufix) {
        if (StringUtils.isNotBlank(kw.getPre()) && StringUtils.isNotBlank(kw.getSufix())) {
            if (null == pre || null == sufix) {
                return null;
            }
            if (!pre.matches(kw.getPre()) || !sufix.matches(kw.getSufix())) {
                return null;
            }
        } else if (StringUtils.isNotBlank(kw.getPre()) && StringUtils.isBlank(kw.getSufix())) {
            if (null == pre) {
                return null;
            }
            if (!pre.matches(kw.getPre())) {
                return null;
            }
        } else if (StringUtils.isBlank(kw.getPre()) && StringUtils.isNotBlank(kw.getSufix())) {
            if (null == sufix) {
                return null;
            }
            if (!sufix.matches(kw.getSufix())) {
                return null;
            }
        }
        return kw;
    }

    /**
     * 查询文本开头的词是否在词库树中，如果在，则返回对应的词，如果不在，则返回null。
     * 
     * @param append 追加的词
     * @param pre 词的前一个字，如果为空，则表示前面没有内容
     * @param nextWordsTree 下一层树
     * @param text 剩余的文本内容
     * @return 返回找到的词
     */
    public static KeyWordWarp getSensitiveWord(String append, String pre,
                                               Map<String, Object> nextWordsTree, String text) {
        if (nextWordsTree == null || nextWordsTree.isEmpty()) {
            return null;
        }
        Integer partialMatchNum = (Integer)nextWordsTree.get(PARTIAL_MATCH_NUM);
        //KMP 移动位数 = 已匹配的字符数 - 对应的部分匹配值
        Integer shift = append.length() - partialMatchNum;
        Map<String, Object> endTag = (Map<String, Object>)nextWordsTree.get(EndTagUtil.TREE_END_TAG);
        // 原始文本已到末尾
        if (StringUtils.isEmpty(text)) {
            // 如果有结束符，则表示匹配成功，没有，则返回null
            if (endTag != null) {
                return new KeyWordWarp(checkPattern(getKeyWord(append, endTag), pre, null));
            } else {
                return new KeyWordWarp(shift);
            }
        }

        String next = text.substring(0, 1);
        String suffix = text.substring(0, 1);
        Map<String, Object> nextTree = (Map<String, Object>)nextWordsTree.get(next);

        // 没有遇到endTag，继续匹配
        if (endTag == null) {
            if (nextTree != null && nextTree.size() > 0) {
                // 没有结束标志，则表示关键词没有结束，继续往下走。
                return getSensitiveWord(append + next, pre, nextTree, text.substring(1));
            }

            // 如果没有下一个匹配的字，表示匹配结束！
            return new KeyWordWarp(shift);
        }

        // 有下一个匹配的词则继续匹配，一直取到最大的匹配关键字
        KeyWordWarp tmp = null;
        if (nextTree != null && nextTree.size() > 0) {
            // 如果大于0，则表示还有更长的词，继续往下找
            tmp = getSensitiveWord(append + next, pre, nextTree, text.substring(1));
            KeyWord tmpKeyWord = tmp.getKeyWord();
            if (tmpKeyWord == null) {
                // 没有更长的词，则就返回这个词。在返回之前，先判断它是模糊的，还是精确的
                tmpKeyWord = getKeyWord(append, endTag);
            }
            return new KeyWordWarp(checkPattern(tmpKeyWord, pre, suffix));
        }

        // 没有往下的词了，返回该关键词。
        return new KeyWordWarp(checkPattern(getKeyWord(append, endTag), pre, suffix));

    }

}
