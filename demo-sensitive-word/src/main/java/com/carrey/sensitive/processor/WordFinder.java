package com.carrey.sensitive.processor;

import com.carrey.sensitive.KeyWord;
import com.carrey.sensitive.KeyWordWarp;
import com.carrey.sensitive.SensitiveWordResult;
import com.carrey.sensitive.util.AnalysisUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 对文本中的关键词进行提取。主要根据关键词对文本中的关键词进行提取！
 * 
 * @author hailin0@yeah.net
 * @createDate 2016年5月22日
 *
 */
public class WordFinder implements Processor {

    /**
     * 将文本中的关键词提取出来。
     */
    @Override
    public List<SensitiveWordResult> process(Map<String, Object> wordsTree, String text,
                                             AbstractFragment fragment, int minLen) {
        // 词的前面一个字
        String pre = null;
        // 词匹配的开始位置
        int startPosition = 0;
        int startPositionShift = 0;
        // 返回结果
        List<SensitiveWordResult> rs = new ArrayList<SensitiveWordResult>();
        //KMP 移动位数
        int shift = 0;
        while (true) {
            try {
                if (wordsTree == null || wordsTree.isEmpty() || StringUtils.isEmpty(text)) {
                    return rs;
                }
                if (text.length() < minLen) {
                    return rs;
                }
                String chr = text.substring(0 + shift, 1 + shift);
                text = text.substring(1 + shift);
                Map<String, Object> nextWord = (Map<String, Object>)wordsTree.get(chr);
                // 没有对应的下一个字，表示这不是关键词的开头，进行下一个循环
                if (nextWord == null) {
                    pre = chr;
                    //移动位数归零
                    shift = 0;
                    continue;
                }
                KeyWordWarp kww = AnalysisUtil.getSensitiveWord(chr, pre, nextWord, text);
                KeyWord kw = kww.getKeyWord();
                if (kw == null) {
                    // 由于数组下标从0开始，所以相应的数组的移动位数应该是n-1相当于数组元素移动了n位
                    shift = kww.getShift() - 1;
                    // 没有匹配到完整关键字，下一个循环
                    pre = text.substring(0 + shift - 1, shift);
                    continue;
                }
                //移动位数归零
                startPositionShift = shift;
                shift = 0;
                // 同一个word多次出现记录在一起
                SensitiveWordResult result = new SensitiveWordResult(startPosition, kw.getWord());
                int index = rs.indexOf(result);
                if (index > -1) {
                    rs.get(index).addPosition(startPosition, kw.getWord());
                } else {
                    rs.add(result);
                }
                // 从text中去除当前已经匹配的内容，进行下一个循环匹配
                text = text.substring(kw.getWordLength() - 1);
                pre = kw.getWord().substring(kw.getWordLength() - 1, kw.getWordLength());
                continue;
            } finally {
                if (pre != null) {
                    startPosition = startPosition + pre.length() + startPositionShift;
                }
            }

        }
    }
}
