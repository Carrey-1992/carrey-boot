package com.carrey.carrey.mybatis;

import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {

  /**
   * 创建人主键.
   */
  @CreateBy
  private Long createBy;
  /**
   * 更新人主键.
   */
  @UpdateBy
  private Long updateBy;
  /**
   * 创建时间.
   */
  @CreateAt
  private Date createAt;
  /**
   * 更新时间.
   */
  @UpdateAt
  private Date updateAt;
}
