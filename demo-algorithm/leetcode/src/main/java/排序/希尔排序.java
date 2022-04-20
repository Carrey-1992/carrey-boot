package 排序;

import com.sun.tools.javac.util.List;

/**
 * @author Carrey
 * @className 希尔排序
 * @description 希尔排序
 * @date 2022/4/20 9:19 PM
 */
public class 希尔排序 {

    public static void main(String[] args) {
        Integer[] array = new Integer[]{6, 9, 4, 3, 5, 8, 2, 1, 10};
        Integer[] sortedArray = sorted(array);
        System.out.println("排序结果为：" + List.from(sortedArray).toString());
    }

    public static Integer[] sorted(Integer[] paramArray) {
        if (null == paramArray || paramArray.length == 1) {
            return paramArray;
        }
        System.out.println("数组的长度为：" + paramArray.length);
        for (int gap = paramArray.length / 2; gap > 0; gap /= 2) {
            for (int i = 0; i < paramArray.length - gap; i++) {
                int a = paramArray[i];
                int b = paramArray[i + gap];
                if (paramArray[i] > paramArray[i + gap]) {
                    paramArray[i] = b;
                    paramArray[i + gap] = a;
                }
            }
            System.out.println("希尔排序的增量为:" + gap + ",当前替换结果为：" + List.from(paramArray).toString());
        }
        System.out.println("希尔排序的替换结果为：" + List.from(paramArray).toString() + "，开始进入插入排序。");
        return 插入排序.sorted(paramArray);
    }

}
