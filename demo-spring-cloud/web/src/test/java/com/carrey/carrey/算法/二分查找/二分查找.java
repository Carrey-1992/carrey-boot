package com.carrey.carrey.算法.二分查找;

import org.junit.Test;

/**
 * @author Carrey
 * @version 0.0.1
 * @description 二分查找
 * @create 2019-12-26 15:06
 */
public class 二分查找 {
  public int bsearch(int[] array,int param) {
    int low = 0;
    int hight = array.length - 1;
    return bsearchInternally(array,hight,low,param);
  }


  public int bsearchInternally(int[] array,int hight,int low,int param) {
    if (low <= hight) {
      //防止溢出
      int index = low + (hight - low)/2;
      if (array[index] == param ) {
        return index;
      }

      if (array[index] > param) {
        hight = index - 1;
      }else if (array[index] < param) {
        low = index + 1;
      }
    }
    return bsearchInternally(array,hight,low,param);
  }

  @Test
  public void test() {
    int[] ints = new int[]{1,2,3,4,5,6,7,8,9,10};
    int param = 6;
    int bsearch = bsearch(ints, param);
    System.out.println(bsearch);
  }
}
