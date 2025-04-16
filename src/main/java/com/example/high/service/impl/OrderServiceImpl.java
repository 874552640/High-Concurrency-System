package com.example.high.service.impl;

import com.example.high.entity.Order;
import com.example.high.entity.Product;
import com.example.high.mapper.OrderMapper;
import com.example.high.service.OrderService;
import com.example.high.service.ProductService;
import com.example.high.vo.OrderVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductService productService;

    @Override
    @Transactional
    public OrderVO createOrder(Order order) {
        // 1. 查询商品信息
        Product product = productService.getProductById(order.getProductId());
        if (product == null) {
            throw new RuntimeException("商品不存在");
        }

        // 2. 检查库存
        if (product.getStock() < order.getQuantity()) {
            throw new RuntimeException("库存不足");
        }

        // 3. 扣减库存
        if (!productService.decreaseStock(order.getProductId(), order.getQuantity())) {
            throw new RuntimeException("库存不足");
        }

        // 4. 计算订单金额
        order.setTotalAmount(product.getPrice().multiply(new java.math.BigDecimal(order.getQuantity())));
        order.setStatus(0); // 待支付状态

        // 5. 创建订单
        orderMapper.insert(order);

        // 6. 转换为VO返回
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(order, orderVO);
        orderVO.setProductName(product.getName());
        return orderVO;
    }

    @Override
    public OrderVO getOrderById(Long id) {
        Order order = orderMapper.selectById(id);
        if (order == null) {
            throw new RuntimeException("订单不存在");
        }
        return convertToVO(order);
    }

    @Override
    public List<OrderVO> getUserOrders(Long userId) {
        List<Order> orders = orderMapper.selectByUserId(userId);
        return orders.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean payOrder(Long id) {
        Order order = orderMapper.selectById(id);
        if (order == null) {
            throw new RuntimeException("订单不存在");
        }
        if (order.getStatus() != 0) {
            throw new RuntimeException("订单状态不正确");
        }
        return orderMapper.updateStatus(id, 1) > 0; // 更新为已支付状态
    }

    @Override
    @Transactional
    public boolean cancelOrder(Long id) {
        Order order = orderMapper.selectById(id);
        if (order == null) {
            throw new RuntimeException("订单不存在");
        }
        if (order.getStatus() != 0) {
            throw new RuntimeException("订单状态不正确");
        }
        
        // 恢复库存
        productService.increaseStock(order.getProductId(), order.getQuantity());
        
        return orderMapper.updateStatus(id, 4) > 0; // 更新为已取消状态
    }

    @Override
    @Transactional
    public boolean confirmOrder(Long id) {
        Order order = orderMapper.selectById(id);
        if (order == null) {
            throw new RuntimeException("订单不存在");
        }
        if (order.getStatus() != 2) {
            throw new RuntimeException("订单状态不正确");
        }
        return orderMapper.updateStatus(id, 3) > 0; // 更新为已完成状态
    }

    private OrderVO convertToVO(Order order) {
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(order, orderVO);
        
        // 查询商品信息
        Product product = productService.getProductById(order.getProductId());
        if (product != null) {
            orderVO.setProductName(product.getName());
        }
        
        return orderVO;
    }
} 