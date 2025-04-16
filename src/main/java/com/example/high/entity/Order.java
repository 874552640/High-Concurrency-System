package com.example.high.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Order {
    /**
     * 订单ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 订单状态：0-待支付，1-已支付，2-已发货，3-已完成，4-已取消
     */
    private Integer status;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人电话
     */
    private String receiverPhone;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
} 