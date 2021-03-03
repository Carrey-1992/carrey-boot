package com.carrey.demosharingsphere.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Conway
 * @className Order
 * @description
 * @date 2021/3/2 3:44 下午
 */
@Data
public class Order {
    /**
     * 主键
     */
    private Long orderId;

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 订单金额
     */
    private BigDecimal orderMoney;

    /**
     * 订单描述
     */
    private String orderDesc;

    /**
     * 启用状态
     */
    private Integer enableState;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private String updateBy;

}
