package com.carrey.sensitive.processor;

import com.carrey.sensitive.KeyWord;
import com.carrey.sensitive.KeyWordWarp;
import com.carrey.sensitive.util.AnalysisUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 对文本进行高亮处理。
 * 
 * @author hailin0@yeah.net
 * @createDate 2016年5月22日
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class Highlight implements Processor {
    /**
     * 将文本中的关键词提取出来。
     * 
     * @param wordsTree 关键词库树
     * @param text 待处理的文本
     * @return 返回提取的关键词或null
     */
    @Override
    public String process(Map<String, Object> wordsTree, String text, AbstractFragment fragment,
            int minLen) {
        StringBuffer result = new StringBuffer("");
        // 词的前面一个字
        String pre = null;
        //KMP 移动位数
        int shift = 0;
        while (true) {
            if (wordsTree == null || wordsTree.isEmpty() || StringUtils.isEmpty(text)) {
                return result.append(text).toString();
            }
            if (text.length() < minLen) {
                return result.append(text).toString();
            }
            String chr = text.substring(0 + shift, 1 + shift);
            text = text.substring(1 + shift);
            Map<String, Object> nextWord = (Map<String, Object>)wordsTree.get(chr);
            // 没有对应的下一个字，表示这不是关键词的开头，进行下一个循环
            if (nextWord == null) {
                result.append(chr);
                pre = chr;
                continue;
            }

            KeyWordWarp kww = AnalysisUtil.getSensitiveWord(chr, pre, nextWord, text);
            KeyWord kw = kww.getKeyWord();
            // 没有匹配到完整关键字，下一个循环
            if (kw == null) {
                // 由于数组下标从0开始，所以相应的数组的移动位数应该是n-1相当于数组元素移动了n位
                shift = kww.getShift() - 1;
                String substring = text.substring(0, shift);
                pre = substring.substring(substring.length() - 1);
                result.append(chr);
                result.append(substring);
                continue;
            }
            shift = 0;
            // 处理片段
            result.append(fragment.format(kw));
            // 从text中去除当前已经匹配的内容，进行下一个循环匹配
            text = text.substring(kw.getWordLength() - 1);
            pre = kw.getWord().substring(kw.getWordLength() - 1, kw.getWordLength());
            continue;
        }
    }

}
