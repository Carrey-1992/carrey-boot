package com.carrey.demoshardingsphere.po;

import lombok.Data;

import java.util.Date;

/**
 * @author Conway
 * @className OrderItem
 * @description
 * @date 2021/3/2 3:49 下午
 */
@Data
public class OrderItem {

    /**
     * 订单详情主键
     */
    private Long orderItemId;

    /**
     * 订单主键
     */
    private Long orderId;

    /**
     * 商品编号
     */
    private String productNo;

    /**
     * 商品数量
     */
    private String productCount;

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
