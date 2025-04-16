package com.example.high.controller;

import com.example.high.entity.Order;
import com.example.high.service.OrderService;
import com.example.high.vo.OrderVO;
import com.example.high.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     */
    @PostMapping
    public Result<OrderVO> createOrder(@RequestBody Order order) {
        OrderVO orderVO = orderService.createOrder(order);
        return Result.success(orderVO);
    }

    /**
     * 获取订单详情
     */
    @GetMapping("/{id}")
    public Result<OrderVO> getOrder(@PathVariable Long id) {
        OrderVO orderVO = orderService.getOrderById(id);
        return Result.success(orderVO);
    }

    /**
     * 获取用户订单列表
     */
    @GetMapping("/user/{userId}")
    public Result<List<OrderVO>> getUserOrders(@PathVariable Long userId) {
        List<OrderVO> orders = orderService.getUserOrders(userId);
        return Result.success(orders);
    }

    /**
     * 支付订单
     */
    @PostMapping("/{id}/pay")
    public Result<Boolean> payOrder(@PathVariable Long id) {
        boolean success = orderService.payOrder(id);
        return Result.success(success);
    }

    /**
     * 取消订单
     */
    @PostMapping("/{id}/cancel")
    public Result<Boolean> cancelOrder(@PathVariable Long id) {
        boolean success = orderService.cancelOrder(id);
        return Result.success(success);
    }

    /**
     * 确认收货
     */
    @PostMapping("/{id}/confirm")
    public Result<Boolean> confirmOrder(@PathVariable Long id) {
        boolean success = orderService.confirmOrder(id);
        return Result.success(success);
    }
} 