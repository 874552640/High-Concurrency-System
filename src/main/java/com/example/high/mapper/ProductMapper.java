package com.example.high.mapper;

import com.example.high.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    
    int insert(Product product);

    int update(Product product);

    int deleteById(Long id);

    Product selectById(@Param("id") Long id);

    List<Product> selectByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);

    List<Product> selectByCategory(String category);

    int updateStock(@Param("id") Long id, @Param("stock") Integer stock);

    int updateStatus(@Param("id") Long id, @Param("status") Integer status);

    /**
     * 扣减库存
     * @param id 商品ID
     * @param quantity 扣减数量
     * @return 影响行数
     */
    int decreaseStock(@Param("id") Long id, @Param("quantity") Integer quantity);

    /**
     * 增加库存
     * @param id 商品ID
     * @param quantity 增加数量
     * @return 影响行数
     */
    int increaseStock(@Param("id") Long id, @Param("quantity") Integer quantity);
} 