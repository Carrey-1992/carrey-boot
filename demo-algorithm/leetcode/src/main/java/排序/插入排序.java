package 排序;

import com.sun.tools.javac.util.List;

/**
 * @author Carrey
 * @className 插入排序
 * @description 插入排序
 * @date 2022/4/15 3:20 PM
 * [6,9,4,3,5,8]
 * [3,4,6,9,5,8]
 * [3,4,6,6,9,8]
 */
public class 插入排序 {

    public static void main(String[] args) {
        Integer[] array = new Integer[]{6,9,4,3,5,8};
        Integer[] sortedArray = sorted(array);
        System.out.println("排序结果为："+ List.from(sortedArray).toString());
    }

    public static Integer[] sorted(Integer[] paramArray) {
        if (null == paramArray || paramArray.length <= 1) {
            //数组元素只有一个则不需要排序
            return paramArray;
        }

        for (int i = 1; i < paramArray.length; i++) {
            Integer thisVal = paramArray[i];
            int j = i - 1;
            for (; j >=0; j--) {
                if (thisVal < paramArray[j]) {
                    paramArray[j+1] = paramArray[j];
                } else {
                    break;
                }
            }

            paramArray[j+1] = thisVal;

            System.out.println(List.from(paramArray).toString());
        }
        return paramArray;
    }

}
