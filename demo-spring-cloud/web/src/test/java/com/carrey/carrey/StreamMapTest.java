package com.carrey.carrey;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.experimental.Accessors;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * java8映射练习测试
 */
public class StreamMapTest {

  @Test
  public void mapTest() throws JsonProcessingException {
    List<Excel> excels = Lists.newArrayList();
    //初始化exces值
    initExcelList(excels);
    //合并excels
    //Excel excel = mergeExcel(excels);
    System.out.println(new ObjectMapper().writeValueAsString(excels));
  }

  private Excel mergeExcel(List<Excel> excels) {
    //将excels合并成一个Excel，并且将sheet按照序号的倒叙排列
    List<Sheet> sheets = excels.stream()
        .map(Excel::getSheetList)
        .flatMap(List::stream)
        .sorted(Comparator.comparing(Sheet::getIndex).reversed())
        .collect(Collectors.toList());
    return new Excel().setSheetList(sheets);
  }
  /**
   * 这只是个初始化值的demo，可以忽略.
   * @param excels
   */
  private void initExcelList(List<Excel> excels) {
    //创建三个ExcelDemo
    int val = 0;
    for (int i = 1;i <=3; i++) {
      List<Sheet> sheets = Lists.newArrayList();

      //每个Demo里添加四个Sheet
      for(int j = 1;j <= 4; j++) {
        sheets.add(new Sheet().setIndex(val + j));
      }

      excels.add(new Excel().setSheetList(sheets));
      val += 4;
    }
  }

  @Data
  @Accessors(chain = true)
  class Excel {
    /**
     * sheet页面集合.
     */
    private List<Sheet> sheetList;
  }

  @Data
  @Accessors(chain = true)
  class Sheet {
    /**
     * sheet页的序号.
     */
    private Integer index;
  }
}
