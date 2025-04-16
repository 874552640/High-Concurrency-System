package com.example.high.mapper;

import com.example.high.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface OrderMapper {
    /**
     * 创建订单
     */
    int insert(Order order);

    /**
     * 更新订单
     */
    int update(Order order);

    /**
     * 根据ID查询订单
     */
    Order selectById(Long id);

    /**
     * 查询用户的所有订单
     */
    List<Order> selectByUserId(Long userId);

    /**
     * 更新订单状态
     */
    int updateStatus(Long id, Integer status);
} 