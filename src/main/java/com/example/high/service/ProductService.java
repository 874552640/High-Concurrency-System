package com.example.high.service;

import com.example.high.entity.Product;
import java.util.List;

public interface ProductService {
    /**
     * 创建商品
     */
    Product createProduct(Product product);

    /**
     * 更新商品
     */
    boolean updateProduct(Product product);

    /**
     * 删除商品
     */
    boolean deleteProduct(Long id);

    /**
     * 获取商品详情
     */
    Product getProductById(Long id);

    /**
     * 分页查询商品
     */
    List<Product> getProductsByPage(int page, int pageSize);

    /**
     * 根据分类查询商品
     */
    List<Product> getProductsByCategory(String category);

    /**
     * 扣减库存
     * @return 是否扣减成功
     */
    boolean decreaseStock(Long id, Integer quantity);

    /**
     * 增加库存
     * @return 是否增加成功
     */
    boolean increaseStock(Long id, Integer quantity);

    /**
     * 更新商品状态
     */
    Product updateStatus(Long id, Integer status);
} 