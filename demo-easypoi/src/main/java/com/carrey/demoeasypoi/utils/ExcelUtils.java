package com.carrey.demoeasypoi.utils;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.carrey.demoeasypoi.bo.ExportView;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

  /**
   * 写出Excel.
   */
  public static void writeExcel(HttpServletRequest request, HttpServletResponse response,
                                Workbook wb, String fileName) throws IOException {
    String agent = request.getHeader("USER-AGENT");
    if (agent != null && agent.indexOf("Firefox") != -1) {
      // 火狐
      fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
    } else {
      // 其他
      fileName = URLEncoder.encode(fileName, "UTF-8");
    }
    // 重置响应对象
    response.reset();
    // 指定下载的文件名--设置响应头
    response.setHeader("content-Type", "application/vnd.ms-excel");
    response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
    // 写出数据输出流到页面
    OutputStream os = response.getOutputStream();
    BufferedOutputStream bos = new BufferedOutputStream(os);
    wb.write(bos);
    bos.flush();
    bos.close();
    os.close();
  }

  /**
   * 获取Excel字节.
   */
  public static byte[] getWorkbookBytes(Workbook wb) throws IOException {
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    wb.write(os);
    return os.toByteArray();
  }

  /**
   * 写入Excel.
   * @param <T>
   * @return
   */
  public static <T> List<T> readExcel(String fileName,Class<T> tClass) {
    ImportParams params = new ImportParams();
    //params.setTitleRows(1);
    params.setHeadRows(1);
    return ExcelImportUtil.importExcel(new File(fileName), tClass, params);
  }

  /**
   * Excel文件名称结尾.
   */
  public static final String XLSX = ".xlsx";


  private static List<Map<String, Object>> getExcelMap(List<ExportView> moreViewList) {
    List<Map<String, Object>> exportParamList = Lists.newArrayList();
    for (ExportView view : moreViewList) {
      Map<String, Object> valueMap = Maps.newHashMap();
      valueMap.put("title", view.getExportParams());
      valueMap.put(NormalExcelConstants.DATA_LIST, view.getDataList());
      valueMap.put(NormalExcelConstants.CLASS, view.getCls());
      exportParamList.add(valueMap);
    }
    return exportParamList;
  }


  /**
   * 导出多sheet页excel文件
   * @param moreViewList
   * @return
   */
  public static Workbook writeExcels(List<ExportView> moreViewList) {
    List<Map<String, Object>> excelMap = getExcelMap(moreViewList);
    return ExcelExportUtil.exportExcel(excelMap, ExcelType.HSSF);
  }

}
