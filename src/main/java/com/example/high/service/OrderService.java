package com.example.high.service;

import com.example.high.entity.Order;
import com.example.high.vo.OrderVO;
import java.util.List;

public interface OrderService {
    /**
     * 创建订单
     */
    OrderVO createOrder(Order order);

    /**
     * 根据ID获取订单
     */
    OrderVO getOrderById(Long id);

    /**
     * 获取用户的所有订单
     */
    List<OrderVO> getUserOrders(Long userId);

    /**
     * 支付订单
     */
    boolean payOrder(Long id);

    /**
     * 取消订单
     */
    boolean cancelOrder(Long id);

    /**
     * 确认收货
     */
    boolean confirmOrder(Long id);
} 