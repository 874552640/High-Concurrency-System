package com.example.high.service.impl;

import com.example.high.entity.Product;
import com.example.high.mapper.ProductMapper;
import com.example.high.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    @Transactional
    public Product createProduct(Product product) {
        productMapper.insert(product);
        return product;
    }

    @Override
    @Transactional
    public boolean updateProduct(Product product) {
        return productMapper.update(product) > 0;
    }

    @Override
    @Transactional
    public boolean deleteProduct(Long id) {
        return productMapper.deleteById(id) > 0;
    }

    @Override
    public Product getProductById(Long id) {
        return productMapper.selectById(id);
    }

    @Override
    public List<Product> getProductsByPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return productMapper.selectByPage(offset, pageSize);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productMapper.selectByCategory(category);
    }

    @Override
    @Transactional
    public boolean decreaseStock(Long id, Integer quantity) {
        // 1. 查询商品
        Product product = productMapper.selectById(id);
        if (product == null) {
            throw new RuntimeException("商品不存在");
        }

        // 2. 检查库存
        if (product.getStock() < quantity) {
            throw new RuntimeException("库存不足");
        }

        // 3. 扣减库存
        return productMapper.decreaseStock(id, quantity) > 0;
    }

    @Override
    @Transactional
    public boolean increaseStock(Long id, Integer quantity) {
        return productMapper.increaseStock(id, quantity) > 0;
    }

    @Override
    @Transactional
    public boolean updateStatus(Long id, Integer status) {
        return productMapper.updateStatus(id, status) > 0;
    }
} 