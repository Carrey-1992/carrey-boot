package com.carrey.demoeasypoi.bo;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ExportView {

  /**
   * 对应注解 class 实例对象的数据集合
   */
  private ExportParams exportParams;
  /**
   * 对应注解的 class
   */
  private List<?> dataList;

  private Class<?> cls;

}
