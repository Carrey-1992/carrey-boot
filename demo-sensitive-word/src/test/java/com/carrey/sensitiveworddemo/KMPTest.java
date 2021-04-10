package com.carrey.sensitiveworddemo;

import com.carrey.sensitive.util.KmpUtils;
import org.junit.jupiter.api.Test;

/**
 * @author Conway
 * @className KMPTest
 * @description
 * @date 2020/7/24 7:24 下午
 */
public class KMPTest {

    @Test
    public void test() {
        String a = "abaacaab";
        String b = "ssdfgasdbababa";
        int[] partialMatchTable1 = KmpUtils.createPartialMatchTable(a);
        System.out.println();
    }
}
